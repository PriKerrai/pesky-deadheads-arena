/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Johan
 */
public interface iDatabaseManager {

    // ArenaUser Functions
    public void createUser(String nick, String name, String email, String password,
                           String userType, boolean isActive, String comment) throws SQLException;

    public void setActive(String nick, boolean active)throws SQLException;

    public int getUserID(String nick) throws SQLException;

    public String getNick(String email)throws SQLException;

    public String getEmail(String nick)throws SQLException;

    public char[] getPassword(String email)throws SQLException;

    public String getUserType(String nick)throws SQLException;

    public String getName(String nick)throws SQLException;

    public boolean isActive(String nick)throws SQLException;

    public boolean isNickUsed(String nick)throws SQLException;

    public boolean isEmailUsed(String email)throws SQLException;

    public void addComment(String nick, String comment) throws SQLException;

    public void makeAdvertiser(String nick) throws SQLException;

    public void makeAdmin(String nick) throws SQLException;

    public String getComment(String nick) throws SQLException;

    public int getAccountBalance(String nick) throws SQLException;

    public void updateAccountBalance(String nick, int amount) throws SQLException;


    // ADVERTISEMENT FUNCTIONS //

    /**
     * @param bannerPath Make sure that the path <u>only</u> includes the image name + file extension
     */
    public void createAdvertisement(int tournamentID, int userID, String bannerPath, int duration, String displayOnArena) throws SQLException;

    //public int getBanner(int ID) throws SQLException;

    //public int getDuration(int ID) throws SQLException;

    //public int getTournamentID(int ID) throws SQLException;

    //public boolean displaysOnArena(int ID) throws SQLException;


    // GAME FUNCTIONS //

    public void removeGame(String gameName) throws SQLException;

    public void addGame(String gameName, String devName, String desc, int minPlayer,
            int maxPlayer, String jarPath) throws SQLException;
    public ArrayList getGameName() throws SQLException;


    // TOURNAMENT FUNCTIONS //

    public void createTournament(int freePlayerSpots, int freeAdSpots, int gameID) throws SQLException;
}
