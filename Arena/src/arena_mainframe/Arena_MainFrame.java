/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena_mainframe;

import Tests.TestAdministrationPanel;
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
    
    JPanel pnl_1 = new JPanel();
    
    
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
        
        pnl_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        getContentPane().add(pnl_1, BorderLayout.CENTER);
        
        pnl_1.add(games);
        
        //setPage(games);
        TestAdministrationPanel testAdminPanel = new TestAdministrationPanel(this.getWidth(), this.getY());
        testAdminPanel.addAdministrationPanel();
    }
    
    public void setPage(JPanel pnl){
        pnl_1.removeAll();
        pnl_1.add(pnl);
        pnl_1.revalidate();
    }
}
