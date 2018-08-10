package src.core;

import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import src.core.GameObject;
import src.core.GameGui;

public abstract class GameLogic
{
  private final int screenWidth;
  private final int screenHeight;
  protected GameGui gui;
  protected ScheduledExecutorService gameExecutor;
  protected boolean active;
  protected Vector<GameObject> gameObjects;

  public GameLogic(int screenWidth, int screenHeight) {
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    gameExecutor = Executors.newSingleThreadScheduledExecutor();
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
    gameExecutor.shutdownNow();
  }

  public void setGui(GameGui gui) {
    this.gui = gui;
  }

  public Vector<GameObject> getGameObjects() {
    return gameObjects;
  }

  public abstract void execute();
}