package BookCircle;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Test;




public class changePasswordTest extends TestCase {
	private changePassword changePassword;
	

	// Constructor

		 public changePasswordTest( String name ) {
		        super( name );
		    }
		//run before every test
			protected void setUp() {
			
				changePassword = new changePassword();
				
			}
			public void testcheckPassword()
			{
				String pass = "jasonb";
				boolean result = changePassword.checkPassword(pass);
				assertEquals(true, result);
				assertNotEquals(false,result);
				String falsePass = "dummy";
				boolean falseResult = changePassword.checkPassword(falsePass);
				assertEquals(false,falseResult);
				assertNotEquals(true, falseResult);
			
			}
			public void testcheckNewPassword()
			{
				String newPassword = "correct";
				String confirmPassword = "correct";
				boolean result = changePassword.checkNewPassword(newPassword, confirmPassword);
				assertEquals(true, result);
				assertNotEquals(false,result);
				String falsePass = "dummy";
				boolean falseResult = changePassword.checkNewPassword(newPassword, falsePass);
				assertEquals(false,falseResult);
				assertNotEquals(true, falseResult);
			
				
			}

		// This adds all of the tests of this class to the test suite
			
	/*	  public static TestSuite suite() {
		        return new TestSuite( changePasswordTest.class );
		    }
		
  
	
	public static void main( String args[] ) {
	     String [] caseName = { changePasswordTest.class.getName() };
	      junit.textui.TestRunner.main( caseName );
	   }*/

}