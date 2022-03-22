package musically;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Musically_mainTest {
    
    public Musically_mainTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    /**
     * Test of updateTables method, of class Musically_main.
     * @throws java.sql.SQLException
     */
    @Test
    public void testUpdateTables() throws SQLException {
        System.out.println("updateTables");
        Musically_main instance = new Musically_main();
        instance.updateTables();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of artistsSongs method, of class Musically_main.
     * @throws java.sql.SQLException
     */
    @Test
    public void testArtistsSongs() throws SQLException {
        System.out.println("artistsSongs");
        Musically_main instance = new Musically_main();
        instance.artistsSongs();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fav_artistsSongs method, of class Musically_main.
     * @throws java.sql.SQLException
     */
    @Test
    public void testFav_artistsSongs() throws SQLException {
        System.out.println("fav_artistsSongs");
        Musically_main instance = new Musically_main();
        instance.fav_artistsSongs();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of beginMusic method, of class Musically_main.
     * @throws java.sql.SQLException
     */
    @Test
    public void testBeginMusic() throws SQLException {
        try {
            System.out.println("beginMusic");
            Musically_main instance = new Musically_main();
            instance.beginMusic();
            // TODO review the generated test code and remove the default call to fail.
            //fail("The test case is a prototype.");
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Musically_mainTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Musically_mainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of repeatMusic method, of class Musically_main.
     * @throws java.sql.SQLException
     */
    @Test
    public void testRepeatMusic() throws SQLException {
        System.out.println("repeatMusic");
        Musically_main instance = new Musically_main();
        instance.repeatMusic();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of playpauseMusic method, of class Musically_main.
     * @throws java.sql.SQLException
     */
    @Test
    public void testPlaypauseMusic() throws SQLException {
        System.out.println("playpauseMusic");
        Musically_main instance = new Musically_main();
        instance.playpauseMusic();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of openWebPage method, of class Musically_main.
     * @throws java.sql.SQLException
     */
    @Test
    public void testOpenWebPage() throws SQLException {
        System.out.println("openWebPage");
        String url = "";
        Musically_main instance = new Musically_main();
        instance.openWebPage(url);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Musically_main.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Musically_main.main(args);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setUserID method, of class Musically_main.
     */
    @Test
    public void testSetUserID()  {
        System.out.println("setUserID");
        Musically_main instance = null;
        try {
            instance = new Musically_main();
        } catch (SQLException ex) {
            Logger.getLogger(Musically_mainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        instance.setUserID();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setUserName method, of class Musically_main.
     */
    @Test
    public void testSetUserName() {
        try {
            System.out.println("setUserName");
            Musically_main instance = new Musically_main();
            instance.setUserName();
            // TODO review the generated test code and remove the default call to fail.
           // fail("The test case is a prototype.");
        } catch (SQLException ex) {
            Logger.getLogger(Musically_mainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
