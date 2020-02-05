package pvt.java;

import java.util.Scanner;

public class Task2_12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of array: ");
        int size = scanner.nextInt();

        int minNumber = 0;
        int maxNumber = 10;
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = minNumber + (int) (Math.random() * (maxNumber - minNumber - 1));
            System.out.print(array[i] + " ");
        }
        System.out.println();
        int shift = 2;
        for (int i = 0; i < 2; i++) {
            int tmp = array[array.length - 1];
            for (int j = 0; j < array.length; j++) {
                if (j == array.length - 1) {
                    array[0] = tmp;
                } else {
                    array[array.length - 1 - j] = array[array.length - shift - j];
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
