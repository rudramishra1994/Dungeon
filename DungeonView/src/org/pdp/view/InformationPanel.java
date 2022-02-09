package org.pdp.view;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * Represents the information panel of the game.
 * The location details ,player information and all related game cues are displayed here.
 */
class InformationPanel extends JPanel {


  JTextArea locInfo;
  JTextArea playerInfo;
  JTextArea gameStatus;

  /**
   * Creates the information Panel.
   */

  protected InformationPanel() {


    locInfo = new JTextArea();
    locInfo.setBorder(BorderFactory.createTitledBorder("Location Information"));
    locInfo.setLineWrap(true);
    locInfo.setEditable(false);
    locInfo.setPreferredSize(new Dimension(this.getWidth(), 100));


    playerInfo = new JTextArea();
    playerInfo.setBorder(BorderFactory.createTitledBorder("PLayer Information"));
    playerInfo.setLineWrap(true);
    playerInfo.setEditable(false);
    playerInfo.setPreferredSize(new Dimension(this.getWidth(), 100));



    gameStatus = new JTextArea();
    gameStatus.setBorder(BorderFactory.createTitledBorder("Game Status"));
    gameStatus.setLineWrap(true);
    gameStatus.setEditable(false);
    gameStatus.setPreferredSize(new Dimension(this.getWidth(), 100));

    add(locInfo);
    add(playerInfo);
    add(gameStatus);

  }

  /**
   * Updates the player information whenever the status of the player changes.
   *
   * @param s the information to be displayed
   */
  public void updatePlayerInfo(String s) {
    playerInfo.setText(s);
  }

  /**
   * Updates the Location information whenever the status of the Location changes.
   *
   * @param s the information to be displayed
   */
  public void updateGameInfo(String s) {
    gameStatus.setText(s);
  }

  /**
   * Updates how the outcome of the action performed by the player.
   *
   * @param s the information to be displayed
   */
  public void updateLocationInfo(String s) {
    locInfo.setText(s);
  }

}
