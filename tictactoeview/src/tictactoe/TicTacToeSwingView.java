package tictactoe;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TicTacToeSwingView extends JFrame implements TicTacToeView {

  private BoardPanel boardPanel;

  public TicTacToeSwingView(ReadonlyTttModel model) {
    super("Tic-Tac-Toe");
    this.setSize(500,500);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    boardPanel = new BoardPanel(model);
    add(boardPanel);

  }

  @Override
  public void addClickListener(TicTacToeController listener) {
    // create the MouseAdapter
    MouseAdapter clickAdapter = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        // arithmetic to convert panel coords to grid coords
        //e.getX(), e.getY()
        //listener.handleCellClick(???);
      }
    };
    boardPanel.addMouseListener(clickAdapter);
  }

  @Override
  public void refresh() {
    repaint();
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }
}
