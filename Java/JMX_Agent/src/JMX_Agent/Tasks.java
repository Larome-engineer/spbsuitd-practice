package JMX_Agent;

import java.util.ArrayList;
public class Tasks implements TasksMBean {
    ArrayList<Task> tasks;
    Tasks(){
        tasks = new ArrayList<>();
    }
    @Override
    public void submit(String name, String classpath, String mainClass, int period) {
        Task task = new Task();
        task.submit(name, classpath, mainClass, period);
        tasks.add(task);
        System.out.println( "Task " + name + " is started, waiting for instructions");
    }
    @Override
    public String status(String name) {
        boolean deleted = false;
        String status = null;
        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                status = task.status(name);
                deleted = true;
            }
        }
        if (!(deleted))
            status = "No task found";
        return status;
    }
    @Override
    public void cancel(String name) {
        boolean deleted = false;
        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                task.cancel(name);
                tasks.remove(task);
                deleted = true;
            }
        }
        if (!(deleted))
            System.out.println("No task found");
    }
}
