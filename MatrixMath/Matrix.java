/**
 * Matrix.
 * Stores a matrix of a specified size. Contains methods for adding and/or 
 * multiplying this Matrix with another Matrix.
 * 
 * @author Maxwell Babey
 * @version 2021, 1.0
 */
public class Matrix {

  /** The matrix represented in this object. */
  private int[][] matrix;

  /**
   * Constructor. Create a Matrix object.
   * 
   * @param rows - an int
   * @param cols - an int
   */
  public Matrix(int rows, int cols) {
    matrix = new int[rows][cols];
  }

  /**
   * Get the number of rows.
   * 
   * @return num of rows - an int.
   */
  public int getRows() {
    return matrix.length;
  }

  /**
   * Get the # of columns.
   * length is the same for any row element.
   * 
   * @return num of columns - an int
   */
  public int getCols() {
    return matrix[0].length;
  }

  /**
   * Get the value at a particular point in the array.
   * 
   * @param row - an int, the row
   * @param col - an int, the column
   * @return the value at a certain spot - an int
   */
  public int getValue(int row, int col) {
    return matrix[row][col];
  }

  /**
   * Set the value at a particular point in the array.
   * 
   * @param row - an int, the row
   * @param col - an int, the column
   * @param val - an int - the value to set
   */
  public void setValue(int row, int col, int val) {
    matrix[row][col] = val;
  }

  /**
   * toString.
   * 
   * @return the matrix, formatted like a matrix
   */
  public String toString() {
    String toMatrix = "";
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[row].length; col++) {
        toMatrix += matrix[row][col] + " ";
      }
      toMatrix += "\n";
    }
    return toMatrix;
  }

  /**
   * Adds two matrices together, provided they are of the same
   * dimensions.
   * 
   * @param other - another Matrix
   * @return the result of the addition
   * @throws IllegalArgumentException if the dimensions are not
   *                                  the same.
   */
  public Matrix add(Matrix other) {
    // Check if the matrices are the same dimensions.
    if (!(this.getRows() == other.getRows()
        && this.getCols() == other.getCols())) {
      throw new IllegalArgumentException("Dimensions do not align.");
    }
    // Create a new matrix.
    Matrix result = new Matrix(this.getRows(), this.getCols());

    // For each row, iterate through and add the columns
    // of both matrices together.
    for (int row = 0; row < this.getRows(); row++) {
      for (int col = 0; col < this.getCols(); col++) {
        result.setValue(row, col,
            (this.getValue(row, col) + other.getValue(row, col)));
      }
    }
    return result;
  }

  public Matrix multiply(Matrix other) {
    if (this.getCols() != other.getRows()) {
      throw new IllegalArgumentException("n2 != m1");
    }

    // Stores two matrices to add.
    Matrix[] toAdd = { new Matrix(this.getRows(), other.getCols()),
        new Matrix(this.getRows(), other.getCols()) };

    // The number of matrices we are multiplying together.
    for (int topi = 0; topi < toAdd.length; topi++) {
      for (int rowNum = 0; rowNum < this.getRows(); rowNum++) {
        for (int colNum = 0; colNum < other.getCols(); colNum++) {
          toAdd[topi].setValue(rowNum, colNum,
              (this.getValue(rowNum, topi)
                  * other.getValue(topi, colNum)));
        }
      }
    }
    return toAdd[0].add(toAdd[1]);
  }
}
