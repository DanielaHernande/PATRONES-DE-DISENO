package PatronesCreacionales.FactoryMethod;

public class NormalTask extends Task {

    // Implementación específica para tareas normales
}

public abstract class Task {

    // Métodos comunes para todas las tareas
}

public class UrgentTask extends Task {
    
    // Implementación específica para tareas urgentes
}

public class TaskFactory {

    public static Task createTask(String type) {
        
        if ("normal".equalsIgnoreCase(type)) {
            return new NormalTask();

        } else if ("urgent".equalsIgnoreCase(type)) {
            return new UrgentTask();
        }
        
        return null;
    }
}


