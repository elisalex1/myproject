package musically;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogIn_SignUpTest {
    
    public LogIn_SignUpTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkUsername method, of class LogIn_SignUp.
     */
    @Test
    public void testCheckUsername() {
        System.out.println("checkUsername");
        String user = "";
        LogIn_SignUp instance = new LogIn_SignUp();
        boolean expResult = false;
        boolean result = instance.checkUsername(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of verifyFields method, of class LogIn_SignUp.
     */
    @Test
    public void testVerifyFields() {
        System.out.println("verifyFields");
        LogIn_SignUp instance = new LogIn_SignUp();
        boolean expResult = false;
        boolean result = instance.verifyFields();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class LogIn_SignUp.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        LogIn_SignUp.main(args);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
