package arena_mainframe;

import com.sun.java.swing.plaf.windows.WindowsBorders.DashedBorder;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.border.*;

/**
 *
 * @author Karl
 */
public class Games extends JPanel
{
    final static Dimension  BANNERSIZE_300_250 = new Dimension(300, 250),
                            GAMES_BUTTON_SIZE1 = new Dimension(200, 40);
    
    private int borderThickness = 1,
                numberOfAds = 3;
    
    JPanel  pnl_Games = new JPanel(),
            pnl_1 = new JPanel(), 
            pnl_Advertisements = new JPanel();
    JTable  tbl_1 = new JTable(25, 5);
    
    ArrayList<JButton> btns_games = new ArrayList();
    ArrayList<JPanel> pnls_ads = new ArrayList();
    
    public Games(){
        
        init();
    }
    
    private void init()
    {
        setLayout(new BorderLayout(5, 5));
        
        
        initPanel_Games();
        initPanel_1();
        initPanel_Advertisements();
        initPanels_Ads();
    }
    
    private void initPanel_Games(){
        
        //pnl_Games.setBackground(Color.LIGHT_GRAY);
        //pnl_Games.setBorder(new LineBorder(Color.DARK_GRAY, borderThickness));
        //pnl_Games.setLayout(new BoxLayout(pnl_Games, BoxLayout.Y_AXIS));
        
        pnl_Games.setLayout(new GridLayout(16, 1, 5, 5));
        
        for (int i = 0; i < 12; i++){
            btns_games.add(new JButton("Game" + (i+1)));
            pnl_Games.add(btns_games.get(i));
            //btns_games.get(i).setPreferredSize(GAMES_BUTTON_SIZE1);
        }
        
        add(pnl_Games, BorderLayout.WEST);
    }
    
    private void initPanel_1(){
        
        //pnl_1.setBackground(Color.LIGHT_GRAY);
        //pnl_1.setBorder(new LineBorder(Color.DARK_GRAY, borderThickness));
        
        
        
        add(pnl_1, BorderLayout.CENTER);
    }
    
    private void initPanel_Advertisements(){
        
        //pnl_Advertisements.setBackground(Color.LIGHT_GRAY);
        //pnl_Advertisements.setBorder(new LineBorder(Color.DARK_GRAY, borderThickness));
        
        pnl_Advertisements.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
        
        add(pnl_Advertisements, BorderLayout.SOUTH);
    }
    
    private void initPanels_Ads(){
                
        for (int i = 0; i < numberOfAds; i++){
            pnls_ads.add(new JPanel());
            pnls_ads.get(i).setBorder(new DashedBorder(Color.DARK_GRAY, borderThickness));
            pnls_ads.get(i).setPreferredSize(BANNERSIZE_300_250);
            pnl_Advertisements.add(pnls_ads.get(i));
        }
        
    }
}
