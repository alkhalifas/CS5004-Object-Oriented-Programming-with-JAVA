/**
 * This interface represents a Node.
 */
public class Node {
  private int coefficient;
  private int degree;
  private Node next;
  private Node previous;

  /**
   * This constructor creates a Node using a coefficient, a power, and two nodes called next and
   * previous that are null upon construction.
   *
   * @param coefficient representing the coefficient infront of the x value of the polynomial.
   * @param degree      representing the power to which the x variable is raised.
   * @IllegalArgumentException is raised if the power has a negative value, the constructor will
   *                           only accept positive values.
   */
  public Node(int coefficient, int degree) throws IllegalArgumentException {
    this.coefficient = coefficient;
    if (degree < 0) {
      throw new IllegalArgumentException("bad value");
    }
    this.degree = degree;
    this.next = null;
    this.previous = null;
  }

  /**
   * This method will retrieve a node called next which represents the next node.
   */
  Node getNextNode() {
    return this.next;
  }

  /**
   * This method will set a node called next which represents the next node.
   */
  void setNextNode(Node next) {
    this.next = next;
  }

  /**
   * This method will retrieve a node called last which represents the last node.
   */
  Node getLastNode() {
    return this.previous;
  }

  /**
   * This method will set a node called last which represents the last node.
   */
  void setLastNode(Node previous) {
    this.previous = previous;
  }

  /**
   * This method will retrieve the degree.
   */
  int getDegree() {
    return this.degree;
  }

  /**
   * This method will retrieve a the coefficient.
   */
  int getCoefficient() {
    return this.coefficient;
  }

  /**
   * This method will set the coefficient by incrementing the coefficient by the coefficient.
   */
  void settingCoefficient(int coefficient) {
    this.coefficient += coefficient;
  }
}
