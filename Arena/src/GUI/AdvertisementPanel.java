package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Josef
 * Date: 2012-12-05
 * Time: 21:23
 */
public class AdvertisementPanel extends JPanel {

    JPanel balancePanel = new JPanel();
    JLabel balanceLabel = new JLabel("Current Balance: ");
    JLabel currentBalanceLabel = new JLabel("$0");

    public AdvertisementPanel() {
        initialize();
    }

    public AdvertisementPanel(int xPos, int yPos) {
        initialize();
    }

    public void initialize() {
        setBorder(new EmptyBorder(10, 10, 10, 10));  // 10px Padding
        setLayout(new GridLayout(1,1));
        initializeBalanceArea();
        setVisible(true);
    }

    public void initializeBalanceArea()  {
        add(balancePanel);
        balancePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Balance"));
        balancePanel.add(balanceLabel);
        balancePanel.add(currentBalanceLabel);
        balancePanel.setVisible(true);
    }
}
