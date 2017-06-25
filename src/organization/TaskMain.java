package organization;

/*
*Created by: Kyle Loomis
*Maintained by: Kyle Loomis
*Details: TaskMain class for task management system
*/

public class TaskMain {

    // hashToNode
    // used to store organized independent
    // and dependent events
    public static N hashToNode(HashMap<Task, Task> taskHash) {
        boolean head = true;
        N node = null;

        N n = null;
        for (int i = 0; i < taskHash.getBucketLength(); i++) {
            Entry<Task, Task> current = taskHash.indexHash(i);
            while (current != null) {
                if (head) {
                    node = new N(current, null);
                    n = node;
                    head = false;
                }
                else {
                    n.setNext(new N(current, null));
                    n = n.getNext();
                }
                current = current.getNext();
            }
        }
        return node;
    }

    public static void main(String[] args) {
        HashMap<Task, Task> taskMap = new HashMap<>();
        Task t1 = new Task("washer", false, null);
        Task[] tasks1 = {t1};
        Task t2 = new Task("dryer", false, tasks1);
        Task[] tasks2 = {t2};
        Task t3 = new Task("fold", false, tasks2);
        Task[] tasks3 = {t3};
        Task t4 = new Task("stash",false, tasks3);
        Task[] tasks4 = {t4};
        Task t5 = new Task("smoke dope",false, tasks4);

        taskMap.put(t1,t2);
        taskMap.put(t2,t3);
        taskMap.put(t3,t4);
        taskMap.put(t4,t5);

        System.out.println("HashMap: " + taskMap.toString());

        N node = hashToNode(taskMap);
        N current = node;
        while (current != null) {
            System.out.println(((Entry<Task, Task>)current.getData()).getKey());
            current = current.getNext();
        }
    }
}
