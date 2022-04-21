package Singleton;

/*Объект всегда будет один. Нельзя создавать несколько объектов этого класса*/

public class SingletonMain {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();
        Singleton singleton1 = Singleton.getSingleton();
        Singleton singleton2 = Singleton.getSingleton();

        singleton.name = "Semen";

        System.out.println(singleton1.name);
        System.out.println(singleton2.name);
    }
}

class Singleton {
    String name = "Alex";

    static Singleton singleton = new Singleton();

    private Singleton() {}

    public static Singleton getSingleton() {
        return singleton;
    }
}