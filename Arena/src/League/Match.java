package League;

import Logic.Player;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Match {
    private List<String> players;
    private List<String> spectators;
    private int noOfPlayers;
    private int matchId;

    public Match() {
    }

    public void addPlayer(int noOfPlayers, int id) {
        this.noOfPlayers = noOfPlayers;
        this.matchId = matchId;
        players = new ArrayList<String>();
        spectators = new ArrayList<String>();
    }

    public void addSpectator(Player spectator) {
        players.add(spectator.getNickName());

    }

    public Iterator<String> listSpectator() {
        return spectators.iterator();

    }
    
    public void addPlayer(Player player) {
        players.add(player.getNickName());
    }
    
    public List getPlayers() {
        return players;
    }

    
}
