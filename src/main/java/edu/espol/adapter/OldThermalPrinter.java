package edu.espol.adapter;

public class OldThermalPrinter {
    public void warmUp() {
        System.out.println("Calentando impresora térmica antigua...");
    }

    public void sendToPort(String data) {
        System.out.println("Imprimiendo en puerto paralelo: " + data);
    }
}
