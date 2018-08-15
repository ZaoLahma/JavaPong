import junit.framework.TestCase;
import static org.junit.Assert.*;
import src.core.GameUniqueId;
public class GameUniqueIdTest extends TestCase {

  public void testGetUniqueId() {
    final int firstId = GameUniqueId.getApi().getUniqueId();
    final int secondId = GameUniqueId.getApi().getUniqueId();

    assertTrue(firstId != secondId);
    assertTrue(secondId > firstId);
  }
}