/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena_mainframe;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Karl
 */
public class BannerPanel extends JPanel{
    
    private BufferedImage image;

    public BannerPanel(String imagePath) {
       try {
          image = ImageIO.read(new File(imagePath));
       } catch (IOException ex) {
            // image could not be read from given path
       }
       setPreferredSize(new Dimension(300, 250));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);         
    }
}
