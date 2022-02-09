package org.pdp.view;

import org.pdp.game.Direction;
import org.pdp.game.IDungeonReadOnly;
import org.pdp.game.ILocation;
import org.pdp.game.Smell;
import org.pdp.game.Treasures;
import org.pdp.game.Weapon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Represents each Individual location of the dungeon on the grid.
 */
class GridCellPanel extends JPanel {

  private IDungeonReadOnly dungeon;
  private final int gridRow;
  private final int gridCol;


  private static int DIAMONDX;
  private static int DIAMONDY;
  private static int RUBYX;
  private static int RUBYY;
  private static int SAPHIREX;
  private static int SAPHIREY;
  private static int ARROWX;
  private static int ARROWY;
  private static int MONSTERX;
  private static int MONSTERY;
  private static int PLAYERX;
  private static int PLAYERY;
  private static int SQUARESIZE;
  private static int PLAYERSIZE;


  /**
   * Creates each individual cell location of the grid.
   *
   * @param dungeon the model.
   * @param row     the x coordinate of the location the cell represents.
   * @param col     the Y coordinate of the location the cell represents.
   */
  public GridCellPanel(IDungeonReadOnly dungeon, int row, int col) {
    this.dungeon = dungeon;
    this.gridRow = row;
    this.gridCol = col;

  }

  /**
   * Returns the X coordinate.
   *
   * @return X-coordinate
   */
  public int getGridRow() {
    return gridRow;
  }

  /**
   * Returns the Y coordinate.
   *
   * @return Y-coordinate
   */
  public int getGridColumn() {
    return gridCol;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    ILocation location = dungeon.getLocationAtGivenCoordinates(gridRow, gridCol);
    try {
      setCoordinateOfLocationItems();
      String locationImageStr = getLocationImageNameString(location);
      BufferedImage locationImage =
          ImageIO.read(ClassLoader.getSystemResource("images/" + locationImageStr));
      BufferedImage diamond = ImageIO.read(ClassLoader.getSystemResource("images/diamond.png"));
      BufferedImage ruby = ImageIO.read(ClassLoader.getSystemResource("images/ruby.png"));
      BufferedImage sapphire = ImageIO.read(ClassLoader.getSystemResource("images/emerald"
          + ".png"));
      BufferedImage otyugh = ImageIO.read(ClassLoader.getSystemResource("images/otyugh.png"));
      BufferedImage player = ImageIO.read(ClassLoader.getSystemResource("images/player.png"));
      BufferedImage arrow = ImageIO.read(ClassLoader.getSystemResource("images/arrow-black"
          + ".png"));
      BufferedImage flag = ImageIO.read(ClassLoader.getSystemResource("images/flag"
          + ".png"));

      BufferedImage lightSmell = ImageIO.read(ClassLoader.getSystemResource("images/light"
          + "-smell.png"));
      BufferedImage heavySmell = ImageIO.read(ClassLoader.getSystemResource("images/heavy"
          + "-smell.png"));
      if (location.isVisited()) {
        Image v = locationImage.getScaledInstance(this.getWidth(), this.getHeight(),
            Image.SCALE_SMOOTH);

        g2d.drawImage(v, 0, 0, null);

        /*
            Adding Treasure /Arrow/ Monster as per availability.
         */
        Map<Treasures, Integer> treasureList = location.getTreasureList();
        if (treasureList.size() > 0 && treasureList.containsKey(Treasures.RUBY)) {
          g2d.drawImage(ruby, RUBYX, RUBYY, SQUARESIZE, SQUARESIZE, null);
        }
        if (treasureList.size() > 0 && treasureList.containsKey(Treasures.SAPPHIRE)) {
          g2d.drawImage(sapphire, SAPHIREX, SAPHIREY, SQUARESIZE, SQUARESIZE, null);
        }

        if (treasureList.size() > 0 && treasureList.containsKey(Treasures.DIAMOND)) {
          g2d.drawImage(diamond, DIAMONDX, DIAMONDY, SQUARESIZE, SQUARESIZE, null);
        }

        if (location.getMonsters().size() > 0) {
          g2d.drawImage(otyugh, MONSTERX, MONSTERY, SQUARESIZE + 10, SQUARESIZE + 10, null);

        }

        if (location.getWeaponList().size() > 0 && location.getWeaponList().get(Weapon.ARROW) > 0) {
          g2d.drawImage(arrow, ARROWX, ARROWY, (int)(this.getWidth() * 0.60), 4, null);
        }

        if (dungeon.getSmellIntensity(location) == Smell.LIGHT) {
          Image smell = lightSmell.getScaledInstance(this.getWidth(), this.getHeight(),
              Image.SCALE_SMOOTH);
          g2d.drawImage(smell, 0, 0, null);
        } else if (dungeon.getSmellIntensity(location) == Smell.HEAVY) {
          Image smell = heavySmell.getScaledInstance(this.getWidth(), this.getHeight(),
              Image.SCALE_SMOOTH);
          g2d.drawImage(smell, 0, 0, null);
        }
        if (location.equals(dungeon.getPlayerCurrentLcation())) {
          g2d.drawImage(player, PLAYERX, PLAYERY, PLAYERSIZE, PLAYERSIZE, null);
        }
        if (location.getXCoordinate() == dungeon.getEndLocation().get(0)
            && location.getYCoordinate() == dungeon.getEndLocation().get(1)) {
          g2d.drawImage(flag, this.getWidth() / 2, this.getHeight() / 2, SQUARESIZE, SQUARESIZE,
              null);
        }

      } else {
        BufferedImage cellNotVisited = ImageIO.read(ClassLoader.getSystemResource("images"
            + "/black.png"));
        Image cell = cellNotVisited.getScaledInstance(this.getWidth(), this.getHeight(),
              Image.SCALE_SMOOTH);
        g2d.drawImage(cell, 0, 0, null);
      }


    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  private void setCoordinateOfLocationItems() {

    DIAMONDX = this.getWidth() / 4;
    DIAMONDY = this.getHeight() / 4;
    RUBYX = this.getWidth() / 2;
    RUBYY = this.getHeight() / 4;
    SAPHIREX = this.getWidth() * 3 / 4;
    SAPHIREY = this.getHeight() / 4;
    ARROWX = this.getWidth() * 2 / 10;
    ARROWY = this.getHeight() * 1 / 2;
    MONSTERX = this.getWidth() * 6 / 10;
    MONSTERY = this.getHeight() * 3 / 5;
    PLAYERX = this.getWidth() * 4 / 10;
    PLAYERY = this.getHeight() * 4 / 10;
    SQUARESIZE = 16;
    PLAYERSIZE = 25;

  }


  private String getLocationImageNameString(ILocation location) {
    List<String> direction = new ArrayList<>();
    for (Direction d : location.getAllowedDirectionToMove().keySet()) {
      if (location.getAllowedDirectionToMove().get(d)) {
        direction.add(d.name());
      }
    }
    Collections.sort(direction);
    StringBuilder fileInitial = new StringBuilder();
    for (String s : direction) {
      fileInitial.append(s.charAt(0));
    }

    StringBuilder b  = new StringBuilder(fileInitial.toString().toUpperCase());
    b.append(".png");
    return b.toString();
  }



}
