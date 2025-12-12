package edu.espol.singleton;

public class DatabaseConnection {

    // Única instancia (lazy)
    private static DatabaseConnection instance;

    private DatabaseConnection() {
        System.out.println("Creando conexión a la base de datos...");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Conectado a la base de datos.");
    }

    public void disconnect() {
        System.out.println("Desconectando...");
    }
}
