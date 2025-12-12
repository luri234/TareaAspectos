package SeccionC.aurelia.src.cod.services;

import SeccionC.aurelia.src.cod.config.ConfigurationManager;

public class BillingService {
    private final ConfigurationManager config;
    
    public BillingService() {
        this.config = ConfigurationManager.getInstance();
    }
    
    public void generateInvoice(String shipmentId) {
        double baseCost = Double.parseDouble(
            config.getValue("base.cost", "10.00")
        );
        String reportsPath = config.getValue("reports.path");
        
        System.out.println("\n[BillingService] Generando factura para: " + shipmentId);
        System.out.println("  → Costo base: $" + baseCost);
        System.out.println("  → Reportes en: " + reportsPath);
    }
}