package src.core;

import java.util.Vector;
import src.core.GameObject;
import src.core.GameGui;

public abstract class GameLogic
{
  private final int screenWidth;
  private final int screenHeight;
  private GameRunner gameRunner;  

  public GameLogic(int screenWidth, int screenHeight) {
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
  }

  private GameLogic() {
    this.screenWidth = 0;
    this.screenHeight = this.screenWidth;
  }

  public int getScreenWidth() {
    return screenWidth;
  }
  public int getScreenHeight() {
    return screenHeight;
  }

  public abstract void execute(int timeDeltaMillis);

  public abstract void onKeyPressed(int key);
}