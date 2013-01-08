/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package League;

import arena_mainframe.Tournament;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Kerrai
 */
public class ListOfTournaments {
    private static ListOfTournaments instance = null;
    private ArrayList<Tournament> tournaments = new ArrayList<>();
    
    private ListOfTournaments() {        
    }
    
    public static ListOfTournaments GetInstance() {
        if(instance == null) {
            instance = new ListOfTournaments();
        }
        return instance;
    }
    
    public Iterator<Tournament> getTournamentList() {
        return this.tournaments.listIterator();
    }
    
}
