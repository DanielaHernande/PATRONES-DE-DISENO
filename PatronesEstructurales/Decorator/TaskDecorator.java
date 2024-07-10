package PatronesEstructurales.Decorator;

/**
 * TaskDecorator
 */

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

private interface Task {
    void perform();
}

public class BasicTask implements Task {
    @Override
    public void perform() {
        System.out.println("Performing basic task.");
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
