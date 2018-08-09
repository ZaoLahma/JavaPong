package src.pong;

import src.pong.PongLogic;
import src.core.GameRunner;

public class PongMain
{
  public static void main(String[] args) {
    System.out.println("Main called");

    GameRunner engine = new GameRunner(new PongLogic());

    engine.execute();
  }
}