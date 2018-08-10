package src.pong;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

import src.core.GameCoord;
import src.core.GameLogic;
import src.core.GameObject;
import src.core.GameObjectText;
import src.pong.PongWall.PongWallDirection;

public class PongLogic extends GameLogic
{
  private final PongLogicRunnable runnable;
  private final PongWall leftWall;
  private final PongWall rightWall;
  private final PongWall upWall;
  private final PongWall downWall;
  private final PongBall ball;

  private long prevTime;

  PongLogic() {
    super(640, 480); /* screenWidth, screenHeight */
    GameObjectText text = new GameObjectText(new GameCoord(30, 30), "Text");
    gameObjects.add(text);
    leftWall = new PongWall(new GameCoord(2, 2), 
                            PongWall.PongWallDirection.VERTICAL, 
                            478);
    rightWall = new PongWall(new GameCoord(638, 2),
                             PongWall.PongWallDirection.VERTICAL, 
                             478);
    upWall = new PongWall(new GameCoord(2, 2),
                          PongWall.PongWallDirection.HORIZONTAL, 
                          638);
    downWall = new PongWall(new GameCoord(2, 478),
                            PongWall.PongWallDirection.HORIZONTAL, 
                            638);
    gameObjects.add(leftWall);
    gameObjects.add(rightWall);
    gameObjects.add(upWall);
    gameObjects.add(downWall);

    ball = new PongBall(new GameCoord(640 / 2, 480 / 2), 640, 480);
    gameObjects.add(ball);

    runnable = new PongLogicRunnable(this);
    prevTime = 0;
  }

  public void execute() {
    System.out.println("Pong START");
    prevTime = System.currentTimeMillis();
    /* Run game logic at 30ish fps */
    gameExecutor.scheduleAtFixedRate(runnable, 0, 33, TimeUnit.MILLISECONDS);    
  }

  public void pongRun() {
    long now = System.currentTimeMillis();

    int timeDelta = (int) (now - prevTime);

    Iterator gameIter = gameObjects.iterator();

    while(gameIter.hasNext()) {
      GameObject object = (GameObject) gameIter.next();
      object.update(timeDelta);
    }

    gui.redraw();

    prevTime = System.currentTimeMillis();
  }
}

class PongLogicRunnable implements Runnable
{
  PongLogic game;

  private PongLogicRunnable() {
    game = null;
  }

  public PongLogicRunnable(PongLogic game) {
    this.game = game;
  }

	public void run() {
    game.pongRun();
	}
}