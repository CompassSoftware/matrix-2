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

    public void testGetMatrix1() {
        double[][] A = {
            {0, 1, 2, 3, 4},
            {10,11,12,13,14},
            {20,21,22,23,24},
            {30,31,32,33,34},
            {40,41,42,43,44}
        };
        double[][] target = {
            {0, 2, 4},
            {20,22,24},
            {40,42,44}
        };
        Matrix m = new Matrix(A);
        int[] r = {1, 3, 5};
        int[] c = {1, 3, 5};
        Matrix sub = m.getMatrix(r, c);
        assertEquals(sub.getRowDimension(), r.length);
        assertEquals(sub.getColDimension(), c.length);
        assertTrue(Arrays.deepEquals(sub.getArray(), target));
    }

    public void testGetMatrix2() {
        double[][] A = {
            {0, 1, 2, 3, 4},
            {10,11,12,13,14},
            {20,21,22,23,24},
            {30,31,32,33,34},
            {40,41,42,43,44}
        };
        double[][] target = {
            {0, 2, 4},
            {10,12,14},
            {20,22,24}
        };
        Matrix m = new Matrix(A);
        int i0 = 1;
        int i1 = 3;
        int[] c = {1, 3, 5};
        Matrix sub = m.getMatrix(1, 3, c);
        assertEquals(sub.getRowDimension(), i1 - i0 + 1);
        assertEquals(sub.getColDimension(), c.length);
        assertTrue(Arrays.deepEquals(sub.getArray(), target));
    }

    public void testGetMatrix3() {
        double[][] A = {
            {0, 1, 2, 3, 4},
            {10,11,12,13,14},
            {20,21,22,23,24},
            {30,31,32,33,34},
            {40,41,42,43,44}
        };
        double[][] target = {
            {0, 1, 2},
            {20,21,22},
            {40,41,42}
        };
        Matrix m = new Matrix(A);
        int[] r = {1, 3, 5};
        int j0 = 1;
        int j1 = 3;
        Matrix sub = m.getMatrix(r, j0, j1);
        assertEquals(sub.getRowDimension(), r.length);
        assertEquals(sub.getColDimension(), j1 - j0 + 1);
        assertTrue(Arrays.deepEquals(sub.getArray(), target));
    }

    public void testGetRowPackedCopy() {
        double[][] A = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double[] target =
            {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Matrix m = new Matrix(A);
        assertTrue(Arrays.equals(m.getRowPackedCopy(), target));
    }

    public void testGetColPackedCopy() {
        double[][] A = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double[] target =
            {1, 4, 7, 2, 5, 8, 3, 6, 9};
        Matrix m = new Matrix(A);
        assertTrue(Arrays.equals(m.getColPackedCopy(), target));
    }

    public void testPlus() {
        double[][] matrix1 = {
            {1,1,1},
            {1,1,1},
            {1,1,1}
        };
        Matrix A = new Matrix(matrix1);

        double[][] matrix2 = {
            {1,1,1},
            {1,1,1},
            {1,1,1}
        };
        Matrix B = new Matrix(matrix2);

        double[][] matrix3 = {
            {2,2,2},
            {2,2,2},
            {2,2,2}
        };
        Matrix D = new Matrix(matrix3);
        Matrix C = A.plus(B);
        assertEquals(C.getColDimension(), B.getColDimension());
        assertEquals(C.getColDimension(), A.getColDimension());
        assertEquals(C.getRowDimension(), B.getRowDimension());
        assertEquals(C.getRowDimension(), A.getRowDimension());

        assertTrue(Arrays.deepEquals(D.getArray(), C.getArray()));
        assertEquals(D.getColDimension(), C.getColDimension());
        assertEquals(D.getRowDimension(), C.getRowDimension());
    }

    public void testPlusEquals() {
        double[][] matrix1 = {
            {1,1,1},
            {1,1,1},
            {1,1,1}
        };
        Matrix A = new Matrix(matrix1);

        double[][] matrix2 = {
            {1,1,1},
            {1,1,1},
            {1,1,1}
        };
        Matrix B = new Matrix(matrix2);

        double[][] matrix3 = {
            {2,2,2},
            {2,2,2},
            {2,2,2}
        };
        Matrix D = new Matrix(matrix3);
        A.plusEquals(B);

        assertEquals(A.getColDimension(), B.getColDimension());
        assertEquals(A.getRowDimension(), B.getRowDimension());
        assertTrue(Arrays.deepEquals(A.getArray(), D.getArray()));
    }
}
