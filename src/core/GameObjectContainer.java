package src.core;

import java.util.Vector;

public class GameObjectContainer
{
  private static GameObjectContainer instance = new GameObjectContainer();

  private final Vector<GameObject> objects = new Vector<GameObject>();

  private GameObjectContainer() {}

  public static GameObjectContainer getApi() {
    return instance;
  }

  public void addGameObject(final GameObject object) {
    objects.add(object);
  }

  public void removeGameObject(final GameObject object) {
    objects.remove(object);
  }

  public Vector<GameObject> getGameObjects() {
    return objects;
  }
}