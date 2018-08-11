package src.pong;

import src.pong.PongLogic;
import src.core.GameRunner;

public class PongMain
{
  public static void main(String[] args) {
    System.out.println("Main called");

    final int targetFps = 100; /* Execute every 10ms */
    GameRunner engine = new GameRunner(new PongLogic(), targetFps);

    System.out.println("Main exited");
  }
}