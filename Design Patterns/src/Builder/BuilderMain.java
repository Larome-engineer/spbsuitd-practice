package Builder;

/*Разбивает процесс конструирования объекта на отдельные шаги, которые необходимы*/

public class BuilderMain {
    public static void main(String[] args) {
        SportCar bmw = new SportCar.Builder("BMW").setColor("black").setMaxSpeed(400).build();
        SportCar mercedes = new SportCar.Builder("MERCEDES").setColor("PINK").build();
        System.out.println(bmw.getName() + " " + bmw.getMaxSpeed() + " " + bmw.getColor());
        System.out.println(mercedes.getName() + " " + mercedes.getColor());

    }
}

class SportCar {
    private final String name;
    private final String color;
    private final int maxSpeed;

    //etc...

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    private SportCar(Builder builder) {
        this.name = builder.name;
        this.color = builder.color;
        this.maxSpeed = builder.maxSpeed;
    }

    static class Builder {
        private String name;
        private String color;
        private int maxSpeed;

        public Builder(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public int getMaxSpeed() {
            return maxSpeed;
        }

        public Builder setMaxSpeed(int maxSpeed) {
            this.maxSpeed = maxSpeed;
            return this;
        }

        public SportCar build() {
            return new SportCar(this);
        }
    }
}
