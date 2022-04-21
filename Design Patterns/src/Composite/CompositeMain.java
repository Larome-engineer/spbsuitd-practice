package Composite;
import java.util.ArrayList;
import java.util.List;
// Компонент
interface Object {
    //Выводит объект
    public void print();
}
// Компоновщик
class Compositeobject implements Object {
    //Коллекция дочерних объектов
    private List<Object> mChildobjects = new ArrayList<Object>();
    //Выводит объект
    public void print() {
        for (Object object : mChildobjects) {
            object.print();
        }
    }
    //Добавляет объект в структуру
    public void add(Object object) {
        mChildobjects.add(object);
    }
    //Удаляет объект из структуры
    public void remove(Object object) {
        mChildobjects.remove(object);
    }
}
// Лист
class TestObject implements Object {
    //Выводит Объект
    public void print() {
        System.out.println("TestObject");
    }
}
/**
 * Клиент
 */
public class CompositeMain {
    public static void main(String[] args) {
        //Инициализация тестовых объектов
        TestObject TestObject1 = new TestObject();
        TestObject TestObject2 = new TestObject();
        TestObject TestObject3 = new TestObject();
        TestObject TestObject4 = new TestObject();

        //Инициализация трех комповощик-объектов
        Compositeobject object = new Compositeobject();
        Compositeobject object1 = new Compositeobject();
        Compositeobject object2 = new Compositeobject();

        //Объеденияем объекты
        object1.add(TestObject1);
        object1.add(TestObject2);
        object1.add(TestObject3);

        object2.add(TestObject4);

        object.add(object1);
        object.add(object2);

        //Выводим
        object.print();
    }
}