package Interpreter;

public class TerminalExp implements Expression{

    private String data;
    

    public TerminalExp(String data) {
        this.data = data;
    }


    @Override
    public boolean interpret(String context) {
        if (context.contains(data)) {
            return true ; 
        }
        return false;
    }
    
}
