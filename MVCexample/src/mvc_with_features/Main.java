package mvc_with_features;

import model.IModel;
import model.Model;

/**
 * This example shows how to specify the (Key,Runnable) keyboard map using
 * shorter syntax using the ability of Java 11 to support lambda expressions.
 */
public class Main {
  /**
   * Starting point for this example.
   * 
   * @param args not used
   */
  public static void main(String[] args) {
    // Create the model
    IModel model = new Model();
    // Create the controller with the model
    Controller controller = new Controller(model);
    // Create the view
    IView view = new JFrameView("Echo a string", controller);
    // Set the view in the controller
    controller.setView(view);
  }
}
