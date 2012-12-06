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

    JPanel optionsPanel = new JPanel();

    JPanel balancePanel = new JPanel();
    JLabel balanceLabel = new JLabel("Current Balance: ");
    JLabel currentBalanceLabel = new JLabel("$0");

    JPanel depositPanel = new JPanel();
    JTextField depositAmountTextField = new JTextField(4);
    JButton depositButton = new JButton("Deposit");

    public AdvertisementPanel() {
        initialize();
    }

    public void initialize() {
        setBorder(new EmptyBorder(10, 10, 10, 10));  // 10px Padding
        setLayout(new GridLayout(1,1));
        initializeOptionsArea();
        setVisible(true);
    }

    public void initializeOptionsArea()  {
        optionsPanel.setLayout(new GridLayout(2,1));
        add(optionsPanel);

        balancePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Balance"));
        balancePanel.add(balanceLabel);
        balancePanel.add(currentBalanceLabel);
        add(balancePanel);

        depositPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Fill up balance"));
        depositPanel.add(depositAmountTextField);
        depositPanel.add(depositButton);
        add(depositPanel);

        optionsPanel.setVisible(true);
    }

    public void updateBalance(int newBalance) {
        currentBalanceLabel.setText("$"+newBalance);
    }

    public void updateBalance(String newBalance) {
        currentBalanceLabel.setText("$"+newBalance);
    }
}
