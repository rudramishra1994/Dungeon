package lookandsay;

import java.math.BigInteger;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * It's an iterator which returns the next term in the sequence or the term before the current term.
 */
public class LookAndSayIterator implements RIterator<BigInteger> {
  private BigInteger seed;
  private final BigInteger end;
  private boolean firstNext = false;
  private static final BigInteger DEFAULT_SEED = new BigInteger("1");
  private static final BigInteger DEFAULT_END = new BigInteger("9".repeat(100));

  /**
   * Construct an iterator object which is used to generate the terms of the look&say sequence.
   *
   * @param seed the start term of the sequence.
   * @param end  the max value of the sequence.
   */
  public LookAndSayIterator(BigInteger seed, BigInteger end) {

    if (seed == null || end == null) {
      throw new IllegalArgumentException("Seed or Maximum value of the iterator cannot be null");
    }

    if (seed.toString().isEmpty() || end.toString().isEmpty()) {
      throw new IllegalArgumentException("Seed/End cannot be blank/empty");
    }

    if (seed.toString().contains("0")) {
      throw new IllegalArgumentException("Seed cannot contain zero");
    }
    if (seed.longValue() < 0) {
      throw new IllegalArgumentException("Seed cannot be negative");
    }
    if (seed.compareTo(end) > 0) {
      throw new IllegalArgumentException("Seed cannot be more than maximum allowed value");
    }
    this.seed = seed;
    this.end = end;
  }

  /**
   * Creates a look&say sequence iterator with a given seed and default end value.
   *
   * @param seed seed of the iterator.
   */
  public LookAndSayIterator(BigInteger seed) {
    this(seed, DEFAULT_END);
  }

  /**
   * Creates a look&say iterator with a default seed and end value.
   */
  public LookAndSayIterator() {
    this(DEFAULT_SEED, DEFAULT_END);
  }

  @Override
  public boolean hasPrevious() {
    if (seed.toString().length() % 2 != 0) {
      return false;
    } else {
      return hasPreviousHelper().compareTo(this.end) <= 0;
    }

  }

  private BigInteger hasPreviousHelper() {
    char[] ch = this.seed.toString().toCharArray();
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < ch.length; i += 2) {
      String s = String.valueOf(ch[i + 1]);
      b.append(IntStream.range(0, Integer.parseInt(String.valueOf(ch[i])))
          .mapToObj(j -> s).collect(Collectors.joining("")));
    }
    return new BigInteger(b.toString());
  }

  @Override
  public BigInteger prev() {
    if (hasPrevious()) {
      this.seed = hasPreviousHelper();
      return this.seed;
    } else {
      throw new NoSuchElementException("Prev does not exist");
    }

  }

  @Override
  public boolean hasNext() {
    // generate next > end? (w/o mutating)
    return hasNextHelper().compareTo(this.end) <= 0;
  }

  private BigInteger hasNextHelper() {
    StringBuilder b = new StringBuilder();

    if (!firstNext) {
      return this.seed;
    }
    String number = this.seed.toString();
    char repeat = this.seed.toString().charAt(0);
    number = number.substring(1) + " ";
    int counter = 1;

    for (char actual : number.toCharArray()) {
      if (actual != repeat) {
        b.append(counter).append(repeat);
        counter = 1;
        repeat = actual;
      } else {
        counter += 1;
      }
    }
    return new BigInteger(b.toString());
  }

  @Override
  public BigInteger next() {
    if (hasNext()) {
      if (!firstNext) {
        firstNext = true;
        return this.seed;
      }
      this.seed = hasNextHelper();
      return this.seed;
    } else {
      throw new NoSuchElementException("Next element does not exist");
    }
  }
}
