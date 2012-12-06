package GUI;

import javax.swing.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Josef
 * Date: 2012-12-06
 * Time: 14:40
 */
public class NewsPanel extends JEditorPane {

    public NewsPanel() {
        initialize();
    }

    public NewsPanel(String HTMLurl) {
        try {
            setPage(HTMLurl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {

    }
}
