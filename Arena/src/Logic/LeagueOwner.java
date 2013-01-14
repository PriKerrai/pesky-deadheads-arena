package Logic;

import Logic.User;
import java.util.ArrayList;
import java.util.Iterator;

public class LeagueOwner extends User {

    public LeagueOwner(int userID, String name, String email, String nickName){
         super(userID, name, email, USER_TYPE_PLAYER, nickName);
    }

}