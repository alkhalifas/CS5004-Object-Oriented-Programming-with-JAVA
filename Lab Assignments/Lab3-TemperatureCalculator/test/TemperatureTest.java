import org.junit.Before;
import org.junit.Test;

import cs5004.temperature.Temperature;
import cs5004.temperature.CelsiusTemperature;
import cs5004.temperature.FahrenheitTemperature;


import static org.junit.Assert.assertEquals;

/**
 * Tests for Temperature, both Celsius and Fahrenheit representations.
 */
public class TemperatureTest {

  private Temperature cTemp;
  private Temperature cTempTwo;
  private Temperature cTempThree;
  private Temperature cTempFour;
  private Temperature cTempFive;
  private Temperature cTempSix;
  private Temperature cTempSeven;
  private Temperature cTempEight;
  private Temperature cTempNine;

  private Temperature fTemp;
  private Temperature fTempTwo;
  private Temperature fTempThree;
  private Temperature fTempFour;
  private Temperature fTempFive;
  private Temperature fTempSix;
  private Temperature fTempSeven;
  private Temperature fTempEight;


  @Before
  public void init() {
    cTemp = new CelsiusTemperature(100);
    cTempTwo = new CelsiusTemperature(0);
    cTempThree = new CelsiusTemperature(-50);
    cTempFour = new CelsiusTemperature(-0.0001);
    cTempFive = new CelsiusTemperature(100000);
    cTempSix = new CelsiusTemperature(0.0001);
    cTempSeven = new CelsiusTemperature(1000, true);
    cTempEight = new CelsiusTemperature(1000, false);
    cTempNine = new CelsiusTemperature(-100, false);


    fTemp = new FahrenheitTemperature(0, false);
    fTempTwo = new FahrenheitTemperature(-50);
    fTempThree = new FahrenheitTemperature(100, true);
    fTempFour = new FahrenheitTemperature(-0.0001, true);
    fTempFive = new FahrenheitTemperature(1000000, true);
    fTempSix = new FahrenheitTemperature(0.0001, true);
    fTempSeven = new FahrenheitTemperature(1000, false);
    fTempEight = new FahrenheitTemperature(1000);


  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTempLow() {
    new FahrenheitTemperature(-1000);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTempVeryLow() {
    new FahrenheitTemperature(-10000000);
  }

  @Test
  public void testInCelsius() {
    assertEquals(100, cTemp.inCelsius(), 0.001);
    assertEquals(0.0, cTempTwo.inCelsius(), 0.001);
    assertEquals(-50, cTempThree.inCelsius(), 0.001);
    assertEquals(-0.0001, cTempFour.inCelsius(), 0.001);
    assertEquals(0.00, fTemp.inCelsius(), 0.001);
    assertEquals(-45.555, fTempTwo.inCelsius(), 0.001);
    assertEquals(1000000, fTempFive.inCelsius(), 0.001);
    assertEquals(1000, fTempSeven.inCelsius(), 0.001);
    assertEquals(537.778, fTempEight.inCelsius(), 0.001);
    assertEquals(537.778, cTempSeven.inCelsius(), 0.001);
    assertEquals(537.778, cTempEight.inCelsius(), 0.001);
    assertEquals(-73.333, cTempNine.inCelsius(), 0.001);

  }

  @Test
  public void testInF() {
    assertEquals(212.0, cTemp.inFahrenheit(), 0.001);
    assertEquals(32.0, cTempTwo.inFahrenheit(), 0.001);
    assertEquals(-58, cTempThree.inFahrenheit(), 0.001);
    assertEquals(31.999, cTempFour.inFahrenheit(), 0.001);
    assertEquals(180032.0, cTempFive.inFahrenheit(), 0.001);
    assertEquals(32.0001, cTempSix.inFahrenheit(), 0.001);
    assertEquals(32.0, fTemp.inFahrenheit(), 0.001);
    assertEquals(-50.0, fTempTwo.inFahrenheit(), 0.001);
    assertEquals(32.0, fTemp.inFahrenheit(), 0.001);

  }

  @Test
  public void testInKelvin() {
    assertEquals(373.15, cTemp.inKelvin(), 0.001);
    assertEquals(273.149, cTempTwo.inKelvin(), 0.001);
    assertEquals(223.149, cTempThree.inKelvin(), 0.001);
    assertEquals(273.149, cTempFour.inKelvin(), 0.001);
    assertEquals(273.149, fTemp.inKelvin(), 0.001);
    assertEquals(227.594, fTempTwo.inKelvin(), 0.001);
    assertEquals(1273.149, fTempSeven.inKelvin(), 0.001);
    assertEquals(810.927, cTempSeven.inKelvin(), 0.001);
    assertEquals(810.927, fTempEight.inKelvin(), 0.001);
    assertEquals(810.927, cTempEight.inKelvin(), 0.001);
    assertEquals(100273.149, cTempFive.inKelvin(), 0.001);
    assertEquals(273.150, cTempSix.inKelvin(), 0.001);


  }


  @Test
  public void testObservers() {
    assertEquals(100, cTemp.inCelsius(), 0.001);
    assertEquals(212, cTemp.inFahrenheit(), 0.001);
    assertEquals(373.15, cTemp.inKelvin(), 0.001);

  }


  @Test
  public void testAdd() {
    assertEquals("200.0° Celsius", cTemp.add(cTemp).toString());
    assertEquals("50.0° Celsius", cTemp.add(cTempThree).toString());
    assertEquals("50.0° Celsius", cTempThree.add(cTemp).toString());
    assertEquals("64.0° Fahrenheit", fTemp.add(fTemp).toString());
    assertEquals("244.0° Fahrenheit", fTemp.add(fTempThree).toString());
    assertEquals("244.0° Fahrenheit", fTempThree.add(fTemp).toString());
    assertEquals("100.0° Celsius", cTemp.add(fTemp).toString());

  }

  @Test
  public void testToString() {
    assertEquals("100.0° Celsius", cTemp.toString());
    assertEquals("537.8° Celsius", cTempSeven.toString());
    assertEquals("537.8° Celsius", cTempEight.toString());
    assertEquals("32.0° Fahrenheit", fTemp.toString());
    assertEquals("32.0° Fahrenheit", fTempSix.toString());
    assertEquals("1832.0° Fahrenheit", fTempSeven.toString());

  }

  @Test
  public void testCompareTo() {
    assertEquals(1, cTemp.compareTo(cTempTwo));
    assertEquals(-1, cTempThree.compareTo(cTemp));
    assertEquals(-1, cTempThree.compareTo(cTempFour));
    assertEquals(0, cTempSeven.compareTo(cTempEight));
    assertEquals(1, fTemp.compareTo(fTempTwo));
    assertEquals(-1, fTemp.compareTo(fTempThree));
    assertEquals(1, fTempThree.compareTo(fTempFour));
    assertEquals(1, fTempSeven.compareTo(fTempEight));
    assertEquals(0, fTempSeven.compareTo(fTempSeven));
    assertEquals(1, fTempSeven.compareTo(cTempSeven));
    assertEquals(-1, cTempSeven.compareTo(fTempSeven));

  }

}
