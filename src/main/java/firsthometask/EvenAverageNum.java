package firsthometask;
/*
 1. Напишите программу, которая использует Stream API для обработки списка чисел. Программа должна вывести
    на экран среднее значение всех четных чисел в списке.
 2. Дополнительная задача: Переработать метод балансировки корзины товаров cardBalancing() с использованием Stream API
*/
import java.util.ArrayList;
import java.util.Random;

public class EvenAverageNum {
    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            nums.add(new Random().nextInt(0,101));
        }
        System.out.println(nums);
        double averageNum = nums.stream().filter(evenNum -> evenNum % 2 == 0).mapToDouble(evenNum -> evenNum).average()
                .getAsDouble();
        nums.stream().filter(evenNum -> evenNum % 2 == 0).forEach(evenNum -> System.out.print(evenNum + " "));
        System.out.println();
        System.out.println("Average value: " + averageNum);
    }
}
