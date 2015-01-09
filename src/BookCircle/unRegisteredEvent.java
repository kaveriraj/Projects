package BookCircle;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;
//dialog that the displays the upcoming event details
public class unRegisteredEvent extends JDialog implements ActionListener {
	JPanel desktop;
	 private static unRegisteredEvent dialog1;
	 private static String value = "";
	static JLabel l1,l2,l3,l4,l5,l6,e1,e2,e3,e4,e5;
	static JButton Join,Decline;
	static Boolean a=false;
	 static JXDatePicker picker;
	public unRegisteredEvent(Frame frame, Component buttonComp)
	{
		 super(frame, "BookCircle", true);
	        desktop = new JPanel(); 
	        desktop.setLayout(new GridBagLayout());
	        GridBagConstraints cs = new GridBagConstraints();
	        
	        cs.fill = GridBagConstraints.RELATIVE;
	        
	        l6 = new JLabel("Event Details");
	        cs.gridx = 2;
	        cs.gridy = 0;
	        cs.gridwidth =1;
	        desktop.add(l6, cs);
	     	 l1 = new JLabel("Name:");
	     	cs.gridx = 1;
	        cs.gridy = 1;
	        cs.gridwidth =1;
	        cs.ipadx=2;  
	        cs.ipady=2;  
	         
	        cs.insets = new Insets(19,19,19,19);  
	       
	        desktop.add(l1, cs);
	       
	        e1 = new JLabel(logIn.selectedEvent);
	        cs.gridx = 3;
	        cs.gridy = 1;
	        cs.gridwidth = 2;
	        desktop.add(e1, cs);
	        l2 = new JLabel("Date:");
	     	cs.gridx = 1;
	        cs.gridy = 2;
	        cs.gridwidth = 1;
	        cs.ipadx=2;  
	        cs.ipady=2;  
	         
	        cs.insets = new Insets(15,15,15,15);    
	        
	        desktop.add(l2, cs);
	        dbInit initDb = new dbInit();
			 event newEvent=new event();
			 newEvent=initDb.getEventDetails(logIn.selectedEvent);
			 
			 picker = new JXDatePicker();
		     picker.setDate(newEvent.getDate());
		     picker.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
			 cs.gridx = 2;
			   cs.gridy = 2;
			  cs.gridwidth = 2;
			   desktop.add(picker, cs);
			   
			   l3 = new JLabel("Time:");
		     	cs.gridx = 1;
		        cs.gridy = 3;
		        cs.gridwidth = 1;
		        cs.ipadx=2;  
		        cs.ipady=2;  
		         
		        cs.insets = new Insets(15,15,15,15);
		        desktop.add(l3, cs);
		        e3 = new JLabel(newEvent.getTime());
		        cs.gridx = 3;
		        cs.gridy = 3;
		        cs.gridwidth = 2;
		       desktop.add(e3, cs);
		       
		       l4 = new JLabel("Location:");
		     	cs.gridx = 1;
		        cs.gridy = 4;
		        cs.gridwidth = 1;
		        cs.ipadx=2;  
		        cs.ipady=2;  
		         
		        cs.insets = new Insets(15,15,15,15);
		        desktop.add(l4, cs);
		        e4= new JLabel(newEvent.getLoc());    
		        cs.gridx = 3;
		        cs.gridy = 4;
		        cs.gridwidth = 1;
		        desktop.add(e4, cs);
		        l5 = new JLabel("Description:");
		     	cs.gridx = 1;
		        cs.gridy = 5;
		        cs.gridwidth=1;
		        cs.ipadx=2;  
		        cs.ipady=2;  
		        cs.insets = new Insets(15,15,15,15);
		        desktop.add(l5, cs);
		        e5= new JLabel(newEvent.getDes()); 
		        cs.gridx = 3;
		        cs.gridy = 5;
		        cs.gridwidth = 1;
		        desktop.add(e5, cs);
		        
		        Join=new JButton("Join");
		        Join.addActionListener(this);
		        Join.setActionCommand("Join");
		     	Decline=new JButton("Decline");
		     	Decline.addActionListener(this);
		     	Decline.setActionCommand("Decline");
		     	 JPanel bp = new JPanel();
		     	 bp.add(Join);
		     	 bp.add(Decline);
		     	 
		         getContentPane().add(desktop, BorderLayout.PAGE_START);
		         getContentPane().add(bp, BorderLayout.PAGE_END);
	}
	public static String showDialog(Component frameComp,
	        Component buttonComp) {
				Frame frame = JOptionPane.getFrameForComponent(frameComp);
				dialog1 = new unRegisteredEvent(frame,
						buttonComp);
				dialog1.pack();
				dialog1.setVisible(true);
				
				return value;
	}

	@Override
	//When the user clicks join the event remains in the list,when the user clicks decline the event is removed from the list.
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==Join)
		{
			if (logIn.events.getSelectedIndex() != -1)
			{
				logIn.selected = true;
				
				dispose();
				logIn.selected = false;
		  
			}
		}
		if(e.getSource()==Decline)
		{
			if (logIn.events.getSelectedIndex() != -1)
			{
				logIn.selected = true;
				logIn.eventList.remove((logIn.events.getSelectedIndex()));
				dispose();
				logIn.selected = false;
		  
			}
		}
	}
}