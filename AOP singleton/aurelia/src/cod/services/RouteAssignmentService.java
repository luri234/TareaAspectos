package SeccionC.aurelia.src.cod.services;

import SeccionC.aurelia.src.cod.config.ConfigurationManager;

public class RouteAssignmentService {
    private final ConfigurationManager config;
    
    public RouteAssignmentService() {
        this.config = ConfigurationManager.getInstance();
    }
    
    public void assignRoute(String shipmentId) {
        int maxTime = config.getIntValue("max.delivery.time", 48);
        String mapsApiKey = config.getValue("api.maps.key");
        
        System.out.println("\n[RouteAssignmentService] Asignando ruta para: " + shipmentId);
        System.out.println("  → Tiempo máximo: " + maxTime + " horas");
        System.out.println("  → API Maps: " + (mapsApiKey.isEmpty() ? "NO CONFIGURADA" : "***"));
    }
}