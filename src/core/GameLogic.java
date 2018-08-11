package src.core;

import java.util.Vector;
import src.core.GameObject;
import src.core.GameGui;

public abstract class GameLogic
{
  private final int screenWidth;
  private final int screenHeight;
  private GameRunner gameRunner;  
  protected boolean active;
  protected Vector<GameObject> gameObjects;

  public GameLogic(int screenWidth, int screenHeight) {
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    this.active = true;
    this.gameObjects = new Vector<GameObject>();
  }

  private GameLogic() {
    this.screenWidth = 0;
    this.screenHeight = this.screenWidth;
    this.active = false;
  }

  public int getScreenWidth() {
    return screenWidth;
  }
  public int getScreenHeight() {
    return screenHeight;
  }

  public void stop() {
    this.active = false;
    gameRunner.stop();
  }

  public void setGameRunner(GameRunner gameRunner) {
    this.gameRunner = gameRunner;
  }

  public Vector<GameObject> getGameObjects() {
    return gameObjects;
  }

  public abstract void execute();

  public abstract void onKeyPressed(int key);
}