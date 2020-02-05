package pvt.java;

import java.util.Scanner;

public class Task2_6 {
    public static void main(String[] args) {
        int minNumber = 0;
        int maxNumber = 10;
        int sizeOfArray;
        int jump = 2;
        int temporary;

        System.out.print("Enter size of array: ");
        Scanner scanner = new Scanner(System.in);
        sizeOfArray = scanner.nextInt();
        int[] array = new int[sizeOfArray];
        for (int i = 0; i < array.length; i++) {
            array[i] = minNumber + (int) (Math.random() * (maxNumber - minNumber - 1));
            System.out.print(array[i] + " ");
        }

        System.out.println();
        for(int i = 0; i < array.length - 1; i = i + jump) {
            temporary = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temporary;
        }
        for (int number : array) {
            System.out.print(number + " ");
        }
    }
}
