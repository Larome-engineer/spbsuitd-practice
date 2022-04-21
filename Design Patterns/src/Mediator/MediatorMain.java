package Mediator;
abstract class Colleague {
    protected Mediator mediator;
    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
    public void send(String message) {
        mediator.send(message, this);
    }
    public abstract void notify(String message);
}
abstract class Mediator {
    public abstract void send(String message, Colleague sender);
}
class ConcreteColleague1 extends Colleague {
    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }
    @Override
    public void notify(String message) {
        System.out.println("Colleague1 gets message: " + message);
    }
}
class ConcreteColleague2 extends Colleague {
    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }
    @Override
    public void notify(String message) {
        System.out.println("Colleague2 gets message: " + message);
    }
}
class ConcreteMediator extends Mediator {
    private ConcreteColleague1 colleague1;
    private ConcreteColleague2 colleague2;
    public void setColleague1(ConcreteColleague1 colleague) {
        this.colleague1 = colleague;
    }
    public void setColleague2(ConcreteColleague2 colleague) {
        this.colleague2 = colleague;
    }
    @Override
    public void send(String message, Colleague sender) {
        if (sender.equals(colleague1)) {
            colleague2.notify(message);
        } else {
            colleague1.notify(message);
        }
    }
}
class MediatorMain {
    public static void main(String[] args) {
        ConcreteMediator m = new ConcreteMediator();
        ConcreteColleague1 c1 = new ConcreteColleague1(m);
        ConcreteColleague2 c2 = new ConcreteColleague2(m);
        m.setColleague1(c1);
        m.setColleague2(c2);
        c1.send("How are you?");
        c2.send("Fine, thanks");
    }
}