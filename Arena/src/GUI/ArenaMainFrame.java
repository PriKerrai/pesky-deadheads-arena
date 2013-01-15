/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Game.HandleGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Karl
 */
public class ArenaMainFrame extends JFrame {

    private Dimension frameSize = new Dimension(1020, 800);
    private String title = "Arena";
    // Do a list containing all "pages"(jpanels - games, tournament etc)?
    ArrayList<JPanel> pages = new ArrayList();
    Games games = new Games();
    NewsPanel newsPanel = new NewsPanel();
    JPanel mainPanel = new JPanel();
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem handleItem = new JMenuItem("Handle Game");
    JMenuItem adminItem = new JMenuItem("Admin panel");
    JMenuItem advItem = new JMenuItem("Adv. controlpanel");
    JMenuItem exitItem = new JMenuItem("Exit");

    public ArenaMainFrame() {
        init();
    }

    private void init() {

        setTitle(title);
        setMinimumSize(frameSize);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);

        menuBar.add(fileMenu);
        fileMenu.add(handleItem);
        fileMenu.add(adminItem);
        fileMenu.add(advItem);
        fileMenu.add(exitItem);

        MyListener myListener = new MyListener();
        
        handleItem.addActionListener(myListener);
        adminItem.addActionListener(myListener);
        advItem.addActionListener(myListener);
        exitItem.addActionListener(myListener);

        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        add(mainPanel, BorderLayout.CENTER);

        setPage(games);
        //setPage(newsPanel);

        AdministrationPanel testAdministrationPanel = new AdministrationPanel(this.getWidth() + 2, this.getY());
        AdvertisementPanel testAdvertisementPanel =
                new AdvertisementPanel(this.getWidth() + 2, testAdministrationPanel.getHeight() + 5);
    }

    public void setPage(JPanel pnl) {
        mainPanel.removeAll();
        mainPanel.add(pnl);
        mainPanel.revalidate();
    }

    private class MyListener implements ActionListener {

        public MyListener() {
        }

        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source.equals(handleItem)) {
                HandleGame hg = new HandleGame();
            } else if (source.equals(adminItem)) {
                AdministrationPanel ap = new AdministrationPanel();
            } else if (source.equals(advItem)) {
                AdvertisementPanel ap = new AdvertisementPanel();
            } else {
                System.exit(0);
            }
        }
    }
}
