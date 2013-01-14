package Logic;

import java.security.PublicKey;

/**
 * Created with IntelliJ IDEA.
 * User: silfer
 * Date: 2012-12-06
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
public abstract class User  {
    public static final String USER_TYPE_ADMIN = "Admin",
                            USER_TYPE_OPERATOR = "Operator",
                            USER_TYPE_ADVERTISER = "Advertiser",
                            USER_TYPE_LEAGUEOWNER = "LeagueOwner",
                            USER_TYPE_PLAYER = "Player";

    int userID;
    String nickName;
    String name;
    String email;
    String userType;

    public User(int userID, String name, String email, String userType, String nickName) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.nickName = nickName;
        this.userType = userType;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
