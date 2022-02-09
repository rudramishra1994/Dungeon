package questions;

/**
 * Represents Likert questions which allow the user to give a rating out of 5.
 */
public class Likert extends AbstractQuestion {

  private static final int LOWEST = 1;
  private static final int HIGHEST = 5;

  /**
   * Creates a Likert question.
   *
   * @param text The text of the question.
   */
  public Likert(String text) {
    this.testQuestionText(text);
    this.text = text;
  }

  @Override
  public String answer(String answer) {
    int userAnswer;
    try {
      userAnswer = Integer.parseInt(answer);
    } catch (NumberFormatException e) {
      return Question.INCORRECT;
    }
    if (userAnswer >= LOWEST && userAnswer <= HIGHEST) {
      return Question.CORRECT;
    } else {
      return Question.INCORRECT;
    }
  }


  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion abstractQuestion = (AbstractQuestion) o;
      return abstractQuestion.compareToLikert(this);
    }
    return 1;
  }

  @Override
  protected int compareToLikert(Likert likert) {
    return likert.getText().compareTo(this.getText());
  }


  @Override
  protected int compareToMultipleChoice(MultipleChoice multipleChoice) {
    return -1;
  }

  @Override
  protected int compareToMultipleSelect(MultipleSelect multiSelect) {
    return -1;
  }
}
