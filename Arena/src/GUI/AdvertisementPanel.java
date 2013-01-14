package GUI;

import Database.DatabaseManager;
import Database.iDatabaseManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
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

    iDatabaseManager dbm = new DatabaseManager();

    private JPanel mainPanel = new JPanel();

    // North area
    private JPanel balancePanel = new JPanel();
    private JLabel balanceLabel = new JLabel("Current Balance: "),
           currentBalanceLabel = new JLabel("$0");

    private JPanel depositPanel = new JPanel();
    private JTextField depositAmountTextField = new JTextField(4);
    private JButton depositButton = new JButton("Deposit");

    // Center area
    private DefaultTableModel activeAdsTableModel = new DefaultTableModel(new String[] {"Banner", "League/Tourn.", "Time Left"}, 50);
    private JTable advertisementTable = new JTable(activeAdsTableModel);
    private JScrollPane advertisementTableScrollPane = new JScrollPane(advertisementTable);

    private JLabel showOnArenaLabel = new JLabel("Also show randomly on the ARENA mainframe?");
    private JCheckBox showOnArenaCheckBox = new JCheckBox();

    private JLabel durationLabel = new JLabel("Duration (days): ");
    private JTextField durationTextField = new JTextField(2);

    // East area
    private JPanel advertisementSchemePanel = new JPanel();

    private DefaultTableModel activeTournamentsTableModel = new DefaultTableModel(new String[] {"League/Tourn.", "Description", "Free Ad Spots"}, 50);
    private JTable activeTournamentsTable = new JTable(activeTournamentsTableModel);
    private JScrollPane activeTournamentsScrollPane = new JScrollPane(activeTournamentsTable);

    //JPanel uploadPanel = new JPanel();
    private JButton openButton = new JButton("Open"),
            uploadButton = new JButton("Upload");
    private JTextField filePathTextField = new JTextField(15);
    private JFileChooser fileChooser = new JFileChooser();

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
        initializeNorthArea();
        initializeCenterArea();
        initializeEastArea();
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeMainPanel() {
        add(mainPanel);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));  // 10px Padding
        mainPanel.setLayout(new BorderLayout());
    }

    private void initializeNorthArea() {
        JPanel northPanel = new JPanel();
        mainPanel.add(northPanel, BorderLayout.NORTH);
        //westPanel.setLayout(new GridLayout(2,1));
        northPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Fill up balance"));

        balancePanel.add(balanceLabel);
        balancePanel.add(currentBalanceLabel);
        northPanel.add(balancePanel);

        depositPanel.add(depositAmountTextField);
        depositPanel.add(depositButton);
        northPanel.add(depositPanel);

        northPanel.setVisible(true);
    }

    private void initializeCenterArea() {
        JPanel centerPanel = new JPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Active Advertisements"));
        centerPanel.add(advertisementTableScrollPane, BorderLayout.CENTER);

        // Load currently active advertisements into the advertisementTable
        // --
    }

    private void initializeEastArea() {
        mainPanel.add(advertisementSchemePanel, BorderLayout.EAST);
        advertisementSchemePanel.setLayout(new BorderLayout());
        advertisementSchemePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Define Advertisement Scheme"));

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Placement"));
        advertisementSchemePanel.add(northPanel, BorderLayout.NORTH);
        northPanel.add(activeTournamentsScrollPane, BorderLayout.NORTH);

        JPanel checkBoxPanel = new JPanel();
        northPanel.add(checkBoxPanel, BorderLayout.SOUTH);
        checkBoxPanel.add(showOnArenaLabel);
        checkBoxPanel.add(showOnArenaCheckBox);

        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Duration"));
        advertisementSchemePanel.add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(durationLabel);
        centerPanel.add(durationTextField);

        JPanel southPanel = new JPanel();
        advertisementSchemePanel.add(southPanel, BorderLayout.SOUTH);
        southPanel.setLayout(new FlowLayout());
        southPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Banner File"));

        filePathTextField.setEditable(false);
        filePathTextField.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        filePathTextField.setForeground(Color.DARK_GRAY);
        openButton.addActionListener(this);
        uploadButton.addActionListener(this);

        southPanel.add(filePathTextField);
        southPanel.add(openButton);
        southPanel.add(uploadButton);
    }

    private void showOpenDialog() {
        int choice = fileChooser.showOpenDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) {
            filePathTextField.setText(fileChooser.getCurrentDirectory().toString() + "\\" +
                                      fileChooser.getSelectedFile().getName());
        }
        if (choice == JFileChooser.CANCEL_OPTION) {
            filePathTextField.setText("");
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
                System.out.println("Functionality not yet implemented.");
            }
            else if (tmp == depositButton) {

            }
        }
    }
}
