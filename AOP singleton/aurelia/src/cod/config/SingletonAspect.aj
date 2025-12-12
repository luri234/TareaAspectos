import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspecto que intercepta intentos de instanciación directa
 * del ConfigurationManager y los redirige al Singleton.
 */
@Aspect
public class SingletonAspect {
    
    /**
     * Pointcut que detecta intentos de crear ConfigurationManager directamente
     */
    @Pointcut("call(com.aurelia.logistics.config.ConfigurationManager.new(..))")
    public void configManagerConstruction() {}
    
    /**
     * Advice que se ejecuta ANTES de intentar construir una instancia
     */
    @Before("configManagerConstruction()")
    public void enforceeSingleton() {
        System.err.println("\n⚠⚠⚠ ADVERTENCIA ⚠⚠⚠");
        System.err.println("Intento de instanciación directa de ConfigurationManager detectado.");
        System.err.println("Usa ConfigurationManager.getInstance() en su lugar.");
        System.err.println("⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠⚠\n");
        
        throw new IllegalStateException(
            "No se permite instanciación directa de ConfigurationManager. " +
            "Usa ConfigurationManager.getInstance()"
        );
    }
}