package JMX_Agent;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task implements TasksMBean {
    private String name;
    private String status;
    private final ScheduledExecutorService scheduledExecutorService;
    public Task() {
        name = "Untitled";
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        status = "is running...";
    }
    private void Task(String classpath, String className) {
        var path = Path.of(classpath);
        try {
            ClassLoader loader = new URLClassLoader(new URL[] {path.toUri().toURL()});
            var clazz = loader.loadClass(className);
            clazz.getMethod("run").invoke(null);
        } catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            this.status = ("There's an error of type:" + Arrays.toString(e.getStackTrace()));
            this.cancel(this.name);
        }
    }
    public String getName() {
        return name;
    }
    @Override
    public void submit(String name, String classpath, String mainClass, int period) {
        this.name=name;
        Runnable task = () -> Task(classpath,mainClass);
        scheduledExecutorService.scheduleAtFixedRate(task, 1, period, TimeUnit.SECONDS);
    }
    @Override
    public String status(String name) {
        return status;
    }
    @Override
    public void cancel(String name) {
        this.scheduledExecutorService.shutdown();
        System.out.println( this.name +  " is canceled.");
    }
}
