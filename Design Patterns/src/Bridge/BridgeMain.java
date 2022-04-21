package Bridge;

import java.util.ArrayList;
import java.util.List;

/*Отделить абстракцию от реализации таким образом,
 * чтобы мы смогли изменять независимо друг от друга и то, и другое.*/

public class BridgeMain {
    public static void main(String[] args) {
        List<Program> programs = new ArrayList<>();
        programs.add(new BankSystem(new JavaDeveloper()));
        programs.add(new Game(new CppDeveloper()));
        programs.add(new AndroidApp(new KotlinDeveloper()));
        programs.add(new AI(new JavaDeveloper()));

        for (Program program : programs) {
            program.developProgram();
        }
    }
}
/////////////////////////////////////////////////////////////////
interface Developer {
    void writeCode();
}

class JavaDeveloper implements Developer {

    @Override
    public void writeCode() {
        System.out.println("Java developer write Java code");
    }
}

class KotlinDeveloper implements Developer {

    @Override
    public void writeCode() {
        System.out.println("Kotlin developer write Kotlin code");
    }
}

class CppDeveloper implements Developer {

    @Override
    public void writeCode() {
        System.out.println("C++ developer write C++ code");
    }
}
/////////////////////////////////////////////////////////////////
abstract class Program {
    protected Developer developer;

    protected Program(Developer developer) {
        this.developer = developer;
    }

    public abstract void developProgram();
}
/////////////////////////////////////////////////////////////////
class BankSystem extends Program {

    protected BankSystem(Developer developer) {
        super(developer);
    }

    @Override
    public void developProgram() {
        System.out.print("Bank system in progress: ");
        developer.writeCode();
    }
}

class Game extends Program {

    protected Game(Developer developer) {
        super(developer);
    }

    @Override
    public void developProgram() {
        System.out.print("Game dev in progress: ");
        developer.writeCode();
    }
}

class AndroidApp extends Program {

    protected AndroidApp(Developer developer) {
        super(developer);
    }

    @Override
    public void developProgram() {
        System.out.print("Android app is coming: ");
        developer.writeCode();
    }
}

class AI extends Program {

    protected AI(Developer developer) {
        super(developer);
    }

    @Override
    public void developProgram() {
        System.out.print("AI is coming: ");
        developer.writeCode();
    }
}


