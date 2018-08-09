package src.pong;

import java.util.concurrent.TimeUnit;

import src.core.GameLogic;

public class PongLogic extends GameLogic
{
  PongLogic() {
    super(640, 480); /* screenWidth, screenHeight */
  }

  public void execute() {
    System.out.println("Pong START");
    /* Run game logic at 30ish fps */
    gameExecutor.scheduleAtFixedRate(PongLogic::pongRun, 0, 33, TimeUnit.MILLISECONDS);    
  }

  private static void pongRun() {
    System.out.println("pongRun called");
  }
}