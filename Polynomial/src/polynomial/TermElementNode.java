package polynomial;

import java.util.Objects;

class TermElementNode implements ListOfTerm {

  private Term term;
  private ListOfTerm restOfList;
  protected TermElementNode(){

  }

  protected TermElementNode(Term term, ListOfTerm rest) {
    this.term = term;
    this.restOfList = rest;
  }

  @Override
  public ListOfTerm add(ListOfTerm other) throws IllegalArgumentException {
    other = other.addTerm(this.term.getCoefficient(), this.term.getPower());
    return this.restOfList.add(other);
  }

  @Override
  public ListOfTerm addTerm(int coefficient, int power) {
    if (power > 0 && coefficient == 0) {
      return this;
    }
    if (this.term.getPower() == power) {
      if (this.term.getCoefficient() + coefficient == 0) {
        return this.restOfList;
      } else {
        this.term.updateCoeffcient(coefficient);
        return this;
      }
    } else if (this.term.getPower() > power) {
      this.restOfList = this.restOfList.addTerm(coefficient, power);
      return this;
    } else {
      return new TermElementNode(new Term(coefficient, power), this);
    }

  }

  @Override
  public boolean equalsElementNode(TermElementNode other) {
    return term.equals(other.term) && restOfList.equals(other.restOfList);
  }


  @Override
  public int getCoefficient(int power) {
    if (this.term.getPower() == power) {
      return this.term.getCoefficient();
    } else {
      return restOfList.getCoefficient(power);
    }
  }

  @Override
  public double evaluate(double x) {
    return this.term.getCoefficient() * Math.pow(x, this.term.getPower())
            + this.restOfList.evaluate(x);
  }

  @Override
  public int getDegree() {
    return this.term.getPower();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ListOfTerm) {
      ListOfTerm l = (ListOfTerm) o;
      return l.equalsElementNode(this);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(term, restOfList);
  }

  @Override
  public String toString() {
    String ret = toStringHelper(new StringBuilder());
    if (ret.charAt(0) == '+') {
      return ret.substring(1).trim();
    } else {
      return ret.trim();
    }
  }

  @Override
  public String toStringHelper(StringBuilder stringBuilder) {
    return this.restOfList.toStringHelper(stringBuilder.append(this.term.toString()));
  }
}
