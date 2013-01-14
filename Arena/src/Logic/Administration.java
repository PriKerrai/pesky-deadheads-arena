/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Database.DatabaseManager;
import Database.iDatabaseManager;

import java.sql.SQLException;

/**
 *
 * @author Johan
 */
public class Administration {
    
    iDatabaseManager dbm = new DatabaseManager();
    
    public void banPlayer(String nick, String comment) throws SQLException{
        dbm.setActive(nick, false);
        dbm.addComment(nick, comment);
    }
    
    public void unbanPlayer(String nick) throws SQLException{
        dbm.setActive(nick, true);
        dbm.addComment(nick, "");
    }
    
    public void makeAdvertiser(String nick) throws SQLException{
        dbm.makeAdvertiser(nick);
    }
    
    public void makeAdmin(String nick) throws SQLException{
        dbm.makeAdmin(nick);
    }
}
