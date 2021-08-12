package com.pets.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static ConfigManager manager;
    private static final Properties prop = new Properties();
    String propFileName = "config.properties";
    //private constructor
    private ConfigManager() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        prop.load(inputStream);
    }
    //Get Instance method
    public static ConfigManager getInstance(){
        if (manager == null) {
            synchronized (ConfigManager.class) {
                try {
                    manager = new ConfigManager();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return manager;
    }
    //Get the string and load file
    public String getString(String key){
        return System.getProperty(key, prop.getProperty(key));
    }

}
