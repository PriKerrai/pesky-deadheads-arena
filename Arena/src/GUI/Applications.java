/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

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
    private JButton applyButton = new JButton("Apply");
    
    
    public Applications(){
        

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Apply to tournament", null, tournament());
        tabbedPane.addTab("Apply to league", null, league());
        tabbedPane.addTab("Apply for League Owner", null, leagueOwner());
        tabbedPane.addTab("Apply for new usertype", null, userTypes());
        add(tabbedPane);

    }
    
    public JPanel userTypes(){
        add(userTypePanel);
        userTypePanel.setBorder(BorderFactory.createTitledBorder("I wanna be a..."));
        userTypePanel.add(userTypesCB);
        userTypePanel.add(reasonTextField);
        userTypePanel.add(applyButton);
        
        return userTypePanel;
    }
    
    public JPanel leagueOwner(){
        return leagueOwnerPanel;
    }
    
    
    public JPanel tournament(){
        return tournamentPanel;
    }
    
    public JPanel league(){
        return leaguePanel;
    }
}
