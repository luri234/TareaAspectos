package SeccionC.aurelia.src.cod.services;

import SeccionC.aurelia.src.cod.config.ConfigurationManager;

public class TrackingService {
    private final ConfigurationManager config;
    
    public TrackingService() {
        this.config = ConfigurationManager.getInstance();
    }
    
    public void trackShipment(String shipmentId) {
        String apiKey = config.getValue("api.tracking.key");
        String logsPath = config.getValue("logs.path");
        
        System.out.println("\n[TrackingService] Rastreando envío: " + shipmentId);
        System.out.println("  → Usando API key: " + (apiKey.isEmpty() ? "NO CONFIGURADA" : "***"));
        System.out.println("  → Logs en: " + logsPath);
    }
}
