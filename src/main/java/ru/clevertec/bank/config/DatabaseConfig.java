package ru.clevertec.bank.config;

import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class DatabaseConfig {

    private static Connection connection;
    private static final String CONFIG_PATH = "src/main/resources/application.yaml";

    public static void init(){

        try {
            Map<String, Map<String,Object>> data = new Yaml()
                    .load(new FileReader(CONFIG_PATH));

            String username = data.get("datasource").get("username").toString();
            String password = data.get("datasource").get("password").toString();
            String url = data.get("datasource").get("url").toString();

            connection = DriverManager.getConnection(url, username, password);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection() {
        return connection;
    }
}