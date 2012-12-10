package league;

import Game.Game;
import League.Match;
import java.util.ArrayList;
import java.util.Iterator;

public class Tournaments {
    private String tournamentName = "";
    private Game game;
    private ArrayList<Match> matchList = new ArrayList<>();
   
    public Tournaments(String name, Game game) {
        this.tournamentName = name;
        this.game = game;
    }
   
    public Iterator<Match> getMatches() {
        return matchList.listIterator();
    }

}
