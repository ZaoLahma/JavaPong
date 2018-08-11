package src.pong;

import java.awt.Color;
import java.awt.Graphics;

import src.core.GameCoord;
import src.core.GameObject;

class PongPaddle extends GameObject
{
  private int length;
  private final int screenHeight;

  private PongPaddle() {
    super(new GameCoord(0, 0));
    length = 0;
    screenHeight = 0;
  }

  public PongPaddle(GameCoord pos, int length, int screenHeight) {
    super(pos);
    this.length = length;
    this.screenHeight = screenHeight;
  }

  public Boolean isColliding(GameCoord otherPos) {
    Boolean retVal = false;

    if(otherPos.getY() >= pos.getY() - (length / 2) &&
       otherPos.getY() <= pos.getY() + (length / 2)) {
      retVal = true;
    }

    return retVal;
  }

  public int getLength() {
    return length;
  }
  
  public void paint(Graphics g) {
    g.setColor(new Color(0, 0, 0));
    g.fillRect(pos.getX(), pos.getY() - (length / 2), 4, length);
  }

  public void move(int pixels) {
    if(pos.getY() + pixels >= (length / 2) && 
       (pos.getY() + pixels <= (screenHeight - (length / 2)))) {
      pos.setY(pos.getY() + pixels);
    }
    else {
      if(pos.getY() + pixels > (screenHeight - (length / 2))) {
        pos.setY(screenHeight - (length / 2));
      }
      else if(pos.getY() + pixels < (length / 2)) {
        pos.setY(length / 2);
      }
    }
  }

  public void update(int timeDelta) {

  }
}