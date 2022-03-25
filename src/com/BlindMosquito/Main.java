package com.BlindMosquito;

import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        Screen screen = new Screen();
        Rectangle rectangle = null;
        while(true) {
            System.out.println(screen.getIsWorking());
            if(!screen.getIsWorking()) {
                rectangle = screen.getRectangle();
                screen.dispose();
                break;
            }
        }
        if(rectangle == null) return;
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        BufferedImage img = robot.createScreenCapture(rectangle);
        TransferableImage ti = new TransferableImage(img);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ti, null);
    }
}
