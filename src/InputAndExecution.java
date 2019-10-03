import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class InputAndExecution {
    ToDo toDoObject;
    Scanner sc = new Scanner(System.in);
    private Path myPath;
    private List<String> myFile;

    public InputAndExecution(String fileName) {
        myPath = Paths.get(fileName);
        try {
            myFile = Files.readAllLines(myPath);
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        toDoObject = new ToDo(myFile);
        toDoObject.usageInformation();
    }

    public String inputFromUser() {
        return sc.nextLine();
    }

    public void nextCommand() {
        System.out.println("Next command:");
    }

    public void saveTasksToFile() {
        myFile.clear();
        for (Thing thing : toDoObject.thingsToDo) {
            if (thing.isReady()) {
                myFile.add("1" + thing.getTaskDescription());
            } else {
                myFile.add("0" + thing.getTaskDescription());
            }
        }
        try {
            Files.write(myPath, myFile);
        } catch (IOException e) {
            System.out.println("Unable to write in file!");
        }
    }

    public void todo(String inputFromUser) {
        String argument = inputFromUser.substring(0, 2);
        if (inputFromUser.length() == 2 || inputFromUser.charAt(2) == ' ') {
            switch (argument) {
                case "-l":
                    toDoObject.listTasks();
                    break;
                case "-u":
                    toDoObject.listOfUndoneTasks();
                    break;
                case "-a":
                    try {
                        toDoObject.addNewTask(inputFromUser.trim().substring(3));
                        break;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Unable to add: no task provided!");
                        break;
                    }
                case "-r":
                    try {
                        toDoObject.removeTask(Integer.parseInt(inputFromUser.trim().substring(3)));
                        break;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("No index provided!");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Index is out of bound!");
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Index is not a number!");
                        break;
                    }
                case "-c":
                    try {
                        toDoObject.completeTask(Integer.parseInt(inputFromUser.trim().substring(3)));
                        break;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("No index provided!");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Index is out of bound!");
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Index is not a number!");
                        break;
                    }
                default:
                    System.out.println("Unsupported argument!");
                    toDoObject.usageInformation();
            }
        } else {
            System.out.println("Invalid command!");
        }
        saveTasksToFile();
    }
}
