package GUI;

import sun.awt.HorizBagLayout;
import sun.awt.OrientableFlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Josef
 * Date: 2012-12-05
 * Time: 21:23
 */
public class AdvertisementPanel extends JFrame implements ActionListener {

    JPanel mainPanel = new JPanel(),
           centerPanel = new JPanel(),
           westPanel = new JPanel(),
           southPanel = new JPanel();

    JPanel balancePanel = new JPanel();
    JLabel balanceLabel = new JLabel("Current Balance: "),
           currentBalanceLabel = new JLabel("$0");

    JPanel depositPanel = new JPanel();
    JTextField depositAmountTextField = new JTextField(4);
    JButton depositButton = new JButton("Deposit");

    JPanel advertisementSchemePanel = new JPanel();

    JPanel uploadPanel = new JPanel();
    JButton openButton = new JButton("Open"),
            uploadButton = new JButton("Upload");
    JTextField filepathTextField = new JTextField(15);
    JFileChooser fileChooser = new JFileChooser();

    public AdvertisementPanel() {
        super("Advertisement Control Panel");
        initialize();
    }

    public AdvertisementPanel(int xPos, int yPos) {
        super("Advertisement Control Panel");
        initialize();
        setLocation(xPos, yPos);
    }

    private void initialize() {
        setLayout(new BorderLayout());
        initializeMainPanel();
        initializeWestArea();
        initializeCenterArea();
        initializeSouthArea();
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeMainPanel() {
        add(mainPanel);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));  // 10px Padding
        mainPanel.setLayout(new BorderLayout());
    }

    private void initializeWestArea()  {
        mainPanel.add(westPanel, BorderLayout.WEST);
        westPanel.setLayout(new GridLayout(2,1));
        westPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Fill up balance"));

        balancePanel.add(balanceLabel);
        balancePanel.add(currentBalanceLabel);
        westPanel.add(balancePanel);

        depositPanel.add(depositAmountTextField);
        depositPanel.add(depositButton);
        westPanel.add(depositPanel);

        westPanel.setVisible(true);
    }

    private void initializeCenterArea() {
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridLayout(4,1));
        centerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Advertisement Scheme"));
    }

    private void initializeSouthArea() {
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        southPanel.setLayout(new FlowLayout());
        southPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Banner File"));

        filepathTextField.setEditable(false);
        filepathTextField.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        filepathTextField.setForeground(Color.DARK_GRAY);
        openButton.addActionListener(this);
        uploadButton.addActionListener(this);

        southPanel.add(filepathTextField);
        southPanel.add(openButton);
        southPanel.add(uploadButton);
    }

    private void showOpenDialog() {
        int choice = fileChooser.showOpenDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) {
            filepathTextField.setText(fileChooser.getCurrentDirectory().toString() + "\\" +
                                      fileChooser.getSelectedFile().getName());
        }
        if (choice == JFileChooser.CANCEL_OPTION) {
            filepathTextField.setText("");
        }
    }

    public void updateBalance(int newBalance) {
        currentBalanceLabel.setText("$"+newBalance);
    }

    public void updateBalance(String newBalance) {
        currentBalanceLabel.setText("$"+newBalance);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();
        if (ob instanceof JButton) {
            JButton tmp = (JButton)ob;
            if (tmp == openButton) {
                showOpenDialog();
            }
            else if (tmp == uploadButton) {
                System.out.println("U no can has upload at dis tiem.");
            }
        }
    }
}
