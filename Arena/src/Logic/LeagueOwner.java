package logic;

import Logic.User;

public class LeagueOwner extends User {

    public LeagueOwner(String name, String email, String nickName){
         super(name, email, USER_TYPE_PLAYER, nickName);
    }

}