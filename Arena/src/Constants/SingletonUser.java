package Constants;

import Logic.User;

/**
 * Created with IntelliJ IDEA.
 * User: Josef
 * Date: 2013-01-11
 * Time: 12:21
 *
 * This singleton is only used for easy access to the loged-in users' information
 * such as nickname.
 */
public class SingletonUser {

    private static SingletonUser instance = null;
    private static User userInstance = null;

    protected SingletonUser() {

    }

    public static SingletonUser getInstance() {
        if(instance == null) {
            instance = new SingletonUser();
        }
        return instance;
    }

    public static void setUser(User user) {
        if (userInstance == null) {
            userInstance = user;
        }
    }

    public static User getUserInstance() {
        return userInstance;
    }
}
