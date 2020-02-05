package pvt.java;

import java.util.Arrays;
import java.util.Scanner;

public class Task2_8 {
    public static void main(String[] args) {
        int minNumber = 0;
        int maxNumber = 100;
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
        System.out.println("Min number is " + Arrays.stream(array).min().getAsInt());
        System.out.println("Max number is " + Arrays.stream(array).max().getAsInt());
    }
}
