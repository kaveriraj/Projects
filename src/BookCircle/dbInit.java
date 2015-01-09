package BookCircle;

//The class that links the application to the database.
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class dbInit {
	  Connection conn = null;
	  Statement stmt = null;
	  ResultSet rs = null;
	  ResultSet newRs = null;
	  ResultSet newRs1 = null;
	  int queryReturn = 0;
	  String sql;
	  PreparedStatement preparedStatement;
	  public static int sessionUserID;
	
	public  dbInit(){
		  try {
		      conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root");
		      
		  } catch (SQLException ex) {
		      // handle any errors
		      System.out.println("SQLException: " + ex.getMessage());
		      System.out.println("SQLState: " + ex.getSQLState());
		      System.out.println("VendorError: " + ex.getErrorCode());
		  }
	}
//Method for adding a user to the database
	public boolean addUser(String firstName,  String lastName,  String emailAddress,  String password,  String gender){
		
		try{
	
	    	  sql = "INSERT INTO bc_User (firstName, lastName, password, emailAddress, gender ) VALUES (?, ?, ?, ?, ?)";
	    	  preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
	    	  preparedStatement.setString(1, firstName);
	    	  preparedStatement.setString(2, lastName);
	    	  preparedStatement.setString(3, password);
	    	  preparedStatement.setString(4, emailAddress);
	    	  preparedStatement.setString(5, gender);
	    	  preparedStatement.executeUpdate();
	    	  return true;
	      }
	   catch (SQLException ex) {
	      // handle any errors
	      System.out.println("SQLException: " + ex.getMessage());
	      System.out.println("SQLState: " + ex.getSQLState());
	      System.out.println("VendorError: " + ex.getErrorCode());
	      return false;
	  }
		
	}
	//Method for user authentication at the Sign Up Page
	public String logIn(String emailAddress)
	{
		String password="";
		try
		{
		sql = "SELECT password, userID FROM bc_User WHERE emailAddress= ?";
		preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		preparedStatement.setString(1, emailAddress);
		rs=preparedStatement.executeQuery();
		while (rs.next()){
			  password = rs.getString("password");
			  sessionUserID = rs.getInt("userID");
		}
		return password;
		
		}
		 catch (SQLException ex) {
		      // handle any errors
		      System.out.println("SQLException: " + ex.getMessage());
		      System.out.println("SQLState: " + ex.getSQLState());
		      System.out.println("VendorError: " + ex.getErrorCode());
		      return password;
		  }
		
	}
	
	//Method to add an event to the database

	public void addEvent(event newEvent)
	{
		try{
		      stmt = (Statement) conn.createStatement();
	    	
		    	  sql = "INSERT INTO bc_Event (eventName, date, time, location, description, userID) VALUES (?, ?, ?, ?, ?, ?)";
		    	  preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		    	  preparedStatement.setString(1, newEvent.getName());
		    	  preparedStatement.setDate(2,  (java.sql.Date) newEvent.getDate());
		    	  preparedStatement.setString(3, newEvent.getTime());
		    	  preparedStatement.setString(4, newEvent.getLoc());
		    	  preparedStatement.setString(5, newEvent.getDes());
		    	  preparedStatement.setInt(6, newEvent.getUserID());
		    	  preparedStatement.executeUpdate();
		    	  
		      }
		   catch (SQLException ex) {
		      // handle any errors
		      System.out.println("SQLException: " + ex.getMessage());
		      System.out.println("SQLState: " + ex.getSQLState());
		      System.out.println("VendorError: " + ex.getErrorCode());
		     
		  }
	}
	
	//Method that retrieves the members details to display
	public ResultSet getName()
	
	{
		Object[] tempRow = new Object[]{};
		newUser userNew = new newUser();
	
	
		try
		{
			 
		sql = "SELECT firstName,lastName,emailAddress FROM bc_User";
		preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		rs=preparedStatement.executeQuery();
		return rs;
		
		}
		 catch (SQLException ex) {
		      // handle any errors
		      System.out.println("SQLException: " + ex.getMessage());
		      System.out.println("SQLState: " + ex.getSQLState());
		      System.out.println("VendorError: " + ex.getErrorCode());
		      return null;
		  }
		
		
		
	}
	
	//Get all the upcoming events created by other users but not already signed up by the current user
	public ArrayList<String> getUnRegisteredUpcomingEvents(){
		ArrayList<String> unRegisteredEvents = new ArrayList<String>();
		String eventName;
		int eventID;
		int count=0;

		try
		{
			
		sql = "SELECT count(*) FROM bc_Event where userID != ?";
		preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		preparedStatement.setInt(1, sessionUserID);
		rs=preparedStatement.executeQuery();
		while (rs.next()){
			count = rs.getInt(1);
			if(count>0)
			{
				sql = "SELECT eventName FROM bc_Event where userID != ?";
				preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
				preparedStatement.setInt(1, sessionUserID);
				newRs1=preparedStatement.executeQuery();
				while(newRs1.next()){
					eventName= newRs1.getString("eventName");
				   unRegisteredEvents.add(eventName);
			}
			}
			else
			{
				
				   sql = "SELECT eventName FROM bc_Event where userID != ? ";
					preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
					preparedStatement.setInt(1, sessionUserID);
					newRs=preparedStatement.executeQuery();
					while(newRs.next()){
						eventName= newRs.getString("eventName");
					   unRegisteredEvents.add(eventName);
				
			}
		}
		
	
		}
		return unRegisteredEvents;
		}
		 catch (SQLException ex) {
		      // handle any errors
		      System.out.println("SQLException: " + ex.getMessage());
		      System.out.println("SQLState: " + ex.getSQLState());
		      System.out.println("VendorError: " + ex.getErrorCode());
		      return null ;
		  }
	}
	
	//Retrieves the event details
	
	public event getEventDetails(String eventName)
	{
		
		event newEvent = new event();
		

		try
		{
		sql = "SELECT date,time,location,description  FROM bc_Event where eventName = ?";
		preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		preparedStatement.setString(1, eventName);
		rs=preparedStatement.executeQuery();
		while (rs.next()){
			
			newEvent.date=rs.getDate("date");
			newEvent.time=rs.getString("time");
			newEvent.loc=rs.getString("location");
			newEvent.des=rs.getString("description");
			
			
			}
		return newEvent;
		}
		
		 catch (SQLException ex) {
		      // handle any errors
		      System.out.println("SQLException: " + ex.getMessage());
		      System.out.println("SQLState: " + ex.getSQLState());
		      System.out.println("VendorError: " + ex.getErrorCode());
		      return null ;
		  }
		
		
	}
	//Retrieves the names of the events created by the user
	
	public ArrayList<String> getUserEventNames()
	{
		ArrayList<String> eventList = new ArrayList<String>();
		try
		{
		sql = "SELECT eventName  FROM bc_Event where userID = ?";
		preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		preparedStatement.setInt(1, sessionUserID);
		rs=preparedStatement.executeQuery();
		while (rs.next()){
			
			eventList.add(rs.getString("eventName"));
		}
		return eventList;
		}
		
		 catch (SQLException ex) {
		      // handle any errors
		      System.out.println("SQLException: " + ex.getMessage());
		      System.out.println("SQLState: " + ex.getSQLState());
		      System.out.println("VendorError: " + ex.getErrorCode());
		      return null ;
		  }
		
	}
	
	//Retrieves the details of the event created by the user
	
	public event getUserEventDetails()
	{
       event newEvent = new event();
		

		try
		{
		sql = "SELECT date,time,location,description  FROM bc_Event where userID = ?";
		preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		preparedStatement.setInt(1, sessionUserID);
		rs=preparedStatement.executeQuery();
		while (rs.next()){
			
			newEvent.date=rs.getDate("date");
			newEvent.time=rs.getString("time");
			newEvent.loc=rs.getString("location");
			newEvent.des=rs.getString("description");
			
			
			}
		return newEvent;
		}
		
		 catch (SQLException ex) {
		      // handle any errors
		      System.out.println("SQLException: " + ex.getMessage());
		      System.out.println("SQLState: " + ex.getSQLState());
		      System.out.println("VendorError: " + ex.getErrorCode());
		      return null ;
		  }
		
	}
	
	//Method to delete an event
	public void deleteEvent(String eventName)
	{
		try
		{
		sql = "DELETE FROM bc_Event where eventName = ?";
		preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		preparedStatement.setString(1, eventName);
	    preparedStatement.executeUpdate();
		
		}
		
		 catch (SQLException ex) {
		      // handle any errors
		      System.out.println("SQLException: " + ex.getMessage());
		      System.out.println("SQLState: " + ex.getSQLState());
		      System.out.println("VendorError: " + ex.getErrorCode());
		     
		  }
		
	}
	//Method to display the event details created by the user
	public event updateEvent(String eventName)
	{
		event newEvent = new event();

		try
		{
		sql = "SELECT eventID,date,time,location,description  FROM bc_Event where eventName = ?";
		preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		preparedStatement.setString(1, eventName);
		rs=preparedStatement.executeQuery();
		while (rs.next()){
			newEvent.name=eventName;
			newEvent.eventID=rs.getInt("eventID");
		    newEvent.date=rs.getDate("date");
			newEvent.time=rs.getString("time");
			newEvent.loc=rs.getString("location");
			newEvent.des=rs.getString("description");
			
			
			}
		return newEvent;
		
	}
		catch (SQLException ex) {
		      // handle any errors
		      System.out.println("SQLException: " + ex.getMessage());
		      System.out.println("SQLState: " + ex.getSQLState());
		      System.out.println("VendorError: " + ex.getErrorCode());
		     return null;
		  }

}
	//Method to update the event
	public void updateEventDetails(event newEvent){
		
		
		 try{
		      stmt = (Statement) conn.createStatement();
	    	
		    	  sql = "UPDATE bc_Event SET eventName =?, date = ?,location =? , description=?, time=? where eventID=?";
		    	  preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		    	  preparedStatement.setString(1, newEvent.getName());
		    	  preparedStatement.setDate(2,  (java.sql.Date) newEvent.getDate());
		    	  preparedStatement.setString(3, newEvent.getTime());
		    	  preparedStatement.setString(3, newEvent.getLoc());
		    	  preparedStatement.setString(4, newEvent.getDes());
		    	  preparedStatement.setInt(5, newEvent.getEventID());
		    	  preparedStatement.executeUpdate();
		    	  
		      }
		   catch (SQLException ex) {
		      // handle any errors
		      System.out.println("SQLException: " + ex.getMessage());
		      System.out.println("SQLState: " + ex.getSQLState());
		      System.out.println("VendorError: " + ex.getErrorCode());
		     
		  }
	}
	
	//Method to add a book review
	public void addReview(book newBook)
	{
		try{
		      stmt = (Statement) conn.createStatement();
	    	
		    	  sql = "INSERT INTO bc_Review (bookName, rating, review) VALUES (?, ?, ?)";
		    	  preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		    	  preparedStatement.setString(1, newBook.getName());
		         preparedStatement.setString(2, newBook.getRating());
		    	  preparedStatement.setString(3,newBook.getReview());
		    	  
		    	  preparedStatement.executeUpdate();
		    	  
		      }
		   catch (SQLException ex) {
		      // handle any errors
		      System.out.println("SQLException: " + ex.getMessage());
		      System.out.println("SQLState: " + ex.getSQLState());
		      System.out.println("VendorError: " + ex.getErrorCode());
		     
		  }
		
	}
	
//Method to check if a book review exists
public boolean searchBook(String name)
{
	boolean a=false;
	try
	{
	sql = "SELECT review  FROM bc_Review where bookName = ?";
	preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
	preparedStatement.setString(1, name);
	rs=preparedStatement.executeQuery();
	while (rs.next()){
	    
		a=true;
		
		
		}
	return a;
	
}
	catch (SQLException ex) {
	      // handle any errors
	      System.out.println("SQLException: " + ex.getMessage());
	      System.out.println("SQLState: " + ex.getSQLState());
	      System.out.println("VendorError: " + ex.getErrorCode());
	     return a;
	  }
	
}
//Method to retreive the book review details
public book searchBookDetails(String name)
{
	book newBook = new book();
	try
	{
	sql = "SELECT rating,review  FROM bc_Review where bookName = ?";
	preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
	preparedStatement.setString(1, name);
	rs=preparedStatement.executeQuery();
	while (rs.next()){
	    newBook.name=name;
		newBook.rating=rs.getString("rating");
		newBook.review=rs.getString("review");
		
		
		}
	return newBook;
	
	
}
	catch (SQLException ex) {
	      // handle any errors
	      System.out.println("SQLException: " + ex.getMessage());
	      System.out.println("SQLState: " + ex.getSQLState());
	      System.out.println("VendorError: " + ex.getErrorCode());
	     return null;
	  }
	
}

//Method to authenticate the password given by the user at the change password option
public String getPassword()
{
   String pass="";
	try
	{
	sql = "SELECT password  FROM bc_User where userID = ?";
	preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
	preparedStatement.setInt(1, sessionUserID);
	rs=preparedStatement.executeQuery();
	while (rs.next()){
	pass=rs.getString("password");
		
		
		}
	return pass;
	
	}

	catch (SQLException ex) {
	      // handle any errors
	      System.out.println("SQLException: " + ex.getMessage());
	      System.out.println("SQLState: " + ex.getSQLState());
	      System.out.println("VendorError: " + ex.getErrorCode());
	     return pass;
	  }
}

//method to update the new password given by the user
public void updatePassword(String pass)
{
	 try{
	      stmt = (Statement) conn.createStatement();
   	
	    	  sql = "UPDATE bc_User SET password = ? where userID= ?";
	    	  preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
	    	  preparedStatement.setString(1, pass);
	    	  preparedStatement.setInt(2, sessionUserID);
	    	
	    	  preparedStatement.executeUpdate();
	    	  
	      }
	   catch (SQLException ex) {
	      // handle any errors
	      System.out.println("SQLException: " + ex.getMessage());
	      System.out.println("SQLState: " + ex.getSQLState());
	      System.out.println("VendorError: " + ex.getErrorCode());
	     
	  }
	
}

//Method to display the first name at the home page
public String getFirstName()
{
   String name="";
	try
	{
	sql = "SELECT firstName FROM bc_User where userID = ?";
	preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
	preparedStatement.setInt(1, sessionUserID);
	rs=preparedStatement.executeQuery();
	while (rs.next()){
	name=rs.getString("firstName");
		
		
		}
	return name;
	
	}

	catch (SQLException ex) {
	      // handle any errors
	      System.out.println("SQLException: " + ex.getMessage());
	      System.out.println("SQLState: " + ex.getSQLState());
	      System.out.println("VendorError: " + ex.getErrorCode());
	     return null;
	  }
}

}