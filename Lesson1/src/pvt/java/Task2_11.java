package pvt.java;

import java.util.Scanner;

public class Task2_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of the array: ");
        int size = scanner.nextInt();

        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            int minNumber = 1;
            int maxNumber = 10;
            array[i] = (int) (minNumber + (Math.random() * (maxNumber - minNumber)));
            System.out.print(array[i] + " ");
        }
        System.out.println();
        int temp1 = 0;
        int temp2 = 0;
        for (int i = 1; i < array.length - 1; i++) {
            temp1 = (array[i - 1] + array[i + 1]) / 2;
            if (i > 1) {
                array[i - 1] = temp2;
            }
            temp2 = temp1;
        }
        array[array.length - 2] = temp2;
        int i = 0;
        while (i < array.length) {
            System.out.print(array[i] + " ");
            i++;
        }
    }
}
