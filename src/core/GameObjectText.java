package src.core;

import java.awt.Color;
import java.awt.Graphics;
import src.core.GameCoord;

public class GameObjectText extends GameObject
{
  private String str;

  private GameObjectText() {
    super(new GameCoord(0, 0));
    str = "";
  }

  public GameObjectText(GameCoord pos, String str) {
    super(pos);
    this.str = str;
  }

  public void setText(String str) {
    this.str = str;
  }
  
  public void paint(Graphics g) {
    g.setColor(new Color(50, 200, 200));
    g.drawString(str, pos.getX(), pos.getY());
  }
  
  public void update(int timeDelta) {

  }
}