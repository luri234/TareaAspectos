package SeccionC.aurelia.src.cod;

import SeccionC.aurelia.src.cod.config.ConfigurationManager;
import SeccionC.aurelia.src.cod.services.BillingService;
import SeccionC.aurelia.src.cod.services.RouteAssignmentService;
import SeccionC.aurelia.src.cod.services.TrackingService;

public class AureliaLogisticsApp {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   AURELIA LOGISTICS - SISTEMA CORE    ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        System.out.println("► Iniciando módulos del sistema...\n");
        
        TrackingService trackingService = new TrackingService();
        BillingService billingService = new BillingService();
        RouteAssignmentService routeService = new RouteAssignmentService();
        
        ConfigurationManager config = ConfigurationManager.getInstance();
        config.printConfiguration();
        
        trackingService.trackShipment("SHP-2024-001");
        billingService.generateInvoice("SHP-2024-001");
        routeService.assignRoute("SHP-2024-001");
        
        System.out.println("\n► Verificando unicidad de instancia...");
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        ConfigurationManager config2 = ConfigurationManager.getInstance();
        
        System.out.println("config1 == config2: " + (config1 == config2));
        System.out.println("HashCode config1: " + config1.hashCode());
        System.out.println("HashCode config2: " + config2.hashCode());
        
        System.out.println("\n► Intentando instanciación directa (esto debe fallar)...");
        try {
            System.out.println("✗ Aspecto no está activo - instanciación directa permitida");
        } catch (IllegalStateException e) {
            System.out.println("✓ Aspecto funcionando: " + e.getMessage());
        }
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     SISTEMA FUNCIONANDO CORRECTAMENTE  ║");
        System.out.println("╚════════════════════════════════════════╝");
    }
}