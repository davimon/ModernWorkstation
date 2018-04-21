/*	Filename: 	JsonParserJUnitTest.java
 *	Author:		Adam Levandowski
 *	Date:		April 21, 2018
 *	Purpose:	JsonParserJUnitTest.java is the test class used to 
 *          test all methods within the JsonParser.java class.
 */

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;
import workstation.JsonParser;
import workstation.Notes;
import java.io.File;
import java.util.Arrays;

public class JsonParserJUnitTest {
    
    // Declaring JsonParser and NotesArray objects for all methods
    static JsonParser parser;
    static ArrayList<Notes> mainNotesList;
    
    public JsonParserJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("* JsonParserJUnitTest: @BeforeClass method");
        
        //create static JsonParser and NotesArray objects
        parser = new JsonParser();
        mainNotesList = parser.retrieveNotesArray();
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
     * Test of retrieveNotesArray method, of class JsonParser.
     */
    @Test
    public void testRetrieveNotesArray() {
        System.out.println("* JsonParserJUnitTest: Test Method 1 - RetrieveNotesArrayCheck()");
        
        File notesFile = new File("src/workstation/Notes.json");
    		
    		if (notesFile.exists()) {
    			
                    assertNotNull(parser.retrieveNotesArray());
                    assertSame(mainNotesList, parser.retrieveNotesArray());
    			
    		} else {
    		
                    assertNull(parser.retrieveNotesArray());
                    assertSame(mainNotesList, parser.retrieveNotesArray());
    		}
    }

    /**
     * Test of save method, of class JsonParser.
     */
    @Ignore
    @Test
    public void testSave() {
        System.out.println("* JsonParserJUnitTest: Test Method 2 - SaveCheck()");
        
    }

    /**
     * Test of main method, of class JsonParser.
     */
    @Ignore
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        JsonParser.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
