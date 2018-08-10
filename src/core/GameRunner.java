package src.core;

import src.core.GameLogic;
import src.core.GameGui;

public class GameRunner
{
  private final GameLogic game;
  private final GameGui gui;

  public GameRunner(GameLogic game) {
    this.game = game;
    /*
      Prepare GUI and so on here?
    */
    System.out.println("GameRunner instantiated with screen size: " + 
                       Integer.toString(game.getScreenHeight()) + 
                       ", " + 
                       Integer.toString(game.getScreenWidth()));

    gui = new GameGui(this.game);
    this.game.setGui(gui);
  }
  public void execute() {
    game.execute();
  }
}