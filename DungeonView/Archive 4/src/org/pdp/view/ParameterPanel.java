package org.pdp.view;

import java.awt.Color;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Represents a panel to take the game configuration as an input.
 */
class ParameterPanel extends JPanel {

  private JLabel rowLabel;
  private JLabel colLabel;
  private JLabel conLabel;
  private JLabel treasureLabel;
  private JLabel difficultyLabel;
  private JLabel playerNameLabel;
  private JLabel dungeonTypeLabel;
  private JTextField row;
  private JTextField columns;
  private JTextField interconnectivity;
  private JTextField cavesWithTreasure;
  private JTextField difficulty;
  private JTextField playerName;
  private JComboBox dungeonType;
  private JPanel buttonPanels;
  private JButton startGame;
  private JButton exitGame;


  JTextField getRow() {
    return row;
  }


  JTextField getColumns() {
    return columns;
  }


  JTextField getInterconnectivity() {
    return interconnectivity;
  }


  JTextField getCavesWithTreasure() {
    return cavesWithTreasure;
  }


  JTextField getDifficulty() {
    return difficulty;
  }


  JTextField getPlayerName() {
    return playerName;
  }


  JComboBox getDungeonType() {
    return dungeonType;
  }


  JButton getStartGame() {
    return startGame;
  }


  JButton getExitGame() {
    return exitGame;
  }


  private ParameterPanel() {
    rowLabel = new JLabel("Number of dungeon rows");
    rowLabel.setAlignmentX(Container.LEFT_ALIGNMENT);
    colLabel = new JLabel("Number of dungeon columns");
    colLabel.setAlignmentX(Container.LEFT_ALIGNMENT);
    conLabel = new JLabel("Interconnectivity");
    conLabel.setAlignmentX(Container.LEFT_ALIGNMENT);
    treasureLabel = new JLabel("Percentage caves with treasure");
    treasureLabel.setAlignmentX(Container.LEFT_ALIGNMENT);
    difficultyLabel = new JLabel("Difficulty");
    difficultyLabel.setAlignmentX(Container.LEFT_ALIGNMENT);
    playerNameLabel = new JLabel("Name");
    playerNameLabel.setAlignmentX(Container.LEFT_ALIGNMENT);
    dungeonTypeLabel = new JLabel("Type of Dungeon");
    dungeonTypeLabel.setAlignmentX(Container.LEFT_ALIGNMENT);

    row = new JTextField();
    row.setBorder(BorderFactory.createLineBorder(Color.black));
    row.setAlignmentX(Container.LEFT_ALIGNMENT);
    columns = new JTextField();
    columns.setBorder(BorderFactory.createLineBorder(Color.black));
    columns.setAlignmentX(Container.LEFT_ALIGNMENT);
    interconnectivity = new JTextField();
    interconnectivity.setBorder(BorderFactory.createLineBorder(Color.black));
    interconnectivity.setAlignmentX(Container.LEFT_ALIGNMENT);
    cavesWithTreasure = new JTextField();
    cavesWithTreasure.setBorder(BorderFactory.createLineBorder(Color.black));
    cavesWithTreasure.setAlignmentX(Container.LEFT_ALIGNMENT);
    difficulty = new JTextField();
    difficulty.setBorder(BorderFactory.createLineBorder(Color.black));
    difficulty.setAlignmentX(Container.LEFT_ALIGNMENT);
    playerName = new JTextField();
    playerName.setBorder(BorderFactory.createLineBorder(Color.black));
    playerName.setAlignmentX(Container.LEFT_ALIGNMENT);
    buttonPanels = new JPanel();
    buttonPanels.setAlignmentX(Container.LEFT_ALIGNMENT);
    startGame = new JButton("Start");
    exitGame = new JButton("Cancel");
    buttonPanels.add(startGame);
    buttonPanels.add(exitGame);
    String[] dungeonTypeStr = {"Wrapping", "Non-Wrapping"};
    dungeonType = new JComboBox(dungeonTypeStr);
    dungeonType.setSelectedIndex(0);
    dungeonType.setAlignmentX(Container.LEFT_ALIGNMENT);

  }


  public static ParameterPanel createParameterPanel() {
    ParameterPanel panel = new ParameterPanel();
    panel.add(panel.playerNameLabel);
    panel.add(panel.playerName);
    panel.add(panel.rowLabel);
    panel.add(panel.row);
    panel.add(panel.colLabel);
    panel.add(panel.columns);
    panel.add(panel.conLabel);
    panel.add(panel.interconnectivity);
    panel.add(panel.treasureLabel);
    panel.add(panel.cavesWithTreasure);
    panel.add(panel.difficultyLabel);
    panel.add(panel.difficulty);
    panel.add(panel.dungeonTypeLabel);
    panel.add(panel.dungeonType);
    panel.add(panel.buttonPanels);
    return panel;
  }


}
