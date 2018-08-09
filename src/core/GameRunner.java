package src.core;

import src.core.GameLogic;

public class GameRunner
{
  private final GameLogic game;

  public GameRunner(GameLogic game) {
    this.game = game;
    /*
      Prepare GUI and so on here?
    */
  }
  public void execute() {
    game.execute();
  }
}