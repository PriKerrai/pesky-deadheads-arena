/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.SingletonUser;
import arena_mainframe.DatabaseManager;
import arena_mainframe.iDatabaseManager;

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
            BufferedImage m = ImageIO.read(new File("pictures/arena.png"));
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
                    String nick;
                    char[] dbPassword;

                    iDatabaseManager dbm = new DatabaseManager();
                    nick = nickField.getText();
                    email = dbm.getEmail(nick);
                    dbPassword = dbm.getPassword(email);
                    if (nickField.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Please input nick");
                    } else {
                        if (Arrays.equals(passField.getPassword(), dbPassword)) {
                            if(dbm.isActive(nick)){
                                JOptionPane.showMessageDialog(null, "Replace with call to mainscreen");
                                SingletonUser.getInstance(dbm.getUserID(nick), dbm.getName(nick), email, dbm.getUserType(nick), nick);
                            }else{
                                JOptionPane.showMessageDialog(null, "Banned with reason:"
                                        + dbm.getComment(nick));
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid username or password");
                        }
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

        getAccount.addMouseListener(new MouseListener() {
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
