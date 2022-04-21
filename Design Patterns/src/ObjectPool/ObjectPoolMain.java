package ObjectPool;

import java.util.LinkedList;
import java.util.List;

/*Позволяет уменьшить кол-во создаваемых объектов,
* То есть, позволяет переиспользовать их.
* То есть, для уменьшения времени операции,
* лучше будет создать объект один раз, после чего
* переиспользовать его. Паттерн можно использовать
* для всех "тяжелых" объектов.*/

public class ObjectPoolMain {
    public static void main(String[] args) {
        ObjectPool objectPool = new ObjectPool();
        PooledObject pooledObject = objectPool.getPooledObject();
        objectPool.releasePooledObject(pooledObject);
    }
}
class PooledObject {} // Объекты, которые будут храниться в пуле.

class ObjectPool { // Пул объектов
    List<PooledObject> free = new LinkedList<>(); // Свободный лист.
    List<PooledObject> used = new LinkedList<>(); // Занятый лист.

    public PooledObject getPooledObject () {

        if (free.isEmpty()) {
            PooledObject pooledObject = new PooledObject();
            free.add(pooledObject);
            return pooledObject;
        } else {
            PooledObject pooledObject = free.get(0);
            used.add(pooledObject);
            free.remove(pooledObject);
            return pooledObject;
        }
    }

    public void releasePooledObject (PooledObject pooledObject) {
        used.remove(pooledObject);
        free.add(pooledObject);
    }
}

