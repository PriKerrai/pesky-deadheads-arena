/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package League;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Kerrai
 */
public class ListOfTournaments {
    private static ListOfTournaments instance = null;
    private ArrayList<Tournaments> tournaments = new ArrayList<Tournaments>();
    
    private ListOfTournaments() {        
    }
    
    public static ListOfTournaments GetInstance() {
        if(instance == null) {
            instance = new ListOfTournaments();
        }
        return instance;
    }
    
    public Iterator<Tournaments> getTournamentList() {
        return this.tournaments.listIterator();
    }
    
}
