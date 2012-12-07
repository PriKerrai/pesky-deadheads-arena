package arena_mainframe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A class for handling the communication between the game and a given database.
 * It uses SQL to add new scores as well as retrieving the top 25 scores.
 *
 */
public class DatabaseManager implements iDatabaseManager {

    private static final String DRIVER_PATH = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DATABASE_PATH = "jdbc:sqlserver://idasql-db.hb.se:56077;"
                                                + "databaseName=dbtht1202;selectMethod=cursor";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    
    private static final String UPDATE_ACTIVE = "UPDATE ArenaUsers SET Active ='"; 
    
    private static final String INSERT_USER = "INSERT INTO ArenaUsers VALUES(";    
    
    private static final String CREATE_TABLE = "CREATE TABLE ArenaUsers(Nick VARCHAR(30),"
                                                + "Name VARCHAR(30), Email VARCHAR(30),"
                                                + "Password VARCHAR(30), CPassword VARCHAR(30),"
                                                + "UserType VARCHAR(30), Active VARCHAR(10),"
                                                + "Comment VARCHAR(50), PRIMARY KEY(Nick))";

    
    private static final String GET_ACTIVE = "SELECT * FROM ArenaUsers WHERE Nick = '";
    private static final String GET_NICK = "SELECT * FROM ArenaUsers WHERE Email = '";
    private static final String GET_EMAIL = "SELECT * FROM ArenaUsers WHERE Nick = '";
    private static final String GET_PASSWORD = "SELECT * FROM ArenaUsers WHERE Email = '";
    private static final String GET_USERTYPE = "SELECT * FROM ArenaUsers WHERE Nick = '";
    private static final String GET_NAME = "SELECT * FROM ArenaUsers WHERE Nick = '";
    
    private Connection connection;
    private Statement statement;

    /**
     * Creates a new DatabaseManager and connects to it.
     */
    public DatabaseManager() {
        this.connection = connectDB();
    }

     /**
     * Connects to the databae.
     */
    public Connection connectDB() {
        try {
            Class.forName(DRIVER_PATH);
            Connection connection = DriverManager.getConnection(DATABASE_PATH,
                    USERNAME, PASSWORD);
            return connection;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void createTable() {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_TABLE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createUser(String nick, String name, String email, String password,
                            String usertype, boolean active, String comment) throws SQLException {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(INSERT_USER + "'" + nick + "','" + name
                                                + "','" + email + "','" + password + "','"
                                                + usertype +"','" + active+"','" + comment+ "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   //Denna metod har ännu inte testats
   public void setActive(String nick, boolean active) throws SQLException{
       statement = connection.createStatement();
            statement.executeUpdate(UPDATE_ACTIVE +active+ "' WHERE Nick = '" + nick + "')");
   }

    public String getNick(String email) throws SQLException {
        String nick = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_NICK + email + "'");
        while(resultSet.next()){
            nick = resultSet.getString(1);
        }
        return nick;
    }

    public String getEmail(String nick) throws SQLException {
        String email = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_EMAIL + nick + "'");
        while(resultSet.next()){
            email = resultSet.getString(3);
        }
        return email;
    }

    public char[] getPassword(String email) throws SQLException {
        String password = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_PASSWORD + email + "'");
        while(resultSet.next()){
            password = resultSet.getString(4);//Korrekt siffra? 
        }
        char[] charPassword = password.toCharArray();
        return charPassword;
    }

    public String getUserType(String nick) throws SQLException {
        String userType = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_USERTYPE + nick + "'");
        while(resultSet.next()){
           userType = resultSet.getString(5); //Korrekt siffra? 
        }
        return userType;
    }
    
    public String getName(String nick) throws SQLException {
        String name = "";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_NAME  + nick + "'");
        
        while(resultSet.next()){
            name = resultSet.getString(2);
        }
        return name;
    }
    
     public boolean istActive(String nick) throws SQLException { //TODO:Inte färdig än
        String activeString = "";
        String TRUE = "true";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ACTIVE + nick + "'");
        while(resultSet.next()){
           activeString = resultSet.getString(6); //Korrekt siffra? 
        }
        return activeString.equals(TRUE);
    }
    
    public boolean isNickUsed(String nick) throws SQLException{
        String nick1 = "";
        boolean isEqual = false;
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ArenaUsers");
        while(resultSet.next()){
            if(nick.equals(nick1 = resultSet.getString(1))){
                return true;
            }
        }
        return false;
    }
    
    public boolean isEmailUsed(String email) throws SQLException{
        String email1 = "";
        boolean isEqual = false;
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ArenaUsers");
        while(resultSet.next()){
            if(email.equals(email1 = resultSet.getString(3))){
                return true;
            }
        }
        return false;
    }
}
