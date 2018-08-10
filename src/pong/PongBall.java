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
    g.fillOval(pos.getX() - (ballSize / 2), 
               pos.getY() - (ballSize / 2), 
               ballSize, 
               ballSize);
  }

  public void update(int timeDelta) {
    if((pos.getX() <= (ballSize / 2)) || (pos.getX() >= (screenWidth - (ballSize / 2)))) {
      xSpeedPerTimeUnit = -xSpeedPerTimeUnit;
    }

    if((pos.getY() <= (ballSize / 2)) || (pos.getY() >= (screenHeight - (ballSize / 2)))) {
      ySpeedPerTimeUnit = -ySpeedPerTimeUnit;
    }

    int xDiff = (int)((timeDelta * xSpeedPerTimeUnit) + 0.5);
    int yDiff = (int)((timeDelta * ySpeedPerTimeUnit) + 0.5);

    if(pos.getX() + xDiff < (ballSize / 2)) {
      pos.setX((ballSize / 2));
    }
    else if(pos.getX() + xDiff > screenWidth - (ballSize / 2)) {
      pos.setX(screenWidth - (ballSize / 2));
    }

    if(pos.getY() + yDiff < (ballSize / 2)) {
      pos.setY((ballSize / 2));
    }
    else if(pos.getY() + yDiff > screenHeight - (ballSize / 2)) {
      pos.setY(screenHeight - (ballSize / 2));
    }    

    pos.setX(pos.getX() + xDiff);
    pos.setY(pos.getY() + yDiff);
  }
}