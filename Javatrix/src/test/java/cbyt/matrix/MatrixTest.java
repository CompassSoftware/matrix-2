package cbyt.matrix;

import java.util.Arrays;

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
        assertTrue(
            Arrays.deepEquals(A, m.getMatrix())
        );
        assertEquals(m.getRowLength(), 3);
        assertEquals(m.getColLength(), 3);
        A = new double[0][0];
        m = new Matrix(A);
        assertTrue(
            Arrays.deepEquals(A, m.getMatrix())
        );
        assertEquals(m.getRowLength(), 0);
        assertEquals(m.getColLength(), 0);
    }

    public void testConstructor2() {
        double[][] A = {{1,2,3},{1,2,3},{1,2,3}};
        int row = 3;
        int col = 3;
        Matrix m = new Matrix(A, row, col);
        assertTrue(
            Arrays.deepEquals(A, m.getMatrix())
        );
    }

    public void testConstructor3() {
        double[] A = {1, 2, 3, 1, 2, 3, 1, 2, 3};
        double[][] matrixResult = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
        int split = 3;
        Matrix m = new Matrix(A, split);
        assertTrue(Arrays.deepEquals(matrixResult, m.getMatrix()));
        assertEquals(m.getRowLength(), 3);
        assertEquals(m.getColLength(), 3);
        A = new double[0];
        matrixResult = new double[0][0];
        split = 0;
        m = new Matrix(A, split);
        assertTrue(Arrays.deepEquals(matrixResult, m.getMatrix()));
        assertEquals(m.getRowLength(), 0);
        assertEquals(m.getColLength(), 0);

    }

    public void testConstructor4() {
        int row = 3;
        int col = 3;
        double[][] A = {{0,0,0},{0,0,0},{0,0,0}}
        Matrix m = new Matrix(row, col);
        assertEquals(row, m.getRowLength());
        assertEquals(col, m.getColLength());
        assertTrue(Arrays.deepEquals(A, m.getMatrix()));
    }

    public void testConstructor5() {
        double[][] A = {{3,3},{3,3},{3,3}};
        Matrix m = new Matrix(3, 2, 3.0);
        assertTrue(
            Arrays.deepEquals(A, m.getMatrix())
        );
        assertEquals(3, m.getRowLength());
        assertEquals(2, m.getColLength());
    }

    public void testGetMatrix() {
        double[][] A = {{1,2,3},{1,2,3},{1,2,3}};
        Matrix m = new Matrix(A);
        assertEquals(A, m.getMatrix());
        assertTrue(
            Arrays.deepEquals(A, m.getMatrix())
        );
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
