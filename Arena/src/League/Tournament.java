package League;

import League.Match;
import java.util.ArrayList;
import java.util.Iterator;
import Logic.Player;

public class Tournament {
    private String tournamentName = "";
    private int tournamentId;
    private Game game;
    private int freePlayerSpots;
    private int freeAdSpots;

    private ArrayList<Match> matchList = new ArrayList<Match>();
    private ArrayList players = new ArrayList();
    private Match match;

   
    public Tournament(int tournamentId, String name, Game game) {
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
