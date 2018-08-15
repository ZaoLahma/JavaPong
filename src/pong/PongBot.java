package src.pong;

public class PongBot
{
  private final PongLogic pong;
  private final PongPaddle ownPaddle;
  private final PongBall ball;

  private final int PONG_BOT_KEY_UP = 0xFFFFFFFF;
  private final int PONG_BOT_KEY_DOWN = 0xFFFFFFFE;

  private long prevTime = 0;

  private final int PONG_BOT_DELAY = 20; /* ms */

  private PongBot() {
    pong = null;
    ownPaddle = null;
    ball = null;
  }

  public PongBot(PongLogic pong, 
                 PongPaddle ownPaddle, 
                 PongBall ball) {
    this.pong = pong;
    this.ownPaddle = ownPaddle;
    this.ball = ball;

    prevTime = System.currentTimeMillis();
  }

  public int getBotKeyUp() {
    return PONG_BOT_KEY_UP;
  }

  public int getBotKeyDown() {
    return PONG_BOT_KEY_DOWN;
  }

  public void execute() {
    long now = System.currentTimeMillis();

    if ((int)(now - prevTime) > PONG_BOT_DELAY) {
      if(ball.getXSpeedPerTimeUnit() > 0) {
        if(!ownPaddle.isColliding(ball.getPos())) {
          if(ownPaddle.getPos().getY() > ball.getPos().getY()) {
            pong.onKeyPressed(PONG_BOT_KEY_UP);
          }
          else if(ownPaddle.getPos().getY() < ball.getPos().getY()) {
            pong.onKeyPressed(PONG_BOT_KEY_DOWN);
          }
        }
      }
      prevTime = System.currentTimeMillis();
    }
  }
}
