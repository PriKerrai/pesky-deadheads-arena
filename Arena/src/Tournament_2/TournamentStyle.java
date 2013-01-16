
package Tournament_2;

/**
 * Representation of a tournament style. It has a name, a description and a max
 * amount of players that may register for the tournament using that style. 
 *
 * @author Karl Sjöstrand
 *
 * To implement:
 *
 * Thoughts:
 *
 */
public class TournamentStyle 
{
    // Attributes
    private int maxPlayers;
    private String name,
                   description;

    // Constructors
    public TournamentStyle(String name, int maxPlayers)
    {
        this.name = name;
        this.maxPlayers = maxPlayers;
    }
    
    public TournamentStyle(String name, String description, int maxPlayers)
    {
        this.name = name;
        this.description = description;
        this.maxPlayers = maxPlayers;
    }

    // Methods
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public int getMaxPlayers()
    {
        return maxPlayers;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public void setMaxPlayers(int maxPlayers)
    {
        this.maxPlayers = maxPlayers;
    }
}
