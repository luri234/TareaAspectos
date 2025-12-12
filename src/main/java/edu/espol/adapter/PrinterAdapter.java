package edu.espol.adapter;

public class PrinterAdapter implements Printer {

    private OldThermalPrinter oldPrinter;

    public PrinterAdapter(OldThermalPrinter oldPrinter) {
        this.oldPrinter = oldPrinter;
    }

    @Override
    public void print(String text) {
        oldPrinter.warmUp();
        oldPrinter.sendToPort(text);
    }
}
