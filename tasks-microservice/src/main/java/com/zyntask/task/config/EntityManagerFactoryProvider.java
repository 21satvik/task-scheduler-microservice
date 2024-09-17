package com.zyntask.task.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class EntityManagerFactoryProvider {

    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            Map<String, String> properties = new HashMap<>();

            // Load environment variables
            String dbUrl = System.getenv("DB_URL");
            String dbUser = System.getenv("DB_USER");
            String dbPassword = System.getenv("DB_PASSWORD");

            // Populate the properties with environment variables
            properties.put("jakarta.persistence.jdbc.url", dbUrl);
            properties.put("jakarta.persistence.jdbc.user", dbUser);
            properties.put("jakarta.persistence.jdbc.password", dbPassword);

            // Create the EntityManagerFactory with the loaded properties
            emf = Persistence.createEntityManagerFactory("taskPU", properties);
        }
        return emf;
    }
}