package src.core;

import src.core.GameLogic;

import java.util.concurrent.TimeUnit;
import java.util.Vector;

import src.core.GameGui;

public class GameRunner
{
  private final GameLogic game;
  private final GameGui gui;

  private final int targetFps;
  private final int timeToSleep;

  private long prevTime = 0;

  private Boolean firstExec = true;
  private Boolean running = false;

  public GameRunner(GameLogic game, int targetFps) {
    this.game = game;
    this.targetFps = targetFps;
    this.timeToSleep = (int)((1000 / this.targetFps) + 0.5);

    System.out.println("GameRunner instantiated with screen size: " + 
                       Integer.toString(game.getScreenWidth()) + 
                       ", " + 
                       Integer.toString(game.getScreenHeight()));

    gui = new GameGui(this);
  }

  public GameLogic getGame() {
    return game;
  }

  public void stop() {
    running = false;
  }

  public void run() {
    running = true;
    prevTime = System.currentTimeMillis() - timeToSleep;

    while(running) {
      long now = System.currentTimeMillis();
      final int timeDeltaMillis = (int)(now - prevTime);

      Vector<GameObject> gameObjects = GameObjectContainer.getApi().getGameObjects();
      gameObjects.forEach(gameObject -> 
                          gameObject.update(timeDeltaMillis));  

      game.execute(timeDeltaMillis);  
      gui.redraw();  

      prevTime = System.currentTimeMillis();

      int delay = timeToSleep;

      if((timeDeltaMillis - timeToSleep) > 0) {
        delay = delay - (timeDeltaMillis - timeToSleep);
      }

      if(delay > 0) {
        try {
          Thread.sleep(delay);
        } catch (InterruptedException exception) {
          System.out.println(exception);
        }
      }
    }
  }
}