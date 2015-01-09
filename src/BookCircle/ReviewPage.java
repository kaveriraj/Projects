package BookCircle;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReviewPage extends JDialog implements ActionListener{
	 private static ReviewPage dialog;
	 JPanel desktop;
	 private static String value = "";
	 static JLabel l1,l2,l3,e1,e2,e3,l6;
	public ReviewPage(Frame frame, Component buttonComp, String cmd)
	{
		    desktop = new JPanel(); 
		    book newBook = new book();
		    dbInit initDb = new dbInit();
		   newBook=initDb.searchBookDetails(logIn.em.getText());
		 
	        desktop.setLayout(new GridBagLayout());
	        GridBagConstraints cs = new GridBagConstraints();
	        
	        cs.fill = GridBagConstraints.RELATIVE;
	        
	        l6 = new JLabel("Book Review");
	        cs.gridx = 2;
	        cs.gridy = 0;
	        cs.gridwidth =1;
	        desktop.add(l6, cs);
	     	 l1 = new JLabel("Book Name:");
	     	cs.gridx = 1;
	        cs.gridy = 1;
	        cs.gridwidth =1;
	        cs.ipadx=2;  
	        cs.ipady=2;  
	         
	        cs.insets = new Insets(19,19,19,19);  
	       
	        desktop.add(l1, cs);
	       
	        e1 = new JLabel(newBook.getName());
	        cs.gridx = 2;
	        cs.gridy = 1;
	        cs.gridwidth = 2;
	        desktop.add(e1, cs);
	        
	        l2 = new JLabel("Rating:");
	     	cs.gridx = 1;
	        cs.gridy = 2;
	        cs.gridwidth = 1;
	        cs.ipadx=2;  
	        cs.ipady=2;  
	         
	        cs.insets = new Insets(15,15,15,15);    
	        
	        desktop.add(l2, cs);
	        
	        e2 = new JLabel(newBook.getRating());
	        cs.gridx = 2;
	        cs.gridy = 2;
	        cs.gridwidth = 2;
	        desktop.add(e2, cs);
	        
	        l3 = new JLabel("Review:");
	     	cs.gridx = 1;
	        cs.gridy = 3;
	        cs.gridwidth = 1;
	        cs.ipadx=2;  
	        cs.ipady=2;  
	         
	        cs.insets = new Insets(15,15,15,15);
	        desktop.add(l3, cs);
	        
	        e3 = new JLabel(newBook.getReview());
	        cs.gridx = 2;
	        cs.gridy = 3;
	        cs.gridwidth = 2;
	       desktop.add(e3, cs);
	      JButton ok=new JButton("OK");
	        ok.addActionListener(this);
	        ok.setActionCommand("ok");
	        JPanel bp = new JPanel();
	     	 bp.add(ok);
	     	
	         getContentPane().add(desktop, BorderLayout.PAGE_START);
	         getContentPane().add(bp, BorderLayout.PAGE_END);

	       
		   
	}
	public static String showDialog(Component frameComp,
	        Component buttonComp, String cmd) {
				Frame frame = JOptionPane.getFrameForComponent(frameComp);
				dialog = new ReviewPage(frame,
						buttonComp,cmd);
				dialog.pack();
				dialog.setVisible(true);
				
				return value;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
String cmd = e.getActionCommand();
		
		if(cmd.equals("ok"))
		{
			dispose();
		}

}
}