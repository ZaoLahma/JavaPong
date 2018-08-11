package src.pong;

public class PongBot
{
  private final PongLogic pong;
  private final PongPaddle ownPaddle;
  private final PongBall ball;

  private final int PONG_BOT_KEY_UP = 0xFFFFFFFF;
  private final int PONG_BOT_KEY_DOWN = 0xFFFFFFFE;

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
  }

  public int getBotKeyUp() {
    return PONG_BOT_KEY_UP;
  }

  public int getBotKeyDown() {
    return PONG_BOT_KEY_DOWN;
  }

  public void execute() {
    if(!ownPaddle.isColliding(ball.getPos())) {
      if(ownPaddle.getPos().getY() > ball.getPos().getY()) {
        pong.onKeyPressed(PONG_BOT_KEY_UP);
      }
      else if(ownPaddle.getPos().getY() < ball.getPos().getY()) {
        pong.onKeyPressed(PONG_BOT_KEY_DOWN);
      }
    }
  }
}
