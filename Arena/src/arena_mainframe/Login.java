/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena_mainframe;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Karl
 */
public class Login extends JLabel{
         
         PaintComponent();
         BufferedImage m = ImageIO.read(new File("arena_bg.png"));
         JLabel jl = new JLabel(new ImageIcon(m));
         add(jl, BorderLayout.SOUTH);
    }
}
