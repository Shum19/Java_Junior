package secondhometask;

public class Cat extends Animal{
    public Cat(String name, int age){
        super(name, age);
    }
    public String makeSound(){
        return "MOEEEW MOEEEW";
    }
    public void pat(){
        System.out.println("MUR MUR");
    }
}
