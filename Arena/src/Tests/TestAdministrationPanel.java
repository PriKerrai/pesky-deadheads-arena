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

    JFrame panelTest;
    AdministrationPanel adminPanel = new AdministrationPanel();

    public TestAdministrationPanel() {
        panelTest = new JFrame("Administration Panel");
        panelTest.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panelTest.setVisible(true);
    }

    public void addAdministrationPanel() {
        panelTest.add(adminPanel);
        panelTest.pack();
    }
}
