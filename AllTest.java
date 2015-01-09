package BookCircle;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * TestSuite that runs all the extension tests
 */
public class AllTest {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite() { // Collect tests manually because we have to test class collection code
        TestSuite suite = new TestSuite("Framework Tests");
        suite.addTestSuite(logInTest.class);
        suite.addTestSuite(EditEventTest.class);
        suite.addTestSuite(eventTest.class);
        suite.addTestSuite(bookTest.class);
        suite.addTestSuite(changePasswordTest.class);
        return suite;
    }
}
