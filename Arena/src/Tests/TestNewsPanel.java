package Tests;

import GUI.NewsPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Josef
 * Date: 2012-12-07
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */
public class TestNewsPanel {

    JFrame newsPanelTestFrame;

    public static void main(String[] args){
        TestNewsPanel testNewsPanel = new TestNewsPanel();
    }

    private TestNewsPanel() {
        initialize();
    }

    private void initialize() {
        newsPanelTestFrame = new JFrame("News Panel");
        newsPanelTestFrame.setPreferredSize(new Dimension(800, 600));
        newsPanelTestFrame.add(new NewsPanel());
    }
}
