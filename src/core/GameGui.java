package src.core;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

import src.core.GameLogic;

public class GameGui
{
  private final GameScreen screen;
  private final GameRunner runner;

  private GameGui() {
    screen = null;
    runner = null;
  }

  public GameGui(GameRunner runner) {
    this.runner = runner;
    screen = new GameScreen(runner.getGame());

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setResizable(false);
  
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.out.println("Window close");
        runner.stop();
      }
    });

    frame.addKeyListener(new KeyListener() {
      public void keyPressed(KeyEvent event) {
        //System.out.println("Key pressed: " + event.getKeyCode());
        runner.getGame().onKeyPressed(event.getKeyCode());
      }

      public void keyTyped(KeyEvent e) {
        
      }

      public void keyReleased(KeyEvent e) {
        
      }
    });

    frame.add(screen, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
  } 

  public void redraw() {
    screen.redraw();
  }
}

class GameScreen extends JPanel {
  private final GameLogic game;

  private GameScreen() {
    this.game = null;
  }

  public GameScreen(GameLogic game) {
    this.game = game;
  }

  public void redraw() {
    repaint();
  }

  public Dimension getPreferredSize() 
  {
      return new Dimension(game.getScreenWidth(), game.getScreenHeight());
  }  

  protected void paintComponent(Graphics g) 
  {
    super.paintComponent(g);

    Graphics2D g2D = (Graphics2D) g;
    g2D.setStroke(new BasicStroke(3));
    
    Vector<GameObject> gameObjects = game.getGameObjects();
    gameObjects.forEach(gameObject -> gameObject.paint(g));
  }
}