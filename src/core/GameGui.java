package src.core;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import src.core.GameLogic;

public class GameGui
{
  private final GameScreen screen;
  private final GameLogic game;

  private GameGui() {
    screen = null;
    game = null;
  }

  public GameGui(GameLogic game) {
    this.game = game;
    screen = new GameScreen(this.game.getScreenWidth(), 
                            this.game.getScreenHeight());

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  
    frame.addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        System.out.println("Window close");
        game.stop();
      }
    });

    frame.add(screen, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
  } 
}

class GameScreen extends JPanel {
  private final int screenWidth;
  private final int screenHeight;

  private GameScreen() {
    this.screenWidth = 0;
    this.screenHeight = this.screenWidth;
  }

  public GameScreen(int screenWidth, int screenHeight) {
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
  }

  public Dimension getPreferredSize() 
  {
      return new Dimension(screenWidth, screenHeight);
  }  

  protected void paintComponent(Graphics g) 
  {
    super.paintComponent(g);
  }  
}