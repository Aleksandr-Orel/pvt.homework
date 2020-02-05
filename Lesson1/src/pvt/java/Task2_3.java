package pvt.java;

import java.util.Scanner;

public class Task2_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of the array: ");
        int size = scanner.nextInt();

        int minNumber = 0;
        int maxNumber = 10;
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = minNumber + (int) (Math.random() * (maxNumber - minNumber - 1));
            System.out.print(array[i] + " ");
        }

        System.out.println();
        int[] array1 = new int[size];
        System.arraycopy(array, 0, array1, 0, array.length);
        for (int i = 2; i < array1.length; i = i + 3) {
            array1[i] = array1[i] * 2;
        }
        System.out.println();
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + " ");
        }

        for (int i = 0; i < array.length; i++) {
            if ((i + 1) % 3 == 0) {
                array[i] = array[i] * 2;
            }
        }
        System.out.println();
        int iterator = 0;
        while (iterator < array.length) {
            System.out.print(array[iterator] + " ");
            iterator++;
        }
    }
}
