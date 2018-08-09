package src.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import src.core.GameObject;

public abstract class GameLogic
{
  private final int screenWidth;
  private final int screenHeight;
  protected ScheduledExecutorService gameExecutor;
  protected boolean active;

  public GameLogic(int screenWidth, int screenHeight) {
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    gameExecutor = Executors.newSingleThreadScheduledExecutor();
    this.active = true;
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

  public abstract void execute();
}