/**
 * This interface represents a Polynomial.
 */
public interface Polynomial {

  /**
   * A method addTerm that takes a coefficient and a power (both integral numbers) and adds the
   * resulting term to the polynomial. (This will enable you to build a polynomial term-by-term.) It
   * should throw an IllegalArgumentException if a negative power is passed to it.
   *
   * @return a Polynomial with the added power.
   */
  Polynomial addTerm(int coefficient, int power);

  /**
   * A method removeTerm that takes a power and removes any and all terms in the polynomial with
   * that power.
   *
   * @return a Polynomial without the specified power.
   */
  Polynomial removeTerm(int power);

  /**
   * A method getDegree that returns the degree of this polynomial.
   *
   * @return The degree of the polynomial.
   */
  int getDegree();

  /**
   * A method getCoefficient that takes a power and returns the coefficient for the term with that
   * power.
   *
   * @return longest word in the sentence.
   */
  int getCoefficient(int power);

  /**
   * A method evaluate that takes a double-precision decimal number and returns a double-precision
   * result.
   *
   * @return longest word in the sentence.
   */
  double evaluate(double x);

  /**
   * A method add that takes another Polynomial object and returns the polynomial obtained by adding
   * the two polynomials. Any implementation should ensure that this method does not mutate either
   * polynomial. The implementation may assume that the given Polynomial is the of the same concrete
   * class as this object; if it is a different class, the method may throw an
   * IllegalArgumentException.
   *
   * @return The combined polynomials.
   */
  Polynomial add(Polynomial other);

  /**
   * A method to return a polynomial in the form of a string.
   *
   * @return a string
   */
  String toString();

  /**
   * A method to polynomial a value in the form of a string.
   *
   * @return longest word in the sentence.
   */
  Node getStart();

}
