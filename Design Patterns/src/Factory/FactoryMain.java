package Factory;

/*Создание фабрик по созданию чего-либо*/

public class FactoryMain {
    public static void main(String[] args) {
        PhoneFactory phoneFactory = new PhoneFactory();
        Phone phone = phoneFactory.createPhone("Samsung");
        phone.ring();
        phone.sleep();
        phone.wakeUp();
    }
}

interface Phone {
    void ring();
    void sleep();
    void wakeUp();
}

class Iphone implements Phone {

    @Override
    public void ring() {
        System.out.println("RING-RING, IPHONE!");
    }

    @Override
    public void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void wakeUp() {
        System.out.println("IM ALIVE!");
    }
}

class Samsung implements Phone {

    @Override
    public void ring() {
        System.out.println("SAMSUNG IS RINGING!");
    }

    @Override
    public void sleep() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void wakeUp() {
        System.out.println("IM ALIVE");
    }
}

class PhoneFactory {
    public Phone createPhone(String name) {
        switch (name) {
            case ("Apple") : return new Iphone();
            case ("Samsung") : return new Samsung();
            default: return null;
        }
    }
}