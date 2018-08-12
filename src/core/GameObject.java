package src.core;

import src.core.GameUniqueId;
import java.awt.Graphics;

import src.core.GameCoord;

public abstract class GameObject
{
  private final int objectId;
  protected GameCoord pos;

  public GameObject() {
    objectId = 0;
    this.pos = null;
  }
  
  public GameObject(GameCoord pos) {
    objectId = GameUniqueId.getApi().getUniqueId();
    System.out.println("Created object with id " + Integer.toString(objectId));
    this.pos = pos;
    GameObjectContainer.getApi().addGameObject(this);
  }

  public GameCoord getPos() {
    return pos;
  }

  public abstract void paint(Graphics g);

  public abstract void update(int timeDelta);
}