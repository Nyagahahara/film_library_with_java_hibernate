package hibernate;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class ActorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;

    public ActorDialog() {
    	
    	contentPane = new JPanel();
    	buttonOK = new JButton("Сохранить");
        buttonCancel = new JButton("Отменить");
        textField1 = new JTextField();
        textField2 = new JTextField();
        
        contentPane.setLayout(new GridBagLayout()); 
        GridBagConstraints constraints = new GridBagConstraints(); 
         
        constraints.fill = GridBagConstraints.HORIZONTAL; 
        constraints.weightx = 0.5;
        constraints.gridy = 0;
        constraints.gridx = 0;
        
        contentPane.add(new JLabel("Имя"), constraints);
        
        constraints.gridx++;
        contentPane.add(textField1, constraints);
        
        constraints.gridy++;
        constraints.gridx = 0;
        
        contentPane.add(new JLabel("Фамилия"), constraints);
        
        constraints.gridx++;
        contentPane.add(textField2, constraints); 
        
        constraints.gridy++;
        constraints.gridx = 0; 
        contentPane.add(buttonOK, constraints);
        
        constraints.gridx++;
        contentPane.add(buttonCancel, constraints);
                
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String first_name = textField1.getText();
                    String second_name = textField2.getText();
                    Boolean valid = false;
                    if (!first_name.isEmpty() && !second_name.isEmpty())
                    	valid = true;
                    if (valid) {
                    	Actor a = new Actor();
                    	a.setFirstName(first_name);
                    	a.setSecondName(second_name);
                        Factory.getInstance().getActorMapper().addActor(a);
                    } else {
                    	JOptionPane.showMessageDialog(ActorDialog.this, "Неверный ввод");
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
