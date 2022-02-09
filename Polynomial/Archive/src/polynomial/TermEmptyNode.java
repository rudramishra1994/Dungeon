package polynomial;

import java.util.Objects;

class TermEmptyNode implements ListOfTerm {

  @Override
  public ListOfTerm add(ListOfTerm other) throws IllegalArgumentException {
    return other;
  }

  @Override
  public ListOfTerm addTerm(int coefficient, int power) {
    return new TermElementNode(new Term(coefficient, power), this);
  }

  @Override
  public boolean equalsEmptyNode(TermEmptyNode emptyNode) {
    return true;
  }


  @Override
  public int getCoefficient(int power) {
    return 0;
  }

  @Override
  public double evaluate(double x) {
    return 0;
  }

  @Override
  public int getDegree() {
    return 0;
  }

  @Override
  public String toStringHelper(StringBuilder stringBuilder) {
    return stringBuilder.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ListOfTerm) {
      ListOfTerm l = (ListOfTerm) o;
      return l.equalsEmptyNode(this);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash();
  }

  @Override
  public String toString() {
    return "0";
  }


}
