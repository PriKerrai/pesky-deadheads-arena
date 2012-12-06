package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Josef
 * Date: 2012-12-05
 * Time: 21:22
 */
public class AdministrationPanel extends JPanel {

    private JPanel banPlayerPanel = new JPanel();
    private JTextField playerNameTextField = new JTextField(20);
    private JButton banButton = new JButton("Perform Ban");

    public AdministrationPanel() {
        setBorder(new EmptyBorder(10, 10, 10, 10));  // 10px Padding
        setLayout(new GridLayout(1,1));
        initializeBanArea();
        setVisible(true);
    }

    public void initializeBanArea() {
        add(banPlayerPanel);
        banPlayerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Ban Player"));
        banPlayerPanel.add(playerNameTextField);
        banPlayerPanel.add(banButton);
        banPlayerPanel.setVisible(true);
    }

}
