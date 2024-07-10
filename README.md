# PATRONES-DE-DISENO

# Patrones Creacionales

# Singleton:

 - Qué es: Este patrón asegura que una clase solo tenga una única instancia y proporciona un punto de acceso global a esa instancia.

 - Por qué lo usamos: Ideal para cosas que solo deben tener una única instancia en toda la aplicación. Pensemos en un administrador de configuración o una conexión a la base de datos. No queremos tener múltiples instancias.

 - Aplicación: Usaremos un Singleton para la configuración de la aplicación. De esta manera, cada vez que necesitemos acceder a la configuración, estaremos seguros de que estamos usando la misma instancia.

# Ejemplo:

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

----------------------------------------------------------------------------
# Factory Method:

 - Qué es: Este patrón nos permite crear objetos sin especificar la clase exacta que será creada. En lugar de eso, usamos un método que decide qué clase instanciar.

 - Por qué lo usamos: Muy útil cuando tenemos diferentes tipos de objetos y no queremos acoplar nuestro código a clases específicas. Por ejemplo, diferentes tipos de tareas como tareas normales y urgentes.

 - Aplicación: Usamos un Factory Method para crear diferentes tipos de tareas.

# Ejemplo:

    public abstract class Task {

        // Métodos comunes para todas las tareas
    }

    public class NormalTask extends Task {

        // Implementación específica para tareas normales
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

----------------------------------------------------------------------------
# Builder:

 - Qué es: Este patrón nos ayuda a construir objetos complejos paso a paso. Es muy útil cuando hay muchas opciones y parámetros.

 - Por qué lo usamos: Perfecto para crear tareas con muchas propiedades opcionales como título, descripción, etc.

 - Aplicación: Usamos un Builder para crear tareas con múltiples propiedades opcionales.

# Ejemplo 

    public class Task {
        private String title;
        private String description;
        private boolean isUrgent;
        private String assignee;

        private Task(TaskBuilder builder) {
            this.title = builder.title;
            this.description = builder.description;
            this.isUrgent = builder.isUrgent;
            this.assignee = builder.assignee;
        }

        public static class TaskBuilder {
            private String title;
            private String description;
            private boolean isUrgent;
            private String assignee;

            public TaskBuilder setTitle(String title) {
                this.title = title;
                return this;
            }

            public TaskBuilder setDescription(String description) {
                this.description = description;
                return this;
            }

            public TaskBuilder setUrgency(boolean isUrgent) {
                this.isUrgent = isUrgent;
                return this;
            }

            public TaskBuilder setAssignee(String assignee) {
                this.assignee = assignee;
                return this;
            }

            public Task build() {
                return new Task(this);
            }
        }
    }

----------------------------------------------------------------------------

## Patrones Estructurales

# Decorator:

 - Qué es: Este patrón nos permite añadir funcionalidades a un objeto de manera dinámica sin cambiar su estructura.

 - Por qué lo usamos: Ideal para añadir características adicionales a nuestras tareas sin modificar la clase Task original.

 - Aplicación: Decoramos tareas con características adicionales como etiquetas.

# Ejemplo

    public interface Task {
        void perform();
    }

    public class BasicTask implements Task {
        @Override
        public void perform() {
            System.out.println("Performing basic task.");
        }
    }

    public class TaskDecorator implements Task {
        protected Task decoratedTask;

        public TaskDecorator(Task task) {
            this.decoratedTask = task;
        }

        @Override
        public void perform() {
            decoratedTask.perform();
        }
    }

    public class TaggedTask extends TaskDecorator {
        private String tag;

        public TaggedTask(Task task, String tag) {
            super(task);
            this.tag = tag;
        }

        @Override
        public void perform() {
            super.perform();
            System.out.println("Tag: " + tag);
        }
    }

----------------------------------------------------------------------------

# Facade:

 - Qué es: Este patrón proporciona una interfaz simplificada a un subsistema complejo.

 - Por qué lo usamos: Para ocultar la complejidad de varias operaciones de gestión de tareas detrás de una interfaz simple.

 - Aplicación: Una clase TaskManager que proporciona métodos fáciles para las operaciones comunes.

# Ejemplo:

    public class TaskManager {
        private TaskService taskService;
        private UserService userService;

        public TaskManager() {
            this.taskService = new TaskService();
            this.userService = new UserService();
        }

        public void assignTaskToUser(Task task, User user) {
            userService.assignTask(user, task);
            taskService.updateTask(task);
        }

        public void createAndAssignTask(String title, User user) {
            Task task = new Task.TaskBuilder().setTitle(title).build();
            assignTaskToUser(task, user);
        }
    }

----------------------------------------------------------------------------

## Patrones de Comportamiento

# Command:

 - Qué es: Este patrón encapsula una solicitud como un objeto, permitiendo parametrizar clientes con diferentes solicitudes, encolar solicitudes o registrar operaciones.

 - Por qué lo usamos: Para desacoplar el objeto que invoca la operación del objeto que realmente la ejecuta.

 - Aplicación: Usamos comandos para operaciones de tareas como crear, editar y eliminar.

# Ejemplo:

    public interface Command {
        void execute();
    }

    public class CreateTaskCommand implements Command {
        private Task task;

        public CreateTaskCommand(Task task) {
            this.task = task;
        }

        @Override
        public void execute() {
            System.out.println("Creating task: " + task.getTitle());
            // Lógica para crear la tarea
        }
    }

    public class DeleteTaskCommand implements Command {
        private Task task;

        public DeleteTaskCommand(Task task) {
            this.task = task;
        }

        @Override
        public void execute() {
            System.out.println("Deleting task: " + task.getTitle());
            // Lógica para eliminar la tarea
        }
    }

    // Uso del patrón Command
    public class TaskController {
        private Command command;

        public void setCommand(Command command) {
            this.command = command;
        }

        public void executeCommand() {
            command.execute();
        }
    }

----------------------------------------------------------------------------

# Observer:

 - Qué es: Este patrón define una dependencia uno-a-muchos entre objetos, de manera que cuando un objeto cambia de estado, todos sus dependientes son notificados y actualizados automáticamente.

- Por qué lo usamos: Para implementar sistemas de notificaciones o suscripciones.

- Aplicación: Notificar a los usuarios cuando una tarea se actualiza o se les asigna una nueva tarea.

# Ejemplo:

    public interface Observer {
        void update(Task task);
    }

    public class User implements Observer {
        private String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public void update(Task task) {
            System.out.println(name + " notified about task: " + task.getTitle());
        }
    }

    public class TaskSubject {
        private List<Observer> observers = new ArrayList<>();
        private Task task;

        public void setTask(Task task) {
            this.task = task;
            notifyAllObservers();
        }

        public void attach(Observer observer) {
            observers.add(observer);
        }

        private void notifyAllObservers() {
            for (Observer observer : observers) {
                observer.update(task);
            }
        }
    }

----------------------------------------------------------------------------

# Strategy:

 - Qué es: Este patrón define una familia de algoritmos, encapsula cada uno de ellos y los hace intercambiables. El patrón Strategy permite que el algoritmo varíe independientemente de los clientes que lo usan.

 - Por qué lo usamos: Para seleccionar el algoritmo apropiado en tiempo de ejecución.

 - Aplicación: Elegir diferentes estrategias de generación de informes, como generar informes en PDF o Excel.

# Ejemplo:

    public interface ReportStrategy {
        void generateReport(List<Task> tasks);
    }

    public class PDFReportStrategy implements ReportStrategy {
        @Override
        public void generateReport(List<Task> tasks) {
            System.out.println("Generating PDF report");
            // Lógica para generar reporte en PDF
        }
    }

    public class ExcelReportStrategy implements ReportStrategy {
        @Override
        public void generateReport(List<Task> tasks) {
            System.out.println("Generating Excel report");
            // Lógica para generar reporte en Excel
        }
    }

    // Uso del patrón Strategy
    public class ReportGenerator {
        private ReportStrategy strategy;

        public void setStrategy(ReportStrategy strategy) {
            this.strategy = strategy;
        }

        public void generateReport(List<Task> tasks) {
            strategy.generateReport(tasks);
        }
    }
-----------------------------------------------------------------------

## Conclusión

    Usando estos patrones de diseño, podemos estructurar una aplicación de gestión de tareas de manera más modular y mantenible. Cada patrón aborda problemas específicos y nos ayuda a organizar el código de manera más eficiente. Con los patrones creacionales, gestionamos la creación de objetos. Los patrones estructurales nos ayudan a organizar mejor nuestras clases y objetos. Y los patrones de comportamiento nos permiten manejar mejor la interacción y la responsabilidad entre los objetos.