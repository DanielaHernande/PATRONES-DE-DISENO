package PatronesDeComportamiento;

/**
 * Command
 */
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
