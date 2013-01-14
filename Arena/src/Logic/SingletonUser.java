package Logic;

/**
 * Created with IntelliJ IDEA.
 * User: Josef
 * Date: 2013-01-11
 * Time: 12:21
 */
public class SingletonUser extends User {

    private static SingletonUser instance = null;

    private SingletonUser(int userID, String name, String email, String userType, String nick) {
        super(userID, name, email, userType, nick);
    }

    public static SingletonUser getInstance(int userID, String name, String email, String userType, String nick) {
        if(instance == null) {
            instance = new SingletonUser(userID, name, email, userType, nick);
        }
        return instance;
    }

    public static SingletonUser getInstance() {
        return instance;
    }
}
