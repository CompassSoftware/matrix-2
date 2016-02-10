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
            Arrays.deepEquals(A, m.getArray())
        );
        assertEquals(m.getRowDimension(), 3);
        assertEquals(m.getColDimension(), 3);
        A = new double[0][0];
        m = new Matrix(A);
        assertTrue(
            Arrays.deepEquals(A, m.getArray())
        );
        assertEquals(m.getRowDimension(), 0);
        assertEquals(m.getColDimension(), 0);
    }

    public void testConstructor2() {
        double[][] A = {{1,2,3},{1,2,3},{1,2,3}};
        int row = 3;
        int col = 3;
        Matrix m = new Matrix(A, row, col);
        assertTrue(
            Arrays.deepEquals(A, m.getArray())
        );
    }

    public void testConstructor3() {
        double[] A = {1, 2, 3, 1, 2, 3, 1, 2, 3};
        double[][] matrixResult = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
        int split = 3;
        Matrix m = new Matrix(A, split);
        assertTrue(Arrays.deepEquals(matrixResult, m.getArray()));
        assertEquals(m.getRowDimension(), 3);
        assertEquals(m.getColDimension(), 3);
        A = new double[0];
        matrixResult = new double[0][0];
        split = 0;
        m = new Matrix(A, split);
        assertTrue(Arrays.deepEquals(matrixResult, m.getArray()));
        assertEquals(m.getRowDimension(), 0);
        assertEquals(m.getColDimension(), 0);

    }

    public void testConstructor4() {
        // TODO: Write tests...
    }

    public void testConstructor5() {
        double[][] A = {{3,3},{3,3},{3,3}};
        Matrix m = new Matrix(3, 2, 3.0);
        assertTrue(
            Arrays.deepEquals(A, m.getArray())
        );
        assertEquals(3, m.getRowDimension());
        assertEquals(2, m.getColDimension());
    }

    public void testGetArray() {
        double[][] A = {{1,2,3},{1,2,3},{1,2,3}};
        Matrix m = new Matrix(A);
        assertEquals(A, m.getArray());
        assertTrue(
            Arrays.deepEquals(A, m.getArray())
        );
    }

    public void testGetArrayCopy() {
        double[][] A = {{1,2,3},{1,2,3},{1,2,3}};
        Matrix m = new Matrix(A);
        double[][] returnedA = m.getArrayCopy();
        assertTrue(
            Arrays.deepEquals(returnedA, A)
        );
        assertTrue(
            Arrays.deepEquals(returnedA, m.getArray())
        );
        assertTrue(
            returnedA != A
        );
    }

    public void testGetRowDimension() {
        Matrix m = new Matrix(3, 2);
        assertEquals(3, m.getRowDimension());
    }

    public void testGetColDimension() {
        Matrix m = new Matrix(3, 2);
        assertEquals(2, m.getColDimension());
    }
}
