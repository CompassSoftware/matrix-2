package cbyt.matrix;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * The default testing class for cbyt.matrix.Matrix.
 *
 */
public class MatrixTest extends TestCase {

    /**
     * Default test constructor for TestCase.
     * @param  testName Test's name.
     */
    public MatrixTest(String testName){
        super(testName);
    }

    /**
     * Static testing suite.
     * @return TestSuite of self TestCase.
     */
    public static Test suite() {
        return new TestSuite(MatrixTest.class);
    }

    /**
     * Placeholder TestCase test.
     */
    public void testTest() {
        assertTrue(true);
        assertFalse(false);
    }

    public void testConstructor1() {
        double[][] A = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        Matrix m = new Matrix(A);
        // TODO: Write tests...
    }

    public void testConstructor2() {
        // TODO: Write tests...
    }

    public void testConstructor3() {
        // TODO: Write tests...
    }

    public void testConstructor4() {
        int row = 4;
        int col = 5;
        Matrix m = new Matrix(row, col);
        assertEquals(row, m.getRowLength());
        assertEquals(col, m.getColLength());
    }

    public void testConstructor5() {
        // TODO: Write tests...
    }

}
