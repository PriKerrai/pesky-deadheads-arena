/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena_mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Johan
 */
public class Login {
    
    final String GET_ACCOUNT = "U no have account? Click Here";
    final String NICK = "Nick: ";
    final String PASSWORD = "Password: ";
    final String TITLE = "ARENA";
    JFrame frame = new JFrame(TITLE);
    JPanel namePanel = new JPanel();
    JPanel passPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel panelHolder = new JPanel();
    JPanel accountHolder = new JPanel();
    JPanel panel = new JPanel();
    JButton loginButton = new JButton("Log in");
    JButton spectateButton = new JButton("Guest");
    JLabel name = new JLabel(NICK);
    JLabel password = new JLabel(PASSWORD);
    JLabel getAccount = new JLabel(GET_ACCOUNT);
    JTextField nickField = new JTextField("", 15);
    JPasswordField passField = new JPasswordField("", 15);

    public void show() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        }

        try {
            BufferedImage m = ImageIO.read(new File("pictures\\arena.png"));
            JLabel jl = new JLabel(new ImageIcon(m));
            frame.add(jl);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        Thread music = new Thread(new Audio());
        music.start();

        frame.setLocationRelativeTo(frame);
        frame.setVisible(true);
        frame.setSize(800, 497);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.SOUTH);

        panel.add(panelHolder);
        panel.setBackground(Color.white);

        panelHolder.setLayout(new GridLayout(4, 1));
        panelHolder.setBackground(Color.white);

        panelHolder.add(namePanel);
        panelHolder.add(passPanel);
        panelHolder.add(buttonPanel);
        
        accountHolder.setOpaque(false);
        accountHolder.setLayout(new FlowLayout(FlowLayout.RIGHT));
        accountHolder.add(getAccount);
        
        namePanel.setOpaque(false);
        namePanel.setLayout(new GridLayout(2, 1));
        namePanel.add(name);
        namePanel.add(nickField);

        passPanel.setOpaque(false);
        passPanel.setLayout(new GridLayout(2, 1));
        passPanel.add(password);
        passPanel.add(passField);

        buttonPanel.setOpaque(false);
        buttonPanel.add(loginButton);
        buttonPanel.add(spectateButton);
        buttonPanel.add(accountHolder);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String email;
                    char[] dbPassword;
                    
                    DatabaseManager dbm = new DatabaseManager();
                    
                    email = dbm.getEmail( nickField.getText());
                    dbPassword = dbm.getPassword(email);

                    if(Arrays.equals(passField.getPassword(), dbPassword)){
                        JOptionPane.showMessageDialog(null, "Replace with call to mainscreen");
                    }else{
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }
  
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        });

        spectateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("So, you're not playing, huh?!");
            }
        });
        
        getAccount.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent me) {
                CreateAccount c = new CreateAccount();
                c.show();
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }

        });

    }
}
