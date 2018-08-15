package src.core;

import java.util.Vector;
import src.core.GameObject;
import src.core.GameGui;

public abstract class GameLogic
{
  private final int screenWidth;
  private final int screenHeight;
  private GameRunner gameRunner;
  private final String name;

  public GameLogic(final String name, int screenWidth, int screenHeight) {
    this.name = name;
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
  }

  private GameLogic() {
    this.screenWidth = 0;
    this.screenHeight = this.screenWidth;
    this.name = null;
  }

  public int getScreenWidth() {
    return screenWidth;
  }
  public int getScreenHeight() {
    return screenHeight;
  }

  public String getGameName() {
    return name;
  }

  public abstract void execute(int timeDeltaMillis);

  public abstract void onKeyPressed(int key);
}