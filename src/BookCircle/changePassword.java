package BookCircle;
//Class for the change password functionality
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

import org.jdesktop.swingx.JXDatePicker;

public class changePassword extends JDialog implements ActionListener{

	JPanel desktop;
	 private static changePassword dialog;
	 private static String value = "";
	 public static JButton Submit,Cancel;
	public static JLabel l1,l2,l3,l6;
	public static JPasswordField password,newPassword,confirmPassword;
	public changePassword(Frame frame, Component buttonComp)
	{
		 super(frame, "BookCircle", true);
	        desktop = new JPanel(); 
	        desktop.setLayout(new GridBagLayout());
	        GridBagConstraints cs = new GridBagConstraints();
	        
	        cs.fill = GridBagConstraints.RELATIVE;
	        
	        l6 = new JLabel("Change your password");
	        cs.gridx = 2;
	        cs.gridy = 0;
	        cs.gridwidth =1;
	        desktop.add(l6, cs);
	     	 l1 = new JLabel("Old Password");
	     	cs.gridx = 1;
	        cs.gridy = 1;
	        cs.gridwidth =1;
	        cs.ipadx=2;  
	        cs.ipady=2;  
	         
	        cs.insets = new Insets(10,10,10,10);  
	       
	        desktop.add(l1, cs);
	        password=new JPasswordField(20);
	        cs.gridx = 2;
	        cs.gridy = 1;
	        cs.gridwidth = 2;
	        desktop.add(password, cs);
	        l2 = new JLabel("New Password:");
	     	cs.gridx = 1;
	        cs.gridy = 2;
	        cs.gridwidth = 1;
	        cs.ipadx=2;  
	        cs.ipady=2;  
	         
	        cs.insets = new Insets(10,10,10,10);    
	        
	        desktop.add(l2, cs);
	   	 newPassword = new JPasswordField(20);
	   cs.gridx = 2;
	   cs.gridy = 2;
	  cs.gridwidth = 2;
	   desktop.add(newPassword, cs);
	   
	     	 l3 = new JLabel("Confirm Password:");
	     	cs.gridx = 1;
	        cs.gridy = 3;
	        cs.gridwidth = 1;
	        cs.ipadx=2;  
	        cs.ipady=2;  
	         
	        cs.insets = new Insets(10,10,10,10);
	        desktop.add(l3, cs);
	    
	        confirmPassword = new JPasswordField(20);
	    
	        cs.gridx = 2;
	        cs.gridy = 3;
	        cs.gridwidth = 2;
	       desktop.add(confirmPassword, cs);
	       
	       Submit=new JButton("Submit");
	       
	       Submit.addActionListener(this);
	       Submit.setActionCommand("Submit");
	   	Cancel=new JButton("Cancel");
	   	Cancel.addActionListener(this);
	   	Cancel.setActionCommand("Cancel");
	   	 JPanel bp = new JPanel();
	   	 bp.add(Submit);
	   	 bp.add(Cancel);
	   	 
	       getContentPane().add(desktop, BorderLayout.PAGE_START);
	       getContentPane().add(bp, BorderLayout.PAGE_END);
	     
	        
		
	}
	public static String showDialog(Component frameComp,
	        Component buttonComp) {
				Frame frame = JOptionPane.getFrameForComponent(frameComp);
				dialog = new changePassword(frame,
						buttonComp);
				dialog.pack();
				dialog.setVisible(true);
				
				return value;
	}
	
	
	@Override
	//Action Listener when the submit button is clicked
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==Submit)
		{
			newUser.pass=password.getText();
			dbInit initDb = new dbInit();
			String pass=initDb.getPassword();
			if(newUser.pass.equals(pass))
			{
				if(newPassword.getText().equals(confirmPassword.getText()))
				{
					initDb.updatePassword(newPassword.getText());
					JOptionPane.showMessageDialog(dialog,
	        			    "Your password was changed successfully");
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(dialog,
	        			    "The confirm password does not match with the new password");
					
				}
				
			}
			else
			{
				JOptionPane.showMessageDialog(dialog,
        			    "Please enter the correct password");
			}
		}
		if(e.getSource()==Cancel)
		{
			dispose();
		}
		}

}
