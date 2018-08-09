package src.core;

import src.core.GameUniqueId;
import src.core.GameCoord;

public class GameObject
{
  private final int objectId;
  private GameCoord pos;
  
  GameObject(GameCoord pos) {
    objectId = GameUniqueId.getInstance().getUniqueId();
    System.out.println("Created object with id " + Integer.toString(objectId));
    this.pos = pos;
  }

}