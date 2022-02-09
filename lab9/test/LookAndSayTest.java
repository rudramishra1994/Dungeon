import org.junit.Test;

import java.math.BigInteger;
import java.util.NoSuchElementException;

import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases to test the outcomes of the Look&SayIterator class.
 * Tests next and prev functionalities under different circumstances.
 */
public class LookAndSayTest {

  @Test
  public void simpleSeedTest() {
    RIterator<BigInteger> it = new LookAndSayIterator();
    BigInteger num1 = it.next();
    assertEquals(new BigInteger("1"), num1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSeedNegativeValue() {
    RIterator<BigInteger> it = new LookAndSayIterator(new BigInteger("-111111"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSeedNUllValue() {
    RIterator<BigInteger> it = new LookAndSayIterator(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSeedGreaterThanEnd() {
    RIterator<BigInteger> it = new LookAndSayIterator(new BigInteger("1111111111"),
        new BigInteger("1111"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSeedNegativeSmallerThanEnd() {
    RIterator<BigInteger> it = new LookAndSayIterator(new BigInteger("-1111111111"),
        new BigInteger("1111"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEndNUllValue() {
    RIterator<BigInteger> it = new LookAndSayIterator(new BigInteger("111"), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSeedEmptyValue() {
    RIterator<BigInteger> it = new LookAndSayIterator(new BigInteger(""),
        new BigInteger("99999999"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEndEmptyValue() {
    RIterator<BigInteger> it = new LookAndSayIterator(new BigInteger("111111"),
        new BigInteger(""));
  }


  @Test
  public void testNext() {
    RIterator<BigInteger> it = new LookAndSayIterator(new BigInteger("12134"),
        new BigInteger("999999999999999"));
    it.next();
    assertEquals(new BigInteger("1112111314"), it.next());
  }


  @Test(expected = NoSuchElementException.class)
  public void testNextException() {
    RIterator<BigInteger> it = new LookAndSayIterator(new BigInteger("12134"),
        new BigInteger("999999999"));
    it.next();
    it.next();
  }

  @Test
  public void testPrev() {
    RIterator<BigInteger> it = new LookAndSayIterator(new BigInteger("1311322113212221"),
        new BigInteger("999999999999999999999999"));
    assertEquals(new BigInteger("31222113112211"), it.prev());
  }

  @Test(expected = NoSuchElementException.class)
  public void testPrevForException() {
    RIterator<BigInteger> it = new LookAndSayIterator(new BigInteger("111"),
        new BigInteger("999999999999999999999999"));
    it.prev();
  }

  @Test
  public void testHasNextTrue() {
    RIterator<BigInteger> it = new LookAndSayIterator(new BigInteger("111"));
    assertTrue(it.hasNext());
  }

  @Test
  public void testHasNextFalse() {
    RIterator<BigInteger> it = new LookAndSayIterator(new BigInteger("11"), new BigInteger("20"));
    it.next();// 11
    assertFalse(it.hasNext());//21
  }


  @Test
  public void testHasPrev() {
    RIterator<BigInteger> it = new LookAndSayIterator(new BigInteger("121111"),
        new BigInteger("999999999999999999999999999999"));
    assertTrue(it.hasPrevious());
  }

  @Test
  public void testHasPrevFalse() {
    RIterator<BigInteger> it = new LookAndSayIterator(new BigInteger("12111"),
        new BigInteger("999999999999999999999999999999"));
    assertFalse(it.hasPrevious());
  }

  @Test
  public void testHasPrevFalsePrevGreaterThanEnd() {
    RIterator<BigInteger> it = new LookAndSayIterator(new BigInteger("293911"),
        new BigInteger("888888"));
    assertFalse(it.hasPrevious());
  }

}
