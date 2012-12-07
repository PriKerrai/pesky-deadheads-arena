package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Josef
 * Date: 2012-12-06
 * Time: 14:40
 */
public class NewsPanel extends JPanel {

    JEditorPane HTMLPanel = new JEditorPane();

    public NewsPanel() {
        initialize();
        File htmlFile = new File("News.html");
        java.net.URL fileURL = null;
        try {
            fileURL = htmlFile.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        setPage(fileURL);
    }

    public NewsPanel(String HTMLurl) {
        initialize();
        if (HTMLurl != null)
            try {
                HTMLPanel.setPage(HTMLurl);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private void initialize() {
        add(HTMLPanel);
        HTMLPanel.setEditable(false);
        HTMLPanel.setBorder(new EmptyBorder(10, 10, 10, 10));  // 10px Padding
        HTMLPanel.setBackground(new Color(238, 238, 238));
    }

    public void setPage(String HTMLurl) {
        if (HTMLurl != null)
            try {
                HTMLPanel.setPage(HTMLurl);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void setPage(URL HTMLurl) {
        if (HTMLurl != null)
            try {
                HTMLPanel.setPage(HTMLurl);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
