/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import League.Match;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin
 */
public class Game {
    
    private static int gameId = 0;
    private int idNumber = 0;
    private int minPlayer = 0;
    private int maxPlayer = 0;
    private String id = "";
    private String name = "";
    //private String PLACEHOLDER = "";
    private String description = "";
    private List<String> players = new ArrayList<String>();
    private GameState gs;
    private GameState gameState;


    
    
    
    public Game(String name) {
        this.name = name;
        gameId++;
        this.id = getNewId(name);
        this.idNumber = gameId;
    }
    
    public Game(String name, String PLACEHOLDER) {
        this.name = name;
        //this.PLACEHOLDER = PLACEHOLDER;
        gameId++;
        this.id = getNewId(name);
        this.idNumber = gameId;
    }
    
    /* public String getPLACEHOLDER {
    return PLACEHOLDER
    } */
    
    public String getId() {
        return this.id;
    }
    
    private String getNewId(String name) {
        String newId = name.toLowerCase();
        newId.replace(' ', '_');
        return newId + "_" + this.idNumber;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
        this.id = getNewId(name);
     }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinPlayers() {
        return minPlayer;
    }
    public int getMaxPlayers() {
        return maxPlayer;
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
            sendGamestate(match);
            System.out.println("Gamestate sent");
            Thread.sleep(1000);
            sendGamestate(match);
            System.out.println("Gamestate sent");
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
