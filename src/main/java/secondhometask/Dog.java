package secondhometask;

public class Dog extends Animal{
    public Dog(String name, int age){
        super(name, age);
    }
    public String makeSound(){
        return "WOOF WOOF";
    }
    public void play(){
        System.out.println("I like catching ball");
    }


}
