package cbyt.matrix;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class MatrixTest extends TestCase {

    public MatrixTest(String testName){
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(MatrixTest.class);
    }

    public void testTest() {
        assertTrue(true);
        assertFalse(false);
    }

}
