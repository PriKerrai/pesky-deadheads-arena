/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Database.DatabaseManager;
import Database.iDatabaseManager;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

/**
 *
 * @author Johan
 */
public class Applications extends JPanel{
    
    iDatabaseManager dbm = new DatabaseManager();
    
    private final String[] userTypes = {"Advertiser", "Operator"};
    private JTextField reasonTextField = new JTextField("Reason: ", 35);
    private JComboBox userTypesCB = new JComboBox(userTypes);
    
    private JPanel userTypePanel = new JPanel(), leagueOwnerPanel = new JPanel(),
            leaguePanel = new JPanel(), tournamentPanel = new JPanel();
    private JButton applyUsrButton = new JButton("Apply"), applyLOButton = new JButton("Apply"),
            applyLButton = new JButton("Apply"), applyTButton = new JButton("Apply");
    
    
    public Applications(){
        

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Apply to tournament", null, tournament());
        tabbedPane.addTab("Apply to league", null, league());
        tabbedPane.addTab("Apply for League Owner", null, leagueOwner());
        tabbedPane.addTab("Apply for new usertype", null, userTypes());
        add(tabbedPane);
        
        initListeners();

    }
    
    public JPanel userTypes(){
        add(userTypePanel);
        userTypePanel.setBorder(BorderFactory.createTitledBorder("I wanna be a..."));
        userTypePanel.add(userTypesCB);
        userTypePanel.add(reasonTextField);
        userTypePanel.add(applyUsrButton);
        
        return userTypePanel;
    }
    
    public JPanel leagueOwner(){
        
        JList leagueList = new JList();
        JScrollPane scrollList = new JScrollPane(leagueList);
        leagueOwnerPanel.setBorder(BorderFactory.createTitledBorder("Select league"));
        leagueOwnerPanel.add(scrollList);
        leagueOwnerPanel.add(applyLOButton);
        return leagueOwnerPanel;
    }
    
    
    public JPanel tournament(){
        JList tournamentList = new JList();
        JScrollPane scrollList = new JScrollPane(tournamentList);
        tournamentPanel.setBorder(BorderFactory.createTitledBorder("Select tournament"));
        tournamentPanel.add(scrollList);
        tournamentPanel.add(applyTButton);
        return tournamentPanel;
    }
    
    public JPanel league(){
       
        JList leagueList = new JList();
        JScrollPane scrollList = new JScrollPane(leagueList);
        leaguePanel.setBorder(BorderFactory.createTitledBorder("Select league"));
        leaguePanel.add(scrollList);
        leaguePanel.add(applyLButton);
        return leaguePanel;
    }

    private void initListeners() {
        MyListener myListener = new MyListener();
        applyLButton.addActionListener(myListener);
        applyLOButton.addActionListener(myListener);
        applyTButton.addActionListener(myListener);
        applyUsrButton.addActionListener(myListener);
                
    }
    
    
    public class MyListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            Object source = ae.getSource();
            
            if(source == applyUsrButton ){
                
                //Hänta ut userID
                //dbm.addApplication(userID, null, null,
                        //userTypesCB.getSelectedItem().toString(), reasonTextField.getText(), "true");
                
            }else if(source == applyLOButton){
                 
                //Hämta userID och league
                //dbm.addApplication(userID, null, här , "LeagueOwner", null, "true");
            }else if(source == applyLButton ){
                 //hämta userID och League
                //dbm.addApplication(userID, null, här, "League", null, "true");
                
            }else if(source == applyTButton ){
                 //dbm.addApplication(userID, här, null, "Tournament", null, "true");
            }else{
                System.out.println("Invalid button! How did you end up here?" + ae);
            }
            
        }
        
    }
}
