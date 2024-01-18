package secondhometask;

import com.sun.jdi.connect.Connector;

import java.io.PrintStream;
import java.lang.reflect.*;

/**
 * Создайте абстрактный класс "Animal" с полями "name" и "age".
 * Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
 * Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
 * Выведите на экран информацию о каждом объекте.
 * Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
 */
public class MainApp {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {


        Cat murzik = new Cat("Murzik", 2);
        Cat alice = new Cat("Alice", 1);
        Dog jack = new Dog("Jack", 5);
        Dog lady = new Dog("Lady", 4);


        Animal[] pets = {murzik, alice, jack, lady};

        for (Animal pet : pets) {
            Class<?> clazz = pet.getClass();
            System.out.println(clazz);
            Field [] fields = clazz.getSuperclass().getDeclaredFields();
            fields[0].setAccessible(true);
            fields[1].setAccessible(true);
            Constructor [] constructors = clazz.getConstructors();
            Method [] methods = clazz.getDeclaredMethods();
//            Создаем объект через API
            Object obj = constructors[0].newInstance(fields[0].get(pet), fields[1].get(pet));
            methods[0].invoke(obj);

            for (Constructor cons : constructors) {
                String name = cons.toString();
                System.out.println(name);
            }

            for (Field field : fields) {
                System.out.println("Field: " + field.getName());

            }

            for (Method method: methods) {
                System.out.println("Methods: " + method.getName());
                // Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
                if (method.getName().equalsIgnoreCase("makeSound")) {
                    System.out.println(method.invoke(obj));
                }

            }
            System.out.println("-".repeat(54));
        }
    }
}
