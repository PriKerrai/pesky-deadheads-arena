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
    private JPanel unbanPlayerPanel = new JPanel();
    private JTextField playerNameTextField = new JTextField("Name",20);
    private JTextField commentTextField = new JTextField("Reason",20);
    private JTextField playerNameUnbanTextField = new JTextField("Name",20);
    private JButton banButton = new JButton("Perform Ban");
    private JButton unbanButton = new JButton("Remove ban");
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
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 200);
        setVisible(true);
    }

    private void initializeMainPanel() {
        add(mainPanel);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));  // 10px Padding
        mainPanel.setLayout(new GridLayout(1,1));
    }

    private void initializeBanArea() {
        mainPanel.add(banPlayerPanel);
        mainPanel.add(unbanPlayerPanel);
        banPlayerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Ban Player"));
        banPlayerPanel.add(playerNameTextField);
        banPlayerPanel.add(commentTextField);
        banPlayerPanel.add(banButton);
        unbanPlayerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Remove ban"));
        unbanPlayerPanel.add(playerNameUnbanTextField);
        unbanPlayerPanel.add(unbanButton);
        banPlayerPanel.setVisible(true);
    }

}
