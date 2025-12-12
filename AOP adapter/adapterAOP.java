public interface Printer {
    void print(String message);
}

public class OldThermalPrinter {
    public void printText(String text) {
        System.out.println("OLD printer -> " + text);
    }
}

public aspect AdapterAspect {

    pointcut printerCall(Printer p, String msg) :
        call(void Printer.print(String)) && target(p) && args(msg);

    void around(Printer p, String msg) : printerCall(p, msg) {
        if (p instanceof OldThermalPrinter) {
            OldThermalPrinter old = (OldThermalPrinter) p;
            old.printText(msg);
        } else {
            proceed(p, msg);
        }
    }
}

Printer printer = (Printer) new OldThermalPrinter();
printer.print("Factura N° 1023");