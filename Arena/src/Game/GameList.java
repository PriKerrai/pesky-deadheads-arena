/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Kerrai
 */
public class GameList {
    
    private static GameList instance = null;
    private ArrayList<Game> installedGames = new ArrayList<Game>();
    
    private GameList(){
        
    }
    
    public Iterator<Game> getInstalledGames() {
        Iterator<Game> games;
        games = installedGames.listIterator();
        return games;
    }
    
    public void addGame(Game newGame) {
        this.installedGames.add(newGame);
    }
    
   public void removeGame(Game game) {
       this.installedGames.remove(game);
   }
   
}
    
    
