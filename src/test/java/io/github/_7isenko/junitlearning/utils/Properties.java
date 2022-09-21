package io.github._7isenko.junitlearning.utils;

import java.io.FileInputStream;
import java.io.IOException;

// SRC: https://habr.com/ru/post/502292/
public class Properties {
    protected static FileInputStream fileInputStream;
    protected static java.util.Properties PROPERTIES;

    static {
        try {
            //указание пути до файла с настройками
            fileInputStream = new FileInputStream("src/test/resources/configuration.properties");
            PROPERTIES = new java.util.Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            //обработка возможного исключения (нет файла и т.п.)
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * метод для возврата строки со значением из файла с настройками
     */
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
