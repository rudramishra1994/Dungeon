package org.animal.primates;

enum Sex {
  MALE("Male"),
  FEMALE("Female");


  private String value;
  public String getValue() {
    return value;
  }


  Sex(String sex) {
    this.value = sex;
  }

}
