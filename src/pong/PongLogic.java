package src.pong;

import java.util.Iterator;
import java.util.Vector;

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
  private final PongBot bot;

  private final GameCoord PLAYER_1_STRING_POS = new GameCoord(30, 30);
  private final String PLAYER_1_STRING = "Player 1 Score: ";

  private final GameCoord PLAYER_2_STRING_POS = new GameCoord(480, 30);
  private final String PLAYER_2_STRING = "Player 2 Score: ";

  private final int PONG_LOGIC_DELAY = 30; /* ms */
  
  private GameObjectText playerOneScoreText;
  private int player1Score = 0;

  private GameObjectText playerTwoScoreText;
  private int player2Score = 0;

  private int timeDeltaAcc = 0;

  PongLogic() {
    super(640, 480); /* screenWidth, screenHeight */

    playerOneScoreText = new GameObjectText(PLAYER_1_STRING_POS, 
                                            PLAYER_1_STRING + 
                                            Integer.toString(player1Score));
    gameObjects.add(playerOneScoreText);

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

    bot = new PongBot(this, this.rightPaddle, this.ball);
  }

  public void execute(final int timeDeltaMillis) {
    bot.execute();
    if(timeDeltaAcc >= PONG_LOGIC_DELAY) {
      pongRun(timeDeltaAcc);
      timeDeltaAcc = 0;
    }
    else {
      timeDeltaAcc += timeDeltaMillis;
    }
  }

  private void pongRun(final int timeDeltaMillis) {

    gameObjects.forEach(gameObject -> 
                        gameObject.update(timeDeltaMillis));

    if(PongBall.PongBallCollision.LEFT_WALL_COLLISION == ball.getCurrCollision()) {
      player2Score++;
      playerTwoScoreText.setText(PLAYER_2_STRING + 
                                 Integer.toString(player2Score));  
      ball.resetBall(new GameCoord(640 / 2, 480 / 2));
    }
    else if(PongBall.PongBallCollision.RIGHT_WALL_COLLISION == ball.getCurrCollision()) {
      player1Score++;
      playerOneScoreText.setText(PLAYER_1_STRING + 
                                 Integer.toString(player1Score));  
      ball.resetBall(new GameCoord(640 / 2, 480 / 2));
    }
  }

  public void onKeyPressed(int key) {
    if(38 == key) {
      leftPaddle.move(-(leftPaddle.getLength() / 2));
    }
    else if(40 == key) {
      leftPaddle.move((leftPaddle.getLength() / 2));
    }

    if(bot.getBotKeyDown() == key) {
      rightPaddle.move((rightPaddle.getLength() / 2));
    }
    else if(bot.getBotKeyUp() == key) {
      rightPaddle.move(-(rightPaddle.getLength() / 2));
    }
  }
}