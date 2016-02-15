package cbyt.matrix;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * The default testing class for cbyt.matrix.Matrix.
 *
 */
public class MatrixTest extends TestCase {

    /**
     * Redirected System.out buffer.
     */
    private final ByteArrayOutputStream outBuffer =
        new ByteArrayOutputStream();

    /**
     * Redirected System.err buffer.
     */
    private final ByteArrayOutputStream errBuffer =
        new ByteArrayOutputStream();

    /**
     * Original System.out buffer.
     */
    private PrintStream origOutBuffer;

    /**
     * Original System.err buffer.
     */
    private PrintStream origErrBuffer;

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
     * Setup testing environment before each test.
     */
    protected void setUp() {
        this.origOutBuffer = System.out;
        this.origErrBuffer = System.err;
        System.setOut(new PrintStream(this.outBuffer));
        System.setErr(new PrintStream(this.errBuffer));
    }

    /**
     * Restore default environment after each test.
     */
    protected void tearDown() {
        System.setOut(this.origOutBuffer);
        System.setErr(this.origErrBuffer);
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

        assertTrue(Arrays.deepEquals(D.getArray(), D.getArray()));
        assertEquals(D.getColDimension(), C.getColDimension());
        assertEquals(D.getRowDimension(), C.getRowDimension());

    }

    public void testUMinus() {
        double[][] matrix1 = {
            {1,1,1},
            {1,1,1},
            {1,1,1}
        };
        Matrix A = new Matrix(matrix1);
        double[][] matrix2 = {
            {-1,-1,-1},
            {-1,-1,-1},
            {-1,-1,-1}
        };
        Matrix B = new Matrix(matrix2);
        Matrix C = A.uminus();
        assertEquals(C.getRowDimension(), B.getRowDimension());
        assertEquals(C.getColDimension(), B.getColDimension());
        assertTrue(Arrays.deepEquals(B.getArray(), C.getArray()));
    }

    public void testMinus() {
        double[][] matrix1 = {
            {2,2,2},
            {2,2,2},
            {2,2,2}
        };
        Matrix A = new Matrix(matrix1);

        double[][] matrix2 = {
            {1,1,1},
            {1,1,1},
            {1,1,1}
        };
        Matrix B = new Matrix(matrix2);

        double[][] matrix3 = {
            {1,1,1},
            {1,1,1},
            {1,1,1}
        };
        Matrix D = new Matrix(matrix3);
        Matrix C = A.minus(B);
        assertEquals(C.getColDimension(), B.getColDimension());
        assertEquals(C.getColDimension(), A.getColDimension());
        assertEquals(C.getRowDimension(), B.getRowDimension());
        assertEquals(C.getRowDimension(), A.getRowDimension());

        assertTrue(Arrays.deepEquals(C.getArray(), D.getArray()));
        assertEquals(D.getColDimension(), C.getColDimension());
        assertEquals(D.getRowDimension(), C.getRowDimension());
    }

    public void testMinusEquals() {
        double[][] matrix1 = {
            {2,2,2},
            {2,2,2},
            {2,2,2}
        };
        Matrix A = new Matrix(matrix1);

        double[][] matrix2 = {
            {1,1,1},
            {1,1,1},
            {1,1,1}
        };
        Matrix B = new Matrix(matrix2);

        double[][] matrix3 = {
            {1,1,1},
            {1,1,1},
            {1,1,1}
        };
        Matrix C = new Matrix(matrix3);
        A = A.minusEquals(B);
        assertEquals(C.getColDimension(), B.getColDimension());
        assertEquals(C.getColDimension(), A.getColDimension());
        assertEquals(C.getRowDimension(), B.getRowDimension());
        assertEquals(C.getRowDimension(), A.getRowDimension());
        assertTrue(Arrays.deepEquals(C.getArray(), A.getArray()));
    }

    public void testSet() {
        double [][] A = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        Matrix m = new Matrix(A);
        m.set(0, 0, 5);
        assertEquals(m.get(0, 0), 5.0);
        m.set(1, 1, 6);
        assertEquals(m.get(1, 1), 6.0);
        m.set(2, 2, 7);
        assertEquals(m.get(2, 2), 7.0);
    }

    public void testSetMatrix1() {
        double [][] A = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double [][] target = {
            {1, 3},
            {7, 9}
        };
        Matrix m = new Matrix(A);
        Matrix X = new Matrix(2, 2);
        int[] r = {1, 3};
        int[] c = {1, 3};
        m.setMatrix(r, c, X);
        assertTrue(Arrays.deepEquals(X.getArray(), target));
    }

    public void testSetMatrix2() {
        double [][] A = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double [][] target = {
            {1, 2},
            {7, 8}
        };
        Matrix m = new Matrix(A);
        Matrix X = new Matrix(2, 2);
        int[] r = {1, 3};
        int j0 = 1;
        int j1 = 2;
        m.setMatrix(r, j0, j1, X);
        assertTrue(Arrays.deepEquals(X.getArray(), target));
    }

    public void testSetMatrix3() {
        double [][] A = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double [][] target = {
            {1, 3},
            {4, 6}
        };
        Matrix m = new Matrix(A);
        Matrix X = new Matrix(2, 2);
        int i0 = 1;
        int i1 = 2;
        int[] c = {1, 3};
        m.setMatrix(i0, i1, c, X);
        assertTrue(Arrays.deepEquals(X.getArray(), target));
    }

    public void testSetMatrix4() {
        double [][] A = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double [][] target = {
            {1, 2},
            {4, 5}
        };
        Matrix m = new Matrix(A);
        Matrix X = new Matrix(2, 2);
        int i0 = 1;
        int i1 = 2;
        int j0 = 1;
        int j1 = 2;
        m.setMatrix(i0, i1, j0, j1, X);
        assertTrue(Arrays.deepEquals(X.getArray(), target));
    }

    /**
     * Test print(int w, int d).
     */
    public void testPrint1() {
        // TODO: More rigorous testing?
        double[][] A = {{1.2, 2.3, 3.4}, {1.3, 2.4, 3.5}, {1.4, 2.5, 3.6}};
        Matrix m = new Matrix(A);
        ArrayList<String> outContent = new ArrayList<String>();

        m.print(0, 0);
        outContent.add(String.format("%n 1 2 3%n 1 2 4%n 1 2 4%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());
        m.print(1, 0);
        outContent.add(String.format("%n  1  2  3%n  1  2  4%n  1  2  4%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());
        m.print(0, 1);
        outContent.add(String.format("%n 1.2 2.3 3.4%n 1.3 2.4 3.5%n 1.4 2.5 3.6%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());
        m.print(0, 2);
        outContent.add(String.format("%n 1.20 2.30 3.40%n 1.30 2.40 3.50%n 1.40 2.50 3.60%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());
        m.print(-1, 0);
        outContent.add(String.format("%n 1 2 3%n 1 2 4%n 1 2 4%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());
        m.print(-2, 1);
        outContent.add(String.format("%n 1.2 2.3 3.4%n 1.3 2.4 3.5%n 1.4 2.5 3.6%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());
    }

    /**
     * Test print(PrintWriter output, int w, int d).
     */
    public void testPrint2() {
        // TODO: More rigorous testing?
        double[][] A = {{1.2, 2.3, 3.4}, {1.3, 2.4, 3.5}, {1.4, 2.5, 3.6}};
        Matrix m = new Matrix(A);
        ArrayList<String> outContent = new ArrayList<String>();
        ArrayList<String> errContent = new ArrayList<String>();
        PrintWriter outWriter = new PrintWriter(System.out, true);
        PrintWriter errWriter = new PrintWriter(System.err, true);

        m.print(outWriter, 0, 0);
        outContent.add(String.format("%n 1 2 3%n 1 2 4%n 1 2 4%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());
        m.print(errWriter, 0, 0);
        errContent.add(String.format("%n 1 2 3%n 1 2 4%n 1 2 4%n%n"));
        assertEquals(String.join("", errContent), this.errBuffer.toString());

        m.print(outWriter, 1, 0);
        outContent.add(String.format("%n  1  2  3%n  1  2  4%n  1  2  4%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());
        m.print(errWriter, 1, 0);
        errContent.add(String.format("%n  1  2  3%n  1  2  4%n  1  2  4%n%n"));
        assertEquals(String.join("", errContent), this.errBuffer.toString());

        m.print(outWriter, 0, 1);
        outContent.add(String.format("%n 1.2 2.3 3.4%n 1.3 2.4 3.5%n 1.4 2.5 3.6%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());
        m.print(errWriter, 0, 1);
        errContent.add(String.format("%n 1.2 2.3 3.4%n 1.3 2.4 3.5%n 1.4 2.5 3.6%n%n"));
        assertEquals(String.join("", errContent), this.errBuffer.toString());

        m.print(outWriter, 0, 2);
        outContent.add(String.format("%n 1.20 2.30 3.40%n 1.30 2.40 3.50%n 1.40 2.50 3.60%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());
        m.print(errWriter, 0, 2);
        errContent.add(String.format("%n 1.20 2.30 3.40%n 1.30 2.40 3.50%n 1.40 2.50 3.60%n%n"));
        assertEquals(String.join("", errContent), this.errBuffer.toString());

        m.print(outWriter, -2, 1);
        outContent.add(String.format("%n 1.2 2.3 3.4%n 1.3 2.4 3.5%n 1.4 2.5 3.6%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());
        m.print(errWriter, -2, 1);
        errContent.add(String.format("%n 1.2 2.3 3.4%n 1.3 2.4 3.5%n 1.4 2.5 3.6%n%n"));
        assertEquals(String.join("", errContent), this.errBuffer.toString());
    }

    /**
     * Test print(NumberFormat format, int width).
     */
    public void testPrint3() {
        // TODO: More rigorous testing?
        double[][] A = {{1.2, 2.3, 3.4}, {1.3, 2.4, 3.5}, {1.4, 2.5, 3.6}};
        Matrix m = new Matrix(A);
        ArrayList<String> outContent = new ArrayList<String>();
        DecimalFormat format = new DecimalFormat();
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        format.setGroupingUsed(false);

        format.setMinimumIntegerDigits(1);
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(1);
        m.print(format, 0);
        outContent.add(String.format("%n 1.2 2.3 3.4%n 1.3 2.4 3.5%n 1.4 2.5 3.6%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());

        format.setMinimumIntegerDigits(2);
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(1);
        m.print(format, 2);
        outContent.add(String.format("%n 01.2 02.3 03.4%n 01.3 02.4 03.5%n 01.4 02.5 03.6%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());

        format.setMinimumIntegerDigits(1);
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);
        m.print(format, 2);
        outContent.add(String.format("%n 1.20 2.30 3.40%n 1.30 2.40 3.50%n 1.40 2.50 3.60%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());
    }

    /**
     * Test print(PrintWriter output, NumberFormat format, int width).
     */
    public void testPrint4() {
        // TODO: More rigorous testing?
        double[][] A = {{1.2, 2.3, 3.4}, {1.3, 2.4, 3.5}, {1.4, 2.5, 3.6}};
        Matrix m = new Matrix(A);
        ArrayList<String> outContent = new ArrayList<String>();
        ArrayList<String> errContent = new ArrayList<String>();
        PrintWriter outWriter = new PrintWriter(System.out, true);
        PrintWriter errWriter = new PrintWriter(System.err, true);
        DecimalFormat format = new DecimalFormat();
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        format.setGroupingUsed(false);

        format.setMinimumIntegerDigits(1);
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(1);
        m.print(outWriter, format, 0);
        outContent.add(String.format("%n 1.2 2.3 3.4%n 1.3 2.4 3.5%n 1.4 2.5 3.6%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());
        m.print(errWriter, format, 0);
        errContent.add(String.format("%n 1.2 2.3 3.4%n 1.3 2.4 3.5%n 1.4 2.5 3.6%n%n"));
        assertEquals(String.join("", errContent), this.errBuffer.toString());

        format.setMinimumIntegerDigits(2);
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(1);
        m.print(outWriter, format, 2);
        outContent.add(String.format("%n 01.2 02.3 03.4%n 01.3 02.4 03.5%n 01.4 02.5 03.6%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());
        m.print(errWriter, format, 2);
        errContent.add(String.format("%n 01.2 02.3 03.4%n 01.3 02.4 03.5%n 01.4 02.5 03.6%n%n"));
        assertEquals(String.join("", errContent), this.errBuffer.toString());

        format.setMinimumIntegerDigits(1);
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);
        m.print(outWriter, format, 2);
        outContent.add(String.format("%n 1.20 2.30 3.40%n 1.30 2.40 3.50%n 1.40 2.50 3.60%n%n"));
        assertEquals(String.join("", outContent), this.outBuffer.toString());
        m.print(errWriter, format, 2);
        errContent.add(String.format("%n 1.20 2.30 3.40%n 1.30 2.40 3.50%n 1.40 2.50 3.60%n%n"));
        assertEquals(String.join("", errContent), this.errBuffer.toString());

    }

    public void testTranspose() {
        Matrix m, t;
        double[][] A, target;
        A = new double[][] {
            {1, 2}
        };
        target = new double[][] {
            {1},
            {2},
        };
        m = new Matrix(A);
        t = m.transpose();
        assertTrue(Arrays.deepEquals(t.getArray(), target));
        A = new double[][] {
            {1, 2},
            {3, 4}
        };
        target = new double[][] {
            {1, 3},
            {2, 4}
        };
        m = new Matrix(A);
        t = m.transpose();
        assertTrue(Arrays.deepEquals(t.getArray(), target));
        A = new double[][] {
            {1, 2},
            {3, 4},
            {5, 6}
        };
        target = new double[][] {
            {1, 3, 5},
            {2, 4, 6}
        };
        m = new Matrix(A);
        t = m.transpose();
        assertTrue(Arrays.deepEquals(t.getArray(), target));
    }

    public void testNorml() {
        Matrix m;
        double[][] A;
        double target;
        A = new double[][] {
            {1, -7},
            {-2, -3}
        };
        target = 10;
        m = new Matrix(A);
        assertEquals(m.norml(), target);
        A = new double[][] {
            {5, -4, 2},
            {-1, 2, 3},
            {-2, 1, 0}
        };
        target = 8;
        m = new Matrix(A);
        assertEquals(m.norml(), target);
    }

    public void testNormInf() {
        Matrix m;
        double[][] A;
        double target;
        A = new double[][] {
            {1, -7},
            {-2, -3}
        };
        target = 8;
        m = new Matrix(A);
        assertEquals(m.normInf(), target);
        A = new double[][] {
            {5, -4, 2},
            {-1, 2, 3},
            {-2, 1, 0}
        };
        target = 11;
        m = new Matrix(A);
        assertEquals(m.normInf(), target);
    }

    public void testNormF() {
        Matrix m;
        double[][] A;
        double target;
        A = new double[][] {
          {2, 2},
          {2, -2}
        };
        target = 4;
        m = new Matrix(A);
        assertEquals(m.normF(), target);
    }

    public void testArrayRightDivide() {
        double[][] a, b, c;
        Matrix A, B, C;
        a = new double[][] {
            {2, 4, 6, 8},
            {3, 5, 7, 9}
        };
        A = new Matrix(a);
        B = new Matrix(2, 4, 10.0);
        c = new double[][] {
            {0.2, 0.4, 0.6, 0.8},
            {0.3, 0.5, 0.7, 0.9}
        };
        C = A.arrayRightDivide(B);
        assertTrue(Arrays.deepEquals(C.getArray(), c));
        B = new Matrix(1, 4, 10.0);
        c = new double[][] {
            {0.2, 0.4, 0.6, 0.8},
            {3, 5, 7, 9}
        };
        C = A.arrayRightDivide(B);
        assertTrue(Arrays.deepEquals(C.getArray(), c));
    }

    public void testArrayRightDivideEquals() {
        double[][] a, b, c;
        Matrix A, B, C;
        a = new double[][] {
            {2, 4, 6, 8},
            {3, 5, 7, 9}
        };
        A = new Matrix(a);
        B = new Matrix(2, 4, 10.0);
        c = new double[][] {
            {0.2, 0.4, 0.6, 0.8},
            {0.3, 0.5, 0.7, 0.9}
        };
        C = A.arrayRightDivideEquals(B);
        assertTrue(Arrays.deepEquals(C.getArray(), c));
        assertTrue(Arrays.deepEquals(A.getArray(), c));
        A = new Matrix(a);
        B = new Matrix(1, 4, 10.0);
        c = new double[][] {
            {0.2, 0.4, 0.6, 0.8},
            {3, 5, 7, 9}
        };
        C = A.arrayRightDivideEquals(B);
        assertTrue(Arrays.deepEquals(C.getArray(), c));
        assertTrue(Arrays.deepEquals(A.getArray(), c));
    }

    public void testArrayLeftDivide() {
        double[][] a, b, c;
        Matrix A, B, C;
        A = new Matrix(2, 4, 10.0);
        b = new double[][] {
            {2, 4, 6, 8},
            {3, 5, 7, 9}
        };
        B = new Matrix(b);
        c = new double[][] {
            {0.2, 0.4, 0.6, 0.8},
            {0.3, 0.5, 0.7, 0.9}
        };
        C = A.arrayLeftDivide(B);
        assertTrue(Arrays.deepEquals(C.getArray(), c));
        A = new Matrix(1, 4, 10.0);
        c = new double[][] {
            {0.2, 0.4, 0.6, 0.8},
            {3, 5, 7, 9}
        };
        C = A.arrayLeftDivide(B);
        assertTrue(Arrays.deepEquals(C.getArray(), c));
    }

    public void testArrayLeftDivideEquals() {
        double[][] a, b, c;
        Matrix A, B, C;
        A = new Matrix(2, 4, 10.0);
        b = new double[][] {
            {2, 4, 6, 8},
            {3, 5, 7, 9}
        };
        B = new Matrix(b);
        c = new double[][] {
            {0.2, 0.4, 0.6, 0.8},
            {0.3, 0.5, 0.7, 0.9}
        };
        C = A.arrayLeftDivideEquals(B);
        assertTrue(Arrays.deepEquals(C.getArray(), c));
        assertTrue(Arrays.deepEquals(A.getArray(), c));
        A = new Matrix(1, 4, 10.0);
        B = new Matrix(b);
        c = new double[][] {
            {0.2, 0.4, 0.6, 0.8},
            {3, 5, 7, 9}
        };
        C = A.arrayLeftDivideEquals(B);
        assertTrue(Arrays.deepEquals(C.getArray(), c));
        assertTrue(Arrays.deepEquals(A.getArray(), c));
    }

    public void testArrayTimes() {
        double[][] a, b, c;
        Matrix A, B, C;
        a = new double[][] {
            {2, 4, 6, 8},
            {3, 5, 7, 9}
        };
        A = new Matrix(a);
        B = new Matrix(2, 4, 10.0);
        c = new double[][] {
            {20, 40, 60, 80},
            {30, 50, 70, 90}
        };
        C = A.arrayTimes(B);
        assertTrue(Arrays.deepEquals(C.getArray(), c));
        B = new Matrix(1, 4, 10.0);
        c = new double[][] {
            {20, 40, 60, 80},
            {3, 5, 7, 9}
        };
        C = A.arrayTimes(B);
        assertTrue(Arrays.deepEquals(C.getArray(), c));
    }

    public void testArrayTimesEquals() {
        double[][] a, b, c;
        Matrix A, B, C;
        a = new double[][] {
            {2, 4, 6, 8},
            {3, 5, 7, 9}
        };
        A = new Matrix(a);
        B = new Matrix(2, 4, 10.0);
        c = new double[][] {
            {20, 40, 60, 80},
            {30, 50, 70, 90}
        };
        C = A.arrayTimesEquals(B);
        assertTrue(Arrays.deepEquals(C.getArray(), c));
        assertTrue(Arrays.deepEquals(A.getArray(), c));
        A = new Matrix(a);
        B = new Matrix(1, 4, 10.0);
        c = new double[][] {
            {20, 40, 60, 80},
            {3, 5, 7, 9}
        };
        C = A.arrayTimesEquals(B);
        assertTrue(Arrays.deepEquals(C.getArray(), c));
        assertTrue(Arrays.deepEquals(A.getArray(), c));
    }

    public void testTimes() {
        double[][] a, b, c;
        Matrix A, B, C;
        a = new double[][] {
            {1, 2, 3},
            {4, 5, 6}
        };
        b = new double[][] {
            {7, 8},
            {9, 10},
            {11, 12}
        };
        c = new double[][] {
            {58, 64},
            {139, 154}
        };
        A = new Matrix(a);
        B = new Matrix(b);
        C = A.times(B);
        assertEquals(2, C.getRowDimension());
        assertEquals(2, C.getColDimension());
        for (int i = 0; i < C.getRowDimension(); i++) {
            for (int j = 0; j < C.getColDimension(); j++) {
                assertEquals(c[i][j], C.get(i, j));
            }
        }
    }
}
