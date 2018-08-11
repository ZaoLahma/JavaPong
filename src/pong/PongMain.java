package src.pong;

import src.pong.PongLogic;
import src.core.GameRunner;

public class PongMain
{
  public static void main(String[] args) {
    System.out.println("Main called");

    final int targetFps = 30;
    GameRunner engine = new GameRunner(new PongLogic(), targetFps);
  }
}