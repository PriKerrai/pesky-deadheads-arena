package Database;

import League.Tournament;
import com.sun.corba.se.impl.logging.ORBUtilSystemException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * A class for handling the communication between the ARENA and a given
 * database.
 *
 */
public class DatabaseManager implements iDatabaseManager {

    // Usefull datatype list:
    // http://publib.boulder.ibm.com/infocenter/idshelp/v111/index.jsp?topic=/com.ibm.jccids.doc/com.ibm.db2.luw.apdv.java.doc/doc/rjvjdata.htm

    private static final String DRIVER_PATH = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DATABASE_PATH = "jdbc:sqlserver://idasql-db.hb.se:56077;"
            + "databaseName=dbtht1202;selectMethod=cursor";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    // ARENAUSER
    private static final String MAKE_ADMIN = "UPDATE ArenaUsers SET UserType ='Admin'";
    private static final String MAKE_ADVERTISER = "UPDATE ArenaUsers SET UserType ='Advertiser'";
    private static final String UPDATE_ACTIVE = "UPDATE ArenaUsers SET Active ='";
    private static final String INSERT_USER = "INSERT INTO ArenaUsers VALUES(";
    private static final String ADD_COMMENT = "UPDATE ArenaUsers SET Comment ='";
    private static final String DROP_TABLE = "DROP TABLE ";

    private static final String GET_ACTIVE = "SELECT * FROM ArenaUsers WHERE Nick = '";
    private static final String GET_NICK = "SELECT * FROM ArenaUsers WHERE Email = '";
    private static final String GET_EMAIL = "SELECT * FROM ArenaUsers WHERE Nick = '";
    private static final String GET_PASSWORD = "SELECT * FROM ArenaUsers WHERE Email = '";
    private static final String GET_NAME = "SELECT * FROM ArenaUsers WHERE Nick = '";
    private static final String GET_COMMENT = "SELECT * FROM ArenaUsers WHERE Nick = '";
    private static final String GET_USER_ID = "SELECT UserID FROM ArenaUsers WHERE Nick = '";
    private static final String GET_USER_TYPE = "SELECT UserType FROM ArenaUsers WHERE Nick = '";

    private static final String GET_HIGHEST_ID = "SELECT TOP(1) UserID " +
            "FROM ArenaUsers " +
            "ORDER BY UserID DESC";


    // GAME
    private static final String INSERT_GAME = "INSERT INTO Game VALUES(";
    private static final String GET_HIGHEST_GAMEID = "SELECT TOP(1) GameID " +
            "FROM Game " +
            "ORDER BY GameID DESC";
    private static final String GET_GAMENAME = "SELECT * FROM Game ORDER BY GameID ASC";
    private static final String REMOVE_GAME = "DELETE  FROM Game WHERE Name ='";

    // ADVERTISEMENT
    private static final String INSERT_ADVERTISEMENT = "INSERT INTO Advertisement VALUES('";
    private static final String UPDATE_ADVERTISEMENT = "UPDATE Advertisement SET ";
    private static final String UPDATE_BALANCE = "UPDATE ArenaUsers SET AccountBalance = '";

    private static final String REMOVE_ADVERTISEMENT = "DELETE FROM Advertisement WHERE AdID = '";
    private static final String GET_ADVERTISEMENT = "SELECT * FROM Advertisement WHERE ";
    private static final String GET_AD_LIST = "SELECT AdID FROM Advertisement WHERE AdvertiserID = '";
    private static final String GET_AD_BANNER = "SELECT BannerPath FROM Advertisement WHERE AdID = '";
    private static final String GET_AD_DURATION = "SELECT TimeLeft FROM Advertisement WHERE AdID = '";
    private static final String GET_AD_TOURNID = "SELECT TournamentID FROM Advertisement WHERE AdID = '";
    private static final String GET_BALANCE = "SELECT AccountBalance FROM ArenaUsers WHERE Nick = '";
    private static final String GET_HIGHEST_ADID = "SELECT TOP(1) AdID FROM Advertisement ORDER BY AdID DESC";

    // TOURNAMENT
    private static final String INSERT_TOURNAMENT = "INSERT INTO Tournament VALUES('";
    private static final String GET_TOURNAMENT = "SELECT * FROM Tournament WHERE TournamentID = '";
    private static final String GET_ALL_TOURNAMENTID = "SELECT TournamentID FROM Tournament";
    private static final String REMOVE_TOURNAMENT = "DELETE FROM Tournament WHERE TournamentID = '";
    private static final String GET_HIGHEST_TOURNID = "SELECT TOP(1) TournamentID FROM Tournament ORDER BY TournamentID DESC";

    private Connection connection;
    private Statement statement;

    // ArenaUsers Table
    private static final String CREATE_TABLE_AU =
            "CREATE TABLE ArenaUsers("
                    + "UserID SMALLINT NOT NULL,"
                    + "Nick VARCHAR(30) NOT NULL,"
                    + "Name VARCHAR(30)NOT NULL,"
                    + "Email VARCHAR(30)NOT NULL,"
                    + "Password VARCHAR(30)NOT NULL,"
                    + "UserType VARCHAR(30),"
                    + "IsActive VARCHAR(5),"
                    + "Comment VARCHAR(50),"
                    + "AccountBalance SMALLINT,"
                    + "PRIMARY KEY(UserID))";

    private static final String CREATE_TABLE_ADV =
            "CREATE TABLE Advertisement("
                    + "AdID SMALLINT NOT NULL,"
                    + "TournamentID SMALLINT NOT NULL,"
                    + "AdvertiserID SMALLINT NOT NULL,"
                    + "BannerPath VARCHAR(50) NOT NULL,"
                    + "BannerLink VARCHAR(250) NOT NULL,"
                    + "TimeLeft SMALLINT NOT NULL,"
                    + "DisplayOnArena VARCHAR(5),"
                    + "PRIMARY KEY(AdID),"
                    + "FOREIGN KEY(AdvertiserID) references ArenaUsers(UserID),"
                    + "FOREIGN KEY(TournamentID) references Tournament(TournamentID))";

    private static final String CREATE_TABLE_TOURN =
            "CREATE TABLE Tournament("
                    + "TournamentID SMALLINT NOT NULL,"
                    + "Description VARCHAR(50) NOT NULL,"
                    + "FreePlayerSpots SMALLINT NOT NULL,"
                    + "FreeAdSpots SMALLINT NOT NULL,"
                    + "GameID SMALLINT NOT NULL,"
                    + "PRIMARY KEY(TournamentID),"
                    + "FOREIGN KEY(GameID) references Game(GameID))";

    private static final String CREATE_TABLE_GAME =
            "CREATE TABLE Game("
                    + "GameID SMALLINT NOT NULL,"
                    + "Name VARCHAR(30) NOT NULL,"
                    + "Description VARCHAR(100),"
                    + "MinPlayers SMALLINT NOT NULL,"
                    + "MaxPlayers SMALLINT NOT NULL,"
                    + "Developer VARCHAR(30) NOT NULL,"
                    + "JarPath VARCHAR(50) NOT NULL,"
                    + "PRIMARY KEY(GameID))";

    /**
     * Creates a new DatabaseManager and connects to it.
     */
    public DatabaseManager() {
        this.connection = connectDB();
    }

    /**
     * Connects to the database.
     */
    public Connection connectDB() {
        try {
            Class.forName(DRIVER_PATH);
            //Connection connection = DriverManager.getConnection(DATABASE_PATH,
            //       USERNAME, PASSWORD);


            //under utveckling
            String usr;
            String pass;

            usr = JOptionPane.showInputDialog("Användarnamn");
            pass = JOptionPane.showInputDialog("Lösenord");

            Connection connection = DriverManager.getConnection(DATABASE_PATH,
                    usr, pass);
            //utvecklinskoden slutar här

            return connection;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void createTable() {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_TABLE_ADV);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteTable() throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(DROP_TABLE);
    }

    public void makeAdmin(String nick) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(MAKE_ADMIN + "WHERE Nick = '" + nick + "'");
    }

    public void makeAdvertiser(String nick) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(MAKE_ADVERTISER + "WHERE Nick = '" + nick + "'");
    }


    public void createUser(String nick, String name, String email, String password,
                           String userType, boolean isActive, String comment) throws SQLException {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(INSERT_USER + "'" +getNewUserID()+ "','" + nick + "','" + name
                    + "','" + email + "','" + password + "','" + userType + "','"
                    + isActive + "','" + comment + "','" + 0 + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGame(String gameName, String devName, String desc, int minPlayer,
                        int maxPlayer, String jarPath) throws SQLException {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(INSERT_GAME +"'" +getNewGameID()+ "','" + gameName + "','" + desc
                    + "','" + minPlayer + "','" + maxPlayer + "','" + devName + "','" + jarPath + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void setActive(String nick, boolean active) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(UPDATE_ACTIVE + active + "' WHERE Nick = '" + nick + "'");
    }

    @Override
    public int getUserID(String nick) throws SQLException {
        int userID = -1;
        statement = connection.createStatement();
        ResultSet result = statement.executeQuery(GET_USER_ID + nick + "'");
        while (result.next()) {
            userID = result.getInt("UserID");
        }
        return userID;
    }

    public void addComment(String nick, String comment) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(ADD_COMMENT + comment + "' WHERE Nick = '" + nick + "'");
    }

    public String getNick(String email) throws SQLException {
        String nick = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_NICK + email + "'");
        while (resultSet.next()) {
            nick = resultSet.getString("Nick");
        }
        return nick;
    }

    public String getEmail(String nick) throws SQLException {
        String email = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_EMAIL + nick + "'");
        while (resultSet.next()) {
            email = resultSet.getString("Email");
        }
        return email;
    }

    public char[] getPassword(String email) throws SQLException {
        String password = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_PASSWORD + email + "'");
        while (resultSet.next()) {
            password = resultSet.getString("Password");
        }
        char[] charPassword = password.toCharArray();
        return charPassword;
    }

    public String getUserType(String nick) throws SQLException {
        String userType = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_USER_TYPE + nick + "'");
        while (resultSet.next()) {
               userType = resultSet.getString("UserType");
        }
        return userType;
    }

    public String getName(String nick) throws SQLException {
        String name = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_NAME + nick + "'");
        while (resultSet.next()) {
            name = resultSet.getString("Name");
        }
        return name;
    }

    public String getComment(String nick) throws SQLException {
        String comment = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_NAME + nick + "'");
        while (resultSet.next()) {
            comment = resultSet.getString("Comment");
        }
        return comment;
    }

    public boolean isActive(String nick) throws SQLException { //TODO:Inte färdig än
        String activeString = "";
        String TRUE = "true";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ACTIVE + nick + "'");
        while (resultSet.next()) {
            activeString = resultSet.getString("IsActive"); 
        }
        return activeString.equals(TRUE);
    }

    public boolean isNickUsed(String nick) throws SQLException {
        String nick1 = "";
        boolean isEqual = false;
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ArenaUsers");
        while (resultSet.next()) {
            if (nick.equals(nick1 = resultSet.getString("Nick"))) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmailUsed(String email) throws SQLException {
        String email1 = "";
        boolean isEqual = false;
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ArenaUsers");
        while (resultSet.next()) {
            if (email.equals(email1 = resultSet.getString("Email"))) {
                return true;
            }
        }
        return false;
    }

     public void removeGame(String gameName) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(REMOVE_GAME + gameName + "'"); 
    }
    
    
    public ArrayList getGameName() throws SQLException {
        ArrayList <String> nameList = new ArrayList();
        String name = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_GAMENAME);
        while (resultSet.next()) {
            name = resultSet.getString("Name");
            nameList.add(name);
        }
        return nameList;
    }
    
    private int getNewUserID() {
        int count=0;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_HIGHEST_ID);
            while(resultSet.next())
                count = resultSet.getInt("UserID");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count+1;
    }

    private int getNewGameID() {
        int count=0;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_HIGHEST_GAMEID);
            while(resultSet.next())
                count = resultSet.getInt("GameID");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count+1;
    }

    private int getNewAdID() {
        int count=0;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_HIGHEST_ADID);
            while(resultSet.next())
                count = resultSet.getInt("AdID");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count+1;
    }

    private int getNewTournID() {
        int count=0;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_HIGHEST_TOURNID);
            while(resultSet.next())
                count = resultSet.getInt("TournamentID");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count+1;
    }

    // ADVERTISEMENT FUNCTIONS //

    @Override
    public List<Integer> getAdList(int userID) {
        List<Integer> adList = new ArrayList<Integer>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_AD_LIST + userID + "'");
            while (resultSet.next()) {
                adList.add(resultSet.getInt("AdID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adList;
    }

    @Override
    public void createAdvertisement(int tournamentID, int userID, String bannerPath, String bannerLink, int duration, String displayOnArena) throws SQLException {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(INSERT_ADVERTISEMENT + getNewAdID() + "','" + tournamentID + "','" + userID + "','pictures/" +
                                                           bannerPath + "','" + bannerLink + "','" + duration + "','" + displayOnArena + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAdvertisement(int adID) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(REMOVE_ADVERTISEMENT + adID + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAdBanner(int adID) {
        String banner = "";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_AD_BANNER + adID + "'");
            while (resultSet.next()) {
                banner = resultSet.getString("BannerPath");
            }
            System.out.println(banner);
            banner = banner.substring("pictures/".length(), banner.length());
            System.out.println(banner);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banner;
    }

    @Override
    public int getAdDuration(int adID) {
        int duration = -1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_AD_DURATION + adID + "'");
            while (resultSet.next()) {
                duration = resultSet.getInt("TimeLeft");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return duration;
    }

    @Override
    public int getAdTournamentID(int adID) {
        int duration = -1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_AD_TOURNID + adID + "'");
            while (resultSet.next()) {
                duration = resultSet.getInt("TournamentID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return duration;
    }

    @Override
    public int getAccountBalance(String nick) throws SQLException {
        int balance = -1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_BALANCE + nick + "'");
            while (resultSet.next()) {
                balance = resultSet.getInt("AccountBalance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    @Override
    public void updateAccountBalance(String nick, int amount) throws SQLException {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(UPDATE_BALANCE + amount + "' WHERE Nick = '" + nick + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // GAME FUNCTIONS



    // TOURNAMENT FUNCTIONS

    @Override
    public void createTournament(String description, int freePlayerSpots, int freeAdSpots, int gameID) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(INSERT_TOURNAMENT + getNewTournID() + "','" + description + "','" + freePlayerSpots + "','" + freeAdSpots + "','" + gameID + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTournament(int tournamentID) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(REMOVE_TOURNAMENT + tournamentID + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List getTournamentList() {
        List<Integer> tournamentList = new ArrayList<Integer>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_TOURNAMENTID);
            while (resultSet.next()) {
                tournamentList.add(resultSet.getInt("TournamentID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tournamentList;
    }

    @Override
    public String getTournamentDescription(int tournamentID) {
        String description = "";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_TOURNAMENT + tournamentID + "'");
            while (resultSet.next()) {
                description = resultSet.getString("Description");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return description;
    }

    @Override
    public int getTournamentAdSpots(int tournamentID) {
        int adSpots = -1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_TOURNAMENT + tournamentID + "'");
            while (resultSet.next()) {
                adSpots = resultSet.getInt("FreeAdSpots");
            }
        } catch (SQLException e) {

        }
        return adSpots;
    }
}
