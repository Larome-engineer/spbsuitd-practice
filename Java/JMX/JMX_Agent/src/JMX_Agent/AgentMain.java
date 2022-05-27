package JMX_Agent;
import javax.management.*;
import java.lang.management.ManagementFactory;
public class AgentMain {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        Tasks process = new Tasks();
        ManagementFactory.getPlatformMBeanServer().registerMBean(
                process,
                new ObjectName("JMX_Agent.Tasks:name=AgentMain")
        );
        while (true) {
            Thread.sleep(100000);
        }
    }
}