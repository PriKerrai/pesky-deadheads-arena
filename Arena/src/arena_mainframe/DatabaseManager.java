package arena_mainframe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private static final String DROP_TABLE = "DROP TABLE ArenaUsers";

    private static final String GET_ACTIVE = "SELECT * FROM ArenaUsers WHERE Nick = '";
    private static final String GET_NICK = "SELECT * FROM ArenaUsers WHERE Email = '";
    private static final String GET_EMAIL = "SELECT * FROM ArenaUsers WHERE Nick = '";
    private static final String GET_PASSWORD = "SELECT * FROM ArenaUsers WHERE Email = '";
    private static final String GET_USERTYPE = "SELECT * FROM ArenaUsers WHERE Nick = '";
    private static final String GET_NAME = "SELECT * FROM ArenaUsers WHERE Nick = '";
    private static final String GET_COMMENT = "SELECT * FROM ArenaUsers WHERE Nick = '";
    private static final String GET_USER_ID = "SELECT UserID FROM ArenaUsers WHERE Nick = '";

    private static final String GET_HIGHEST_ID = "SELECT TOP(1) UserID " +
			"FROM ArenaUsers " +
			"ORDER BY UserID DESC";
    
    
    // ADVERTISEMENT
    private static final String INSERT_ADVERTISEMENT = "INSERT INTO Advertisement VALUES('";
    private static final String UPDATE_ADVERTISEMENT = "UPDATE Advertisement SET ";
    private static final String UPDATE_BALANCE = "UPDATE ArenaUsers SET AccountBalance ='";

    private static final String GET_ADVERTISEMENT = "SELECT * FROM Advertisement WHERE ";
    private static final String GET_BALANCE = "SELECT AccountBalance FROM ArenaUsers WHERE Nick ='";

    private Connection connection;
    private Statement statement;

    // ArenaUsers Table
    private static final String CREATE_TABLE =
                    "CREATE TABLE ArenaUsers("
                    + "UserID SMALLINT NOT NULL,"
                    + "Nick VARCHAR(30) NOT NULL,"
                    + "Name VARCHAR(30)NOT NULL,"
                    + "Email VARCHAR(30)NOT NULL,"
                    + "Password VARCHAR(30)NOT NULL,"
                    + "isAdmin VARCHAR(5),"
                    + "isOperator VARCHAR(5),"
                    + "isLeagueowner VARCHAR(5),"
                    + "isAdvertiser VARCHAR(5),"
                    + "isActive VARCHAR(5),"
                    + "Comment VARCHAR(50),"
                    + "AccountBalance SMALLINT,"
                    + "PRIMARY KEY(UserID))";
                    
    private static final String CREATE_TABLE_ADV =
                    "CREATE TABLE Advertisement("
                    + "AdID SMALLINT NOT NULL,"
                    + "TournamentID SMALLINT NOT NULL,"
                    + "AdvertiserID SMALLINT NOT NULL,"
                    + "BannerPath VARCHAR(50) NOT NULL,"
                    + "TimeLeft SMALLINT NOT NULL,"
                    + "DisplayOnArena VARCHAR(5),"
                    + "PRIMARY KEY(AdID),"
                    + "FOREIGN KEY(TournamentID) references Tournament(TournamentID))";

    private static final String CREATE_TABLE_TOURN =
                    "CREATE TABLE Tournament("
                    + "TournamentID SMALLINT NOT NULL,"
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
                    + "Players SMALLINT NOT NULL,"
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


    // ARENAUSER FUNCTIONS //
    
    public void makeAdmin(String nick) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(MAKE_ADMIN + "WHERE Nick = '" + nick + "'");
    }
    
    public void makeAdvertiser(String nick) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(MAKE_ADVERTISER + "WHERE Nick = '" + nick + "'");
    }
    
    
    public void createUser(String nick, String name, String email, String password,
            boolean isAdmin, boolean isOperator, boolean isLeagueowner, boolean isAdvertiser,
            boolean isActive, String comment) throws SQLException {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(INSERT_USER +"'" +getNewUserID()+ "','" + nick + "','" + name
                    + "','" + email + "','" + password + "','"
                    + isAdmin + "','" + isOperator + "','" + isLeagueowner + "','" + isAdvertiser + "','"
                    + isActive + "','" + comment + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setActive(String nick, boolean active) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(UPDATE_ACTIVE + active + "' WHERE Nick = '" + nick + "'");
    }

    public void addComment(String nick, String comment) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(ADD_COMMENT + comment + "' WHERE Nick = '" + nick + "'");
    }

    @Override
    public int getUserID(String nick) throws SQLException {
        statement = connection.createStatement();
        ResultSet result = statement.executeQuery(GET_USER_ID + nick + "'");
        return result.getInt("UserID");
    }

    public String getNick(String email) throws SQLException {
        String nick = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_NICK + email + "'");
        while (resultSet.next()) {
            nick = resultSet.getString(1);
        }
        return nick;
    }

    public String getEmail(String nick) throws SQLException {
        String email = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_EMAIL + nick + "'");
        while (resultSet.next()) {
            email = resultSet.getString(3);
        }
        return email;
    }

    public char[] getPassword(String email) throws SQLException {
        String password = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_PASSWORD + email + "'");
        while (resultSet.next()) {
            password = resultSet.getString(4);//Korrekt siffra? 
        }
        char[] charPassword = password.toCharArray();
        return charPassword;
    }

    public String getUserType(String nick) throws SQLException {
        String userType = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_USERTYPE + nick + "'");
        while (resultSet.next()) {
            userType = resultSet.getString(5); //Korrekt siffra? 
        }
        return userType;
    }

    public String getName(String nick) throws SQLException {
        String name = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_NAME + nick + "'");

        while (resultSet.next()) {
            name = resultSet.getString(2);
        }
        return name;
    }

    public String getComment(String nick) throws SQLException {
        String comment = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_NAME + nick + "'");

        while (resultSet.next()) {
            comment = resultSet.getString(7);
        }
        return comment;
    }

    public boolean isActive(String nick) throws SQLException { //TODO:Inte färdig än
        String activeString = "";
        String TRUE = "true";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ACTIVE + nick + "'");
        while (resultSet.next()) {
            activeString = resultSet.getString(6); //Korrekt siffra? 
        }
        return activeString.equals(TRUE);
    }

    public boolean isNickUsed(String nick) throws SQLException {
        String nick1 = "";
        boolean isEqual = false;
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ArenaUsers");
        while (resultSet.next()) {
            if (nick.equals(nick1 = resultSet.getString(1))) {
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
            if (email.equals(email1 = resultSet.getString(3))) {
                return true;
            }
        }
        return false;
    }
    
    private int getNewUserID() {
        int count=0;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_HIGHEST_ID);
            while(resultSet.next())          
                count = resultSet.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count +1;
	}


    // ADVERTISEMENT FUNCTIONS //

    @Override
    public void createAdvertisement(int adID, int tournamentID, int userID, String bannerPath, int duration, String displayOnArena) throws SQLException {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(INSERT_ADVERTISEMENT + adID + "," + tournamentID + "," + userID + ", pictures/" + bannerPath + "," + duration + "," + displayOnArena + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getAccountBalance(String nick) throws SQLException {
        statement = connection.createStatement();
        ResultSet result = statement.executeQuery(GET_BALANCE + nick + "'");
        return result.getInt("AccountBalance");
    }

    @Override
    public void updateAccountBalance(String nick, int amount) throws SQLException {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(UPDATE_BALANCE + amount + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
