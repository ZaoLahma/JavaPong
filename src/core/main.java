package src.core;

import src.core.GameObject;
import src.core.GameCoord;

class Main
{
  public static void main(String[] args) {
    System.out.println("Main called");

    GameObject object = new GameObject(new GameCoord(0, 0));
  }
}