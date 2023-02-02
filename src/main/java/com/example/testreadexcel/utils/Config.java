package com.example.testreadexcel.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    public static final String BASE_CONFIG_PATH = "src/main/resources/application.properties";
    static FileInputStream fis;

    static {
        if (checkExistFile(BASE_CONFIG_PATH) == null) {
            System.out.println("File not found!");
        }
        properties = getProperties(BASE_CONFIG_PATH);
        String environment = properties.getProperty("project.environment");
        if (environment == null) {
            System.out.println("Value not found!");
        }
        String path = String.format("src/main/resources/application.%s.properties", environment);
        if (checkExistFile(path) == null) {
            System.out.println("File not found!");
        }
        properties = getProperties(path);
        System.out.println("It feel ok!");
    }

    public static String getSetting(String key) {
        return properties.getProperty(key);
    }

    private static String checkExistFile(String path) {
        File file = new File(path);
        if (file != null) {
            return path;
        } else {
            return null;
        }
    }

    private static Properties getProperties(String path) {
        try {
            fis = new FileInputStream(path);
            properties.load(fis);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
