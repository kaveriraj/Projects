package BookCircle;

//Main class that contains the home page 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;




public class logIn extends JFrame implements ActionListener,ListSelectionListener{
	JPanel desktop;
	JPanel bp;
	JPanel he;
	public static logIn frame;
	
	public JButton button;
	static JLabel l1,l2,l3,l4;
	static JTextField userName,em;
	static JPasswordField password;
	static JButton SignIn,SignUp,Cancel,submit2,Edit,Delete;
	JPanel desktop1 = new JPanel();
	public static JList events;
	public  static DefaultListModel eventList;
	public static JScrollPane listScrollPane;
	JPanel pReg=new JPanel();
	JPanel p1=new JPanel();
	public static JList members;
	public  static DefaultListModel memberList;
	public static JScrollPane memScrollPane;
	public static String selectedEvent;
	private static final String editString = "Edit";
    private static final String deleteString = "Delete";
    
    public static boolean selected = false;
    //log In Page
	public logIn()
	{
    	super("BookCircle");
	    desktop = new JPanel(); 
        desktop.setLayout(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        cs.fill = GridBagConstraints.VERTICAL;

		l1 = new JLabel("User Name:");
		cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth =1;
       
        desktop.add(l1, cs);
        userName=new JTextField(30);
   	    cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        desktop.add(userName, cs);
    	 l2 = new JLabel("Password:");
    	 cs.gridx = 0;
         cs.gridy = 20;
         cs.gridwidth = 1;
         cs.ipadx=2;  
         cs.ipady=2;  
          
         cs.insets = new Insets(15,15,15,15);  
         
         desktop.add(l2, cs);
    	
    	 password=new JPasswordField(30);
    	 cs.gridx = 1;
         cs.gridy = 20;
         cs.gridwidth = 2;
         desktop.add(password, cs);
    	 SignIn = new JButton("Sign In");
    	 cs.gridx = 1;
         cs.gridy = 22;
         cs.gridwidth = 1;
         cs.ipadx=1;  
         cs.ipady=1;  
          
         cs.insets = new Insets(10,10,15,15);    
         desktop.add(SignIn,cs);
         SignIn.addActionListener(this);
    	 SignIn.setActionCommand("SignIn");
         Cancel=new JButton("Cancel");
         cs.gridx = 2;
         cs.gridy = 22;
         cs.gridwidth = 1;
         
          
          
         
         desktop.add(Cancel,cs);
         
         
         
         SignUp = new JButton("Sign Up");
    	l3= new JLabel("New To Book Circle?");
    	
    	 SignUp.addActionListener(this);
    	 SignUp.setActionCommand("SignUp");
    	 Cancel=new JButton("Cancel");
    	 Cancel.addActionListener(this);
    	 Cancel.setActionCommand("Cancel");

         bp = new JPanel();
       
         bp.add(l3);
         bp.add(SignUp);
         he=new JPanel();
         l4=new JLabel("Log In");
         he.add(l4);
         
         getContentPane().add(he, BorderLayout.NORTH);
         getContentPane().add(desktop, BorderLayout.CENTER);
         getContentPane().add(bp, BorderLayout.PAGE_END);
  
    	
    	     	 
	}
	
	static void createAndShowGUI() {
	       
		 JFrame.setDefaultLookAndFeelDecorated(true);

	        //Create and set up the window.
	       frame = new logIn();
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Display the window.
	        frame.setVisible(true);
		    
		    
		   //Display the window.
	        frame.pack();
	        frame.setVisible(true);   
	}
	
	public void actionPerformed(ActionEvent e ){
		String cmd = e.getActionCommand();
		
		if(cmd.equals("ok"))
		{
			 dbInit initDb = new dbInit();
			 boolean a=initDb.searchBook(em.getText());
			 if(a==true)
			 {
				 button = new JButton("New Review");
		    		String selectedName = ReviewPage.showDialog(
		                frame,
		                button,
		                cmd
		                ); 
			 }
			 else{
				 JOptionPane.showMessageDialog(frame,
	        			    "The book review does not exist");
			 }
			
		}
		
		 //When the user clicks the Sign Up button
	        if(cmd.equals("SignUp"))
	        {
	        		button = new JButton("New User");
	        		String selectedName = SignUpPage.showDialog(
	                    frame,
	                    button,
	                    cmd
	                    );  
	        }
	       //When the user clicks Sign In button
	        else if(cmd.equals("SignIn"))
	        {
	        	newUser.eAddress=userName.getText();
	        	dbInit initDb = new dbInit();
	        	String passw=initDb.logIn(newUser.eAddress);
	        	newUser.pass=password.getText();
	        	if(newUser.eAddress.equals("") && newUser.pass.equals(""))
	        	{
	        		JOptionPane.showMessageDialog(frame,
	        			    "Please enter the username/password");
	        	}
	        	else if(newUser.pass.equals(passw))
	        	{
	        		getContentPane().removeAll();
			        ArrayList unRegisteredEvents = new ArrayList();
	        		desktop1.setLayout(new BorderLayout(10,10));

			       setJMenuBar(createMenuBar());

			       desktop1.add(new JLabel("Upcoming Events"), BorderLayout.PAGE_START);
			        //Get all the upcoming events created by other users but not already signed up by the current user
			        unRegisteredEvents = initDb.getUnRegisteredUpcomingEvents();	
			        eventList=new DefaultListModel();    
			        
			        for (int i=0; i < unRegisteredEvents.size(); i++) 
			        { 
			        eventList.addElement(unRegisteredEvents.get(i)); //access with get() method 
			        }
			        events=new JList(eventList);
			        events.setVisibleRowCount(5);
			        listScrollPane = new JScrollPane(events);
			        
			        events.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			        desktop1.add(listScrollPane, BorderLayout.WEST);
			        events.addListSelectionListener(this);
			      
			        
			       
			       
			        
			        JLabel click=new JLabel("Click here to make your own event");
					desktop1.add(click, BorderLayout.PAGE_END);
					click.addMouseListener(new MouseAdapter() {
	                	 public void mouseClicked(MouseEvent e) {
	                
	                		 button = new JButton("New Event");
	     	        		String selectedName = NewEvent.showDialog(
	     	                    frame,
	     	                    button
	     	                    );  
	                	 } });
	                
					getContentPane().add(desktop1);
					
	        		logIn.this.validate();
					logIn.this.repaint();
	        	}
	        	else 
	        	{
	        		JOptionPane.showMessageDialog(frame,
	        			    "Please enter the correct password");
	        	}
	        }
	        
	        
	 }
	//Action Listener for displaying the upcoming events dialog
	public void valueChanged(ListSelectionEvent e) {
	        if(e.getValueIsAdjusting()==true && selected == false)
	        {
			 selectedEvent=(String) events.getSelectedValue();
			 button = new JButton("New Unregistered Event");
     		String selectedName = unRegisteredEvent.showDialog(
                 frame,
                 button);
	        }	
	}
	//Main Menu
	protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu4 = new JMenu("Home");
        menu4.addMenuListener(new MenuListener(){

			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
			    getContentPane().removeAll();
                getContentPane().add(desktop1);
                getContentPane().add(pReg,BorderLayout.EAST);
				logIn.this.validate();
				logIn.this.repaint();
				
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
        

        //Set up dashboard
        JMenu menu = new JMenu("Dashboard");
        menu.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				getContentPane().removeAll();
				Container contentPane = getContentPane();
				//contentPane.add(createMenuBar1());
				getContentPane().setLayout(new BoxLayout(contentPane,BoxLayout.LINE_AXIS));
				JPanel p=new JPanel();
				p.setLayout(new BorderLayout(5,5));
				p.add(new JLabel("My Circle Members"), BorderLayout.PAGE_START);
				
				 //Getting the details of the circle members from the database and displaying it in a table
				dbInit initDb = new dbInit();
				ResultSet membersData;
				membersData=initDb.getName();
				try{
		           ResultSetMetaData metaData = (ResultSetMetaData) membersData.getMetaData();
		            int numberOfColumns = metaData.getColumnCount();
		            Vector columnNames = new Vector();

		            // Get the column names
		            for (int column = 0; column < numberOfColumns; column++) {
		                columnNames.addElement(metaData.getColumnLabel(column + 1));
		            }

		            // Get all rows.
		            Vector rows = new Vector();

		            while (membersData.next()) {
		                Vector newRow = new Vector();

		                for (int i = 1; i <= numberOfColumns; i++) {
		                    newRow.addElement(membersData.getObject(i));
		                }

		                rows.addElement(newRow);
		            }
					DefaultTableModel model = new DefaultTableModel(rows, columnNames);
					JTable table = new JTable();
					table.setModel(model); 
					table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					table.setFillsViewportHeight(true);
					JScrollPane scroll = new JScrollPane(table);
					scroll.setHorizontalScrollBarPolicy(
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					scroll.setVerticalScrollBarPolicy(
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
					newUser userNew = new newUser();
					p.add(scroll, BorderLayout.WEST);

				} catch (Exception e1) {
		            e1.printStackTrace();
		        }
				
				
		        JPanel p1 = new JPanel();
		       
		        getContentPane().add(createMenuBar1(),BorderLayout.LINE_START);  
		        Dimension minSize = new Dimension(10, 100);
		        Dimension prefSize = new Dimension(100, 100);
		        Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
		        getContentPane().add(new Box.Filler(minSize, prefSize, maxSize));
		        getContentPane().add(p);
				logIn.this.validate();
				logIn.this.repaint();     
				
				}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
        
        dbInit initDb = new dbInit();
        JMenu menu6 = new JMenu("Welcome,");
        JMenu menu5 = new JMenu(initDb.getFirstName());
        JMenu menu1 = new JMenu("My Account");
        menu1.addMenuListener(new MenuListener() {

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			//The dialog for changing the password
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				button = new JButton("Change Password");
        		String selectedName = changePassword.showDialog(
                    frame,
                    button); 
                   
                     
			}
        });
        
        
        
        JMenu menu3 = new JMenu("LogOut");
        menu3.addMenuListener(new MenuListener() {

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				int confirm = JOptionPane.showOptionDialog(frame,
                        "Are You Sure to Close this Application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
			}
        });
       
        menuBar.add(menu4);
        menuBar.add(menu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(menu6);
        menuBar.add(menu5);
        menuBar.add(menu1);
       
        menuBar.add(menu3);
      return menuBar;
    }
	
//The vertical side menu in the dash board page
	 public JMenuBar createMenuBar1() {
	        JMenuBar menuBar = new JMenuBar();
	       menuBar.setLayout(new GridLayout(4,0,0,0));
	        
	        JMenu menu4 = new JMenu("Circle Members");
	        menu4.addMenuListener(new MenuListener() {

	            

				@Override
				public void menuSelected(MenuEvent e) {
					// TODO Auto-generated method stub
					
					getContentPane().removeAll();
					Container contentPane = getContentPane();
					//contentPane.add(createMenuBar1());
					getContentPane().setLayout(new BoxLayout(contentPane,BoxLayout.LINE_AXIS));
					JPanel p=new JPanel();
					p.setLayout(new BorderLayout(5,5));
					p.add(new JLabel("My Circle Members"), BorderLayout.PAGE_START);
				
					dbInit initDb = new dbInit();
					ResultSet membersData;
					membersData=initDb.getName();
					try{
			           ResultSetMetaData metaData = (ResultSetMetaData) membersData.getMetaData();
			            int numberOfColumns = metaData.getColumnCount();
			            Vector columnNames = new Vector();

			            // Get the column names
			            for (int column = 0; column < numberOfColumns; column++) {
			                columnNames.addElement(metaData.getColumnLabel(column + 1));
			            }

			            // Get all rows.
			            Vector rows = new Vector();

			            while (membersData.next()) {
			                Vector newRow = new Vector();

			                for (int i = 1; i <= numberOfColumns; i++) {
			                    newRow.addElement(membersData.getObject(i));
			                }

			                rows.addElement(newRow);
			            }
						DefaultTableModel model = new DefaultTableModel(rows, columnNames);
						JTable table = new JTable();
						table.setModel(model); 
						table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
						table.setFillsViewportHeight(true);
						JScrollPane scroll = new JScrollPane(table);
						scroll.setHorizontalScrollBarPolicy(
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						scroll.setVerticalScrollBarPolicy(
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
					   p.add(scroll, BorderLayout.WEST);

					} catch (Exception e1) {
			            e1.printStackTrace();
			        }
			        getContentPane().add(createMenuBar1(),BorderLayout.LINE_START);  
			        Dimension minSize = new Dimension(10, 100);
			        Dimension prefSize = new Dimension(100, 100);
			        Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
			        getContentPane().add(new Box.Filler(minSize, prefSize, maxSize));
			        getContentPane().add(p);
					logIn.this.validate();
					logIn.this.repaint();     
					
				}

				@Override
				public void menuDeselected(MenuEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void menuCanceled(MenuEvent e) {
					// TODO Auto-generated method stub
					
				}
	        });
	        //Event Manager menu Option
	        JMenu menu5 = new JMenu("Event Manager");
	        menu5.addMenuListener(new MenuListener() {
				@Override
				public void menuSelected(MenuEvent e) {
					// TODO Auto-generated method stub
					
					getContentPane().removeAll();
					Container contentPane = getContentPane();
					getContentPane().setLayout(new BoxLayout(contentPane,BoxLayout.LINE_AXIS));
					JPanel p=new JPanel();
					p.setLayout(new BorderLayout(5,5));
					p.add(new JLabel("My Events"), BorderLayout.PAGE_START);
					memberList=new DefaultListModel();    
				    dbInit initDb = new dbInit();
				    ArrayList<String> al=new ArrayList<String>();
				    al=initDb.getUserEventNames(); //Retrives the event details of the user from the database
				     for (int i=0; i < al.size(); i++) 
				        { 
				        memberList.addElement(al.get(i)); //access with get() method 
				        }
				       members=new JList(memberList);
				       members.setLayoutOrientation(JList.VERTICAL);
				        members.setVisibleRowCount(5);
				       
				       memScrollPane = new JScrollPane(members);
				     
				    members.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
			       
				    p.add(memScrollPane, BorderLayout.WEST);
				    JPanel eventButtons = new JPanel();
				    Edit=new JButton("Edit");
				    Edit.setActionCommand(editString);
			        Edit.addActionListener(new EditListener());
			        Delete = new JButton(deleteString);
			        Delete.setActionCommand(deleteString);
			        Delete.addActionListener(new DeleteListener());
			        
			        
			        eventButtons.add(Edit);
			        eventButtons.add(Delete);
			        p.add(eventButtons,BorderLayout.PAGE_END);
			        getContentPane().add(createMenuBar1(),BorderLayout.LINE_START);  
			       Dimension minSize = new Dimension(10, 100);
			        Dimension prefSize = new Dimension(100, 100);
			        Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
			        getContentPane().add(new Box.Filler(minSize, prefSize, maxSize));
			        getContentPane().add(p);
					logIn.this.validate();
					logIn.this.repaint();  
				}

				@Override
				public void menuDeselected(MenuEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void menuCanceled(MenuEvent e) {
					// TODO Auto-generated method stub
					
				}
	        });
	        
	        //Book Poll menu item
	        JMenu menu6 = new JMenu("Book Poll");
	        menu6.addMenuListener(new MenuListener() {
				@Override
				public void menuSelected(MenuEvent e) {
					// TODO Auto-generated method stub
					
					getContentPane().removeAll();
					Container contentPane = getContentPane();
					
					getContentPane().setLayout(new BoxLayout(contentPane,BoxLayout.LINE_AXIS));
					
					bookPoll r = new bookPoll();
                    JPanel pn =r.getPoll();
                    JPanel pb = new JPanel();
                    pb.setLayout(new BorderLayout());
                    pb.add(pn,BorderLayout.WEST);
			        getContentPane().add(createMenuBar1(),BorderLayout.LINE_START);  
			        Dimension minSize = new Dimension(10, 100);
			        Dimension prefSize = new Dimension(100, 100);
			        Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
			        getContentPane().add(new Box.Filler(minSize, prefSize, maxSize));
			        getContentPane().add(pb);
			        logIn.this.validate();
					logIn.this.repaint();  
					
				}

				@Override
				public void menuDeselected(MenuEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void menuCanceled(MenuEvent e) {
					// TODO Auto-generated method stub
					
				}
	        });
	        //Book Review Menu Item
	        JMenu menu7 = new JMenu("Book Reviews");
            menu7.addMenuListener(new MenuListener() {

	            @Override
				public void menuSelected(MenuEvent e) {
					// TODO Auto-generated method stub
					
					getContentPane().removeAll();
					Container contentPane = getContentPane();
					//contentPane.add(createMenuBar1());
					getContentPane().setLayout(new BoxLayout(contentPane,BoxLayout.LINE_AXIS));
					review r = new review();
                    JPanel pn =r.getReview();
                    JPanel pb = new JPanel();
                    pb.setLayout(new BorderLayout());
                    pb.add(pn,BorderLayout.WEST);
				   
			        JPanel p3 = searchSpace();
			        pb.add(p3,BorderLayout.EAST);
			        getContentPane().add(createMenuBar1());  
			        getContentPane().add(pb);
			        logIn.this.validate();
					logIn.this.repaint();     
					
				}

				@Override
				public void menuDeselected(MenuEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void menuCanceled(MenuEvent e) {
					// TODO Auto-generated method stub
					
				}
	        });
	        
	        menuBar.add(menu4);
	        menuBar.add(menu5);
	        menuBar.add(menu6);
	        menuBar.add(menu7);

	       menuBar.setBorder(BorderFactory.createMatteBorder(0,0,0,1,
	                                                          Color.BLACK));
	        return menuBar;
	       
	    }
	 //Action Listener for the edit button , a new dialog pops up
	 class EditListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            
	        	 button = new JButton("Edit Event");
	        		String selectedName = EditEvent.showDialog(
	                    frame,
	                    button);
	          
	        }
	    }
	 //Action Listener for the delete button, it removes the event from the list and the database 
	 
	 class DeleteListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            //This method can be called only if
	            //there's a valid selection
	            //so go ahead and remove whatever's selected.
	            int index = members.getSelectedIndex();
	            dbInit initDb = new dbInit();
	            initDb.deleteEvent((String) memberList.getElementAt(index));
	            memberList.remove(index);
	 
	            int size = memberList.getSize();
	 
	            if (size == 0) { //Nobody's left, disable firing.
	                Delete.setEnabled(false);
	 
	            } else { //Select an index.
	                if (index == memberList.getSize()) {
	                    //removed item in last position
	                	 index--;
	                }
	 
	                members.setSelectedIndex(index);
	                members.ensureIndexIsVisible(index);
	            }
	        }
	    }
	 
	 
	 public JPanel searchSpace()
	 {
	 	JPanel p3 = new JPanel();
	     JLabel in=new JLabel("Search for a review:");
	      em = new JTextField(20);
	     JButton ok=new JButton("Search");
	     ok.addActionListener(this);
	 	ok.setActionCommand("ok");
	     p3.add(in);
	     p3.add(em);
	     p3.add(ok);
	     return p3;
	 }
	
	 
	
	 
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
