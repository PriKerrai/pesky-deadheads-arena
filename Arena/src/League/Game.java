/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package League;

import League.Match;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin
 */
public class Game {
    
    private static int gameId;
    private int idNumber;
    private int minPlayer = 2;
    private int maxPlayer = 2;
    private String gameName;
    private String description = "";
    private List<String> players = new ArrayList<String>();
    private GameState gs;
    private GameState gameState;

    public Game(String gameName, int gameId) {
        this.gameName = gameName;
        this.gameId = gameId;
    }
    
    public String getGameName() {
        return gameName;
    }

    public int getGameId() {
        return this.gameId;
    }
    
    public int getMinPlayers() {
        return minPlayer;
    }
    public int getMaxPlayers() {
        return maxPlayer;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    public void setNumberOfPlayers(int minPlayer, int maxPlayer) {
        this.minPlayer = minPlayer;
        this.maxPlayer = maxPlayer;
    }
    
    private void setPlayers(Match match) {
        players = match.getPlayers();
    }
    
     public void startGame(Match match) {
        setPlayers(match);
        System.out.println("Game started");
        try {
            Thread.sleep(1000);
            System.out.println("Sending State to opponent");
            sendGamestate(match);
            System.out.println("Opponent revieved state");
            Thread.sleep(1000);
            System.out.println("Sending State to opponent");
            sendGamestate(match);
            System.out.println("Opponent revieved state");
            Thread.sleep(1000);
            System.out.println("Game is over");
            //Save result of game TODO
        } catch (InterruptedException e) {
        }

    }
     
     public void sendGamestate(Match match) {
        gameState = new GameState(match);
        //Store state TODO
    }



    
}
