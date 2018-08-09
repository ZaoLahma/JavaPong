package src.core;

class GameUniqueId
{
  private static GameUniqueId instance = new GameUniqueId();
  private GameUniqueId() {}

  private int currId = 0;

  public static GameUniqueId getInstance() {
    return instance;
  }

  public int getUniqueId() {
    return currId++;
  }
}