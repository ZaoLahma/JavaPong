package src.core;

public class GameUniqueId
{
  private static GameUniqueId instance = new GameUniqueId();
  private GameUniqueId() {}

  private int currId = 0;

  public static GameUniqueId getApi() {
    return instance;
  }

  public int getUniqueId() {
    return currId++;
  }
}