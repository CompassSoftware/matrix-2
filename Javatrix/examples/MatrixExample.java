package examples;

import cbyt.matrix.Matrix;


/**
 * Some simple examples on how to use cbyt.matrix.Matrix.
 */
public class MatrixExample {

    /**
     * Main static method.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        double[][] array = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        Matrix defaultMatrix = new Matrix(array);
        System.out.println(String.format(
            "=============%nMatrixExample%n=============%n"
        ));

        System.out.println("Printing the default matrix...");
        defaultMatrix.print(1, 0);

        System.out.println("Printng the transpose of the default matrix...");
        defaultMatrix.transpose().print(1, 0);

        System.out.println("Multiplying the default matrix by 2...");
        defaultMatrix.times(2).print(1, 0);

        System.out.println("Printing a 3x3 identity matrix...");
        Matrix.identity(3, 3).print(1, 0);

        System.out.println("Printing a 10x10 identity matrix...");
        Matrix.identity(10, 10).print(1, 0);

        System.out.println("Printing a 3x3 random matrix...");
        Matrix.random(3, 3).print(1, 5);

        System.out.println("Printing a 10x10 random matrix...");
        Matrix.random(10, 10).print(1, 5);

    }

}
