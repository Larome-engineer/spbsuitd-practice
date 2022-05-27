package JMX_Agent;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;

public class TaskList implements TaskListMBean {

    ArrayList<Tasks> tasks;

    TaskList(){
        tasks = new ArrayList<>();
    }


    @Override
    public void submit(String name, String classpath, String mainClass, int period) {
        Tasks tasks = new Tasks();
        tasks.submit(name, classpath, mainClass, period);
        this.tasks.add(tasks);
        System.out.println(name + " is started");
    }

    @Override
    public String status(String name) {
        boolean deleted = false;
        String status = null;
        for (Tasks tasks : this.tasks) {
            if (tasks.getName().equals(name)) {
                status = tasks.status();
                deleted = true;
            }
        }
        if (!(deleted))
            status = "No such process";
        return status;
    }

    @Override
    public void cancel(String name) {
        boolean deleted = false;
        for (Tasks tasks : this.tasks) {
            if (tasks.getName().equals(name)) {
                tasks.cancel();
                this.tasks.remove(tasks);
                deleted = true;
            }
        }
        if (!(deleted))
            System.out.println("No such process");
    }

    @Override
    public void startProfiling(String name) {
        for (Tasks tasks : this.tasks) {
            if (tasks.getName().equals(name)) {
                try {
                    if (!(tasks.isProfiled))
                    tasks.startProfiling();
                } catch (NotFoundException | IOException | CannotCompileException | IllegalClassFormatException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void stopProfiling(String name) {
        for (Tasks tasks : this.tasks) {
            if (tasks.getName().equals(name)) {
                if (tasks.isProfiled)
                    tasks.stopProfiling();
            }
        }
    }
}
