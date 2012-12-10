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
    public static final String USER_TYPE_OPERATOR = "Operator";
    public static final String USER_TYPE_ADVERTISER = "Advertiser";
    public static final String USER_TYPE_PLAYER = "Player";
    public static final String USER_TYPE_LEAGUEOWNER = "LeagueOwner";

    String nickName;
    String name;
    String email;
    String userType;
    public User(String name, String email, String userType, String nickName){
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.nickName = nickName;
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
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
