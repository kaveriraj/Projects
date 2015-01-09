package BookCircle;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;


public class SignUpPage extends JDialog implements ActionListener
{
	JPanel desktop;
	 private static SignUpPage dialog;
	 private static String value = "";
	public static JLabel l1,l2,l3,l4,l5,l6;
	public static JTextField firstName,lastName,emailAddress,gender,password;
	public static JRadioButton maleButton,femaleButton;
	public static ButtonGroup group;
	public static JButton SignUp,Cancel;
    public static String maleString = "Male";
    public static String femaleString = "Female";
	public SignUpPage(Frame frame, Component buttonComp, String cmd)
	{
        super(frame, "BookCircle", true);
	    desktop = new JPanel(); 
        desktop.setLayout(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
     cs.fill = GridBagConstraints.VERTICAL;
     
     l5 = new JLabel("Sign Up");
     cs.gridx = 2;
     cs.gridy = 0;
     cs.gridwidth =1;
    
     desktop.add(l5, cs);
     l1 = new JLabel("First Name :");
     cs.gridx = 1;
     cs.gridy = 1;
     cs.gridwidth =1;
     cs.ipadx=2;  
     cs.ipady=2;  
      
     cs.insets = new Insets(19,19,19,19);  
    
     desktop.add(l1, cs);
     firstName=new JTextField(30);
     cs.gridx = 2;
     cs.gridy = 1;
     cs.gridwidth = 2;
     desktop.add(firstName, cs);
   	 l2 = new JLabel("Last Name:");
   	cs.gridx = 1;
    cs.gridy = 2;
    cs.gridwidth = 1;
    cs.ipadx=2;  
    cs.ipady=2;  
     
    cs.insets = new Insets(15,15,15,15);    
    
    desktop.add(l2, cs);
    lastName=new JTextField(30);
    cs.gridx = 2;
    cs.gridy = 2;
    cs.gridwidth = 2;
    desktop.add(lastName, cs);
   	 l3=new JLabel("Email Address:");
   	cs.gridx = 1;
    cs.gridy = 3;
    cs.gridwidth = 1;
    cs.ipadx=2;  
    cs.ipady=2;  
     
    cs.insets = new Insets(15,15,15,15);
    desktop.add(l3, cs);
    emailAddress=new JTextField(30);
    cs.gridx = 2;
    cs.gridy = 3;
    cs.gridwidth = 2;
    desktop.add(emailAddress, cs);
    
   	 l4=new JLabel("Gender:");
 	cs.gridx = 1;
    cs.gridy = 4;
    cs.gridwidth = 1;
    cs.ipadx=2;  
    cs.ipady=2;  
     
    cs.insets = new Insets(15,15,15,15);
    desktop.add(l4, cs);
    maleButton = new JRadioButton(maleString);
 	 femaleButton = new JRadioButton(femaleString);
 group = new ButtonGroup();
   group.add(maleButton);
   cs.gridx = 2;
   cs.gridy = 4;
   cs.gridwidth = 1;
   desktop.add(maleButton, cs);
   group.add(femaleButton);
   cs.gridx = 3;
   cs.gridy = 4;
   cs.gridwidth = 1;
   desktop.add(femaleButton, cs);
   	 
   	 l5=new JLabel("Password:");
   	cs.gridx = 1;
    cs.gridy = 5;
    cs.gridwidth=1;
    cs.ipadx=2;  
    cs.ipady=2;  
    cs.insets = new Insets(15,15,15,15);
    desktop.add(l5, cs);
   	password=new JPasswordField(20);
   	cs.gridx = 2;
    cs.gridy = 5;
    cs.gridwidth = 1;
    desktop.add(password, cs);
   
    SignUp=new JButton("Sign Up");
    
    SignUp.addActionListener(this);
	 SignUp.setActionCommand("SignUp");
	Cancel=new JButton("Cancel");
	Cancel.addActionListener(this);
	Cancel.setActionCommand("Cancel");
	 JPanel bp = new JPanel();
	 bp.add(SignUp);
	 bp.add(Cancel);
	 
    getContentPane().add(desktop, BorderLayout.PAGE_START);
    getContentPane().add(bp, BorderLayout.PAGE_END);
  
    
    
	}
	
	public static String showDialog(Component frameComp,
	        Component buttonComp, String cmd) {
				Frame frame = JOptionPane.getFrameForComponent(frameComp);
				dialog = new SignUpPage(frame,
						buttonComp,cmd);
				dialog.pack();
				dialog.setVisible(true);
				
				return value;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==SignUp)
		{
			newUser.fname=firstName.getText();
			newUser.lname=lastName.getText();
			newUser.eAddress=emailAddress.getText();
			for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
	            AbstractButton button = buttons.nextElement();

	            if (button.isSelected()) {
	                 newUser.gen=button.getText();
	            }
	        }
			newUser.pass=password.getText();
			
			dbInit initDb = new dbInit();
			initDb.addUser(newUser.fname, newUser.lname, newUser.eAddress, newUser.pass, newUser.gen);
			JOptionPane.showMessageDialog(dialog,
    			    "Please use your email address as the username to sign in");
			dispose();
			
		}
		else if(e.getSource()==Cancel)
 		{
	   		dispose();
 		}
		
	}
		   
		    
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		        
		        

	
	


