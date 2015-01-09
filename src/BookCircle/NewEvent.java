package BookCircle;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

import org.jdesktop.swingx.JXDatePicker;
 
public class NewEvent extends JDialog implements ActionListener{
	JPanel desktop;
	 private static NewEvent dialog1;
	 private static String value = "";
	static JLabel l1,l2,l3,l4,l5,l6;
	static JTextField name,location,time;
	static JTextArea description;
    static JXDatePicker picker;
    static JButton Submit,Cancel;
    static Date value1;
    static JSpinner timeSpinner;
   static JComponent editor;
	public NewEvent(Frame frame, Component buttonComp)
	{
        super(frame, "BookCircle", true);
        desktop = new JPanel(); 
        desktop.setLayout(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        cs.fill = GridBagConstraints.RELATIVE;
        
        l6 = new JLabel("Create your event");
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
        name=new JTextField(30);
        cs.gridx = 2;
        cs.gridy = 1;
        cs.gridwidth = 2;
        desktop.add(name, cs);
        
     	 l2 = new JLabel("Date:");
     	cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        cs.ipadx=2;  
        cs.ipady=2;  
         
        cs.insets = new Insets(15,15,15,15);    
        
        desktop.add(l2, cs);
   	 picker = new JXDatePicker();
  picker.setDate(Calendar.getInstance().getTime());
   
   
   picker.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
   cs.gridx = 1;
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
        SpinnerModel model = new SpinnerDateModel();
        timeSpinner = new JSpinner(model);
        editor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(editor);
       

    
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
       desktop.add(timeSpinner, cs);
       l4 = new JLabel("Location:");
     	cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 1;
        cs.ipadx=2;  
        cs.ipady=2;  
         
        cs.insets = new Insets(15,15,15,15);
        desktop.add(l4, cs);
        
        location=new JTextField(30);
        cs.gridx = 2;
        cs.gridy = 4;
        cs.gridwidth = 1;
        desktop.add(location, cs);
     	 
     	 l5 = new JLabel("Description:");
     	cs.gridx = 1;
        cs.gridy = 5;
        cs.gridwidth=1;
        cs.ipadx=2;  
        cs.ipady=2;  
        cs.insets = new Insets(15,15,15,15);
        desktop.add(l5, cs);
     	description=new JTextArea();
     	description.setColumns(31);
     	description.setRows(5);
    	cs.gridx = 2;
        cs.gridy = 5;
        cs.gridwidth = 1;
        desktop.add(description, cs);
        
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
				dialog1 = new NewEvent(frame,
						buttonComp);
				dialog1.pack();
				dialog1.setVisible(true);
				
				return value;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==Submit)
		{
			event newEvent = new event();
			newEvent.setName(name.getText());
			
			newEvent.setDate(new java.sql.Date(picker.getDate().getTime()));
			newEvent.setTime(((DateEditor) editor).getFormat().format(timeSpinner.getValue()));
			newEvent.setLoc(location.getText());
			newEvent.setDes(description.getText());
			newEvent.setUserID(dbInit.sessionUserID);
			dbInit intiDb=new dbInit();
			intiDb.addEvent(newEvent);//adds the event to the database
			dispose();
			
		}
		else if(e.getSource()==Cancel)
 		{
	   		dispose();
 		}
		
	}

	   

}
