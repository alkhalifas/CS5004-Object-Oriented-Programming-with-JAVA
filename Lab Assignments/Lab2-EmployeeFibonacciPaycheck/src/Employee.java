/**
 * This class represents a single hourly employee.
 */
public class Employee {
  private String fullName;
  private double hoursWorked;
  private double payRate;

  /**
   * Constructs an Employee object and initializes it to the given Name and payrate.
   *
   * @param fullName    the name of this employee
   * @param hoursWorked the number of hours worked
   * @param payRate     the year of birth of this person
   */
  public Employee(String fullName, double hoursWorked, double payRate) {
    this.fullName = fullName;
    this.hoursWorked = 0;
    this.payRate = payRate;
  }

  /**
   * Adds the value of the parameter to the current number of hours.
   */
  public void addHoursWorked(double hoursWorked) {
    this.hoursWorked = hoursWorked + this.hoursWorked;
  }

  /**
   * Adds the value of the parameter to the current number of hours.
   *
   * @return total hours worked
   */
  public double getHours() {
    return this.hoursWorked;
  }

  /**
   * Resets  employees hours to zero.
   */
  public void resetHoursWorked() {
    this.hoursWorked = 0;
  }

  /**
   * returns a new PayCheck object that is initialized to the current Employee name, rate and hours
   * worked.
   *
   * @return A new paycheck object
   */
  public PayCheck getWeeklyCheck() {
    return new PayCheck(this.fullName, this.hoursWorked, this.payRate);
  }

  /**
   * Allows Employee objects to be represented by the employee name using the format $xxx.yy.
   *
   * @return String of the total pay
   */
  public String toString() {
    return this.fullName;
  }


}
