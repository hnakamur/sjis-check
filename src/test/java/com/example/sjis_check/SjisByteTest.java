package com.example.sjis_check;

import com.example.sjis_check.SjisByte;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SjisByteTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SjisByteTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SjisByteTest.class );
    }

    public void testIsSjis()
    {
        assertTrue( SjisByte.isSjis("?") );
        assertTrue( SjisByte.isSjis("あ") );
        assertFalse( SjisByte.isSjis("①") );
        assertFalse( SjisByte.isSjis("Ⅰ") );
        assertFalse( SjisByte.isSjis("㈱") );
        assertFalse( SjisByte.isSjis("\u9AD9") ); //はしご高
        assertFalse( SjisByte.isSjis("\uFA11") ); //立つ崎
        
    }
}
