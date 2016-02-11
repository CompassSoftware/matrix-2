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
        int row = 3;
        int col = 3;
        double[][] A = {{0,0,0},{0,0,0},{0,0,0}};
        Matrix m = new Matrix(row, col);
        assertTrue(Arrays.deepEquals(A, m.getArray()));
        assertEquals(row, m.getRowDimension());
        assertEquals(col, m.getColDimension());
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

    public void testConstructWithCopy() {
        double[][] A = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        Matrix m = Matrix.constructWithCopy(A);
        assertTrue(Arrays.deepEquals(A, m.getArray()));
        assertEquals(3, m.getRowDimension());
        assertEquals(3, m.getColDimension());
        A = new double[0][0];
        m = Matrix.constructWithCopy(A);
        assertTrue(Arrays.deepEquals(A, m.getArray()));
        assertEquals(0, m.getRowDimension());
        assertEquals(0, m.getColDimension());
    }

    public void testCopy() {
        int row = 3;
        int col = 3;
        double[][] A = {{1,2,3},{1,2,3},{1,2,3}};
        Matrix m = new Matrix(A, row, col);
        Matrix copy = m.copy();
        assertEquals(m.getColDimension(), copy.getColDimension());
        assertEquals(m.getRowDimension(), copy.getRowDimension());
        assertTrue(Arrays.deepEquals(A, m.getArray()));

        A = new double[0][0];
        m = new Matrix(A);
        copy = m.copy();
        assertEquals(m.getColDimension(), copy.getColDimension());
        assertEquals(m.getRowDimension(), copy.getRowDimension());
        assertTrue(Arrays.deepEquals(A, m.getArray()));
    }

    public void testClone() {
        double[][] A = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        Matrix m = new Matrix(A);
        Object tmp = m.clone();
        assertTrue(tmp instanceof Object);
        Matrix m2 = (Matrix) tmp;
        assertTrue(m2 instanceof Matrix);
        assertTrue(Arrays.deepEquals(A, m.getArray()));
        assertTrue(Arrays.deepEquals(m.getArray(), m2.getArray()));
        assertEquals(3, m.getRowDimension());
        assertEquals(3, m.getColDimension());
        assertEquals(m.getRowDimension(), m2.getRowDimension());
        assertEquals(m.getColDimension(), m2.getColDimension());
    }

    public void testIdentity() {
        double[][] A = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        Matrix m = Matrix.identity(3, 3);
        assertTrue(Arrays.deepEquals(A, m.getArray()));
        assertEquals(3, m.getRowDimension());
        assertEquals(3, m.getColDimension());
        A = new double[0][0];
        m = Matrix.identity(0, 0);
        assertTrue(Arrays.deepEquals(A, m.getArray()));
        assertEquals(0, m.getRowDimension());
        assertEquals(0, m.getColDimension());
    }

    public void testRandom() {
        Matrix m = Matrix.random(3, 3);
        assertEquals(3, m.getRowDimension());
        assertEquals(3, m.getColDimension());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertNotNull(m.get(i, j));
            }
        }
    }
}
