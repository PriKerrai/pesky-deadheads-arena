/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena_mainframe;

import Tests.TestAdministrationPanel;
import Tests.TestAdvertisementPanel;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Karl
 */
public class Arena_MainFrame extends JFrame
{
    private Dimension frameSize = new Dimension(1020, 800);
    private String title = "Arena";
    
    // Do a list containing all "pages"(jpanels - games, tournament etc)?
    ArrayList<JPanel> pages = new ArrayList();
    
    Games games = new Games();
    
    JPanel mainPanel = new JPanel();
    
    public Arena_MainFrame()
    {
        init();
    }
    
    private void init(){
        
        setTitle(title);
        setMinimumSize(frameSize);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        
        setPage(games);

        TestAdministrationPanel testAdministrationPanel = new TestAdministrationPanel(this.getWidth()+2, this.getY());
        TestAdvertisementPanel testAdvertisementPanel =
                new TestAdvertisementPanel(this.getWidth()+2, testAdministrationPanel.getFrame().getHeight()+5);
    }
    
    public void setPage(JPanel pnl){
        mainPanel.removeAll();
        mainPanel.add(pnl);
        mainPanel.revalidate();
    }
}
