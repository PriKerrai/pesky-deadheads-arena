/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
                
        leagueOwnerPanel.add(scrollList);
        leagueOwnerPanel.add(applyLOButton);
        return leagueOwnerPanel;
    }
    
    
    public JPanel tournament(){
        JList tournamentList = new JList();
        JScrollPane scrollList = new JScrollPane(tournamentList);
                
        tournamentPanel.add(scrollList);
        tournamentPanel.add(applyTButton);
        return tournamentPanel;
    }
    
    public JPanel league(){
       
        JList leagueList = new JList();
        JScrollPane scrollList = new JScrollPane(leagueList);
                
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
                /*
                 * get username
                 * get selected type
                 * call DB
                 */
            }else if(source == applyLOButton){
                 /*
                 * get username
                 * get selected type
                 * call DB
                 */
            }else if(source == applyLButton ){
                 /*
                 * get username
                 * get selected type
                 * call DB
                 */;
            }else if(source == applyTButton ){
                 /*
                 * get username
                 * get selected type
                 * call DB
                 */
            }else{
                System.out.println("Invalid button! How did you end up here?" + ae);
            }
            
        }
        
    }
}
