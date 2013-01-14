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
    private ArrayList players = new ArrayList();
    private Game game;
    private Player player;
    private LeagueOwner leagueOwner;
    private List<Tournaments> tournamentList = new ArrayList<Tournaments>();
    private List<Player> playerList = new ArrayList<Player>();

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
    
    public Iterator<Tournaments> getTournaments() {
        return this.tournamentList.listIterator();
    }
    
    public void addTournament(Tournaments tournament) {
                this.tournamentList.add(tournament);
        }

    public void addPlayerToLeague(Player player) {
                this.playerList.add(player);
        
    }


}





