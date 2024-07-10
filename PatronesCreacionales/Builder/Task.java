package PatronesCreacionales.Builder;

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
