/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import arena_mainframe.DatabaseManager;
import java.sql.SQLException;

/**
 *
 * @author Johan
 */
public class TestDatabase {
    
    public static void main(String[] args) throws SQLException{
        DatabaseManager dbm = new DatabaseManager();
        
        //För bästa resultat bör någon av nedanstående rader kommenteras bort
        
        //dbm.makeAdmin("Pimpslayer");
        //System.out.println(dbm.getUserType("Pimpslayer"));
        //dbm.setActive("viktor", false);
        //dbm.addComment("viktor", "För dålig för att kasta sten");
        //dbm.createTable();
        
        //dbm.deleteTable();
    }
    
}
