/**
 * This class represents a paycheck using a fullname, hoursworked, rate, and total.
 * send keith an email and ask about the issue oif having instance variables
 */
public class PayCheck {
  private String fullName;
  private double hoursWorked;
  private double payRate;
  private double totalPay;

  /**
   * Constructs an Employee object and initializes it to the given Name and pay rate.
   *
   * @param fullName    the name of this employee
   * @param hoursWorked the number of hours worked
   * @param payRate     the year of birth of this person
   */
  public PayCheck(String fullName, double hoursWorked, double payRate) {
    this.hoursWorked = hoursWorked;
    this.payRate = payRate;
    this.fullName = fullName;

    if (hoursWorked > 40) {
      double overTime = hoursWorked - 40;
      double overTimePay = overTime * 1.5;
      this.totalPay = overTimePay + (40 * payRate);
    }
    else if (hoursWorked <= 40) {
      this.totalPay = payRate * hoursWorked;
    }
  }

  /**
   * Returns payRate.
   *
   * @return payRate
   */
  public double getPayRate() {
    return this.payRate;
  }

  /**
   * Returns hoursWorked.
   *
   * @return hoursWorked
   */
  public double getHoursWorked() {
    return this.hoursWorked;
  }

  /**
   * Returns fullName.
   *
   * @return fullName
   */
  public String getFullName() {
    return this.fullName;
  }

  /**
   * Calculates and returns the total pay for the week.
   *
   * @return total pay
   */
  public double getTotalPay() {
    return this.totalPay;
  }
  // account for overtime

  /**
   * Allows paycheck object to be represented by the totalPay (in proper dollars/cents) using the
   * format $xxx.yy.
   *
   * @return String of the total pay
   */
  public String toString() {
    String str;
    str = String.format("$%.2f", this.totalPay);
    return str;
  }

}