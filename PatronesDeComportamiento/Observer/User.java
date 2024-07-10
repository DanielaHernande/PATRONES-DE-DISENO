package PatronesDeComportamiento.Observer;

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
