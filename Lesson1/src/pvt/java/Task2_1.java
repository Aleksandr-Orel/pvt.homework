package pvt.java;

import java.util.Scanner;

public class Task2_1 {
    public static void main(String[] args) {
        int minNumber = 1;
        int maxNumber = 100;
        int sizeOfArray;
        System.out.print("Enter size of array: ");
        Scanner scanner = new Scanner(System.in);
        sizeOfArray = scanner.nextInt();
        int[] array = new int[sizeOfArray];
        int iterator = 0;
        while (iterator < array.length) {
            array[iterator] = minNumber + (int)(Math.random() * (maxNumber - minNumber - 1));
            System.out.print(array[iterator] + " ");
            iterator++;
        }

        System.out.println();
        for(int i = array.length - 1; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
    }
}
