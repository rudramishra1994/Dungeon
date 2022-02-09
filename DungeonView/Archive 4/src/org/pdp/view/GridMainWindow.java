package org.pdp.view;

import org.pdp.controller.GameFeatures;
import org.pdp.game.Direction;
import org.pdp.game.IDungeonReadOnly;
import org.pdp.game.ILocation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * This class represents the main game view.
 * It has a grid that shows the dungeon and an information panel for information about the game.
 */
public class GridMainWindow extends JFrame implements IGameMainView {
  private JMenu menu;
  private JScrollPane scrollableGrid;
  private JPanel masterPanel;
  private IDungeonReadOnly dungeon;
  private InformationPanel informationPanel;


  /**
   * Creates the main view of the game which shows the explored grid and information panel.
   * @param dungeon the model.
   */
  public GridMainWindow(IDungeonReadOnly dungeon) {

    super("Maze Runner");
    if (dungeon == null) {
      throw new IllegalArgumentException("dungeon cannot be null for the view");
    }
    this.dungeon = dungeon;
    this.menu = new JMenu("Menu");

    addMenuItems();

    this.setSize(1000, 800);
    this.setLayout(new BorderLayout());
    this.setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    JMenuBar gameMenuBar = new JMenuBar();
    gameMenuBar.add(menu);
    this.setJMenuBar(gameMenuBar);
    this.setVisible(true);


    this.informationPanel = new InformationPanel();
    informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
    informationPanel.setPreferredSize(new Dimension(this.getWidth() / 4, this.getHeight()));
    //informationPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


    masterPanel = new JPanel();
    masterPanel.setPreferredSize(new Dimension((this.getWidth() * 3 / 4), this.getHeight()));
    //masterPanel.setVisible(true);
    addLocationPanel(masterPanel);


    this.scrollableGrid = new JScrollPane(masterPanel);
    this.scrollableGrid.getVerticalScrollBar().setUnitIncrement(50);
    this.scrollableGrid.getHorizontalScrollBar().setUnitIncrement(16);
    this.scrollableGrid.setPreferredSize(new Dimension(500, 500));
    //this.scrollableGrid.setMaximumSize(new Dimension(700, 700));
    this.scrollableGrid.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10,
        10),BorderFactory.createLineBorder(Color.BLACK)));
    this.add(scrollableGrid, BorderLayout.CENTER);
    this.add(informationPanel,BorderLayout.EAST);
    ///this.pack();
    this.setLocationRelativeTo(null);
  }

  private void addLocationPanel(JPanel masterPanel) {
    int row = dungeon.getDungeonRows();
    int col = dungeon.getDungeonColumns();
    masterPanel.setLayout(new GridLayout(row, col));
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        GridCellPanel cellPanel = new GridCellPanel(dungeon, i, j);
        cellPanel.setPreferredSize(new Dimension(100, 100));
        masterPanel.add(cellPanel);
      }
    }


  }

  private void addMenuItems() {
    JMenuItem newGame = new JMenuItem("New Game");
    newGame.setName("NEWGAME");
    JMenuItem restartGame = new JMenuItem("Restart Game");
    restartGame.setName("RESTART");
    JMenuItem exitGame = new JMenuItem("Exit");
    exitGame.setName("EXIT");

    this.menu.add(newGame);
    this.menu.add(restartGame);
    this.menu.add(exitGame);

  }


  @Override
  public void makeVisible() {
    focusScrollPanel();
    this.setVisible(true);
  }


  @Override
  public void updateGameStatusBoard(StringBuilder info) {
    informationPanel.updateGameInfo(info.toString());
  }

  @Override
  public void updatePlayerStatusBoard(StringBuilder info) {
    informationPanel.updatePlayerInfo(info.toString());
  }

  @Override
  public void updateLocationStatusBoard(StringBuilder info) {
    informationPanel.updateLocationInfo(info.toString());
  }

  @Override
  public void setGameFeatures(GameFeatures feature) {

    Component[] menuItem = this.menu.getMenuComponents();
    for (Component c : menuItem) {
      JMenuItem item = (JMenuItem) c;
      switch (item.getName()) {
        case "RESTART":
          item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
              super.mouseReleased(e);
              int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to restart the "
                  + "Game", "Restart Game", JOptionPane.YES_NO_CANCEL_OPTION);
              if (x == 0) {
                feature.restartGame();
              }
            }
          });
          break;
        case "EXIT":
          item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
              super.mouseReleased(e);
              int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to Start a new "
                  + "Game?", "New Game", JOptionPane.YES_NO_CANCEL_OPTION);
              if (x == 0) {
                feature.exitGame();
              }
            }
          });
          break;
        case "NEWGAME":
          item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
              super.mouseReleased(e);

              GameStartUp configuration = new GameStartUp();
              configuration.setVisible(true);
              configuration.getParameterPanel().getExitGame().addActionListener(event ->
                  configuration.dispose());
              configuration.setLocationRelativeTo(item);
              configuration.getParameterPanel().getStartGame().addActionListener(
                  e1 -> {
                  Map<String, String> conf = new HashMap<>();
                  conf.put("name",
                      configuration.getParameterPanel().getPlayerName().getText());
                  conf.put("row", configuration.getParameterPanel().getRow().getText());
                  conf.put("col", configuration.getParameterPanel().getColumns().getText());
                  conf.put("con",
                      configuration.getParameterPanel().getInterconnectivity().getText());
                  conf.put("difficulty",
                      configuration.getParameterPanel().getDifficulty().getText());
                  conf.put("treasure",
                      configuration.getParameterPanel().getCavesWithTreasure().getText());
                  conf.put("dungeonType",
                       configuration.getParameterPanel().getDungeonType()
                          .getSelectedItem().toString());
                  configuration.dispose();
                  feature.startNewGame(conf);
                });

            }
          });
          break;
        default:
          break;

      }
    }

    this.addKeyListener(new KeyListener() {
      private boolean shootArrow = false;

      @Override
      public void keyTyped(KeyEvent e) {
          //do nothing
      }

      @Override
      public void keyReleased(KeyEvent e) {
        if (!(dungeon.hasReachedGoal()) && dungeon.isPlayerAlive())  {
          if (e.getKeyCode() == KeyEvent.VK_S) {
            this.shootArrow = true;
          }
          Direction d = null; //default val
          if (shootArrow) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
              d = Direction.NORTH;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
              d = Direction.SOUTH;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
              d = Direction.WEST;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
              d = Direction.EAST;
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
              shootArrow = false;
            }
            if (shootArrow && d != null) {
              String name = JOptionPane.showInputDialog(null, "Enter a number between [1-5].");
              if (name != null) {
                try {
                  Integer distance = Integer.parseInt(name);
                  feature.shootArrow(d, distance);
                  shootArrow = false;
                } catch (NumberFormatException ex) {
                  showExceptionDialogBox("Invalid Input.Please give a valid input");
                } catch (IllegalArgumentException e2) {
                  showExceptionDialogBox(e2.getMessage());
                }
              }
            }

          } else {
            try {
              if (e.getKeyCode() == KeyEvent.VK_UP) {
                feature.movePlayer(Direction.NORTH);
              }
              if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                feature.movePlayer(Direction.SOUTH);
              }
              if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                feature.movePlayer(Direction.WEST);
              }
              if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                feature.movePlayer(Direction.EAST);
              }
              if (e.getKeyChar() == 'a') {
                feature.pickArrow();
              }
              if (e.getKeyChar() == 't') {
                feature.pickTreasure();
              }
            } catch (IllegalArgumentException exp) {
              showExceptionDialogBox(exp.getMessage());
            }
          }
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
        // do nothing.
        if (e.getKeyCode() == KeyEvent.VK_UP) {
          //feature.restoreCase();
        }
      }
    });
    this.requestFocus();
    MouseAdapter clickAdapter = new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        super.mouseClicked(e);
        if  (!(dungeon.hasReachedGoal())) {
          GridCellPanel cell = (GridCellPanel) e.getComponent();
          feature.movePlayer(cell.getGridRow(), cell.getGridColumn());
        }
      }
    };
    JPanel masterGridPanel = (JPanel) this.scrollableGrid.getViewport().getComponents()[0];
    Component[] c = masterGridPanel.getComponents();
    for (Component child : c) {
      GridCellPanel cell = (GridCellPanel) child;
      cell.addMouseListener(clickAdapter);
    }
  }


  @Override
  public void refreshGrid(boolean wasPlayerActionSuccessful) {
    if (wasPlayerActionSuccessful) {
      JPanel masterGridPanel = (JPanel) this.scrollableGrid.getViewport().getComponents()[0];
      Component[] components = masterGridPanel.getComponents();
      for (Component c : components) {
        GridCellPanel panel = (GridCellPanel) c;
        ILocation l = dungeon.getLocationAtGivenCoordinates(panel.getGridRow(),
            panel.getGridColumn());
        if (l.isVisited()) {
          panel.repaint();
        }
      }


    } //else do nothing.
  }

  @Override
  public void setModel(IDungeonReadOnly dungeon) {
    this.dungeon = dungeon;
  }

  @Override
  public void showExceptionDialogBox(String exceptionMsg) {
    JOptionPane.showMessageDialog(null, exceptionMsg);
  }

  @Override
  public void resetGridOnNewGame() {
    this.masterPanel.removeAll();
    this.masterPanel.repaint();
    this.addLocationPanel(this.masterPanel);
    //masterPanel.repaint();
    focusScrollPanel();
  }


  private GridCellPanel getCurrentGridCell() {
    int currPlayerX = dungeon.getCurrentLocation().get(0);
    int currPlayerY = dungeon.getCurrentLocation().get(1);
    Component[] components = masterPanel.getComponents();
    for (Component c : components) {
      GridCellPanel cell = (GridCellPanel) c;
      if (cell.getGridRow() == currPlayerX && cell.getGridColumn() == currPlayerY) {
        return cell;
      }
    }
    return new GridCellPanel(dungeon,99999,99999);
  }

  private void focusScrollPanel() {
    GridCellPanel currCell = getCurrentGridCell();
    this.scrollableGrid.getHorizontalScrollBar().setValue(currCell.getX());
    this.scrollableGrid.getHorizontalScrollBar().setValue(currCell.getY());
  }

}
