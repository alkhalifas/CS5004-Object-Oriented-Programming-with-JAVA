/**
 * This interface represents a Polynomial implementation.
 */
public class PolynomialImpl implements Polynomial {
  private Node start;
  private Node end;

  /**
   * This is the first constructor that takes no parameters and creates a polynomial with no terms.
   * The coefficient and degree are set to zero.
   */
  public PolynomialImpl() {
    this.start = new Node(0, 0);
    this.end = start;
  }

  /**
   * This is the second constructor that takes a polynomial as a string, parses it and creates the
   * polynomial accordingly. The input string will have elements separated by a space such as "4x^3
   * +3x^1 -5""
   *
   * @param inputPolynomial This represents a polynomial in the form of a string.
   * @IllegalArgumentException Will be thrown if the polynomial length in not correct or the power
   *                           is of a negative value.
   */
  public PolynomialImpl(String inputPolynomial) throws IllegalArgumentException {
    int iterator;
    Node currentNode;
    currentNode = new Node(0, 0);

    if (!(inputPolynomial.equals(""))) {
      String[] polyRegexSep = inputPolynomial.split(" ");
      for (iterator = 0; iterator < polyRegexSep.length; iterator++) {

        ///Look up regex for splitting the string using x
        String[] termOfInterest = polyRegexSep[iterator].split("x\\^");
        String coefficient = termOfInterest[0];
        String power;

        ///////////////////////////////////////////////////////////
        if (termOfInterest.length < 2) {
          power = "0";
        } else if (termOfInterest.length > 2) {
          throw new IllegalArgumentException("Polynomial format is incorrect!");
        } else {
          power = termOfInterest[1];
        }

        int powerNumber;
        int coefficientNumber;

        try {
          coefficientNumber = Integer.parseInt(coefficient);
          powerNumber = Integer.parseInt(power);
        } catch (Exception e) {
          throw new IllegalArgumentException("Bad value");
        }

        if (powerNumber < 0) {
          throw new IllegalArgumentException("Bad value");
        }

        if (powerNumber < 0) {
          throw new IllegalArgumentException("Bad value");
        }

        if (iterator == 0) {
          this.start = new Node(coefficientNumber, powerNumber);
          currentNode = this.start;
          this.end = start;
        } else if (iterator == polyRegexSep.length - 1) {
          Node newNode = new Node(coefficientNumber, powerNumber);
          this.end = newNode;
          currentNode.setNextNode(this.end);
          this.end.setLastNode(currentNode);

        } else {
          Node newNode = new Node(coefficientNumber, powerNumber);
          newNode.setLastNode(currentNode);
          currentNode.setNextNode(newNode);
          currentNode = newNode;
          this.end = currentNode;
        }
      }

    } else {
      this.start = new Node(0, 0);
      this.end = start;
    }
  }

  /**
   * A method addTerm that takes a coefficient and a power (both integral numbers) and adds the
   * resulting term to the polynomial. (This will enable you to build a polynomial term-by-term.) It
   * should throw an IllegalArgumentException if a negative power is passed to it.
   *
   * @return a Polynomial with the added power.
   */
  @Override
  public Polynomial addTerm(int coefficient, int power) throws IllegalArgumentException {
    Node tempvar = this.end;
    Node newNode = new Node(coefficient, power);

    if (power < 0) {
      throw new IllegalArgumentException("Bad value");
    }

    if (coefficient == 0) {
      return this;
    }

    if (power > this.start.getDegree()) {
      this.start.setLastNode(newNode);
      newNode.setNextNode(this.start);
      this.start = newNode;
      return this;
    }

    while (power > tempvar.getDegree() && tempvar.getLastNode() != null) {
      tempvar = tempvar.getLastNode();
    }

    if (power == tempvar.getDegree()) {
      tempvar.settingCoefficient(coefficient);
      return this;
    }

    Node tempvarnext = tempvar.getNextNode();
    tempvarnext.setLastNode(newNode);
    tempvar.setNextNode(newNode);
    newNode.setNextNode(tempvarnext);
    newNode.setLastNode(tempvar);

    return this;
  }

  /**
   * A method removeTerm that takes a power and removes any and all terms in the polynomial with
   * that power.
   *
   * @return a Polynomial without the specified power.
   */
  @Override
  public Polynomial removeTerm(int power) {
    Node tempvar = this.end;
    while (power >= tempvar.getDegree()) {
      if (power == tempvar.getDegree()) {
        if (tempvar.getLastNode() == null) {
          this.start = tempvar.getNextNode();
          this.start.setLastNode(null);
          return this;
        }
        if (tempvar.getNextNode() == null) {
          tempvar.getLastNode().setNextNode(null);
          this.end = tempvar.getLastNode();
          return this;
        }
        Node tempvarnext = tempvar.getNextNode();
        tempvar = tempvar.getLastNode();
        tempvarnext.setLastNode(tempvar);
        tempvar.setNextNode(tempvarnext);
        return this;
      } else if (tempvar.getLastNode() == null) {
        return this;
      }
      tempvar = tempvar.getLastNode();
    }
    return this;
  }

  /**
   * A method getDegree that returns the degree of this polynomial.
   *
   * @return The degree of the polynomial.
   */
  @Override
  public int getDegree() {
    return this.start.getDegree();
  }

  /**
   * A method getCoefficient that takes a power and returns the coefficient for the term with that
   * power.
   *
   * @return longest word in the sentence.
   */
  @Override
  public int getCoefficient(int power) {
    Node tempvar = this.end;
    while (power != tempvar.getDegree() && tempvar.getLastNode() != null) {
      tempvar = tempvar.getLastNode();
    }
    if (power == tempvar.getDegree()) {
      return tempvar.getCoefficient();
    }
    return 0;
  }

  /**
   * A method evaluate that takes a double-precision decimal number and returns a double-precision
   * result.
   *
   * @return longest word in the sentence.
   */
  @Override
  public double evaluate(double x) {
    double total = 0;
    Node tempvar = this.end;

    while (tempvar != null) {
      total += tempvar.getCoefficient() * Math.pow(x, tempvar.getDegree());
      tempvar = tempvar.getLastNode();
    }

    return total;
  }

  /**
   * A method add that takes another Polynomial object and returns the polynomial obtained by adding
   * the two polynomials. Any implementation should ensure that this method does not mutate either
   * polynomial. The implementation may assume that the given Polynomial is the of the same concrete
   * class as this object; if it is a different class, the method may throw an
   * IllegalArgumentException.
   *
   * @return The combined polynomials.
   */
  @Override
  public Polynomial add(Polynomial polynomial) throws IllegalArgumentException {
    if (!(polynomial instanceof Polynomial)) {
      throw new IllegalArgumentException("Argument must be Polynomial object!");
    }

    Node clone = polynomial.getStart();
    PolynomialImpl total = new PolynomialImpl();

    while (clone != null) {
      total.addTerm(clone.getCoefficient(), clone.getDegree());
      clone = clone.getNextNode();
    }

    Node temp = this.end;

    while (temp.getLastNode() != null) {
      total.addTerm(temp.getCoefficient(), temp.getDegree());
      temp = temp.getLastNode();
    }
    total.addTerm(temp.getCoefficient(), temp.getDegree());
    return total;
  }

  /**
   * A method to polynomial a value in the form of a string.
   *
   * @return longest word in the sentence.
   */
  @Override
  public Node getStart() {
    return this.start;
  }

  /**
   * A method to return a polynomial in the form of a string.
   *
   * @return a string
   */
  @Override
  public String toString() {

    Node tempvar = this.start;
    String output = "";

    while (tempvar.getDegree() >= 0) {

      if (tempvar.getCoefficient() != 0) {
        if (tempvar.getDegree() == 0) {
          output += tempvar.getCoefficient();
        } else {
          output += tempvar.getCoefficient() + "x^" + tempvar.getDegree();
        }
      }
      if (tempvar.getNextNode() != null) {
        tempvar = tempvar.getNextNode();
        if (tempvar.getCoefficient() < 0) {
          output += " ";
        } else if (tempvar.getCoefficient() > 0) {
          output += " +";
        }
      } else {
        if (output.equals("")) {
          output += "0";
        }
        break;
      }
    }
    return output;
  }

}
