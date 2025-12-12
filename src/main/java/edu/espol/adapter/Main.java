package edu.espol.adapter;

public class Main {
    public static void main(String[] args) {
        OldThermalPrinter old = new OldThermalPrinter();
        Printer printer = new PrinterAdapter(old);

        printer.print("Factura 1023");
    }
}
