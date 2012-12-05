/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena_mainframe;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Karl
 */
public class Arena_MainFrame extends JFrame
{
    JLabel content1 = new JLabel();
    
    public Arena_MainFrame()
    {
        setSize(1280, 720);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(content1);
    }
    
    public void changeContent(JLabel lbl){
        content1.removeAll();
        content1.add(lbl);
    }
}
