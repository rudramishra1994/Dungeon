package tictactoe;

public class TicTacToeControllerImpl implements TicTacToeController{

  private  TicTacToe model;
  private  TicTacToeView view;
  public TicTacToeControllerImpl(TicTacToeView view){
    this.view= view;
  }

  @Override
  public void playGame(TicTacToe m) {
      this.model = m;
      view.makeVisible();
  }

  @Override
  public void handleCellClick(int row, int col) {

  }
}
