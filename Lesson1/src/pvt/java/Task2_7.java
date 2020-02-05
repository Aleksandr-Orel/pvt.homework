package pvt.java;

import java.util.Scanner;

public class Task2_7 {
    public static void main(String[] args) {
        int minNumber = 0;
        int maxNumber = 100;
        int sizeOfArray;
        int temporary;

        System.out.print("Enter size of array: ");
        Scanner scanner = new Scanner(System.in);
        sizeOfArray = scanner.nextInt();
        int[] array = new int[sizeOfArray];
        for (int i = 0; i < array.length; i++) {
            array[i] = minNumber + (int) (Math.random() * (maxNumber - minNumber - 1));
            System.out.print(array[i] + " ");
        }

        for (int i = 0; i < Math.floor(array.length / 2); i++) {
            temporary = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temporary;
        }
        System.out.println();
        for (int number : array) {
            System.out.print(number + " ");
        }
    }
}
