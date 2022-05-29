/**
 * MatrixDriver.
 * Makes use of the Matrix class and its methods.
 * 
 * @author Maxwell Babey
 * @version 2021, 1.0
 */
public class MatrixDriver {

  /**
   * Drives the program.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    // Create a 2x2 array.
    Matrix mat1 = new Matrix(2, 2);
    // Populate the 2x2 array.
    int val1 = 2;
    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 2; col++) {
        mat1.setValue(row, col, val1);
        val1++;
      }
    }

    // Create a 2x2 array.
    Matrix mat2 = new Matrix(2, 2);
    // Populate the 2x2 array.
    int val2 = 1;
    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 2; col++) {
        mat2.setValue(row, col, val2);
        val2++;
      }
    }

    // Create a 2x3 array.
    Matrix mat3 = new Matrix(2, 3);
    // Populate the 2x3 array.
    int val3 = 1;
    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 3; col++) {
        mat3.setValue(row, col, val3);
        val3++;
      }
    }

    // Add mat1 and mat2.
    System.out.print(mat1.toString());
    System.out.println(" + "); // OPERATOR IF ADDING
    System.out.print(mat2.toString());
    Matrix mat4 = mat1.add(mat2);
    System.out.println(" = "); // EQUALS
    System.out.print(mat4.toString());

    System.out.println();

    // Multiply mat1 and mat3.
    System.out.print(mat1.toString());
    System.out.println(" * "); // OPERATOR IF MULTIPLYING
    System.out.print(mat3.toString());
    Matrix mat5 = mat1.multiply(mat3);
    System.out.println(" = "); // EQUALS
    System.out.println(mat5.toString());
  }

}
