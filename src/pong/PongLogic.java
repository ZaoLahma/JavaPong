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
  private final PongWall leftWall;
  private final PongWall rightWall;
  private final PongWall upWall;
  private final PongWall downWall;
  private final PongBall ball;
  private final PongPaddle leftPaddle;
  private final PongPaddle rightPaddle;

  private final GameCoord PLAYER_2_STRING_POS = new GameCoord(480, 30);
  private final String PLAYER_2_STRING = "Player 2 Score: ";
  
  private GameObjectText playerTwoScoreText;
  private int player2Score = 0;
  private long prevTime;

  PongLogic() {
    super(640, 480); /* screenWidth, screenHeight */
    playerTwoScoreText = new GameObjectText(PLAYER_2_STRING_POS, 
                                            PLAYER_2_STRING + 
                                            Integer.toString(player2Score));
    gameObjects.add(playerTwoScoreText);
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

    leftPaddle = new PongPaddle(new GameCoord(5, 480 / 2), 60, 480);
    gameObjects.add(leftPaddle);

    rightPaddle = new PongPaddle(new GameCoord(631, 480 / 2), 60, 480);
    gameObjects.add(rightPaddle);    

    ball = new PongBall(new GameCoord(640 / 2, 480 / 2), 
                        640, 
                        480,
                        leftPaddle,
                        rightPaddle);
    gameObjects.add(ball);

    prevTime = 0;
  }

  public void execute() {
    System.out.println("Pong START");
    /* Run game logic at 30ish fps */
    Runnable pongRunnable = new Runnable()
    {
      public void run()
      {
        pongRun();
      }
    };
    prevTime = System.currentTimeMillis();
    gameExecutor.scheduleAtFixedRate(pongRunnable, 0, 33, TimeUnit.MILLISECONDS);    
  }

  public void pongRun() {
    long now = System.currentTimeMillis();

    int timeDelta = (int) (now - prevTime);

    Iterator gameIter = gameObjects.iterator();

    while(gameIter.hasNext()) {
      GameObject object = (GameObject) gameIter.next();
      object.update(timeDelta);
    }

    if(PongBall.PongBallCollision.LEFT_WALL_COLLISION == ball.getCurrCollision()) {
      player2Score++;
      playerTwoScoreText.setText(PLAYER_2_STRING + 
                                 Integer.toString(player2Score));  
      ball.resetBall(new GameCoord(640 / 2, 480 / 2));
    }
    else if(PongBall.PongBallCollision.RIGHT_WALL_COLLISION == ball.getCurrCollision()) {

    }

    gui.redraw();

    prevTime = System.currentTimeMillis();
  }

  public void onKeyPressed(int key) {
    if(38 == key) {
      leftPaddle.move(-20);
    }
    else if(40 == key) {
      leftPaddle.move(20);
    }
  }
}