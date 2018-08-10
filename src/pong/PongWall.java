package src.pong;

import java.awt.Color;
import java.awt.Graphics;

import src.core.GameCoord;
import src.core.GameObject;

public class PongWall extends GameObject
{
  public enum PongWallDirection {
    HORIZONTAL,
    VERTICAL
  }

  private final PongWallDirection direction;
  private final int length;

  private PongWall() {
    super(new GameCoord(0, 0));
    direction = null;
    length = 0;
  }

  public PongWall(GameCoord pos, 
                  PongWallDirection direction, 
                  int length) {
    super(pos);
    this.direction = direction;
    this.length = length;
  }

  public void paint(Graphics g) {
    g.setColor(new Color(0, 0, 0));
    if(PongWallDirection.VERTICAL == direction) {
      g.drawLine(pos.getX(), pos.getY(), pos.getX(), length);
    }
    else if(PongWallDirection.HORIZONTAL == direction) {
      g.drawLine(pos.getX(), pos.getY(), length, pos.getY());
    }
  }

  public void update(int timeDelta) {

  }
}