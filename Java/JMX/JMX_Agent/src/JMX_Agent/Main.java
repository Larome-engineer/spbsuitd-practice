package JMX_Agent;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        TaskList process = new TaskList();
        ManagementFactory.getPlatformMBeanServer().registerMBean(process, new ObjectName("TaskList:name=JMXAGENT")
        );
        while (true){
            Thread.sleep(10000);
        }
    }
}