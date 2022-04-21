package Adapter;

/*Превращает старый интерфейс в новый. По сути своя, это обертка,
* которая позволяет запихнуть объект в метод, ожидающий другой объект.
* Превращает один интерфейс в другой.*/

/*Что тут происходит? У нас есть класс Phone с методом зарядки,
* также у нас есть класс Laptop со своей зарядкой. Чтобы
* зарядить Лаптоп зарядкой от телефона, мы создаем обертку, которая
* позволяет через chargePhone зарядить MyLaptop*/

public class AdapterMain {
    public static void main(String[] args) {
        Charging charging = new Charging();
        charging.chargePhone(new LaptopWrapper(new MyLaptop()));
    }
}

class LaptopWrapper implements Phone {
    Laptop laptop;
    public LaptopWrapper (Laptop laptop) {
        this.laptop = laptop;
    }

    @Override
    public void charge() {
        laptop.superLaptopCharge();
    }
}

interface Laptop {
    void superLaptopCharge();
}

class MyLaptop implements Laptop {

    @Override
    public void superLaptopCharge() {
        System.out.println("LAPTOP IS CHARGING!");
    }
}

interface Phone {
    void charge();
}

class Iphone implements Phone {

    @Override
    public void charge() {
        System.out.println("IS CHARGING");
    }
}

class Charging {
    public void chargePhone (Phone phone) {
        phone.charge();
    }
}

