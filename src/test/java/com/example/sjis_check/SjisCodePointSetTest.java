package com.example.sjis_check;

import com.example.sjis_check.SjisCodePointSet;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SjisCodePointSetTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SjisCodePointSetTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SjisCodePointSetTest.class );
    }

    public void testIsSjis()
    {
        assertTrue( SjisCodePointSet.isSjis("?") );
        assertTrue( SjisCodePointSet.isSjis("あ") );
        assertFalse( SjisCodePointSet.isSjis("①") );
        assertFalse( SjisCodePointSet.isSjis("Ⅰ") );
        assertFalse( SjisCodePointSet.isSjis("㈱") );
        assertFalse( SjisCodePointSet.isSjis("\u9AD9") ); //はしご高
        assertFalse( SjisCodePointSet.isSjis("\uFA11") ); //立つ崎
        
    }
}
