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
  private final double maxYSpeed = 0.32;
  private final PongPaddle leftPaddle;
  private final PongPaddle rightPaddle;

  private double xSpeedPerTimeUnit;
  private double ySpeedPerTimeUnit;

  private PongBallCollision currCollision = PongBallCollision.NO_COLLISION;

  public enum PongBallCollision {
    NO_COLLISION,
    LEFT_WALL_COLLISION,
    RIGHT_WALL_COLLISION
  }

  private PongBall() {
    super(new GameCoord(0, 0));
    screenWidth = 0;
    screenHeight = screenWidth;
    xSpeedPerTimeUnit = 0;
    ySpeedPerTimeUnit = 0;
    leftPaddle = null;
    rightPaddle = null;
  }

  public PongBall(GameCoord pos, 
                  int screenWidth, 
                  int screenHeight,
                  PongPaddle leftPaddle,
                  PongPaddle rightPaddle) {
    super(pos);
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    xSpeedPerTimeUnit = 0.25;
    ySpeedPerTimeUnit = 0.25;
    this.leftPaddle = leftPaddle;
    this.rightPaddle = rightPaddle;
  }

  public double getXSpeedPerTimeUnit() {
    return xSpeedPerTimeUnit;
  }
  
  public void resetBall(GameCoord pos) {
    this.pos = pos;
    xSpeedPerTimeUnit = 0.25;
    ySpeedPerTimeUnit = 0.25;
  }

  public void paint(Graphics g) {
    g.setColor(new Color(255, 75, 75));
    g.fillOval(pos.getX() - (ballSize / 2), 
               pos.getY() - (ballSize / 2), 
               ballSize, 
               ballSize);
  }

  public PongBallCollision getCurrCollision() {
    return currCollision;
  }

  public void update(int timeDelta) {
    currCollision = PongBallCollision.NO_COLLISION;

    if((pos.getX() <= (ballSize / 2))) {
      if(!leftPaddle.isColliding(pos)) {
        currCollision = PongBallCollision.LEFT_WALL_COLLISION;
        System.out.println("LEFT WALL");
      }
    }
    else if(pos.getX() >= (screenWidth - (ballSize / 2))) {
      if(!rightPaddle.isColliding(pos)) {
        currCollision = PongBallCollision.RIGHT_WALL_COLLISION;
        System.out.println("RIGHT WALL");
      }
    }

    if((pos.getX() <= (ballSize / 2)) || (pos.getX() >= (screenWidth - (ballSize / 2)))) {
      xSpeedPerTimeUnit = -xSpeedPerTimeUnit;
      if(xSpeedPerTimeUnit > 0) {
        xSpeedPerTimeUnit += 0.01;
      }
      else {
        xSpeedPerTimeUnit -= 0.01;
      }
   
      if(Math.abs(ySpeedPerTimeUnit) < maxYSpeed) {
        if(ySpeedPerTimeUnit > 0) {
          ySpeedPerTimeUnit += 0.01;
        }
        else {
          ySpeedPerTimeUnit -= 0.01;
        }
      }
    }

    if((pos.getY() <= (ballSize / 2)) || (pos.getY() >= (screenHeight - (ballSize / 2)))) {
      ySpeedPerTimeUnit = -ySpeedPerTimeUnit;
    }

    int xDiff = (int)((timeDelta * Math.abs(xSpeedPerTimeUnit)) + 0.5);
    int yDiff = (int)((timeDelta * Math.abs(ySpeedPerTimeUnit)) + 0.5);

    if((xSpeedPerTimeUnit < 0) && (xDiff > 0)) {
      xDiff = -xDiff;
    }

    if((ySpeedPerTimeUnit < 0) && (yDiff > 0)) {
      yDiff = -yDiff;
    }    

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