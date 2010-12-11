package com.example.sjis_check;

import com.example.sjis_check.SjisUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SjisUtilTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SjisUtilTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SjisUtilTest.class );
    }

    public void testIsSjis()
    {
        assertTrue( SjisUtil.isSjis("?") );
        assertTrue( SjisUtil.isSjis("あ") );
        assertFalse( SjisUtil.isSjis("①") );
        assertFalse( SjisUtil.isSjis("Ⅰ") );
        assertFalse( SjisUtil.isSjis("㈱") );
        assertFalse( SjisUtil.isSjis("\u9AD9") ); //はしご高
        assertFalse( SjisUtil.isSjis("\uFA11") ); //立つ崎
        
    }
}
