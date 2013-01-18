/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package League;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Johan
 */
public class HandleLeague extends JPanel{
    
    private JPanel holder = new JPanel();
    private JButton addButton = new JButton("Add"), removeButton = new JButton("Remove");
    //private 
    
    public HandleLeague(){
         try {
            BufferedImage m = ImageIO.read(new File("pictures/question_mark.png"));
            JLabel jl = new JLabel(new ImageIcon(m));
            add(jl);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    
}
