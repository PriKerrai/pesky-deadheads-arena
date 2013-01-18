/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package League;

import Database.DatabaseManager;
import Database.iDatabaseManager;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Johan
 */
public class HandleLeague extends JFrame {

    private JPanel addPanel = new JPanel(), removePanel = new JPanel(),
            addHolder = new JPanel(), removeHolder = new JPanel(),
            addButtonPanel = new JPanel(), playerPanel = new JPanel(),
            textFieldHolder = new JPanel(), removeButtonPanel = new JPanel();
    private JButton addButton = new JButton("Add"), removeButton = new JButton("Remove");
    private JTextField minPlayers = new JTextField("Min.Players"), maxPlayers = new JTextField("Max Players"),
            leagueName = new JTextField("Leaguename", 25), leageOwnerName = new JTextField("Leagueowner nick:", 25);
    //private 
    iDatabaseManager dbm = new DatabaseManager();

    public HandleLeague() {

        setTitle("Handle game");
        setVisible(true);
        setSize(500, 550);
        setResizable(false);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Add League", null, initAddpanel());
        tabbedPane.addTab("Remove League", null, initRemovepanel());
        add(tabbedPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JPanel initAddpanel() {
        try {
            ArrayList games = dbm.getGameName();
            String[] gameListString = new String[games.size()];

            Iterator iter = games.iterator();
            for (int i = 0; i < games.size(); i++) {
                gameListString[i] = iter.next().toString();
            }

            JList gameList = new JList(gameListString);
            JScrollPane scrollList = new JScrollPane(gameList);

            addHolder.setLayout(new GridLayout(4, 1));
            addPanel.add(addHolder);
            addHolder.add(scrollList);
            addHolder.add(textFieldHolder);
            addHolder.add(playerPanel);
            textFieldHolder.setLayout(new GridLayout(0,1));
            addHolder.add(addButtonPanel);
            textFieldHolder.add(leagueName);
            textFieldHolder.add(leageOwnerName);
            
            playerPanel.add(minPlayers);
            playerPanel.add(maxPlayers);
            addButtonPanel.add(addButton);

            return addPanel;
        } catch (SQLException ex) {
            Logger.getLogger(HandleGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return addPanel;
    }

     public JPanel initRemovepanel() { //OBS! Just nu listas spelen i remove league, detta ska ändras när tabell skapats för ligor
        try {
            
            ArrayList leagues = dbm.getGameName();
            String[] leageuListString = new String[leagues.size()];

            Iterator iter = leagues.iterator();
            for (int i = 0; i < leagues.size(); i++) {
                leageuListString[i] = iter.next().toString();
            }

            JList leagueList = new JList(leageuListString);
            JScrollPane scrollList = new JScrollPane(leagueList);

            removeHolder.setLayout(new GridLayout(2,1));
            removePanel.add(removeHolder);
            removeHolder.add(scrollList);
            removeHolder.add(removeButtonPanel, BorderLayout.SOUTH);
            removeButtonPanel.add(removeButton);
            
            return removePanel;
        } catch (SQLException ex) {
            Logger.getLogger(HandleGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return removePanel;
    }

    
    public static void main(String[] args){
        HandleLeague h = new HandleLeague();
    }
}
