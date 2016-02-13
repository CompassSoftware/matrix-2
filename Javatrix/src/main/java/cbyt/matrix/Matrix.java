package cbyt.matrix;

import java.io.Serializable;
import java.io.PrintWriter;
import java.lang.Cloneable;
import java.lang.IllegalArgumentException;
import java.lang.Math;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Javatrix = Java Matrix class.
 *
 * The Javatrix Java Matrix Class provides fundamental operations of numerical linear algebra.
 * Various constructors create Matrices from two dimensional arrays of double precision floating point numbers.
 * Various "gets" and "sets" provide access to submatrices and matrix elements.
 * Several methods implement basic matrix arithmetic, including matrix addition and multiplication, matrix norms, and element-by-element array operations.
 * Methods for reading and printing matrices are also included.
 * All the operations in this version of the Matrix Class involve real matrices.
 * Complex matrices as well as various decompositions may be handled in a future version.
 *
 */
public class Matrix implements java.io.Serializable, java.lang.Cloneable {

    /**
     * Class scoped matrix storage.
     */
    private double[][] matrix;

    /**
     * Matrix row length.
     */
    private int rowLength;

    /**
     * Matrix column length.
     */
    private int colLength;

    /**
     * Construct a matrix from a 2-D array.
     * @param  A Two-dimensional array of doubles.
     */
    public Matrix(double[][] A) throws java.lang.IllegalArgumentException {
        if (A.length > 0) {
            Integer rowlen = null;
            for (int i = 0; i < A.length; i++) {
                if (rowlen == null) {
                    rowlen = A[i].length;
                } else {
                    if (A[i].length != rowlen) {
                        throw new java.lang.IllegalArgumentException(
                            "All rows must have the same length"
                        );
                    }
                }
            }
            this.rowLength = A.length;
            this.colLength = A[0].length;
            this.matrix = new double[this.rowLength][this.colLength];
            for (int i = 0; i < this.rowLength; i++) {
                for (int j = 0; j < this.colLength; j++) {
                    this.matrix[i][j] = A[i][j];
                }
            }
        }
        else if (A.length == 0) {
            this.matrix = new double[0][0];
            this.rowLength = 0;
            this.colLength = 0;
        }
    }

    /**
     * Construct a matrix quickly without checking arguments.
     * @param   A Two-dimensional array of doubles.
     * @param   m Number of rows.
     * @param   n Number of columns.
     */
    public Matrix(double[][] A, int m, int n) throws java.lang.IllegalArgumentException {
        if(m < 0)
            throw new java.lang.IllegalArgumentException(
                "Row dimension must be non-negative"
            );
        if(n < 0)
            throw new java.lang.IllegalArgumentException(
                "Col dimension must be non-negative"
            );
        this.matrix = new double[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
              this.matrix[i][j] = A[i][j];
            }
        }
        this.rowLength = m;
        this.colLength = n;
    }

    /**
     * Construct a matrix from a one-dimensional packed array.
     * @param   vals One-dimensional array of doubles, packed by columns.
     * @param   m    Number of rows.
     */
    public Matrix(double[] vals, int m) throws java.lang.IllegalArgumentException {
        if(m < 0) {
            throw new java.lang.IllegalArgumentException(
                "Row dimension must be non-negative"
            );
        }
        if (vals.length > 0) {
            if ((vals.length % m) == 0) {
                int split = (m != 0 ? (vals.length / m) : 0);
                this.matrix = new double[split][m];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < split; j++) {
                        this.matrix[i][j] = vals[(i + (j * m))];
                    }
                }
                this.rowLength = m;
                this.colLength = split;
            } else {
                throw new java.lang.IllegalArgumentException(
                    "Array length must be a multiple of m"
                );
            }
        } else {
            this.matrix = new double[0][0];
            this.rowLength = 0;
            this.colLength = 0;
        }
    }

    /**
     * Construct an m-by-n matrix of zeros.
     * @param   m Number of rows.
     * @param   n Number of columns.
     */

    public Matrix(int m, int n) throws java.lang.IllegalArgumentException {
        if(m < 0)
            throw new java.lang.IllegalArgumentException(
                "Row dimension must be non-negative"
            );
        if(n < 0)
            throw new java.lang.IllegalArgumentException(
                "Col dimension must be non-negative"
            );
        this.matrix = new double[m][n];
        this.rowLength = m;
        this.colLength = n;
        for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
            this.matrix[i][j] = 0;
          }
        }
    }

    /**
     * Construct an m-by-n constant matrix.
     * @param   m Number of rows.
     * @param   n Number of columns.
     * @param   s Fill the matrix with this scalar value.
     */
    public Matrix(int m, int n, double s) throws java.lang.IllegalArgumentException {
        if(m < 0)
            throw new java.lang.IllegalArgumentException(
                "Row dimension must be non-negative"
            );
        if(n < 0)
            throw new java.lang.IllegalArgumentException(
                "Col dimension must be non-negative"
            );
        this.matrix = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                this.matrix[i][j] = s;
            }
        }
        this.rowLength = m;
        this.colLength = n;
    }

    /**
     * Return the 2d array for the internal matrix values.
     * @return   The internal 2d array of the matrix object
     */
    public double[][] getArray(){
        return this.matrix;
    }

    /**
     * Returns a deep copy of the indernal 2d array.
     * @return   2d array of doubles
     */
    public double[][] getArrayCopy(){
        double[][] A = new double[rowLength][colLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
              A[i][j] = this.matrix[i][j];
            }
        }
        return A;
    }

    /**
     * Getter for the row length internal.
     * @return   Int: Value of rowLength
     */
    public int getRowDimension(){
        return this.rowLength;
    }

    /**
     * Getter for the col length internal.
     * @return    Int: Value fo colLength
     */
    public int getColDimension(){
        return this.colLength;
    }

    /**
     * Fetchs a single element of index i by j
     * @return     Double of row i and column j
     */
    public double get(int i, int j){
        return this.matrix[i][j];
    }

    /**
     * Returns a submatrix mapped across rows in r and collumns in c.
     * @return      New Matrix from mapped r and c
     * TODO: Decide whether passed r and c values should start at 0 or 1
     */
    public Matrix getMatrix(int[] r, int[] c){
        double[][] target = new double[r.length][c.length];
        for (int i = 0; i < r.length; i++) {
          for (int j = 0; j < c.length; j++) {
              target[i][j] = this.matrix[r[i]-1][c[j]-1];
          }
        }
        return new Matrix(target);
    }

    /**
     * Returns a submatrix from rows i0 to i1 and columns in c.
     * @return      New sub matrix
     * TODO: Decide whether passed r and c values should start at 0 or 1
     */
    public Matrix getMatrix(int i0, int i1, int[] c){
        double[][] target = new double[i1 - i0 + 1][c.length];
        for (int i = i0 - 1; i < i1; i++) {
            for (int j = 0; j < c.length; j++) {
                target[i][j] = this.matrix[i][c[j] - 1];
            }
        }
        return new Matrix(target);
    }

    /**
     * Retruns a submatrix mapped across rows in r and columns from j0 to j1.
     * @return      New sub matrix
     * TODO: Decide whether passed r and c values should start at 0 or 1
     */
    public Matrix getMatrix(int[] r, int j0, int j1) {
        double[][] target = new double[r.length][j1 - j0 + 1];
        for (int i = 0; i < r.length; i++) {
            for (int j = j0 - 1; j < j1; j++) {
                target[i][j] = this.matrix[r[i] - 1][j];
            }
        }
        return new Matrix(target);
    }

    /**
     * Make a one-dimensional row packed copy of the internal array.
     * @return      double[] packed by row
     */
    public double[] getRowPackedCopy() {
        double[] target = new double[rowLength * colLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                target[i * colLength + j] = this.matrix[i][j];
            }
        }
        return target;
    }

    /**
     * Make a one-dimensional column packed copy of the internal array.
     * @return      double[] packed by columns
     */
    public double[] getColPackedCopy() {
        double[] target = new double[rowLength * colLength];
        for (int j = 0; j < colLength; j++) {
            for (int i = 0; i < rowLength; i++) {
                target[j * rowLength + i] = this.matrix[i][j];
            }
        }
        return target;
    }

    /**
     * Sets element at index i j to value s.
     * @param    i row index.
     * @param    j col index.
     * @param    s target value.
     */
    public void set(int i, int j, double s) {
        this.matrix[i][j] = s;
    }

    /**
     * Sets sub matrix mapped by values of r and c to s.
     * @param    r array of target rows
     * @param    c array of target columns
     * @param    X new matrix
     */
    public void setMatrix(int[] r, int[] c, Matrix X) {
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < c.length; j++) {
                X.matrix[i][j] = this.matrix[r[i] - 1][c[j] - 1];
            }
        }
    }

    /**
     * Sets a submatrix mapped across rows in r and columns from j0 to j1
     * @param  r int[] contained row indeces
     * @param  j0 starting column index
     * @param  j1 ending column index
     * @param  X target Matrix
     */
    public void setMatrix(int[] r, int j0, int j1, Matrix X) {
        for (int i = 0; i < r.length; i++) {
            for (int j = j0; j <= j1; j++) {
                X.matrix[i][j - j0] = this.matrix[r[i] - 1][j - 1];
            }
        }
    }

    /**
     * Sets a submatrix mapped from rows i0 to i1 and columns in c
     * @param   i0 starting row index
     * @param   i1 ending row index
     * @param   c int[] containing column indeces
     * @param   X target Matrix
     */
    public void setMatrix(int i0, int i1, int[] c, Matrix X) {
        for (int i = i0; i <= i1; i++) {
            for (int j = 0; j < c.length; j++) {
                X.matrix[i - i0][j] = this.matrix[i - 1][c[j] - 1];
            }
        }
    }

    /**
     * Sets a submatrix mapped from rows i0 to i1 and columns j0 to j1.
     * @param  i0 starting row index
     * @param  i1 ending row index
     * @param  j0 starting column index
     * @param  j1 ending column index
     * @param  X target Matrix
     */
    public void setMatrix(int i0, int i1, int j0, int j1, Matrix X) {
        for (int i = i0; i <= i1; i++) {
            for (int j = j0; j <= j1; j++) {
                X.matrix[i - i0][j- j0] = this.matrix[i - 1][j - 1];
            }
        }
    }

    /**
     * Retrusn the transpose of calling Matrix.
     * @return The transpose of calling Matrix.
     */
    public Matrix transpose() {
        double[][] target = new double[this.colLength][this.rowLength];
        for (int i = 0; i < this.rowLength; i++) {
            for (int j = 0; j < this.colLength; j++) {
                target[j][i] = this.matrix[i][j];
            }
        }
        return new Matrix(target);
    }

     /**
      * Generate identity matrix.
      * @param   m Number of rows.
      * @param   n Number of columns.
      * @return  An m-by-n matrix with ones on the diagonal and zeros elseware.
      */
    public static Matrix identity(int m, int n) throws java.lang.IllegalArgumentException {
        if (m < 0) {
            throw new java.lang.IllegalArgumentException(
                "Row dimension must be non-negative"
            );
        }
        if (n < 0) {
            throw new java.lang.IllegalArgumentException(
                "Column dimension must be non-negative"
            );
        }
        double[][] A = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = ((i == j) ? 1 : 0);
            }
        }
        return new Matrix(A);
    }

    /**
     * Construct a matrix from a copy of a 2-D array.
     * @param      A Two-dimensional array of doubles.
     * @exception    IllegalArgumentException All rows must have the same length.
     * @return       Matrix
     */
    public static Matrix constructWithCopy(double[][] A) throws java.lang.IllegalArgumentException {
        if (A.length > 0) {
            int rCount = A.length;
            int cCount = A[0].length;
            Matrix retn  = new Matrix(rCount, cCount);
            double[][] arr = retn.getArray();
            for (int i = 0; i < rCount; i++) {
                if (A[i].length != cCount) {
                    throw new java.lang.IllegalArgumentException(
                        "All rows must have the same length."
                    );
                }
                for (int j = 0; j < cCount; j++) {
                    arr[i][j] = A[i][j];
                }
            }
            return retn;
        } else {
            return new Matrix(0, 0);
        }
    }

    /**
    * Return a deep copy a matrix
    * @return   A deep copy of a matrix
    */
    public Matrix copy() {
        double[][] A = new double[rowLength][colLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                A[i][j] = this.matrix[i][j];
            }
        }
        Matrix m = new Matrix(A, rowLength, colLength);
        return m;
    }

    /**
     * Clone the Matrix Object.
     * @return Object.
     */
    public Object clone() {
        return this.copy();
    }

    /**
    * Fill a matrix with random elements
    * @return a matrix with random elements
    */
    public static Matrix random(int m, int n) throws java.lang.IllegalArgumentException {
        if (m < 0) {
            throw new java.lang.IllegalArgumentException(
                "Row dimension must be non-negative"
            );
        }
        if (n < 0) {
            throw new java.lang.IllegalArgumentException(
                "Column dimension must be non-negative"
            );
        }
        double[][] A = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = Math.random();
            }
        }
        return new Matrix(A);
    }

    /**
     * Adding two matrices and return a matrix
     * @param  B a matrix
     * @return   the resulting matrix
     */
    public Matrix plus(Matrix B) {
        Matrix A = this;
        Matrix C = new Matrix(this.rowLength, this.colLength);
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                C.matrix[i][j] = A.matrix[i][j] + B.matrix[i][j];
            }
        }
        return C;
    }

    /**
     * Print the matrix to stdout. Line the elements up in columns with a Fortran-like `Fw.d` style format.
     * @param  w Column width.
     * @param  d Number of digits after the decimal.
     */
    public void print(int w, int d) {
        this.print(new PrintWriter(System.out, true), w, d);
    }

    /**
     * Print the matrix to the output stream. Line the elements up in columns with a Fortran-like 'Fw.d' style format.
     * @param  output Output stream.
     * @param  w      Column width.
     * @param  d      Number of digits after the decimal.
     */
    public void print(PrintWriter output, int w, int d) {
        DecimalFormat format = new DecimalFormat();
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        format.setMinimumIntegerDigits(1);
        format.setMaximumFractionDigits(d);
        format.setMinimumFractionDigits(d);
        format.setGroupingUsed(false);
        this.print(output, format, (w + 2));
    }

    /**
     * Print the matrix to stdout. Line the elements up in columns. Use the format object, and right justify within columns of width characters. Note that is the matrix is to be read back in, you probably will want to use a NumberFormat that is set to US Locale.
     * @param  format A Formatting object for individual elements.
     * @param  width  Field width for each column.
     */
    public void print(NumberFormat format, int width) {
        this.print(new PrintWriter(System.out, true), format, width);
    }

    /**
     * Print the matrix to the output stream. Line the elements up in columns. Use the format object, and right justify within columns of width characters. Note that is the matrix is to be read back in, you probably will want to use a NumberFormat that is set to US Locale.
     * @param  output The output stream.
     * @param  format A formatting object to format the matrix elements.
     * @param  width  Column width.
     */
    public void print(PrintWriter output, NumberFormat format, int width) {
        output.println();
        for (int i = 0; i < this.rowLength; i++) {
            for (int j = 0; j < this.colLength; j++) {
                String element = format.format(this.matrix[i][j]);
                int padding = Math.max(1, (width - element.length()));
                for (int k = 0; k < padding; k++) {
                    output.print(" ");
                }
                output.print(element);
            }
            output.println();
        }
        output.println();
    }
}
