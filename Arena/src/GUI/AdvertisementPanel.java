package GUI;

import Database.DatabaseManager;
import Database.iDatabaseManager;
import Logic.SingletonUser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

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
    private JButton clearBalanceButton = new JButton("Clear Balance");

    // Center area
    private DefaultTableModel activeAdsTableModel = new DefaultTableModel(new String[] {"Ad", "Banner", "League/Tourn.", "Time Left"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable activeAdsTable = new JTable(activeAdsTableModel);
    private JScrollPane advertisementTableScrollPane = new JScrollPane(activeAdsTable);

    private JLabel showOnArenaLabel = new JLabel("Also show randomly on the ARENA mainframe?");
    private JCheckBox showOnArenaCheckBox = new JCheckBox();

    private JLabel durationLabel = new JLabel("Duration (days): ");
    private JTextField durationTextField = new JTextField(2);

    // East area
    private JPanel advertisementSchemePanel = new JPanel();

    private DefaultTableModel activeTournamentsTableModel = new DefaultTableModel(new String[] {"League/Tourn.", "Description", "Free Ad Spots"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable activeTournamentsTable = new JTable(activeTournamentsTableModel);
    private JScrollPane activeTournamentsScrollPane = new JScrollPane(activeTournamentsTable);

    private JButton removeAdvertisementButton = new JButton("Remove Ad");

    //JPanel uploadPanel = new JPanel();
    JLabel chosenFileLabel = new JLabel("Chosen File:");
    private JButton openButton = new JButton("Open"),
            uploadButton = new JButton("Upload");
    private JTextField filePathTextField = new JTextField(15);
    private JFileChooser fileChooser = new JFileChooser();

    private JLabel bannerLinkLabel = new JLabel("Banner Link:");
    private JTextField bannerLinkTextField = new JTextField(27);

    private JLabel costPerClickLabel = new JLabel("Cost per click: $0");
    private JButton createAdvertisementButton = new JButton("Create Advertisement");

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
        northPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Fill up balance"));

        balancePanel.add(balanceLabel);
        balancePanel.add(currentBalanceLabel);
        northPanel.add(balancePanel);
        try {
            currentBalanceLabel.setText("$"+dbm.getAccountBalance(SingletonUser.getInstance().getNickName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        depositPanel.add(depositAmountTextField);
        depositPanel.add(depositButton);
        depositPanel.add(clearBalanceButton);
        northPanel.add(depositPanel);
        depositButton.addActionListener(this);
        clearBalanceButton.addActionListener(this);

        northPanel.setVisible(true);
    }

    private void initializeCenterArea() {
        JPanel centerPanel = new JPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Active Advertisements"));
        centerPanel.add(advertisementTableScrollPane, BorderLayout.CENTER);

        removeAdvertisementButton.addActionListener(this);
        centerPanel.add(removeAdvertisementButton, BorderLayout.SOUTH);

        updateActiveAdsList();
    }

    private void initializeEastArea() {
        mainPanel.add(advertisementSchemePanel, BorderLayout.EAST);
        advertisementSchemePanel.setLayout(new BorderLayout());
        advertisementSchemePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Define Advertisement Scheme"));

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Placement"));

        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Duration"));

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        advertisementSchemePanel.add(southPanel, BorderLayout.SOUTH);

        JPanel bannerPanel = new JPanel();
        bannerPanel.setLayout(new BorderLayout());
        bannerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Banner File"));

        updateActiveTournamentsList();
        advertisementSchemePanel.add(northPanel, BorderLayout.NORTH);
        northPanel.add(activeTournamentsScrollPane, BorderLayout.NORTH);

        JPanel checkBoxPanel = new JPanel();
        northPanel.add(checkBoxPanel, BorderLayout.SOUTH);
        checkBoxPanel.add(showOnArenaLabel);
        checkBoxPanel.add(showOnArenaCheckBox);

        advertisementSchemePanel.add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(durationLabel);
        centerPanel.add(durationTextField);

        JPanel bannerFilePanel = new JPanel();
        bannerFilePanel.setLayout(new FlowLayout());

        filePathTextField.setEditable(false);
        filePathTextField.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        filePathTextField.setForeground(Color.DARK_GRAY);
        openButton.addActionListener(this);
        uploadButton.addActionListener(this);

        bannerFilePanel.add(chosenFileLabel);
        bannerFilePanel.add(filePathTextField);
        bannerFilePanel.add(openButton);
        bannerFilePanel.add(uploadButton);

        JPanel bannerLinkPanel = new JPanel();
        bannerLinkPanel.setLayout(new FlowLayout());

        bannerLinkPanel.add(bannerLinkLabel);
        bannerLinkPanel.add(bannerLinkTextField);

        bannerPanel.add(bannerFilePanel, BorderLayout.NORTH);
        bannerPanel.add(bannerLinkPanel, BorderLayout.SOUTH);

        JPanel createAdPanel = new JPanel();
        createAdPanel.setLayout(new FlowLayout());
        createAdvertisementButton.addActionListener(this);
        createAdPanel.add(costPerClickLabel);
        createAdPanel.add(createAdvertisementButton);

        southPanel.add(bannerPanel, BorderLayout.NORTH);
        southPanel.add(createAdPanel, BorderLayout.SOUTH);
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

    private void updateActiveTournamentsList() {
        try {
            List<Integer> tournamentList = dbm.getTournamentList();
            Iterator iterator = tournamentList.iterator();
            clearJTable(activeTournamentsTableModel);
            while(iterator.hasNext()) {
                int tournamentID = (Integer)iterator.next();
                activeTournamentsTableModel.addRow(new Object[]{tournamentID, dbm.getTournamentDescription(tournamentID), dbm.getTournamentAdSpots(tournamentID)});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateBalance(int newBalance) {
        currentBalanceLabel.setText("$"+newBalance);
    }

    private void updateBalance() {
        try {
            dbm.updateAccountBalance(SingletonUser.getInstance().getNickName(),
                                     dbm.getAccountBalance(SingletonUser.getInstance().getNickName()) +
                                     Integer.parseInt(depositAmountTextField.getText()));
            updateBalance(dbm.getAccountBalance(SingletonUser.getInstance().getNickName()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void clearBalance() {
        try {
            dbm.updateAccountBalance(SingletonUser.getInstance().getNickName(), 0);
            updateBalance(0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void createAdvertisement() {
        String showOnArena = "false";
        if (showOnArenaCheckBox.isSelected())
            showOnArena = "true";
        try {
            fileChooser.getSelectedFile().getName();
            dbm.createAdvertisement((Integer)activeTournamentsTableModel.getValueAt(activeTournamentsTable.getSelectedRow(), 0),
                    SingletonUser.getInstance().getUserID(),
                    fileChooser.getSelectedFile().getName(),
                    bannerLinkTextField.getText(),
                    Integer.parseInt(durationTextField.getText()),
                    showOnArena);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateActiveAdsList();
    }

    private void removeAdvertisement() {
        try {
            dbm.removeAdvertisement((Integer)activeAdsTableModel.getValueAt(activeAdsTable.getSelectedRow(), 0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateActiveAdsList();
    }

    private void updateActiveAdsList() {
        try {
            List<Integer> adList = dbm.getAdList(SingletonUser.getInstance().getUserID());
            Iterator iterator = adList.iterator();
            clearJTable(activeAdsTableModel);
            while(iterator.hasNext()) {
                int adID = (Integer)iterator.next();
                activeAdsTableModel.addRow(new Object[]{adID, dbm.getAdBanner(adID), dbm.getTournamentDescription(dbm.getAdTournamentID(adID)), dbm.getAdDuration(adID)});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearJTable(DefaultTableModel dtm) {
        for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
            dtm.removeRow(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();
        if (ob instanceof JButton) {
            JButton tmp = (JButton)ob;
            if (tmp == openButton) {
                showOpenDialog();
            }
            else if (tmp == createAdvertisementButton) {
                createAdvertisement();
            }
            else if (tmp == removeAdvertisementButton) {
                removeAdvertisement();
            }
            else if (tmp == depositButton) {
                updateBalance();
            }
            else if (tmp == clearBalanceButton) {
                clearBalance();
            }
        }
    }
}
