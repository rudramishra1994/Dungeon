package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import questions.Likert;
import questions.MultipleChoice;
import questions.MultipleSelect;
import questions.Question;
import questions.TrueFalse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the validity of the questions and their answers.
 */
public class QuestionTest {

  @Test
  public void testLikertCreation() {
    Question question = new Likert("How will you rate Khoury colleges curriculum?");
    assertEquals("How will you rate Khoury colleges curriculum?", question.getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLikertForNullText() {
    Question question = new Likert(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLikertForEmptyText() {
    Question question = new Likert(" ");
  }

  @Test
  public void testLikertForWrongAnswer() {
    Question question = new Likert("How will you rate Khoury colleges curriculum?");
    assertEquals("incorrect", question.answer("7").toLowerCase());
  }

  @Test
  public void testTrueFalseCreation() {
    Question question = new TrueFalse("Is Mercury closest planet to the sun?", "True");
    assertEquals("correct", question.answer("true").toLowerCase());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTrueFalseForNullText() {
    Question question = new TrueFalse(null, "True");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTrueFalseForEmptyText() {
    Question question = new TrueFalse(" ", "False");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTrueFalseForNullAnswer() {
    Question question = new TrueFalse("Is Mercury closest planet to the sun?", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTrueFalseForEmptyAnswer() {
    Question question = new TrueFalse("Is Mercury closest planet to the sun?", " ");
  }

  @Test
  public void testTrueFalseForWrongAnswer() {
    Question question = new TrueFalse("Is Mercury closest planet to the sun?", "True");
    assertEquals("incorrect", question.answer("false").toLowerCase());
    assertEquals("incorrect", question.answer("abc").toLowerCase());

  }


  @Test
  public void testSortingOfQuestions() {
    Question question1 = new TrueFalse("A", "True");
    Question question2 = new TrueFalse("B", "False");
    Question question3 = new MultipleSelect("C", "1 2", "cat", "dog", "rat");
    Question question4 = new MultipleSelect("D", "2 3", "Crow", "eagle", "hawk", "duck");
    Question question5 = new MultipleChoice("E", "1", "Crow", "eagle", "hawk", "duck");
    Question question6 = new MultipleChoice("F", "3", "cat", "dog", "rat");
    Question question7 = new Likert("G");
    Question question8 = new Likert("H");

    List<Question> expectedList = new ArrayList<>(Arrays.asList(question1, question2, question5,
            question6, question3, question4, question7, question8));
    List<Question> actualList = new ArrayList<>(Arrays.asList(question2, question1, question6,
            question7, question8, question3, question4, question5));
    Collections.sort(actualList);
    assertTrue(expectedList.equals(actualList));
  }


  @Test
  public void testMultipleChoiceCreation() {
    Question question = new MultipleChoice("Test question 1", "1", "a", "b", "c", "d");
    assertEquals("correct", question.answer("1").toLowerCase());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceCreationNullText() {
    Question question = new MultipleChoice(null, "1", "a", "b", "c", "d");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceCreationEmptyText() {
    Question question = new MultipleChoice(" ", "1", "a", "b", "c", "d");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceCreationEmptyCorrectAnswer() {
    Question question = new MultipleChoice("A", " ", "a", "b", "c", "d");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceCreationNullCorrectAnswer() {
    Question question = new MultipleChoice("A", null, "a", "b", "c", "d");
  }

  @Test
  public void testMultipleChoiceWrongAnswer() {
    Question question = new MultipleChoice("A", "2", "a", "b", "c", "d");
    assertEquals("incorrect", question.answer("3").toLowerCase());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceCreationOutsideOptionCorrectAnswer() {
    Question question = new MultipleChoice("A", "5", "a", "b", "c", "d");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceCreationLessThanThreeOptions() {
    Question question = new MultipleChoice("A", "5", "a", "b");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceCreationMoreThanEightOptions() {
    Question question = new MultipleChoice("A", "5", "a", "b", "d", "e",
            "f", "g", "h", "k", "l", "m", "n");
  }

  @Test(expected = NumberFormatException.class)
  public void testMultipleChoiceIncorrectTypeOfCorrectAnswer() {
    Question question = new MultipleChoice("A", "sngj", "a", "b", "d",
            "e", "f", "g", "h", "k");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceIncorrectNumberOfCorrectAnswer() {
    Question question = new MultipleChoice("A", "1 2", "a", "b", "d",
            "e", "f", "g", "h", "k");
  }


  @Test
  public void testMultipleSelectCreation() {
    Question question = new MultipleSelect("C", "1 2", "cat", "dog", "rat");
    assertEquals("correct", question.answer("1 2").toLowerCase());
    assertEquals("incorrect", question.answer("2 3").toLowerCase());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleSelectNullText() {
    Question question = new MultipleSelect(null, "1 2", "cat", "dog", "rat");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleSelectEmptyText() {
    Question question = new MultipleSelect(" ", "1 2", "cat", "dog", "rat");
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMultipleSelectNullCorrectAnswer() {
    Question question = new MultipleSelect("A", null, "cat", "dog", "rat");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleSelectEmptyCorrectAnswer() {
    Question question = new MultipleSelect("A", " ", "cat", "dog", "rat");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleSelectOverflowCorrectAnswer() {
    Question question = new MultipleSelect("A", "1 2 3 4", "cat", "dog", "rat");
  }


  @Test
  public void testMultipleSelectWrongAnswer() {
    Question question = new MultipleSelect("A", "1 18", "cat", "dog", "rat", "tiger");
    assertEquals("incorrect", question.answer("1 19").toLowerCase());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleSelectCreationLessThanThreeOptions() {
    Question question = new MultipleSelect("A", "1 2", "a", "b");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleSelectCreationMoreThanEightOptions() {
    Question question = new MultipleSelect("A", "5 6", "a", "b", "d", "e", "f",
            "g", "h", "k", "l", "m", "n");
  }

  @Test
  public void testMultipleSelectIncorrectNumberOfCorrectAnswer() {
    Question question = new MultipleSelect("A", "4 5 6", "a", "b", "d", "e", "f", "g", "h", "k");
    assertEquals("incorrect", question.answer("4 5").toLowerCase());
    assertEquals("incorrect", question.answer("4 5 6 7").toLowerCase());
  }

  @Test(expected = NumberFormatException.class)
  public void testMultipleSelectIncorrectTypeOfCorrectAnswer() {
    Question question = new MultipleSelect("A", "sngj ejghs", "a", "b", "d",
            "e", "f", "g", "h", "k");
  }


}