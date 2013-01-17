package GUI;

import Database.DatabaseManager;
import Database.iDatabaseManager;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Josef
 * Date: 2012-12-05
 * Time: 21:22
 */
public class AdministrationPanel extends JFrame {

    private final String[] users = {"Player", "Advertiser", "Operator"};
    private JComboBox userTypesJCB = new JComboBox(users);
    private JPanel userPanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel banPlayerPanel = new JPanel();
    private JPanel unbanPlayerPanel = new JPanel();
    private JTextField playerNameTextField = new JTextField("Name",20);
    private JTextField commentTextField = new JTextField("Reason",20);
    private JTextField playerNameUnbanTextField = new JTextField("Name",20);
    private JTextField userNameTextField = new JTextField("Name",20);
    private JButton banButton = new JButton("Perform Ban");
    private JButton unbanButton = new JButton("Remove ban");
    private JButton usertypeButton = new JButton("Set usertype");
    
    iDatabaseManager dbm = new DatabaseManager();
    
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
        mainPanel.add(userPanel);
        banPlayerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Ban Player"));
        banPlayerPanel.add(playerNameTextField);
        banPlayerPanel.add(commentTextField);
        banPlayerPanel.add(banButton);
        unbanPlayerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Remove ban"));
        unbanPlayerPanel.add(playerNameUnbanTextField);
        unbanPlayerPanel.add(unbanButton);
        banPlayerPanel.setVisible(true);
        userPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), "Set usertype"));
        userPanel.add(userNameTextField);
        userPanel.add(userTypesJCB);
        userPanel.add(usertypeButton);
    }

    public class MyListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            Object source = ae.getSource();
            
            if(source == banButton){
                try {
                    dbm.setActive(playerNameTextField.getText(), false);
                    dbm.addComment(playerNameTextField.getText(), commentTextField.getText());
                } catch (SQLException ex) {
                    System.out.println("DATABASE ERROR! Ban not performed");
                }
            } else if(source == unbanButton){
                try {                
                    dbm.setActive(playerNameUnbanTextField.getText(), true);
                    dbm.addComment(playerNameTextField.getText(), "");
                
                } catch (SQLException ex) {
                    System.out.println("DATABASE ERROR! Unban not performed");
                }
                
            } else if(source == usertypeButton){
                try {
                    int userType = userTypesJCB.getSelectedIndex();
                    switch(userType){
                        case 0:
                            dbm.makePlayer(userNameTextField.getText());
                            break;
                        case 1:
                            dbm.makeAdvertiser(userNameTextField.getText());
                            break;
                        case 2:
                            dbm.makeAdmin(userNameTextField.getText());
                            break;
                        default:
                            System.out.println("Invalid usertype");
                    }
                } catch (SQLException ex) {
                    System.out.println("DATABASE ERROR! Usertype not changed");
                }
                
            }else{
                System.out.println("Oh god! There is something wrong!");
            }
            
        }
        
    }
    
}
