package hibernate;

import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class FilmDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JCheckBox seenCheckBox;
    private JList list1;
    private JList list2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public FilmDialog(final Film film, final Boolean need_create) {
    	
    	contentPane = new JPanel();
    	buttonOK = new JButton("Сохранить");
        buttonCancel = new JButton("Отменить");
        textField1 = new JTextField();
        textField2 = new JTextField();
        seenCheckBox = new JCheckBox("Просмотрен");
        list1 = new JList();
        list2 = new JList();
        button1 = new JButton("Добавить актера");
        button2 = new JButton("Удалить актера");
        button3 = new JButton("Добавить жанр");
        button4 = new JButton("Удалить жанр");
        
        contentPane.setLayout(new GridBagLayout()); 
        GridBagConstraints constraints = new GridBagConstraints(); 
         
        constraints.fill = GridBagConstraints.HORIZONTAL; 
        constraints.weightx = 0.5;
        constraints.gridy = 0;
        
        constraints.gridx = 0; 
        contentPane.add(new JLabel("Название"), constraints); 
        
        constraints.gridx++;
        contentPane.add(textField1, constraints); 
        
        constraints.gridy++;
        constraints.gridx = 0; 
        contentPane.add(new JLabel("Рейтинг"), constraints);
        
        constraints.gridx++;
        contentPane.add(textField2, constraints);
        
        constraints.gridy++;
        constraints.gridx = 1;
        contentPane.add(seenCheckBox, constraints);
        
        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridheight = 2;
        contentPane.add(new JLabel("Актеры"), constraints);
        constraints.gridx++;
        contentPane.add(list1, constraints);
        
        constraints.gridheight = 1;
        constraints.gridx++;
        contentPane.add(button1, constraints);
        constraints.gridy++;
        contentPane.add(button2, constraints);
        
        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridheight = 2;
        contentPane.add(new JLabel("Жанры"), constraints);
        constraints.gridx++;
        contentPane.add(list2, constraints);
        
        constraints.gridheight = 1;
        constraints.gridx++;
        contentPane.add(button3, constraints);
        constraints.gridy++;
        contentPane.add(button4, constraints);
        
        constraints.gridy++;
        constraints.gridx = 0;
        contentPane.add(buttonOK, constraints);
        constraints.gridx++;
        contentPane.add(buttonCancel, constraints);
        
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        textField1.setText(film.getName());
        textField2.setText(Double.toString(film.getRating()));
        seenCheckBox.setSelected(film.getSeen());

        final DefaultListModel<Actor> modelActors = new DefaultListModel<>();
        final DefaultListModel<Genre> modelGenres = new DefaultListModel<>();
        Iterator<Actor> itr = film.getActors().iterator();
        int i = 0;
        while (itr.hasNext())
            modelActors.add(i++, itr.next());
        list1.setModel(modelActors);
        i = 0;
        Iterator<Genre> itr2 = film.getGenres().iterator();
        while (itr2.hasNext())
            modelGenres.add(i++, itr2.next());
        list2.setModel(modelGenres);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    film.setName(textField1.getText());
                    film.setRating(Double.parseDouble(textField2.getText()));
                    film.setSeen(seenCheckBox.isSelected());
                    HashSet<Actor> actors = new HashSet<Actor>();
                    for (int i = 0; i < modelActors.size(); i++)
                        actors.add(modelActors.get(i));
                    film.setActors(actors);
                    HashSet<Genre> genres = new HashSet<>();
                    for (int i = 0; i < modelGenres.size(); i++)
                        genres.add(modelGenres.get(i));
                    film.setGenres(genres);
                    if (need_create == true) {
                    	System.out.println("trying to add film" + film.getName());
                    	Factory.getInstance().getFilmMapper().addFilm(film);
                	} else {
                		Factory.getInstance().getFilmMapper().updateFilm(film.getID(), film);
                	}
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ArrayList<Actor> actors;
				try {
					actors = (ArrayList<Actor>) Factory.getInstance().getActorMapper().getAllActors();
				   	Object result = JOptionPane.showInputDialog(
                        FilmDialog.this,
                        "Выберите актера: ",
                        "Выбор актера",
                        JOptionPane.QUESTION_MESSAGE,
                        null, actors.toArray(), actors.get(0));
            	modelActors.addElement((Actor)result);
				} catch (SQLException e1) {}
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list1.getSelectedIndex();
                if (index != -1) {
                    modelActors.remove(index);
                    //film.getActors().remove(film.getActors().index);
                }
            }
        });
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ArrayList<Genre> genres;
				try {
					genres = (ArrayList<Genre>) Factory.getInstance().getGenreMapper().getAllGenres();
					Object result = JOptionPane.showInputDialog(
                        FilmDialog.this,
                        "Выберите жанр: ",
                        "Выбор жанра",
                        JOptionPane.QUESTION_MESSAGE,
                        null, genres.toArray(), genres.get(0));
					modelGenres.addElement((Genre)result);
				} catch (SQLException e1) {}
            }
        });
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = list2.getSelectedIndex();
                if (index != -1) {
                    modelGenres.remove(index);
                }
            }
        });
        setSize(800, 400);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
