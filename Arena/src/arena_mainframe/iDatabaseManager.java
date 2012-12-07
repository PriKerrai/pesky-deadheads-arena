/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena_mainframe;

import java.sql.SQLException;

/**
 *
 * @author Johan
 */
public interface iDatabaseManager {
    
    public void createUser(String nick, String name, String email, String password,
                            String usertype, boolean active, String comment)throws SQLException;
    
    public void setActive(String nick, boolean active)throws SQLException;
    
    public String getNick(String email)throws SQLException;
    
    public String getEmail(String nick)throws SQLException;
    
    public char[] getPassword(String email)throws SQLException;
    
    public String getUserType(String nick)throws SQLException;
    
    public String getName(String nick)throws SQLException;
    
    public boolean istActive(String nick)throws SQLException;
    
    public boolean isNickUsed(String nick)throws SQLException;
    
    public boolean isEmailUsed(String email)throws SQLException;
    
}
