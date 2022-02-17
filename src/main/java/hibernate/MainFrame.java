package hibernate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {
	
    private JList list1;
    private JButton allFilmsButton;
    private JButton showButton;
    private JButton removeButton;
    private JButton addButton;
    private JButton searchByActorButton;
    private JButton searchByGenreButton;
    private JButton searchByRatingButton;
    private JButton addActorButton;
    private JButton deleteActorButton;
    private JButton addGenreButton;
    private JButton deleteGenreButton;
    
    private DefaultListModel<Film> model;
	
	public MainFrame(){
        super();
        int height = 500, width = 700;
       
        setLayout(new BorderLayout());
             
        list1 = new JList();
        allFilmsButton = new JButton("Все фильмы");
        showButton = new JButton("Показать фильм");
        removeButton = new JButton("Удалить фильм");
        addButton = new JButton("Добавить фильм");
        addActorButton = new JButton("Добавить актера");
        deleteActorButton = new JButton("Удалить актера");
        addGenreButton = new JButton("Добавить жанр");
        deleteGenreButton = new JButton("Удалить жанр");
        searchByActorButton = new JButton("Поиск по актеру");
        searchByGenreButton = new JButton("Поиск по жанру");
        searchByRatingButton = new JButton("Поиск по рейтингу");
        
        model = new DefaultListModel<Film>();
        list1.setModel(model);

        allFilmsButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Film> films = new ArrayList<>();
                model.clear();
                try {
                	System.out.println("MEOW");
                    films = (ArrayList<Film>) Factory.getInstance().getFilmMapper().getAllFilms();
                    for (int i = 0; i < films.size(); i++)
                        model.add(i, films.get(i));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                list1.setModel(model);
                list1.updateUI();
            }
        });
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list1.getSelectedIndex();
                FilmDialog f = new FilmDialog((Film) model.get(index), false);
                f.setVisible(true);
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int index = list1.getSelectedIndex();
            	Film f = model.elementAt(index);
            	try {
					Factory.getInstance().getFilmMapper().deleteFilm(f);
				} catch (SQLException e1) {}
            	model.remove(index);
            	
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int index = list1.getSelectedIndex();
            	Film film = new Film();
                FilmDialog f = new FilmDialog(film, true);
                f.setVisible(true);
            }
        });
        searchByActorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Actor> actors = (ArrayList<Actor>) Factory.getInstance().getActorMapper().getAllActors();
                    Object result = JOptionPane.showInputDialog(
                            MainFrame.this,
                            "Выберите актера: ",
                            "Выбор актера",
                            JOptionPane.QUESTION_MESSAGE,
                            null, actors.toArray(), actors.get(0));

                    ArrayList<Film> films = new ArrayList<Film>();
                    model.clear();
                    films = (ArrayList<Film>) Factory.getInstance().getFilmMapper().getFilmsByActor((Actor) result);
                    for (int i = 0; i < films.size(); i++)
                        model.add(i, films.get(i));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                list1.setModel(model);
            }
        });
        searchByGenreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                model.clear();
                try {
                    ArrayList<Genre> genres = (ArrayList<Genre>) Factory.getInstance().getGenreMapper().getAllGenres();
                    Object result = JOptionPane.showInputDialog(
                            MainFrame.this,
                            "Выберите жанр: ",
                            "Выбор жанра",
                            JOptionPane.QUESTION_MESSAGE,
                            null, genres.toArray(), genres.get(0));
                    ArrayList<Film> films;
                    films = (ArrayList<Film>) Factory.getInstance().getFilmMapper().getFilmsByGenre((Genre) result);
                    System.out.println(films.size());
                    for (int i = 0; i < films.size(); i++){
                        model.add(i, films.get(i));
                        System.out.println(films.get(i));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                list1.setModel(model);
                list1.updateUI();
            }
        });
        searchByRatingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Film> films = null;
                model.clear();
                try {
                    String result = JOptionPane.showInputDialog(
                            this,
                            "Введите минимальный рейтинг: ");
                    films = (ArrayList<Film>) Factory.getInstance().getFilmMapper().getFilmsByMinRating(Double.parseDouble(result));
                    for (int i = 0; i < films.size(); i++)
                        model.add(i, films.get(i));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                list1.setModel(model);
            }
        });
        
        addActorButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		ActorDialog f = new ActorDialog();
                f.setVisible(true);
        	}
        });
        deleteActorButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		ArrayList<Actor> actors;
				try {
					actors = (ArrayList<Actor>) Factory.getInstance().getActorMapper().getAllActors();
					Object result = JOptionPane.showInputDialog(MainFrame.this,
                        "Выберите актера: ", "Выбор актера",
                        JOptionPane.QUESTION_MESSAGE,
                        null, actors.toArray(), actors.get(0));
					Factory.getInstance().getActorMapper().deleteActor((Actor) result);
				} catch (SQLException e1) {}
        	}
        });
        addGenreButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String result = JOptionPane.showInputDialog(
                        "Введите название жанра : ");
            	if (!result.isEmpty()) {
            		Genre newGenre = new Genre();
            		newGenre.setName(result);
            		try {
						Factory.getInstance().getGenreMapper().addGenre(newGenre);
					} catch (SQLException e1) {e1.printStackTrace();}
            	}
        	}
        });
        deleteGenreButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		ArrayList<Genre> genres;
				try {
					genres = (ArrayList<Genre>) Factory.getInstance().getGenreMapper().getAllGenres();
					Object result = JOptionPane.showInputDialog(MainFrame.this,
                        "Выберите жанр: ", "Выбор жанра",
                        JOptionPane.QUESTION_MESSAGE,
                        null, genres.toArray(), genres.get(0));
					Factory.getInstance().getGenreMapper().deleteGenre((Genre) result);
				} catch (SQLException e1) {}
        	}
        });
        
        JPanel leftPanel = new JPanel();
        leftPanel.setSize(width/2, height);
        leftPanel.setLayout(new GridLayout(8, 1, 5, 12));
        leftPanel.add(allFilmsButton);
        leftPanel.add(showButton);
        leftPanel.add(removeButton);
        leftPanel.add(addButton);
        leftPanel.add(searchByActorButton);
        leftPanel.add(searchByGenreButton);
        leftPanel.add(searchByRatingButton);
        leftPanel.add(addActorButton);
        leftPanel.add(deleteActorButton);
        leftPanel.add(addGenreButton);
        leftPanel.add(deleteGenreButton);
        add(leftPanel, BorderLayout.WEST);
        
        
        JPanel rightPanel = new JPanel();
        rightPanel.setSize(width/2, height);
        rightPanel.add(list1);
        JScrollPane scroll = new JScrollPane(rightPanel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);               
        add(scroll, BorderLayout.CENTER);
        
    
        this.setMinimumSize(new Dimension(height,width));
        this.setSize(new Dimension(width, height));
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

	}
	
	
	private static final long serialVersionUID = 1L;

}
