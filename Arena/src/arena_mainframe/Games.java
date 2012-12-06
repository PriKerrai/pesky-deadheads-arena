package arena_mainframe;

import com.sun.java.swing.plaf.windows.WindowsBorders.DashedBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.border.*;

/**
 *
 * @author Karl
 */
public class Games extends JPanel
{
    final static Dimension  BANNER_SIZE_300_250 = new Dimension(300, 250),
                            GAMES_BUTTON_SIZE_200_40 = new Dimension(200, 40);
    
    private int borderThickness = 1,
                numberOfAds = 3;
    
    JPanel  pnl_Games = new JPanel(),
            pnl_1 = new JPanel(), 
            pnl_Advertisements = new JPanel();
    JScrollPane tablePane = new JScrollPane();
    String[] columnNames_tbl_1 = {"Tournament", "Ad1", "Ad2", "Ad3"};
    Object[][] data_tbl_1 = {};
    JTable  tbl_1 = new JTable(data_tbl_1, columnNames_tbl_1);
    
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
        
        pnl_Games.setLayout(new GridLayout(16, 1, 5, 5));
        
        for (int i = 0; i < 12; i++){
            btns_games.add(new JButton("Game" + (i+1)));
            pnl_Games.add(btns_games.get(i));
            btns_games.get(i).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                // stuff happens
            }});
        }
        
        add(pnl_Games, BorderLayout.WEST);
    }
    
    private void initPanel_1(){
        
        pnl_1.setLayout(new GridLayout(1, 1));
        JScrollPane tableScrollPane1 = new JScrollPane(tbl_1);
        pnl_1.add(tableScrollPane1);
        add(pnl_1, BorderLayout.CENTER);
    }
    
    private void initPanel_Advertisements(){
        
        pnl_Advertisements.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
        
        add(pnl_Advertisements, BorderLayout.SOUTH);
    }
    
    private void initPanels_Ads(){
                
        for (int i = 0; i < numberOfAds; i++){
            pnls_ads.add(new JPanel());
            pnls_ads.get(i).setBorder(new DashedBorder(Color.DARK_GRAY, borderThickness));
            pnls_ads.get(i).setPreferredSize(BANNER_SIZE_300_250);
            pnl_Advertisements.add(pnls_ads.get(i));
        }
        
        setAd(1);
    }
    
    public void setAd(int adIndex){
        pnls_ads.get(adIndex);
        pnls_ads.get(adIndex).add(new Banner_Panel("pictures/ad_orange.png"));
        // stuff happens
    }
}
