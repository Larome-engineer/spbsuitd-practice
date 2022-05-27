package Interpreter;

public class InterpreterMain {
    public static void main(String[] args) {
        Expression isJava = getJavaExp();
        Expression isJavaEEDev = getJavaEEExp();

        System.out.println("Does developer knows Java Core: " + isJava.interpret("Java"));
        System.out.println("Does developer knows Java EE: " + isJavaEEDev.interpret("Java Spring"));
    }
    
    public static Expression getJavaExp(){
        Expression java = new TerminalExp("Java");
        Expression javaCore = new TerminalExp("Java Core");

        return new OrExp(java, javaCore);
    }

    public static Expression getJavaEEExp(){
        Expression java = new TerminalExp("Java");
        Expression spring = new TerminalExp("Spring");

        return new AndExp(java, spring);
    }
}
