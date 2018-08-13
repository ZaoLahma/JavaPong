package src.core;

import src.core.GameLogic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Vector;

import src.core.GameGui;

public class GameRunner
{
  private final GameLogic game;
  private final GameGui gui;
  private final ScheduledExecutorService gameExecutor;
  private final int targetFps;

  private long prevTime = 0;
  private Boolean firstExec = true;

  public GameRunner(GameLogic game, int targetFps) {
    this.game = game;
    this.targetFps = targetFps;
    System.out.println("GameRunner instantiated with screen size: " + 
                       Integer.toString(game.getScreenWidth()) + 
                       ", " + 
                       Integer.toString(game.getScreenHeight()));

    gui = new GameGui(this);

    gameExecutor = Executors.newSingleThreadScheduledExecutor();
    Runnable gameRunnable = new Runnable()
    {
      public void run()
      {
        execute();
      }
    };
    gameExecutor.scheduleAtFixedRate(gameRunnable, 1000, (int)((1000 / targetFps) + 0.5), TimeUnit.MILLISECONDS);     
  }

  public GameLogic getGame() {
    return game;
  }

  public void stop() {
    gameExecutor.shutdownNow();
  }

  private void execute() {
    if(firstExec) {
      prevTime = System.currentTimeMillis();
      firstExec = false;
    }

    long now = System.currentTimeMillis();
    int timeDeltaMillis = (int)(now - prevTime);

    Vector<GameObject> gameObjects = GameObjectContainer.getApi().getGameObjects();
    gameObjects.forEach(gameObject -> 
                        gameObject.update(timeDeltaMillis));  

    game.execute(timeDeltaMillis);  
    gui.redraw();  

    prevTime = System.currentTimeMillis();
  }
}