package organization;

/*
*Created by: Kyle Loomis
*Maintained by: Kyle Loomis
*Details: Task class for task management system
*/

public class Task {
    private String description;
    private boolean time;
    private Task[] tasks;

    public Task(String description, boolean time, Task[] tasks) {
        this.description = description;
        this.time = time;
        this.tasks = tasks;
    }

    public String getDescription() { return description; }
    public boolean getTime() { return time; }
    public Task[] getTasks() { return tasks; }

    public String displayTasks() {
        if (tasks == null) return null;
        else {
            String output = "[";
            if (tasks.length > 0) {
                output += tasks[0].getDescription();
            }
            for (int i = 1; i < tasks.length; i++) {
                output += ", " + tasks[i].getDescription();
            }
            return output + "]";
        }

    }

    public String toString() {
        String output = "";
        output += "   Description: " + description + ", ";
        output += "Time: " + time + ", ";
        output += "Depends on: " + displayTasks();

        return output;
    }

    public static void main(String[] args) {
        Task t1 = new Task("washer", false, null);
        Task[] tasks1 = {t1};
        Task t2 = new Task("dryer", false, tasks1);
        Task[] tasks2 = {t1, t2};
        Task t3 = new Task("fold", false, tasks2);

        System.out.println(t1.toString());
        System.out.println(t2.toString());
        System.out.println(t3.toString());
    }
}
