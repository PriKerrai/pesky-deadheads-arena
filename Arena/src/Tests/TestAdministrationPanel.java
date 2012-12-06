package Tests;

import GUI.AdministrationPanel;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Josef
 * Date: 2012-12-05
 * Time: 21:47
 */
public class TestAdministrationPanel {

    JFrame testFrame;
    AdministrationPanel adminPanel = new AdministrationPanel();

    public TestAdministrationPanel() {
        initialize();
    }

    /**
     * @param xPos The horizontal position of the administration frame
     * @param yPos The vertical position ...
     */
    public TestAdministrationPanel(int xPos, int yPos) {
        initialize();
        testFrame.setLocation(xPos, yPos);
    }

    public void initialize() {
        testFrame = new JFrame("Administration Panel");
        testFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        testFrame.setVisible(true);
    }

    public void addAdministrationPanel() {
        testFrame.add(adminPanel);
        testFrame.pack();
    }

    public JFrame getFrame() {
        return testFrame;
    }
}
