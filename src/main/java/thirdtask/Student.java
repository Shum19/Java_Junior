package thirdtask;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;
    transient double GPA;


    public Student(String name, int age, double GPA){
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }
    public Student(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }
    public String toString(){
        return String.format("Name - %s; Age - %s; GPA %s (GPA 0.0 потому что поле доступа " +
                                "transient)", this.name, this.age, this.GPA);
    }
}
