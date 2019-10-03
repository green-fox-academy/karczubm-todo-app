import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//this class represents all the tasks
public class ToDo {
    List<Thing> thingsToDo;


    public ToDo(List<String> savedTasks) {
        thingsToDo = new ArrayList<>();
        for (String task : savedTasks) {
            if (task.charAt(0) == '1') {
                thingsToDo.add(new Thing(task.substring(1), true));
            } else {
                thingsToDo.add(new Thing(task.substring(1), false));
            }
        }
    }

    public void usageInformation() {
        System.out.println("Command Line Todo application");
        System.out.println("=============================");
        System.out.println("Command line arguments:");
        System.out.println("-l   Lists all the tasks");
        System.out.println("-u   Lists undone tasks");
        System.out.println("-a   Adds a new task");
        System.out.println("-r   Removes a task");
        System.out.println("-c   Completes a task");
    }

    public void addNewTask(String taskDescription) {
        Thing task = new Thing(taskDescription);
        thingsToDo.add(task);
    }

    public void removeTask(int numberOfUnwantedTask) {
        thingsToDo.remove(numberOfUnwantedTask - 1);
    }

    public void completeTask(int numberOfCompletedTask) {
        thingsToDo.get(numberOfCompletedTask - 1).setReady(true);
    }

    public void listTasks() {
        for (int i = 0; i < thingsToDo.size(); i++) {
            if (thingsToDo.get(i).isReady()) {
                System.out.println((i + 1) + " - " + "[X] " + thingsToDo.get(i).getTaskDescription());
            } else {
                System.out.println((i + 1) + " - " + "[ ] " + thingsToDo.get(i).getTaskDescription());
            }
        }
    }

    public void listOfUndoneTasks() {
        if (thingsToDo.isEmpty()) {
            System.out.println("Nothing to do!");
        }
        //print only the uncompleted things
        for (int i = 0; i < thingsToDo.size(); i++) {
            if (!thingsToDo.get(i).isReady()) {
                System.out.println((i + 1) + " - " + "[ ] " + thingsToDo.get(i).getTaskDescription());
            }
        }
    }
}
