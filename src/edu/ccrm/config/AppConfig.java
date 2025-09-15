package edu.ccrm.config;

public class AppConfig {
    private static AppConfig appConfigInstance;
    private String dataDirectory = "data";

    private AppConfig() {}

    public static AppConfig getInstance() {
        if (appConfigInstance == null) appConfigInstance = new AppConfig();
        return appConfigInstance;
    }

    public void loadConfig() {
        System.out.println("Loaded AppConfig. Data folder: " + dataDirectory);
    }

    public String getDataFolder() {
        return dataDirectory;
    }
}