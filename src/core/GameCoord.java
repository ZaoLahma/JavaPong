package src.core;

public class GameCoord
{
  private int x;
  private int y;

  private GameCoord() {}

  public GameCoord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public GameCoord(GameCoord newCoord) {
    this.x = newCoord.getX();
    this.y = newCoord.getY();
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void updateCoord(GameCoord coord) {
    this.setX(coord.getX());
    this.setY(coord.getY());
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}