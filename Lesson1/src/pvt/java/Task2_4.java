package pvt.java;

import java.util.Arrays;
import java.util.Scanner;

public class Task2_4 {
    public static void main(String[] args) {
        int minNumber = 0;
        int maxNumber = 10;
        int sizeOfArray;
        System.out.print("Enter size of array: ");
        Scanner scanner = new Scanner(System.in);
        sizeOfArray = scanner.nextInt();
        int[] array = new int[sizeOfArray];
        for (int i = 0; i < array.length; i++) {
            array[i] = minNumber + (int) (Math.random() * (maxNumber - minNumber - 1));
            System.out.print(array[i] + " ");
        }

        System.out.println();
        int countOfNull = (int) Arrays.stream(array).filter(x -> x == 0).count();
        if(countOfNull > 0) {
            System.out.println("Amount of nulls is " + countOfNull);
        } else {
            System.out.println("There aren't nulls in this array.");
        }
    }
}
