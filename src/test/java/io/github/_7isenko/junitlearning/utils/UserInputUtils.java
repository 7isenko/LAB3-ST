package io.github._7isenko.junitlearning.utils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * @author 7isenko
 */
public class UserInputUtils {
    public static void pressEsc() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getClipboard() {
        try {
            return (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (HeadlessException | IOException | UnsupportedFlavorException e) {
            return "";
        }
    }
}
