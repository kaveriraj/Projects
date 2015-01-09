package BookCircle;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import junit.framework.TestCase;
import junit.framework.TestSuite;



public class EditEventTest extends TestCase{
	

	private EditEvent event;

	// Constructor

		 public EditEventTest( String name ) {
		        super( name );
		    }
		//run before every test
			protected void setUp() {
			
				event = new EditEvent();
			}

		// This adds all of the tests of this class to the test suite
			
		 /* public static TestSuite suite() {
		        return new TestSuite( EditEventTest.class );
		    }*/
		 //test method that retrives the details of the event created by the user
       public void testgetUserEvent()
       {
    	 
    	
    	 java.sql.Date date = java.sql.Date.valueOf( "2013-12-13" ); 
    	
    	 
    	 event newEvent = new event(date,"14:23:36","35th Lexington Avnue","book");
    	 event falseEvent = new event(date,"21:09:37","hvhb h","Discussion");
    	event testEvent = new event();
    	 testEvent = event.getUserEvent("Book night");
    	  
          assertEquals(newEvent,testEvent);
         assertNotEquals(falseEvent,testEvent);
          
    	 
    	   
    	   
    	   
       }
     /*  public static void main( String args[] ) {
  	     String [] caseName = { EditEventTest.class.getName() };
  	      junit.textui.TestRunner.main( caseName );
  	   }*/

	

}