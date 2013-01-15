package League;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TournamentStyle {

    private Map<Integer, String[]> matches = new HashMap<Integer, String[]>();
    private int numberOfPlayers;

    TournamentStyle(int numberOfPlayers) {
      this.numberOfPlayers = numberOfPlayers;
    }
   
   
    public int noOfPlayers(){
      return numberOfPlayers;
    }

    public Map generateMatchScheme(ArrayList players) {
        int id = 1;
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                String tmp[] = { (String)players.get(i), (String)players.get(j) };
                matches.put(id, tmp);
                id++;
            }
        }
        return matches;
    }
}
