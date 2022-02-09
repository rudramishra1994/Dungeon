package tictactoe;

import java.awt.*;

import javax.swing.JPanel;

class BoardPanel extends JPanel {

  private final ReadonlyTttModel model;

  public BoardPanel(ReadonlyTttModel model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    Dimension d = this.getSize();
    g2d.setColor(Color.BLACK);
    //drawing grid lines.
    g2d.drawLine(0,d.height/3,d.width,d.height/3);
    g2d.drawLine(0,2*d.height/3,d.width,2*d.height/3);
    g2d.drawLine(d.width/3,0,d.width/3,d.height);
    g2d.drawLine(2*d.width/3,0,2*d.width/3,d.height);

    Player[][] board = model.getBoard();
    board = new Player[][]{{Player.X, Player.O, Player.X}, {Player.X, Player.O, Player.X}, {Player.X, Player.O,
      Player.X}};
    for(int i = 0;i<3;i++){
      for(int j = 0;j<3;j++){

      }
    }
    //g2d.setFont(???);
    // iterate over board, draw X and O accordingly
    //g2d.drawString("asdf", 5, 6);


  }
}
