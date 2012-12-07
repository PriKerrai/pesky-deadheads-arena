/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena_mainframe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import arena_mainframe.iDatabaseManager;

/**
 *
 * @author Johan
 */
public class CreateAccount {
    
    final String TITLE = "Apply for an ARENA-Account";
    final String NICK = "Nick:";
    final String NAME = "Name:";
    final String EMAIL = "Email:";
    final String PASSWORD = "Password:";
    final String CONFIRM_PASSWORD = "Confirm password:";
    final String APPLY = "Apply";
    JFrame frame = new JFrame(TITLE);
    JLabel name = new JLabel(NAME), email = new JLabel(EMAIL),
            password = new JLabel(PASSWORD), confirmPassword = new JLabel(CONFIRM_PASSWORD),
            nick = new JLabel(NICK);
    JTextField nameField = new JTextField("", 20);
    JTextField emailField = new JTextField("", 20);
    JTextField nickField = new JTextField("", 20);
    JPasswordField passField = new JPasswordField("", 20);
    JPasswordField cPassField = new JPasswordField("", 20);
    JButton applyButton = new JButton(APPLY);
    JPanel fieldHolder = new JPanel();
    JPanel namePanel = new JPanel();
    JPanel passPanel = new JPanel();
    JPanel nickPanel = new JPanel();
    JPanel cPassPanel = new JPanel();
    JPanel emailPanel = new JPanel();
    JPanel panel = new JPanel();
    JPanel buttonPanel = new JPanel();

    public void show() {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        }

        try {
            BufferedImage m = ImageIO.read(new File("pictures\\arena_s.png"));
            JLabel jl = new JLabel(new ImageIcon(m));
            frame.add(jl, BorderLayout.NORTH);
        } catch (IOException ex) {
            System.out.println("fel");
        }

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);

        frame.add(panel);
        panel.add(fieldHolder);
        panel.setBackground(Color.white);

        fieldHolder.setLayout(new GridLayout(6, 1));
        fieldHolder.setBackground(Color.white);
        fieldHolder.add(nickPanel);
        fieldHolder.add(namePanel);
        fieldHolder.add(emailPanel);
        fieldHolder.add(passPanel);
        fieldHolder.add(cPassPanel);
        fieldHolder.add(buttonPanel);

        nickPanel.setLayout(new GridLayout(2, 1));
        nickPanel.setOpaque(false);
        nickPanel.add(nick);
        nickPanel.add(nickField);

        namePanel.setLayout(new GridLayout(2, 1));
        namePanel.setOpaque(false);
        namePanel.add(name);
        namePanel.add(nameField);

        emailPanel.setLayout(new GridLayout(2, 1));
        emailPanel.setOpaque(false);
        emailPanel.add(email);
        emailPanel.add(emailField);

        passPanel.setLayout(new GridLayout(2, 1));
        passPanel.setOpaque(false);
        passPanel.add(password);
        passPanel.add(passField);

        cPassPanel.setLayout(new GridLayout(2, 1));
        cPassPanel.setOpaque(false);
        cPassPanel.add(confirmPassword);
        cPassPanel.add(cPassField);

        buttonPanel.add(applyButton);
        buttonPanel.setOpaque(false);

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    DatabaseManager dbm = new DatabaseManager();
                    String passwordString = "";
                    String nickName = nickField.getText();
                    String name = nameField.getText();
                    String email = emailField.getText();
                    char[] password = passField.getPassword();
                    char[] cPassword = cPassField.getPassword();
                    

                    if (dbm.isNickUsed(nickName)) {
                        JOptionPane.showMessageDialog(null, "Nick is already in use");
                    } else {
                        if (dbm.isEmailUsed(email)) {
                            JOptionPane.showMessageDialog(null, "Email is already in use");
                        } else {
                            if(password.length < 5){
                                JOptionPane.showMessageDialog(null,"Lösenordet måste vara minst"
                                        + " 27 tecken långt, varav minst 8 versaler, 7 specialtecken"
                                        + " och 6 siffror");
                            }else{                            
                            if (Arrays.equals(password, cPassword)) {
                                for(int i = 0; i<password.length; i++){
                                    passwordString += password[i];     
                                }
                                dbm.createUser(nickName, name, email, passwordString, "User",
                                        true, "");
                            } else {
                                JOptionPane.showMessageDialog(null, "Passwords don't match, try again");
                            }
                            }
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
