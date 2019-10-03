//this class represents a task that have to be done

public class Thing {
    private String taskDescription;
    private boolean isReady = false;
    //private int priority=0;

    public Thing(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Thing(String taskDescription, boolean isReady) {
        this.taskDescription = taskDescription;
        this.isReady = isReady;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
