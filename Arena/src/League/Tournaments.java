package League;

import Game.Game;
import League.Match;
import java.util.ArrayList;
import java.util.Iterator;
import Logic.Player;

public class Tournaments {
    private String tournamentName = "";
    private int tournamentId;
    private Game game;
    private ArrayList<Match> matchList = new ArrayList<Match>();
    private ArrayList players = new ArrayList();
    private Match match;

   
    public Tournaments(int tournamentId, String name, Game game) {
        this.tournamentId = tournamentId;
        this.tournamentName = name;
        this.game = game;
    }
    
    public void addPlayer(Player player) {
        players.add(player.getNickName());
    }
    
    //public Match getMatch(Player player) {
    //    //TODO
   // }
    
   
    public Iterator<Match> getMatches() {
        return matchList.listIterator();
    }
  
}
