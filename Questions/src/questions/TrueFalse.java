package questions;

/**
 * Represents the True/False type question.
 */
public class TrueFalse extends AbstractQuestion {


  private final String correctAnswer;

  /**
   * Creates a true false question.
   *
   * @param text          the text of the question.
   * @param correctAnswer correct answer to the question either true or false.
   */

  public TrueFalse(String text, String correctAnswer) {

    this.testQuestionText(text);

    if (correctAnswer == null) {
      throw new IllegalArgumentException("Every True False Question should have a correct "
              + "answer. It cannot be null");
    }
    correctAnswer = correctAnswer.trim();
    if (correctAnswer.trim().length() == 0) {
      throw new IllegalArgumentException("Every True/false Question should have a true or false"
              + "answer as the correct answer it cannot be empty");
    }
    if (correctAnswer.equalsIgnoreCase("true") || correctAnswer.equalsIgnoreCase("false")) {
      this.correctAnswer = correctAnswer;
    } else {
      throw new IllegalArgumentException("A True/False Question should have a correct answer as "
              + "either True or False. All other answers are invalid");
    }

    this.text = text;

  }

  @Override
  public String answer(String answer) {
    if (correctAnswer.equalsIgnoreCase(answer)) {
      return Question.CORRECT;
    } else {
      return Question.INCORRECT;
    }
  }


  @Override
  protected int compareToTrueFalse(TrueFalse trueFalse) {
    return trueFalse.getText().compareTo(this.getText());
  }


  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion abstractQuestion = (AbstractQuestion) o;
      return abstractQuestion.compareToTrueFalse(this);
    }
    return 1;
  }
}
