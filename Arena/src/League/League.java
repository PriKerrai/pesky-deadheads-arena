/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package League;

import java.util.ArrayList;
import Game.Game;
import java.util.Iterator;

import Logic.LeagueOwner;

/**
 *
 * @author Kerrai
 */
public class League {
    
    private int leagueId;
    private String leagueName = "";
    private ArrayList players = new ArrayList();
    private Game game;
    private LeagueOwner leagueOwner;
    private GameType gameType;

    private ArrayList<Tournaments> tournamentList = new ArrayList<Tournaments>();
    private Object tournaments;

    public League(Game game, GameType gameType) {
                this.game = game;
                this.gameType = gameType;
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
    
    public void setLeagueName(String name) {
        this.leagueName = name;
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
    
    public GameType getGameMode() {
                return gameType;
        }


}





