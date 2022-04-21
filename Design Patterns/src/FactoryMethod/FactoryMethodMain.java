package FactoryMethod;

/*Метод, который создает сам себя*/

public class FactoryMethodMain {
    public static void main(String[] args) {
        Coffee coffee = Coffee.create();
    }
}

class Coffee {
    private Coffee() {
        for (int i = 1; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println("JUST COFFEE! " + i + " CUPS!");
            }
        }
    }

    public static Coffee create() {
        return new Coffee();
    }
}

