/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Database.DatabaseManager;
import Database.iDatabaseManager;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Johan
 */
public class HandleGame extends JFrame{
    
    private JPanel gNamePanel = new JPanel(), devNamePanel = new JPanel(),
            gamePanel = new JPanel(), addPanel = new JPanel(),
            removePanel = new JPanel(),
            buttonPanel = new JPanel(),
            descPanel = new JPanel(),
            minPlayersPanel = new JPanel(),
            maxPlayersPanel = new JPanel();
    
    private JLabel gNameLabel = new JLabel("Game Name:"),
            devNameLabel = new JLabel("Developer Name:"),
            gameLabel = new JLabel("Game (.jar-path):"),
            descLabel = new JLabel("Description:"),
            minPlayerLabel = new JLabel("Min. players:"),
            maxPlayerLabel = new JLabel("Max players:"); 
    
    private JTextField gNameText = new JTextField(25), devNameText = new JTextField(25),
            gameText = new JTextField(25), descText = new JTextField(25),
            minPlayerText = new JTextField(25), maxPlayerText = new JTextField(25),
            removeGameText = new JTextField("Enter Gamename",38);
    
    private JTextArea gameListArea = new JTextArea(25,38);
    private JScrollPane scrollList = new JScrollPane(gameListArea);
    
    private JButton addButton = new JButton("Add"),
            removeButton = new JButton("Remove");
    
    
    public HandleGame(){
        iDatabaseManager dbm = new DatabaseManager();
        setTitle("Handle game");
        setVisible(true);
        setSize(500, 550);
        setResizable(false);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Add Game", null, addPanel);
        tabbedPane.addTab("Remove Game", null, removePanel);
        add(tabbedPane);
        
        
        
        try {
            ArrayList games = dbm.getGameName();
            Iterator iter = games.iterator();
            
            while(iter.hasNext()){
                gameListArea.append(iter.next().toString() + '\n');
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HandleGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        gNamePanel.setLayout(new GridLayout(2,1));
        gNamePanel.add(gNameLabel);
        gNamePanel.add(gNameText);
        devNamePanel.setLayout(new GridLayout(2,1));
        devNamePanel.add(devNameLabel);
        devNamePanel.add(devNameText);
        gamePanel.setLayout(new GridLayout(2,1));
        gamePanel.add(gameLabel);
        gamePanel.add(gameText);
        descPanel.setLayout(new GridLayout(2,1));
        descPanel.add(descLabel);
        descPanel.add(descText);
        minPlayersPanel.setLayout(new GridLayout(2,1));
        minPlayersPanel.add(minPlayerLabel);
        minPlayersPanel.add(minPlayerText);
        maxPlayersPanel.setLayout(new GridLayout(2,1));
        maxPlayersPanel.add(maxPlayerLabel);
        maxPlayersPanel.add(maxPlayerText);
        buttonPanel.add(addButton);
        
        addPanel.add(gNamePanel);
        addPanel.add(devNamePanel);
        addPanel.add(gamePanel);
        addPanel.add(descPanel);
        addPanel.add(minPlayersPanel);
        addPanel.add(maxPlayersPanel);
        addPanel.add(buttonPanel);
        
        removePanel.add(scrollList);
        removePanel.add(removeGameText);
        removePanel.add(removeButton);
        
        addButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                    iDatabaseManager dbm = new DatabaseManager();
                    String gameName = gNameText.getText();
                    String devName = devNameText.getText();
                    String game = gameText.getText();
                    String desc = descText.getText();
                    int minPlayer = Integer.parseInt(minPlayerText.getText());
                    int maxPlayer = Integer.parseInt(maxPlayerText.getText());
                    
                    gNameText.setText("");
                    devNameText.setText("");
                    gameText.setText("");
                    descText.setText("");
                    minPlayerText.setText("");
                    maxPlayerText.setText("");
                    try {
                    dbm.addGame(gameName, devName, desc, minPlayer, maxPlayer, game);
                    
                    JOptionPane.showMessageDialog(null, gameName + " har lagts till!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "OBS! " + gameName + " har INTE lagts till!");
                }
            }
        });
        
        
        removeButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
               String gameName = removeGameText.getText();
               int ans = JOptionPane.showConfirmDialog(null, "Vill du ta bort " + gameName + " ?");
               
               if(ans == 0){
                    try {
                        iDatabaseManager dbm = new DatabaseManager();
                        dbm.removeGame(gameName);
                        dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(HandleGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }
            }
        });
        
    }
}
