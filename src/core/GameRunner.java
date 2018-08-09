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
    System.out.println("GameRunner instantiated with screen size: " + 
                       Integer.toString(game.getScreenHeight()) + 
                       ", " + 
                       Integer.toString(game.getScreenWidth()));
  }
  public void execute() {
    game.execute();
  }
}