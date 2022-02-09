package test;

import org.junit.Test;

import polynomial.Polynomial;
import polynomial.PolynomialImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class is used to text the implementation of the polynomial recursive union.
 */
public class TestPolynomialImpl {

  @Test
  public void TestPolynomialNoArgument() {
    Polynomial polynomial = new PolynomialImpl();
    assertEquals("0", polynomial.toString());
  }

  @Test
  public void TestPolynomial() {
    Polynomial polynomial = new PolynomialImpl("4x^3 +3x^1 -5");
    assertEquals("4x^3 +3x^1 -5", polynomial.toString());
  }

  @Test
  public void TestPolynomialSamePower() {
    Polynomial polynomial = new PolynomialImpl("4x^4 +3x^4 -5x^4");
    assertEquals("2x^4", polynomial.toString());
  }

  @Test
  public void TestPolynomialForConstants() {
    Polynomial polynomial = new PolynomialImpl("10");
    assertEquals("10", polynomial.toString());
  }

  @Test
  public void TestPolynomialForMultipleTermsWithSamePower() {
    Polynomial polynomial = new PolynomialImpl("-3x^4 -2x^5 +5 +11x^1");
    assertEquals("-2x^5 -3x^4 +11x^1 +5", polynomial.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestPolynomialWithNullString() {
    Polynomial polynomial = new PolynomialImpl(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestPolynomialWithEmptyString() {
    Polynomial polynomial = new PolynomialImpl("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestPolynomialWithBlankString() {
    Polynomial polynomial = new PolynomialImpl("    ");
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestPolynomialWithInvalidString() {
    Polynomial polynomial = new PolynomialImpl("-3x^4 -2x^5 +5+11x^1");
  }

  @Test
  public void TestGetCoefficientForExistingTerm() {
    Polynomial polynomial = new PolynomialImpl("-3x^4 -2x^5 +5 +11x^1");
    assertEquals(-2, polynomial.getCoefficient(5));
  }

  @Test
  public void TestGetCoefficientForNonExistingTerm() {
    Polynomial polynomial = new PolynomialImpl("-3x^4 -2x^5 +5 +11x^1");
    assertNotEquals(-2, polynomial.getCoefficient(3));
  }

  @Test
  public void TestEvaluateForPositiveX() {
    Polynomial polynomial = new PolynomialImpl("2x^2 +3x^1 +1");
    assertEquals(15, polynomial.evaluate(2), 0.00001);
  }

  @Test
  public void TestEvaluateForNegativeX() {
    Polynomial polynomial = new PolynomialImpl("2x^2 +3x^1 +1");
    assertEquals(3, polynomial.evaluate(-2), 0.00001);
  }

  @Test
  public void TestEvaluateForZero() {
    Polynomial polynomial = new PolynomialImpl("2x^2 +3x^1 +1");
    assertEquals(1, polynomial.evaluate(0), 0.00001);
  }

  @Test
  public void TestGetDegree() {
    Polynomial polynomial = new PolynomialImpl("-3x^4 -2x^5 +5 +11x^1");
    assertEquals(5, polynomial.getDegree());
  }

  @Test
  public void TestGetDegreeForNoArgPolynomial() {
    Polynomial polynomial = new PolynomialImpl();
    assertEquals(0, polynomial.getDegree());
  }

  @Test
  public void TestGetDegreeForContant() {
    Polynomial polynomial = new PolynomialImpl("102");
    assertEquals(0, polynomial.getDegree());
  }

  @Test
  public void TestAddPolynomials() {
    Polynomial polynomial1 = new PolynomialImpl("2x^2 +3x^1 +1");
    Polynomial polynomial2 = new PolynomialImpl("4x^2 +6x^1 +1");

    assertEquals("6x^2 +9x^1 +2", polynomial1.add(polynomial2).toString());
  }

  @Test
  public void TestAddPolynomialsWithDifferentPowers() {
    Polynomial polynomial1 = new PolynomialImpl("2x^2 +3x^1 +1");
    Polynomial polynomial2 = new PolynomialImpl("4x^4 +6x^3 +1");

    assertEquals("4x^4 +6x^3 +2x^2 +3x^1 +2", polynomial1.add(polynomial2).toString());
  }

  @Test
  public void TestAddPolynomialsWithSomeDifferentPowers() {
    Polynomial polynomial1 = new PolynomialImpl("-5x^5 -2x^2 +3x^1 +1");
    Polynomial polynomial2 = new PolynomialImpl("4x^4 +6x^3 +1x^1");
    String str = polynomial1.add(polynomial2).toString();
    assertEquals("-5x^5 +4x^4 +6x^3 -2x^2 +4x^1 +1", str);
  }

  @Test
  public void TestAddPolynomialsWithOneZeroPolynomial() {
    Polynomial polynomial1 = new PolynomialImpl();
    Polynomial polynomial2 = new PolynomialImpl("4x^4 +6x^3 +1x^1");
    String str = polynomial1.add(polynomial2).toString();
    assertEquals("4x^4 +6x^3 +1x^1", str);
  }

  @Test
  public void TestAddPolynomialsWithBothZeroPolynomial() {
    Polynomial polynomial1 = new PolynomialImpl();
    Polynomial polynomial2 = new PolynomialImpl();
    String str = polynomial1.add(polynomial2).toString();
    assertEquals("0", str);
  }


  @Test
  public void TestIsSamePolynomial() {
    Polynomial polynomial1 = new PolynomialImpl("2x^2 +3x^1 +1");
    Polynomial polynomial2 = new PolynomialImpl("2x^2 +5x^1 -2x^1 +1");
    assertTrue(polynomial1.isSame(polynomial2));

  }

  @Test
  public void TestIsSamePolynomialNotTrue() {
    Polynomial polynomial1 = new PolynomialImpl("2x^2 +3x^1 +1");
    Polynomial polynomial2 = new PolynomialImpl("2x^3 +5x^4 -2x^6 +1x^1");
    assertFalse(polynomial1.isSame(polynomial2));

  }


  @Test
  public void TestIsSamePolynomialNotTrue2() {
    Polynomial polynomial1 = new PolynomialImpl();
    Polynomial polynomial2 = new PolynomialImpl("2x^3 +5x^4 -2x^6 +1x^1");
    assertFalse(polynomial1.isSame(polynomial2));

  }

  @Test
  public void TestToString() {
    Polynomial polynomial1 = new PolynomialImpl("2x^2 +3x^1 +1");
    assertEquals("2x^2 +3x^1 +1", polynomial1.toString());
  }


  @Test
  public void TestToStringForSingleTerm() {
    Polynomial polynomial = new PolynomialImpl("1x^1");
    assertEquals('1', polynomial.toString().charAt(0));
  }


  @Test
  public void TestSamePower() {
    Polynomial polynomial = new PolynomialImpl();
    polynomial.addTerm(10, 1);
    polynomial.addTerm(5, 1);
    assertEquals("15x^1", polynomial.toString());

  }

  @Test
  public void TestAddTerm() {
    Polynomial polynomial = new PolynomialImpl();
    polynomial.addTerm(10, 1);
    polynomial.addTerm(5, 2);
    assertEquals("5x^2 +10x^1", polynomial.toString());

  }

}
