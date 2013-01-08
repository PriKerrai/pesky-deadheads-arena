package GUI;

import arena_mainframe.DatabaseManager;
import arena_mainframe.iDatabaseManager;
import sun.awt.HorizBagLayout;
import sun.awt.OrientableFlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
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

    //iDatabaseManager dbm = new DatabaseManager();

    JPanel mainPanel = new JPanel();

    // North area
    JPanel balancePanel = new JPanel();
    JLabel balanceLabel = new JLabel("Current Balance: "),
           currentBalanceLabel = new JLabel("$0");

    JPanel depositPanel = new JPanel();
    JTextField depositAmountTextField = new JTextField(4);
    JButton depositButton = new JButton("Deposit");

    // Center area
    DefaultTableModel activeAdsTableModel = new DefaultTableModel(new String[] {"Banner", "Description", "Time Left"}, 50);
    JTable advertisementTable = new JTable(activeAdsTableModel);
    JScrollPane advertisementTableScrollPane = new JScrollPane(advertisementTable);

    JLabel showOnArenaLabel = new JLabel("Also show randomly on the ARENA mainframe?");
    JCheckBox showOnArenaCheckBox = new JCheckBox();

    JLabel durationLabel = new JLabel("Duration (days): ");
    JTextField durationTextField = new JTextField(2);

    // East area
    JPanel advertisementSchemePanel = new JPanel();

    DefaultTableModel activeTournamentsTableModel = new DefaultTableModel(new String[] {"League/Tourn. Name", "Description", "Free Ad Spots"}, 50);
    JTable activeTournamentsTable = new JTable(activeTournamentsTableModel);
    JScrollPane activeTournamentsScrollPane = new JScrollPane(activeTournamentsTable);

    //JPanel uploadPanel = new JPanel();
    JButton openButton = new JButton("Open"),
            uploadButton = new JButton("Upload");
    JTextField filePathTextField = new JTextField(15);
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

    private void initializeNorthArea()  {
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
        //centerPanel.setLayout(new GridLayout(1, 1));
        centerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Active Advertisements"));
        centerPanel.add(advertisementTableScrollPane);

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
                System.out.println("U no can has upload at dis tiem.");
            }
        }
    }
}
