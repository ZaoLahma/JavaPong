package src.pong;

import src.core.GameLogic;

public class PongLogic extends GameLogic
{
  PongLogic() {
    super(640, 480); /* screenWidth, screenHeight */
  }

  public void execute() {
    System.out.println("Pong START");
  }
}