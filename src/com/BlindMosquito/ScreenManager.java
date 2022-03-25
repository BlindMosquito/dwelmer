package com.BlindMosquito;

import org.w3c.dom.css.Rect;

import javax.swing.plaf.InsetsUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class ScreenManager {
  private List<Screen> screens = null;

  public ScreenManager() { run(); }

  /** Controls the flow of the program **/
  public void run() {
    setup();                                        // setup the screens
    Rectangle bounds = programLoop();               // Gets the boundaries for the screenshot
    disposeAll();                                   // Close all the frames so they are not in the way
    takeScreenshot(bounds);                         // Send info to take screenshot
  }

  /** This gets the bounds from all the displays and creates screens to that size and location */
  private void setup() {
    List<Rectangle> bounds = boundsSetup();         // get the bounds from the displays
    if(bounds == null || bounds.size() < 1) return; // if you have no displays then why you here, so exit
    for(Rectangle bound : bounds) {                 // create a new rectangle for each display
      Screen screen = new Screen();                 // Create a new screen
      screen.setBounds(bound);                      // Have its size and location mimic the display
      if(screens == null) screens = new ArrayList<Screen>();
      screens.add(screen);
    }
  }

  /**
   * This is the main program loop
   * Keeps checking if all windows are busy
   * When a frame is done then it takes its rectangle and coordinates to get the screenshot
   */
  private Rectangle programLoop() {
    if(screens == null) return null;      // If doesn't exist then leave
    boolean keepGoing = true;             // Checks if main loop is done
    Rectangle bounds = null;              // Rectangle to return
    while(keepGoing) {                    // program loop checks if screens are ready
      for(Screen screen : screens) {      // for each screen
        if(screen.isDone()) {             // If true the screenshot should be ready
          bounds = screen.getRectangle(); // Get the bounds from the screen that finished
          if(bounds != null) {            // bounds shouldn't be null, but if they are then might be start of program
            bounds.x += screen.getX();    // offset the returned x position to the screens
            bounds.y += screen.getY();    // offset the returned y position to the screens
            keepGoing = false;            // All done
          }
        }
      }
    }
    return bounds;                        // Return the bounds to get screen shot
  }

  /** Disposes all the open screens */
  private void disposeAll() {
    if(screens == null || screens.isEmpty()) return;  // Should have open screens but if not then leave
    for(Screen screen : screens) {
      screen.dispose();
    }
  }

  /** Takes screenshot and sends it to the clipboard */
  private void takeScreenshot(Rectangle bounds) {
    if(bounds == null) return;            // if no bounds then leave
    Robot robot = null;                   // robot who is going to do my work
    try {
      robot = new Robot();                // robots can be unstable creating a new one
    } catch (AWTException except) {
      except.printStackTrace();
    }
    BufferedImage img = robot.createScreenCapture(bounds);
    TransferableImage transfer = new TransferableImage(img);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(transfer, null);
  }

  /**
   * Gets the location and size of all the screens
   * @return List of screen boundaries and locations
   */
  private List<Rectangle> boundsSetup() {
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice[] devices = env.getScreenDevices();
    List<Rectangle> bounds = null;
    for (GraphicsDevice device : devices) {
      if(bounds == null) bounds = new ArrayList<Rectangle>();
      GraphicsConfiguration config = device.getDefaultConfiguration();
      bounds.add(config.getBounds());
    }
    return bounds;
  }

  /**
   * This finds the furthest away monitor for horz and vert.
   * Then this returns a rectangle the spans across all monitors
   */
  private Rectangle getMaxSize(List<Rectangle> bounds) {
    if(bounds == null) return null;
    Rectangle rect = new Rectangle();
    rect.x = 0;
    rect.y = 0;
    rect.width = 0;
    rect.height = 0;

    for(Rectangle bound : bounds) {
      if(rect.x < bound.x) {
        rect.x = bound.x;
        rect.width = bound.width;
      } else {
        if (rect.width < 1) rect.width = bound.width;
      }
      if(rect.y < bound.y) {
        rect.y = bound.y;
        rect.height = bound.height;
      } else {
        if (rect.height < 1) rect.height = bound.height;
      }
  }
    rect.width += rect.x;
    rect.height += rect.y;
    rect.x = 0;
    rect.y = 0;
    return rect;
  }
}
