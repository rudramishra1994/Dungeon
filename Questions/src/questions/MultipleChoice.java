package questions;

/**
 * Represents multiple choice question with a single correct answer.
 */
public class MultipleChoice extends AbstractQuestion {

  private final String correctAnswer;
  private static final int MIN = 3;
  private static final int MAX = 8;

  /**
   * creates a multiple choice question.
   *
   * @param text          text of the question:String.
   * @param correctAnswer the correct answer to the question:String.
   * @param options       options given to the user to select answer from.
   */
  public MultipleChoice(String text,
                        String correctAnswer,
                        String... options) {


    this.testQuestionText(text);
    if (correctAnswer == null) {
      throw new IllegalArgumentException("Every Multiple Choice Question should have a correct "
              + "answer. It cannot be null");
    }
    correctAnswer = correctAnswer.trim();
    if (correctAnswer.length() == 0) {
      throw new IllegalArgumentException("Every Multiple choice answer should have 1 "
              + "correct answer.Please check your correct answer");
    }
    if (correctAnswer.contains(" ")) {
      throw new IllegalArgumentException("Multiple choice has only one correct answer.Please check"
              + "your correct answer");
    }
    int corrAnswer;

    try {
      corrAnswer = Integer.parseInt(correctAnswer);
    } catch (NumberFormatException e) {
      throw new NumberFormatException(String.format("The option correct answer should be "
              + "between 1 and %s", options.length));
    }
    if (options.length < MIN) {
      throw new IllegalArgumentException("The number of options cannot be less than 3");
    } else if (options.length > MAX) {
      throw new IllegalArgumentException("The number of options cannot be more than 8");
    }

    if (corrAnswer < 1 || corrAnswer > options.length) {
      throw new IllegalArgumentException(String.format("The correct answer must be an option "
              + "between 1 and %s", options.length));
    }
    this.text = text;
    this.correctAnswer = correctAnswer;

  }


  @Override
  public String answer(String answer) {
    if (answer.equalsIgnoreCase(correctAnswer)) {
      return Question.CORRECT;
    } else {
      return Question.INCORRECT;
    }
  }

  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion abstractQuestion = (AbstractQuestion) o;
      return abstractQuestion.compareToMultipleChoice(this);
    }
    return 1;
  }


  @Override
  protected int compareToMultipleChoice(MultipleChoice multipleChoice) {
    return multipleChoice.getText().compareTo(this.getText());
  }


}
