/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package League;

import java.util.ArrayList;
import Game.Game;
import java.util.Iterator;
import Logic.LeagueOwner;
import Logic.Player;
import java.util.List;

/**
 *
 * @author Kerrai
 */
public class League {
    
    private int leagueId;
    private String leagueName = "";
    private Game game;
    private Player player;
    private LeagueOwner leagueOwner;
    private ArrayList playerList = new ArrayList();

    public League(Game game, String leagueName, int leagueId) {
                this.game = game;
                this.leagueName = leagueName;
                this.leagueId = leagueId;
        }

    
    public int getLeagueId() {
        return leagueId;
    }
    
    public String getLeagueName() {
        return leagueName;              
    }
    
    public Game getGame() {
        return game;
    }
    
    public LeagueOwner getLeagueOwner() {
        return leagueOwner;
    }
    public void setLeagueOwner(LeagueOwner leagueOwner) {
        this.leagueOwner = leagueOwner;
    }

    public void addPlayerToLeague(Player player) {
                playerList.add(player.getUserID());
    }
    
    public void playMatch(Match match) {
        game.startGame(match);
    }


}





