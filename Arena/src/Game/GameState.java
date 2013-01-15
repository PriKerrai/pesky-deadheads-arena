package Game;

import League.Match;
import java.util.Random;

public class GameState {


    private int state;

    public GameState(Match match) {
      createGamestate();
    }

    private void createGamestate() {
        Random rand = new Random();
        state = rand.nextInt(255);
    }
   
    public int getGamestate(){
      return state;
    }
}   
    