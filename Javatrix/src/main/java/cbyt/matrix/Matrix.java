package cbyt.matrix;

import java.io.Serializable;
import java.lang.Cloneable;
import java.lang.IllegalArgumentException;


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
            this.matrix = A;
            this.colLength = this.matrix.length;
            this.rowLength = ((this.colLength > 0) ? this.matrix[0].length : 0);
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
        if(m < 0)
            throw new java.lang.IllegalArgumentException(
                "Row dimension must be non-negative"
            );
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
    public double[][] getMatrix(){
        return this.matrix;
    }

    /**
     * Getter for the row length internal.
     * @return   Int: Value of rowLength
     */
    public int getRowLength(){
        return this.rowLength;
    }

    /**
     * Getter for the col length internal.
     * @return    Int: Value fo colLength
     */
    public int getColLength(){
        return this.colLength;
    }

    /**
    * Make a deep copy of a matrix.
    * @return   Matrix: a copy of original matrix.
    */
    public Matrix copy() {

    }
    
    /*
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

}
