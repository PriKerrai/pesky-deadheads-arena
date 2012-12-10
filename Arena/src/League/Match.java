package League;

import Logic.Player;
import java.util.ArrayList;
import java.util.Iterator;

public class Match {
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Player> spectators = new ArrayList<Player>();

    public Match() {
    }

    public void addPlayer(Player player) {

    }

    public Iterator<Player> listPlayers() {
        return players.listIterator();

    }
    public void addSpectator(Player spectator) {
        players.add(spectator);

    }

    public Iterator<Player> listSpectator() {
        return spectators.iterator();

    }

}
