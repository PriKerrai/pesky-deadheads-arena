package logic;

import Logic.User;
import java.util.ArrayList;
import java.util.Iterator;

public class LeagueOwner extends User {

    public LeagueOwner(String name, String email, String nickName){
         super(name, email, USER_TYPE_PLAYER, nickName);
    }

}