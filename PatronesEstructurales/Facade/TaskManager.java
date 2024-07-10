package PatronesEstructurales.Facade;

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
