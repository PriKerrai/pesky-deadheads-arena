package Tests;

import GUI.AdvertisementPanel;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Josef
 * Date: 2012-12-06
 * Time: 13:39
 */
public class TestAdvertisementPanel {

    JFrame testFrame;
    AdvertisementPanel advertisementPanel = new AdvertisementPanel();

    public TestAdvertisementPanel() {
        initialize();
    }

    /**
     * @param xPos The horizontal position of the administration frame
     * @param yPos The vertical position ...
     */
    public TestAdvertisementPanel(int xPos, int yPos) {
        initialize();
        testFrame.setLocation(xPos, yPos);
    }

    public void initialize() {
        testFrame = new JFrame("Advertisement Control Panel");
        testFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        testFrame.setVisible(true);
    }

    public void addAdvertisementPanel() {
        testFrame.add(advertisementPanel);
        testFrame.pack();
    }

    public JFrame getFrame() {
        return testFrame;
    }
}
