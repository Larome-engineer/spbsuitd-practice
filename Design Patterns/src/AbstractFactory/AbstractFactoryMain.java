package AbstractFactory;

/*Фабрика по созданию фабрик*/

public class AbstractFactoryMain {
    public static void main(String[] args) {
        Factory factory = new AbstractFactory().createFactory("Car");
        Car car = factory.createCar("Bmw");
        car.drive();
    }
}

interface Car {
    void drive();
}

interface Tank {
    void bang();
}

class T34 implements Tank {

    @Override
    public void bang() {
        System.out.println("T34 is banging");
    }
}

class Armata implements Tank {

    @Override
    public void bang() {
        System.out.println("Armata is banging");
    }
}

class Bmw implements Car {

    @Override
    public void drive() {
        System.out.println("BMW POWER!");
    }
}

class Jeep implements Car {

    @Override
    public void drive() {
        System.out.println("JEEP POWER!");
    }
}

class CarFactory implements Factory {
    public Car createCar(String model) {
        return switch (model) {
            case ("Bmw") -> new Bmw();
            case ("Jeep") -> new Jeep();
            default -> null;
        };
    }

    @Override
    public Tank createTank(String model) {
        return null;
    }
}

class TankFactory implements Factory {
    public Tank createTank(String model) {
        return switch (model) {
            case ("T34") -> new T34();
            case ("Armata") -> new Armata();
            default -> null;
        };
    }

    @Override
    public Car createCar(String model) {
        return null;
    }
}

interface Factory {
    Car createCar(String model);

    Tank createTank(String model);
}

class AbstractFactory {
    Factory createFactory(String typeOfFactory) {
        return switch (typeOfFactory) {
            case ("Tank") -> new TankFactory();
            case ("Car") -> new CarFactory();
            default -> null;
        };
    }
}
