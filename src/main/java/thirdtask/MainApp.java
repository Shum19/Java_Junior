package thirdtask;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static thirdtask.SerialiseUtil.deserialize;

/*
* Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
  Обеспечьте поддержку сериализации для этого класса. Создайте объект класса Student и инициализируйте его данными.
  Сериализуйте этот объект в файл. Десериализуйте объект обратно в программу из файла.
  Выведите все поля объекта, включая GPA, и ответьте на вопрос,почему значение GPA не было сохранено/восстановлено.
*/
public class MainApp {
    public static void main(String[] args) {


        Student studentFirst = new Student("Alex", 19, 4.5);

        String fileName = "students.txt";
        SerialiseUtil.serialise(fileName, studentFirst);

        Student student = (Student) deserialize(fileName);
        System.out.println(student.toString());

    }
}
