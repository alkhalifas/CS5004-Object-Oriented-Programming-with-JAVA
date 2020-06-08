/**
 * This class represents a vector object consisting of x, y, ,z and its associated methods.
 */


public class Vector3D {
  private double x;
  private double y;
  private double z;

  /**
   * Construct a VectorComponent object that takes in the x, y, and z coordinates of the vectors.
   * This part is working now.
   *
   * @param x coordinate
   * @param y coordinate
   * @param z coordinate
   */
  public Vector3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Returns the point x of the vector.
   *
   * @return a double
   */
  public double getX() {
    return this.x;
  }

  /**
   * Returns the point y of the vector.
   *
   * @return a double
   */
  public double getY() {
    return this.y;
  }

  /**
   * Returns the point z of the vector.
   *
   * @return a double
   */
  public double getZ() {
    return this.z;
  }


  /**
   * Returns a string of x, y, and z in the format (x, y, z) of the vector.
   *
   * @return a String
   */
  public String toString() {
    String firstOne = String.format("%.2f", this.x);
    String secondOne = String.format("%.2f", this.y);
    String thirdOne = String.format("%.2f", this.z);

    return "(" + firstOne + "," + secondOne + "," + thirdOne + ")";
  }


  /**
   * Returns the magnitude of your vector at hand by squaring and adding the components.
   *
   * @return a double
   */
  public double getMagnitude() {
    return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
  }


  /**
   * Returns the normalized vector without changing the original vector.
   *
   * @return a Vector3D object.
   */
  public Vector3D normalize() throws IllegalStateException {

    if ((x * y * z) < 0) {
      throw new IllegalStateException("Cannot Normalize this!");
    }
    double myMagnitude;
    double myXResult;
    double myYResult;
    double myZResult;
    double myXYZResult;

    myMagnitude = getMagnitude();

    if (myMagnitude == 0.0) {
      throw new IllegalStateException("Cannot Normalize this!");
    }

    myXResult = (x / myMagnitude);
    myYResult = (y / myMagnitude);
    myZResult = (z / myMagnitude);
    Vector3D normalizedVector = new Vector3D(myXResult, myYResult, myZResult);
    return normalizedVector;
  }

  /**
   * Returns the added vectors together by adding each component separately.
   *
   * @return a Vector3D object.
   */
  public Vector3D add(Vector3D v) {
    double newX = this.getX() + v.getX();
    double newY = this.getY() + v.getY();
    double newZ = this.getZ() + v.getZ();

    Vector3D addedVector = new Vector3D(newX, newY, newZ);
    return addedVector;
  }


  /**
   * Returns the vector multiplied by a constant c.
   *
   * @return a Vector3D object.
   */
  public Vector3D multiply(double c) {
    double newX = this.getX() * c;
    double newY = this.getY() * c;
    double newZ = this.getZ() * c;

    return new Vector3D(newX, newY, newZ);
  }


  /**
   * Returns the dot product of two vectors by multiplying each of the components and summing them.
   *
   * @return a double.
   */
  public double dotProduct(Vector3D v) {
    double newX = this.getX() * v.getX();
    double newY = this.getY() * v.getY();
    double newZ = this.getZ() * v.getZ();

    double newDotProduct = newX + newY + newZ;

    return newDotProduct;
  }

  /**
   * Returns the dot product of two vectors by multiplying each of the components and summing them.
   *
   * @return a double
   */
  public double angleBetween(Vector3D v2) throws IllegalStateException {

    if ((x * y * z) < 0) {

      throw new IllegalStateException("Cannot Calculate");
    }
    double newNumerator = this.dotProduct(v2);
    double newDenominator = this.getMagnitude() * v2.getMagnitude();
    double theta = newNumerator / newDenominator;
    double angle = Math.toDegrees(Math.acos(theta));
    return angle;
  }

}