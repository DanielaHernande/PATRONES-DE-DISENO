package PatronesCreacionales.Singleton;

public class ConfigManager {
    
    private static ConfigManager instance;

    private ConfigManager() {
        // Configuración inicial
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }
}
