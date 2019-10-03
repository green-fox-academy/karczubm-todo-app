public class Main {
    public static void main(String[] args) {
        InputAndExecution iae = new InputAndExecution("task.txt");
        while (true) {
            iae.nextCommand();
            iae.todo(iae.inputFromUser());
        }
    }
}
