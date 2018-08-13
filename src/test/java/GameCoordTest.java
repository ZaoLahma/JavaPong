import junit.framework.TestCase;
import static org.junit.Assert.*;
import src.core.GameCoord;
public class GameCoordTest extends TestCase {

  public void testGameCoordCTOR() {
    final int EXPECTED_X_COORD = 25;
    final int EXPECTED_Y_COORD = 40;
    GameCoord coord = new GameCoord(EXPECTED_X_COORD, EXPECTED_Y_COORD);

    assertTrue(coord.getX() == EXPECTED_X_COORD);
    assertTrue(coord.getY() == EXPECTED_Y_COORD);
  }
}