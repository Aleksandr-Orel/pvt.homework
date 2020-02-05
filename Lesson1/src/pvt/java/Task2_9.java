package pvt.java;

import java.util.Arrays;
import java.util.Scanner;

public class Task2_9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of the array: ");
        int size = scanner.nextInt();

        int minNumber = 0;
        int maxNumber = 100;
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = minNumber + (int) (Math.random() * (maxNumber - minNumber - 1));
            System.out.print(array[i] + " ");
        }
        System.out.println();

        int max = array[0];

        int min = array[0];
        int rate_max = 0;
        int rate_min = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= max) {
                max = array[i];
                rate_max = i;
            }
            if (array[i] < min) {
                min = array[i];
                rate_min = i;
            }
        }
        System.out.println("Position of the biggest rate: = " + rate_max);
        System.out.println("Position of the smallest rate: = " + rate_min);
    }
}
