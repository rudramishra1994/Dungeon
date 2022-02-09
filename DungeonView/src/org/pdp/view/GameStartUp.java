package org.pdp.view;


import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;


/**
 * A form to take the game configuration from the user for a new Game.
 */
public class GameStartUp extends JFrame {

  private ParameterPanel parameterPanel;
  private  static final String startUpWindowTitle = "Game Configuration";
  private  static final int FRAME_WIDTH = 400;
  private  static final int FRAME_HEIGHT = 400;

  /**
   * Returns the values of the configuration as entered by the user.
   *
   * @return elements of the configuration panel.
   */
  public ParameterPanel getParameterPanel() {
    return parameterPanel;
  }

  /**
   * Creates an instance of the Game Configuration page.
   */
  public GameStartUp() {
    super(startUpWindowTitle);
    this.parameterPanel = ParameterPanel.createParameterPanel();
    this.parameterPanel.setLayout(new BoxLayout(this.parameterPanel, BoxLayout.Y_AXIS));
    this.parameterPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    this.setResizable(false);
    this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().setLayout(new GridLayout(1, 2));
    this.getContentPane().add(parameterPanel);
    this.setVisible(true);
  }
}
