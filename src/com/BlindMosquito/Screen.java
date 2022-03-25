package com.BlindMosquito;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Screen extends JFrame implements MouseListener, MouseMotionListener {

  private boolean isWorking = true;
  public boolean getIsWorking() { return isWorking; }

  private class KeyListen implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) { }
    @Override
    public void keyPressed(KeyEvent keyEvent) {}
    @Override
    public void keyReleased(KeyEvent keyEvent) { if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(1); }
  }


  private class MouseLocation {
    public int X;
    public int Y;
  }

  /**
   * Had help with this part from stack overflow
   * https://stackoverflow.com/questions/40945461/java-swing-draw-rectangle-in-mouse-drag-and-drop/40945778
   */
  private class Pane extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
      if(start == null || end == null) return;
      super.paintComponent(g);
      g.setColor(new Color(29, 58, 116, 60));
      //g.fillRect(getStartX(), getStartY(), getEndX(), getEndY());
      Rectangle rectangle = getRectangle();
      g.fillRect((int)rectangle.getX(), (int)rectangle.getY(), (int)rectangle.getWidth(), (int)rectangle.getHeight());
    }
  }

  private MouseLocation start = null;
  private MouseLocation end = null;
  private Pane pane = null;

  public Screen() {
    pane = new Pane();
    this.setContentPane(pane);
    this.setSize(600, 400);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setUndecorated(true);
    this.setOpacity(0.2f);
    this.setVisible(true);
    KeyListen keys = new KeyListen();
    this.addKeyListener(keys);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
  }

  public Rectangle getRectangle() {
    if(start == null || end == null) return null;
    int startX = Math.min(start.X, end.X);
    int startY = Math.min(start.Y, end.Y);
    int endX = Math.abs(start.X - end.X);
    int endY = Math.abs(start.Y - end.Y);
    Rectangle rectangle = new Rectangle();
    rectangle.x = startX;
    rectangle.y = startY;
    rectangle.setSize(endX, endY);
    return rectangle;
  }



  private int getStartX() {
    if(start == null || end == null) return 0;
    return Math.min(start.X, end.X);
  }
  private int getStartY() {
    if(start == null || end == null) return 0;
    return Math.min(start.Y, end.Y);
  }

  private int getEndX() {
    if(start == null || end == null) return 0;
    return Math.abs(start.X - end.X);
  }
  private int getEndY() {
    if(start == null || end == null) return 0;
    return Math.abs(start.Y - end.Y);
  }
  @Override
  public void mouseClicked(MouseEvent mouseEvent) { }
  @Override
  public void mousePressed(MouseEvent mouseEvent) {
    start = new MouseLocation();
    start.X = mouseEvent.getX();
    start.Y = mouseEvent.getY();
  }
  @Override
  public void mouseReleased(MouseEvent mouseEvent) {
    isWorking = false;
  }
  @Override
  public void mouseEntered(MouseEvent mouseEvent) { }
  @Override
  public void mouseExited(MouseEvent mouseEvent) { }
  @Override
  public void mouseDragged(MouseEvent mouseEvent) {
    end = new MouseLocation();
    end.X = mouseEvent.getX();
    end.Y = mouseEvent.getY();
    pane.repaint();
  }
  @Override
  public void mouseMoved(MouseEvent mouseEvent) { }
}
