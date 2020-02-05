package pvt.java;

import java.util.Arrays;
import java.util.Scanner;

public class Task2_2 {
    public static void main(String[] args) {
        int minNumber = 1;
        int maxNumber = 10;
        int sizeOfArray;
        System.out.print("Enter size of array: ");
        Scanner scanner = new Scanner(System.in);
        sizeOfArray = scanner.nextInt();
        int[] array = new int[sizeOfArray];
        for(int i = 0; i < array.length; i++) {
            array[i] = minNumber + (int)(Math.random() * (maxNumber - minNumber - 1));
            System.out.print(array[i] + " ");
        }

        System.out.println();
        System.out.println(Arrays.stream(array).reduce((s1, s2) -> s1 * s2).getAsInt());
    }
}
