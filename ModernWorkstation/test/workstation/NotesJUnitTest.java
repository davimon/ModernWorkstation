/*	Filename: 	NotesJUnitTest.java
 *	Author:		Adam Levandowski
 *	Date:		April 21, 2018
 *	Purpose:	NotesJUnitTest.java is the test class used to 
 *          test all methods within the Notes.java class.
 */

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import workstation.Notes;

public class NotesJUnitTest {
    
    public NotesJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
  
        System.out.println("* NotesJUnitTest: @BeforeClass method");
       
    }

    /**
     * Test of getID method, of class Notes.
     */
    @Test
    public void testSetGetID() {
        Notes noteTester = new Notes();
        
        System.out.println("* NotesJUnitTest: Test Method 1 - SetGetIDCheck()");
        int newID = 3;
    
        // set new ID
        noteTester.setID(newID);
        
        // Assert ID has been changed successfuly
        assertEquals(newID, noteTester.getID());
    }

    /**
     * Test of getTitle method, of class Notes.
     */
    @Test
    public void testGetTitle() {
        Notes noteTester = new Notes();
        
        System.out.println("* NotesJUnitTest: Test Method 2 - GetTitleCheck()");
        String expResult = "New Title";
        String result = noteTester.getTitle();
        
        assertNotSame(expResult, result);
    }

    /**
     * Test of getCategory method, of class Notes.
     */
    @Test
    public void testGetCategory() {
        Notes noteTester = new Notes();
        
        System.out.println("* NotesJUnitTest: Test Method 3 - GetCategoryCheck()");
        String expResult = "Leisure";
        String result = noteTester.getCategory();
        
        assertNotSame(expResult, result);
    }

    /**
     * Test of getContent method, of class Notes.
     */
    @Test
    public void testGetContent() {
        Notes noteTester = new Notes();
        
        System.out.println("* NotesJUnitTest: Test Method 4 - GetContentCheck()");
        String expResult = "Real\nContent";
        String result = noteTester.getContent();
        
        assertNotSame(expResult, result);
    }

    /**
     * Test of setTitle method, of class Notes.
     */
    @Test
    public void testSetTitle() {
        Notes noteTester = new Notes();
        
        System.out.println("* NotesJUnitTest: Test Method 5 - SetTitleCheck()");
        String expResult = "New Title";
        String result = noteTester.getTitle();
        noteTester.setTitle(expResult);
        
        assertNotSame(expResult, result);
        assertEquals(expResult, noteTester.getTitle());
    }

    /**
     * Test of setCategory method, of class Notes.
     */
    @Test
    public void testSetCategory() {
        Notes noteTester = new Notes();
        
        System.out.println("* NotesJUnitTest: Test Method 6 - SetCategoryCheck()");
        String expResult = "Leisure";
        String result = noteTester.getCategory();
        noteTester.setCategory(expResult);
        
        assertNotSame(expResult, result);
        assertEquals(expResult, noteTester.getCategory());
    }

    /**
     * Test of setContent method, of class Notes.
     */
    @Test
    public void testSetContent() {
        Notes noteTester = new Notes();
        
        System.out.println("* NotesJUnitTest: Test Method 7 - SetContentCheck()");
        String expResult = "Real\nContent";
        String result = noteTester.getContent();
        noteTester.setContent(expResult);
        
        assertNotSame(expResult, result);
        assertEquals(expResult, noteTester.getContent());
    }

    /**
     * Test of toString method, of class Notes.
     */
    @Test
    public void testToString() {
        Notes noteTester = new Notes();
        
        System.out.println("* NotesJUnitTest: Test Method 8 - ToStringCheck()");
        
        String expResult = "{\n\"Title\":\"Class Notes 1\",\n" 
                + "\"Category\":\"School\",\n" 
                + "\"Content\":\"Fell asleep in class during lecture.\"\n}";
        
        String result = noteTester.toString();
        
        assertNotSame(expResult, result);
        
        noteTester.setTitle("Class Notes");
        noteTester.setCategory("School");
        noteTester.setContent("Fell asleep in class during lecture.");
        
        result = noteTester.toString();
        
        assertNotSame(expResult, result);
    }
    
}
