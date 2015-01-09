package BookCircle;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Test;




public class logInTest extends TestCase {
	private logIn logIn;
	

	// Constructor

		 public logInTest( String name ) {
		        super( name );
		    }
		//run before every test
			protected void setUp() {
			
				logIn = new logIn();
				
			}

		// This adds all of the tests of this class to the test suite
			
		  /*public static TestSuite suite() {
		        return new TestSuite( logInTest.class );
		    }*/
		
	//test method for the login method	  
  public void testcheck()
	{
		String name = "jason@gmail.com";
		String pass = "jasonb";
		boolean result = logIn.check(name, pass);
		assertEquals(true, result);
		assertNotEquals(false,result);
		String falsePass = "dummy";
		boolean falseResult = logIn.check(name, falsePass);
		assertEquals(false,falseResult);
		assertNotEquals(true, falseResult);
	
		
	}
  //test method for the method that retrieves the list of upcoming events for the user
	public void testgetEvents()
	{
		ArrayList <String> expectedEvent = new ArrayList<String>();
		expectedEvent.add("Book Discussion ");
		expectedEvent.add("Book Signing Event");
		expectedEvent.add("Story night");
		expectedEvent.add("Book night");
		ArrayList <String> unexpectedEvent = new ArrayList<String>();
		unexpectedEvent.add("evening");
		unexpectedEvent.add("party");
		unexpectedEvent.add("pizza");
		
	
		ArrayList <String> actualEvent = new ArrayList<String> ();
		actualEvent = logIn.getEvents();
		
		assertEquals(expectedEvent.size(),actualEvent.size());
		assertNotEquals(unexpectedEvent.size(),actualEvent.size());
		
		for(int i=0;i<expectedEvent.size();i++)
		{
		assertEquals(expectedEvent.get(i),actualEvent.get(i));
		}
		for(int i=0;i<unexpectedEvent.size();i++)
		{
		assertNotEquals(unexpectedEvent.get(i),actualEvent.get(i));
		}
		
	}
	  //test method for the method that retrieves the list of events created by the user
	public void testgetUserEvent()
	{
		ArrayList <String> expectedEvent = new ArrayList<String>();
		expectedEvent.add("Book night");
		expectedEvent.add("Review night");
		ArrayList <String> actualEvent = new ArrayList<String> ();
		actualEvent = logIn.getUserEvent();
		ArrayList <String> unexpectedEvent = new ArrayList<String>();
		unexpectedEvent.add("evening");
		
		assertEquals(expectedEvent.size(),actualEvent.size());
		assertNotEquals(unexpectedEvent.size(),actualEvent.size());
		
		for(int i=0;i<expectedEvent.size();i++)
		{
		assertEquals(expectedEvent.get(i),actualEvent.get(i));
		}
		for(int i=0;i<unexpectedEvent.size();i++)
		{
		assertNotEquals(unexpectedEvent.get(i),actualEvent.get(i));
		}
		
	}
	//test method for searching the review of the book by the book's name
	public void testcheckBook()
	{
		String trueBookName = "blink";
		boolean result = logIn.checkBook(trueBookName);
		String falseBookName = "alice";
		boolean falseresult = logIn.checkBook(falseBookName);
		assertEquals(true,result);
		assertNotEquals(false,result);
		assertEquals(false,falseresult);
		assertNotEquals(true, falseresult);
		
	}

	
	/*public static void main( String args[] ) {
	     String [] caseName = { logInTest.class.getName() };
	      junit.textui.TestRunner.main( caseName );
	   }*/

}
