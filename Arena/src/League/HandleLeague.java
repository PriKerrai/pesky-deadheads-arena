/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package League;

import Database.DatabaseManager;
import Database.iDatabaseManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Johan
 */
public class HandleLeague extends JFrame{
    
    private JPanel addPanel = new JPanel(), removePanel = new JPanel(),
            addHolder = new JPanel(), removeHolder = new JPanel();
    private JButton addButton = new JButton("Add"), removeButton = new JButton("Remove");
    
    //private 
    iDatabaseManager dbm = new DatabaseManager();
    
    public HandleLeague(){

        setTitle("Handle game");
        setVisible(true);
        setSize(500, 550);
        setResizable(false);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Add League", null, addPanel);
        tabbedPane.addTab("Remove League", null, removePanel);
        add(tabbedPane);
        
         try {
            ArrayList games = dbm.getGameName();
            String[] gameListString = new String[games.size()]; 
            
            Iterator iter = games.iterator();
            for(int i = 0; i<games.size(); i++){
                gameListString[i] = iter.next().toString();
            }
            
            JList gameList = new JList(gameListString);

            JScrollPane scrollList = new JScrollPane(gameList);
            addPanel.add(addHolder);
            addHolder.add(scrollList);
            addHolder.add(addButton);
            
        } catch (SQLException ex) {
            Logger.getLogger(HandleGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args){
        HandleLeague h = new HandleLeague();
    }
}
