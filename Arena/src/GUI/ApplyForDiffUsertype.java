/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Johan
 */
public class ApplyForDiffUsertype extends JPanel{
    
    private final String[] userTypes = {"Advertiser", "Operator"};
    private JTextField reasonTextField = new JTextField("Reason: ", 35);
    private JComboBox userTypesCB = new JComboBox(userTypes);
    private JPanel holder = new JPanel();
    private JButton applyButton = new JButton("Apply");
    
    
    public ApplyForDiffUsertype(){
        add(holder);
        holder.setBorder(BorderFactory.createTitledBorder("I wanna be a..."));
        holder.add(userTypesCB);
        holder.add(reasonTextField);
        holder.add(applyButton);
               
    }
}
