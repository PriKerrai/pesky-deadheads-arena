/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena_mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author Karl
 */
public class Login {
    
    final String GET_ACCOUNT = "U no have account? Click Here";
    final String NAME = "Name: ";
    final String PASSWORD = "Password: ";
    final String TITLE = "ARENA";
    JFrame frame = new JFrame(TITLE);
    JPanel namePanel = new JPanel();
    JPanel passPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel panelHolder = new JPanel();
    JPanel panel = new JPanel();
    JButton loginButton = new JButton("Log in");
    JButton spectateButton = new JButton("Guest");
    JLabel name = new JLabel(NAME);
    JLabel password = new JLabel(PASSWORD);
    JLabel getAccount = new JLabel(GET_ACCOUNT);
    JTextField nameField = new JTextField("", 15);
    //JTextField passField = new JTextField("",25);
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
            System.out.println("fel");
        }

        Thread music = new Thread(new Audio());
        music.start();

        frame.setLocationRelativeTo(frame);
        frame.setVisible(true);
        frame.setSize(800, 455);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.SOUTH);

        panel.add(panelHolder);
        panel.setBackground(Color.white);

        panelHolder.setLayout(new GridLayout(3, 1));
        panelHolder.setBackground(Color.white);

        panelHolder.add(namePanel);
        panelHolder.add(passPanel);
        panelHolder.add(buttonPanel);

        namePanel.setOpaque(false);
        namePanel.setLayout(new GridLayout(2, 1));
        namePanel.add(name);
        namePanel.add(nameField);

        passPanel.setOpaque(false);
        passPanel.setLayout(new GridLayout(2, 1));
        passPanel.add(password);
        passPanel.add(passField);

        buttonPanel.setOpaque(false);
        buttonPanel.add(loginButton);
        buttonPanel.add(spectateButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                //TODO: Anrop mot databas
                passField.getPassword();
                nameField.getText();
                JOptionPane.showMessageDialog(null, "Do you feel lucky, punk?");
            }
        });

        spectateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("So, you're not playing, huh?!");
            }
        });

    }
}
