/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import League.HandleGame;
import League.HandleLeague;

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
    //Games games = new Games();
    //NewsPanel newsPanel = new NewsPanel();
    JPanel mainPanel = new JPanel();
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem newsItem = new JMenuItem("News");
    JMenuItem applyItem = new JMenuItem("Apply for...");
    JMenuItem handleGameItem = new JMenuItem("Handle Game");
    JMenuItem handleLeagueItem = new JMenuItem("Handle League");
    JMenuItem adminItem = new JMenuItem("Admin panel");
    JMenuItem advItem = new JMenuItem("Adv. controlpanel");
    JMenuItem exitItem = new JMenuItem("Exit");
    JMenuItem statisticsItem = new JMenuItem("Statistics");
    JMenuItem logOutItem = new JMenuItem("Log out");
    JMenuItem gameItem = new JMenuItem("Games");
    
    public ArenaMainFrame() {
        setLayout(new BorderLayout());
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
        fileMenu.add(newsItem);
        fileMenu.add(gameItem);
        fileMenu.add(applyItem);
        fileMenu.add(handleGameItem);
        fileMenu.add(handleLeagueItem);
        fileMenu.add(adminItem);
        fileMenu.add(advItem);
        fileMenu.add(statisticsItem);
        fileMenu.add(logOutItem);
        fileMenu.add(exitItem);
        
        MyListener myListener = new MyListener();
        
        newsItem.addActionListener(myListener);
        gameItem.addActionListener(myListener);
        applyItem.addActionListener(myListener);
        handleGameItem.addActionListener(myListener);
        handleLeagueItem.addActionListener(myListener);
        adminItem.addActionListener(myListener);
        advItem.addActionListener(myListener);
        exitItem.addActionListener(myListener);
        logOutItem.addActionListener(myListener);
        statisticsItem.addActionListener(myListener);
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        
        setPage(new NewsPanel());
    }
    
    public void setPage(JPanel pnl) {
        mainPanel.removeAll();
        mainPanel.add(pnl, BorderLayout.CENTER);
        mainPanel.revalidate();
    }
    
    private class MyListener implements ActionListener {
        
        public MyListener() {
        }
        
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            
            if (source.equals(handleGameItem)) {
                HandleGame hg = new HandleGame();
            } else if (source.equals(adminItem)) {
                AdministrationPanel ap = new AdministrationPanel();
            } else if (source.equals(handleLeagueItem)) {
               HandleLeague h = new HandleLeague();
            } else if (source.equals(advItem)) {
                AdvertisementPanel ap = new AdvertisementPanel();
            } else if (source.equals(gameItem)) {
                setPage(new Games());
            } else if (source.equals(newsItem)) {
                setPage(new NewsPanel());
            } else if (source.equals(logOutItem)) {
                dispose();
                Login l = new Login();
                l.show();
            } else if (source.equals(applyItem)) {
                //new Applications();
                setPage(new Applications());
            } else if (source.equals(statisticsItem)) {
                //setPage();
            } else {
                System.exit(0);
            }
        }
    }
    
    public static void main(String[] args){
        ArenaMainFrame a = new ArenaMainFrame();
    }
}
