package questions;

import java.util.Objects;

/**
 * An abstract class which represent the common behavior of different types of questions.
 */
public abstract class AbstractQuestion implements Question {


  protected String text;

  @Override
  public abstract String answer(String answer);

  @Override
  public String getText() {
    return this.text;
  }

  @Override
  public abstract int compareTo(Question o);

  /**
   * compares an abstract question to Likert type.
   *
   * @param likert Likert question.
   * @return returns 1 0 or -1 depending on the result of the comparison.
   */
  protected int compareToLikert(Likert likert) {
    return 1;
  }


  /**
   * compares an abstract question to True/False type.
   *
   * @param trueFalse TrueFalse question.
   * @return returns 1 0 or -1 depending on the result of the comparison.
   */
  protected int compareToTrueFalse(TrueFalse trueFalse) {
    return -1;
  }


  /**
   * compares an abstract question to Multiple Choice type.
   *
   * @param multipleChoice MultipleChoice question instance.
   * @return returns 1 0 or -1 depending on the result of the comparison.
   */

  protected int compareToMultipleChoice(MultipleChoice multipleChoice) {
    return 1;
  }


  /**
   * compares an abstract question to Multiple Select type.
   *
   * @param multiSelect MultipleSelect question instance.
   * @return returns 1 0 or -1 depending on the result of the comparison.
   */
  protected int compareToMultipleSelect(MultipleSelect multiSelect) {
    return 1;
  }


  protected void testQuestionText(String text) {
    if (text == null) {
      throw new IllegalArgumentException("The Question cannot be null");
    }
    if (text.trim().length() == 0) {
      throw new IllegalArgumentException("The Question text cannot be empty");
    }
  }


  /*
    Although multiple choice and multiple  select questions are questions of different types
    multiple select question is a special case of multiple choice question where there is only
    one correct answer. Thus, we can say the same for other type of question as well.If the question
    text of two different category of question are same they can be considered as the same question.
    and equals can be used to check their equality.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Question)) {
      return false;
    }
    AbstractQuestion question = (AbstractQuestion) obj;
    return this.getText().equalsIgnoreCase(question.getText());

  }

  @Override
  public int hashCode() {
    return Objects.hash(text);
  }


}
