package questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Multiselect represents questions with 1 or more correct answers from the option.
 */
public class MultipleSelect extends AbstractQuestion {

  private List<String> correctAnswer;
  private static final int MIN = 3;
  private static final int MAX = 8;


  /**
   * creates a question with a text, a list of correct answers and the options of the question.
   *
   * @param text          test of the question:String
   * @param correctAnswer correct answers to the questions:String
   * @param options       the options give to the user to choose answers from:String
   */
  public MultipleSelect(String text, String correctAnswer, String... options) {

    this.testQuestionText(text);
    if (correctAnswer == null) {
      throw new IllegalArgumentException("Every Multiple Select Question should have at least 1"
              + " correct answer. It cannot be null");
    }
    correctAnswer = correctAnswer.trim();
    String[] expectedAnswers = correctAnswer.split(" ", -1);
    if (correctAnswer.length() == 0) {
      throw new IllegalArgumentException("Every Multiple choice answer should have 1 "
              + "correct answer.Please check your correct answer");
    }
    if (expectedAnswers.length > options.length) {
      throw new IllegalArgumentException("Number of Correct answers "
              + "cannot be more than the options provided");
    }
    this.correctAnswer = new ArrayList<>();

    for (String s : expectedAnswers) {
      try {
        Integer.parseInt(s);
        this.correctAnswer.add(s);
      } catch (NumberFormatException e) {
        throw new NumberFormatException(String.format("The option correct answer should be "
                + "between 1 and %s", options.length));
      }
    }

    if (options.length < MIN) {
      throw new IllegalArgumentException("The number of options cannot be less than 3");
    } else if (options.length > MAX) {
      throw new IllegalArgumentException("The number of options cannot be more than 8");
    }

    this.text = text;
    Collections.sort(this.correctAnswer);
  }

  @Override
  public String answer(String answer) {
    answer = answer.trim();
    String[] userAnswers = answer.split(" ");
    for (String s : userAnswers) {
      if (!this.correctAnswer.contains(s)) {
        return Question.INCORRECT;
      }
    }
    if (userAnswers.length == this.correctAnswer.size()) {
      return Question.CORRECT;
    } else {
      return Question.INCORRECT;
    }

  }


  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion abstractQuestion = (AbstractQuestion) o;
      return abstractQuestion.compareToMultipleSelect(this);
    }
    return 1;
  }


  @Override
  protected int compareToMultipleChoice(MultipleChoice multipleChoice) {
    return -1;
  }

  @Override
  protected int compareToMultipleSelect(MultipleSelect multiSelect) {
    return multiSelect.getText().compareTo(this.getText());
  }
}
