package Logic;

/**
 * Created with IntelliJ IDEA.
 * User: silfer
 * Date: 2012-12-06
 * Time: 16:50
 * To change this template use File | Settings | File Templates.
 */
public class Player extends User {

    public Player(String name, String email, String nickName){
         super(name, email, USER_TYPE_PLAYER, nickName);
    }

}
