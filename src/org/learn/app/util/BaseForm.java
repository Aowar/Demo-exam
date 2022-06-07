package org.learn.app.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BaseForm extends JFrame {
    public static String APP_TITLE = "Test";
    public static Image APP_ICON = null;

    static {
        try {
            APP_ICON = ImageIO.read(BaseForm.class.getClassLoader().getResource("Одежда для ручек.png"));
        } catch (Exception e) {
            e.printStackTrace();
            DialogUtil.showError(null, "Ошибка загрузки иконки приложения");
        }
    }

    public BaseForm(int width, int height) {
        setMinimumSize(new Dimension(width, height));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 - width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2 - height/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(APP_TITLE);
        setIconImage(APP_ICON);
    }
}
