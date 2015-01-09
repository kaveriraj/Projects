package BookCircle;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import junit.framework.TestCase;
import junit.framework.TestSuite;



public class eventTest extends TestCase{
	

	private event event;

	// Constructor

		 public eventTest( String name ) {
		        super( name );
		    }
		//run before every test
			protected void setUp() {
			
				event = new event();
			}

		// This adds all of the tests of this class to the test suite
			
		/*  public static TestSuite suite() {
		        return new TestSuite( eventTest.class );
		    }*/
		//test method that retrieves the details of the upcoming events
       public void testgetDetails()
       {
    	 
    	
    	 java.sql.Date date = java.sql.Date.valueOf( "2013-12-18" ); 
    	
    	  
    	   event newEvent = new event(date,"19:09:32","35,Spruce Street","Discussion of the works of James Chase");
    	   event falseEvent = new event(date,"21:09:37","hvhb h","Discussion");
    	   event testEvent = new event();
      	 testEvent = event.getDetails("Book Discussion");
    	  
          assertEquals(newEvent,testEvent);
          assertNotEquals(falseEvent,testEvent);
          
    	 
    	   
    	   
    	   
       }
     /*  public static void main( String args[] ) {
  	     String [] caseName = { eventTest.class.getName() };
  	      junit.textui.TestRunner.main( caseName );
  	   }*/

	

}