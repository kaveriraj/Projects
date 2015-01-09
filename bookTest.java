package BookCircle;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import junit.framework.TestCase;
import junit.framework.TestSuite;



public class bookTest extends TestCase{
	

	private book book;

	// Constructor

		 public bookTest( String name ) {
		        super( name );
		    }
		//run before every test
			protected void setUp() {
			
				book = new book();
			}

		// This adds all of the tests of this class to the test suite
			
		/*  public static TestSuite suite() {
		        return new TestSuite( bookTest.class );
		    }*/
		 //test method that retreives the details of the book review
       public void testgetReview()
       {
    	 
    	
    	 
    	
    	  book expectedBook = new book("test","3","good book");
    	  book unexpectedBook = new book("dummy","3","dummy book");
    	  book actualBook = new book();
    	  actualBook = book.getReview("test");
    	  
          assertEquals(expectedBook,actualBook);
          assertNotEquals(unexpectedBook,actualBook);




          
    	 
    	   
    	   
    	   
       }
     /*  public static void main( String args[] ) {
  	     String [] caseName = { bookTest.class.getName() };
  	      junit.textui.TestRunner.main( caseName );
  	   }*/

	

}
