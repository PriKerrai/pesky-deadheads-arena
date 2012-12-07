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
public class AdministrationPanel extends JFrame {

    private JPanel mainPanel = new JPanel();
    private JPanel banPlayerPanel = new JPanel();
    private JTextField playerNameTextField = new JTextField(20);
    private JButton banButton = new JButton("Perform Ban");

    public AdministrationPanel() {
        super("Administration Panel");
        initialize();
    }

    public AdministrationPanel(int xPos, int yPos) {
        super("Administration Panel");
        initialize();
        setLocation(xPos, yPos);
    }

    private void initialize() {
        initializeMainPanel();
        initializeBanArea();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void initializeMainPanel() {
        add(mainPanel);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));  // 10px Padding
        mainPanel.setLayout(new GridLayout(1,1));
    }

    private void initializeBanArea() {
        mainPanel.add(banPlayerPanel);
        banPlayerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Ban Player"));
        banPlayerPanel.add(playerNameTextField);
        banPlayerPanel.add(banButton);
        banPlayerPanel.setVisible(true);
    }

}
