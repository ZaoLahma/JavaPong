package src.pong;

import java.awt.Color;
import java.awt.Graphics;

import src.core.GameCoord;
import src.core.GameObject;

public class PongBall extends GameObject 
{
  private final int screenWidth;
  private final int screenHeight;
  private final int ballSize = 20;

  private double xSpeedPerTimeUnit;
  private double ySpeedPerTimeUnit;

  private PongBall() {
    super(new GameCoord(0, 0));
    screenWidth = 0;
    screenHeight = screenWidth;
    xSpeedPerTimeUnit = 0;
    ySpeedPerTimeUnit = 0;
  }

  public PongBall(GameCoord pos, 
                  int screenWidth, 
                  int screenHeight) {
    super(pos);
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    xSpeedPerTimeUnit = 0.3;
    ySpeedPerTimeUnit = 0.3;
  }

  public void paint(Graphics g) {
    g.setColor(new Color(255, 75, 75));
    g.fillOval(pos.getX(), pos.getY(), ballSize, ballSize);
  }

  public void update(int timeDelta) {
    if((pos.getX() <= ballSize) || (pos.getX() >= (screenWidth - ballSize))) {
      xSpeedPerTimeUnit = -xSpeedPerTimeUnit;
    }

    if((pos.getY() <= ballSize) || (pos.getY() >= (screenHeight - ballSize))) {
      ySpeedPerTimeUnit = -ySpeedPerTimeUnit;
    }

    int xDiff = (int)((timeDelta * xSpeedPerTimeUnit) + 0.5);
    int yDiff = (int)((timeDelta * ySpeedPerTimeUnit) + 0.5);

    pos.setX(pos.getX() + xDiff);
    pos.setY(pos.getY() + yDiff);
  }
}