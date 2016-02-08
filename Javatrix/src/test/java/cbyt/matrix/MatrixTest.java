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
        assertEquals(m.getMatrix(), A);
        A = new double[0][0];
        m = new Matrix(A);
        assertNull(A);
        assertNull(m.getMatrix());
    }

    public void testConstructor2() {
        // TODO: Write tests...
    }

    public void testConstructor3() {
        // TODO: Write tests...
    }

    public void testConstructor4() {
        // TODO: Write tests...
    }

    public void testConstructor5() {
        double[][] A = {{3,3},{3,3},{3,3}};
        Matrix m = new Matrix(3, 2, 3.0);
        assertEquals(A, m.getMatrix());
        assertEquals(3, m.getRowLength());
        assertEquals(2, m.getColLength());
    }

    public void testGetMatrix() {
        double[][] A = {{1,2,3},{1,2,3},{1,2,3}};
        Matrix m = new Matrix(A);
        assertEquals(A, m.getMatrix());
    }

    public void testGetRowLength() {
        Matrix m = new Matrix(3, 2);
        assertEquals(3, m.getRowLength());
    }

    public void testGetColLength() {
        Matrix m = new Matrix(3, 2);
        assertEquals(2, m.getColLength());
    }
}
