package SeccionC.aurelia.src.cod.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConfigurationManager {
    
    private static volatile ConfigurationManager instance;
    private final Map<String, String> configValues;
    private final ReadWriteLock lock;
    
    private static final String CONFIG_FILE = "config/application.properties";
    
    private ConfigurationManager() {
        this.configValues = new HashMap<>();
        this.lock = new ReentrantReadWriteLock();
        load();
        System.out.println("✓ ConfigurationManager inicializado");
    }
    
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }
    
    private void load() {
        Properties props = new Properties();
        
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            props.load(fis);
            
            lock.writeLock().lock();
            try {
                // Cargar configuraciones de conexión a BD
                configValues.put("db.host", props.getProperty("db.host", "localhost"));
                configValues.put("db.port", props.getProperty("db.port", "5432"));
                configValues.put("db.name", props.getProperty("db.name", "aurelia_db"));
                configValues.put("db.user", props.getProperty("db.user", "admin"));
                configValues.put("db.password", props.getProperty("db.password", "secret"));
                
                // APIs externas
                configValues.put("api.tracking.key", props.getProperty("api.tracking.key", ""));
                configValues.put("api.maps.key", props.getProperty("api.maps.key", ""));
                
                // Políticas internas
                configValues.put("max.delivery.time", props.getProperty("max.delivery.time", "48"));
                configValues.put("base.cost", props.getProperty("base.cost", "10.00"));
                
                // Rutas de archivos
                configValues.put("logs.path", props.getProperty("logs.path", "/var/log/aurelia"));
                configValues.put("reports.path", props.getProperty("reports.path", "/var/reports"));
                
                System.out.println("✓ Configuración cargada: " + configValues.size() + " parámetros");
            } finally {
                lock.writeLock().unlock();
            }
            
        } catch (IOException e) {
            System.err.println("⚠ No se pudo cargar el archivo de configuración, usando valores por defecto");
            loadDefaults();
        }
    }
    
    private void loadDefaults() {
        lock.writeLock().lock();
        try {
            configValues.put("db.host", "localhost");
            configValues.put("db.port", "5432");
            configValues.put("max.delivery.time", "48");
            configValues.put("base.cost", "10.00");
            configValues.put("logs.path", "/var/log/aurelia");
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public String getValue(String key) {
        lock.readLock().lock();
        try {
            return configValues.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }
    
    public String getValue(String key, String defaultValue) {
        String value = getValue(key);
        return value != null ? value : defaultValue;
    }
    
    public int getIntValue(String key, int defaultValue) {
        String value = getValue(key);
        try {
            return value != null ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    public synchronized void reload() {
        System.out.println("↻ Recargando configuración...");
        load();
    }
    
    public void printConfiguration() {
        lock.readLock().lock();
        try {
            System.out.println("\n=== Configuración Actual ===");
            configValues.forEach((key, value) -> {
                if (key.toLowerCase().contains("password")) {
                    System.out.println(key + " = ********");
                } else {
                    System.out.println(key + " = " + value);
                }
            });
            System.out.println("===========================\n");
        } finally {
            lock.readLock().unlock();
        }
    }
}