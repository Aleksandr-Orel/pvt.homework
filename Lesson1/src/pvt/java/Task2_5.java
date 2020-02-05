package pvt.java;

import java.util.Scanner;

public class Task2_5 {
    public static void main(String[] args) {
        int minNumber = 0;
        int maxNumber = 10;
        int counter = 0;
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
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                System.out.print(i + " ");
                counter++;
            }
        }
        System.out.println();
        if (counter == 0) {
            System.out.println("There aren't nulls in this array.");
        }
    }
}
