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
public class DatabaseManager {
	private static final String DRIVER_PATH = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DATABASE_PATH = "jdbc:sqlserver://idasql-db.hb.se:56077;" +
			"databaseName=dbtht1202;selectMethod=cursor";
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
        
        private static final String INSERT_USER = "INSERT INTO ArenaUsers VALUES(";
        
        private static final String CREATE_TABLE = "CREATE TABLE ArenaUsers(Nick VARCHAR(30),"
                                                    + "Name VARCHAR(30), Email VARCHAR(30),"
                                                    + "Password VARCHAR(30), CPassword VARCHAR(30),"
                                                    + "UserType VARCHAR(30), PRIMARY KEY(Nick))";
        
        private static final String GET_NICK = "SELECT Nick FROM ArenaUsers";
        private static final String GET_EMAIL = "SELECT Email FROM ArenaUsers";
        private static final String GET_PASSWORD = "SELECT Password FROM ArenaUsers";
        private static final String GET_USERTYPE = "SELECT UserType FROM ArenaUsers";
        private static final String GET_NAME = "SELECT Name FROM ArenaUsers";

	private Connection connection;
	private Statement statement;

	/**
	 * Creates a new DatabaseManager and connects to it.
	 */
	public DatabaseManager() {
		this.connection = connectDB();			
	}
       
        public void createTable(){
            try {
                statement = connection.createStatement();
                statement.executeUpdate(CREATE_TABLE);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        
        public void createUser(String nick, String name, String email, String password ,String usertype) throws SQLException {	
		try {
			statement = connection.createStatement();
			statement.executeUpdate(INSERT_USER + "'" + nick + "','" + name
                                                + "','" + email + "','" + password + "','"
                                                + usertype + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Connects to the databae.
	 */
	public Connection connectDB(){
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


        public String getNick() throws SQLException{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_NICK);
            String nick = resultSet.getString(3); //eller 0
            return nick;
        }
        
         public String getEmail() throws SQLException{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_EMAIL);
            String email = resultSet.getString(1); //eller 0
            return email;
        }
         
          public String getPassword() throws SQLException{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_PASSWORD);
            String password = resultSet.getString(1); //eller 0
            return password;
        }

           public String getUserType() throws SQLException{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_USERTYPE);
            String userType = resultSet.getString(1); //eller 0
            return userType;
        }
           
           public String getName() throws SQLException{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_NAME);
            String name = resultSet.getString(1); //eller 0
            return name;
        }
}
