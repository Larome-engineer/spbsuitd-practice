package Proxy;
/*
 Необходимо контролировать доступ к объекту, не изменяя при этом поведение клиента.
 Необходимо иметь доступ к объекту так,
 чтобы не создавать реальные объекты непосредственно,
 а через другой объект, который может иметь дополнительную функциональность.
 Решение:
 Создать суррогат реального объекта. «Заместитель» хранит ссылку,
 которая позволяет заместителю обратиться к реальному субъекту (объект класса «Заместитель» может
 обращаться к объекту класса «Субъект», если интерфейсы «Реального Субъекта» и «Субъекта» одинаковы).
 Поскольку интерфейс «Реального Субъекта» идентичен интерфейсу «Субъекта», так, что «Заместителя»
 можно подставить вместо «Реального Субъекта», контролирует доступ к «Реальному Субъекту»,
 может отвечать за создание или удаление «Реального Субъекта». «Субъект» определяет общий
 для «Реального Субъекта» и «Заместителя» интерфейс так, что «Заместитель» может быть использован везде,
 где ожидается «Реальный Субъект».
 При необходимости запросы могут быть переадресованы «Заместителем» «Реальному Субъекту».
 */
class ProxyMain {
    public static void main(String[] args) {
        // Create math proxy
        IMath p = new MathProxy();
        // Do the math
        System.out.println("4 + 2 = " + p.add(4, 2));
        System.out.println("4 - 2 = " + p.sub(4, 2));
        System.out.println("4 * 2 = " + p.mul(4, 2));
        System.out.println("4 / 2 = " + p.div(4, 2));
    }
}
/**
 * "Субъект"
 */
interface IMath {
    public double add(double x, double y);
    public double sub(double x, double y);
    public double mul(double x, double y);
    public double div(double x, double y);
}
/**
 * "Настоящий Субъект"
 */
class Math implements IMath {
    public double add(double x, double y) {
        return x + y;
    }
    public double sub(double x, double y) {
        return x - y;
    }
    public double mul(double x, double y) {
        return x * y;
    }
    public double div(double x, double y) {
        return x / y;
    }
}
/**
 * "Заместитель"
 */
class MathProxy implements IMath {
    private Math math;
    public double add(double x, double y) {
        lazyInitMath();
        return math.add(x, y);
    }
    public double sub(double x, double y) {
        lazyInitMath();
        return math.sub(x, y);
    }
    public double mul(double x, double y) {
        lazyInitMath();
        return math.mul(x, y);
    }
    public double div(double x, double y) {
        lazyInitMath();
        return math.div(x, y);
    }
    private void lazyInitMath() {
        if (math == null) {
            math = new Math();
        }
    }
}
