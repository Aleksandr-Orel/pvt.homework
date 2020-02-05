package pvt.java;

import java.util.Scanner;

public class Task2_10 {
    public static void main(String[] args) {
        int minNumber = 0;
        int maxNumber = 100;
        int sizeOfArray;
        boolean flag = false;
        System.out.print("Enter size of array: ");
        Scanner scanner = new Scanner(System.in);
        sizeOfArray = scanner.nextInt();
        int[] array = new int[sizeOfArray];
        for (int i = 0; i < array.length; i++) {
            array[i] = minNumber + (int) (Math.random() * (maxNumber - minNumber - 1));
            System.out.print(array[i] + " ");
        }

        System.out.println();
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] >= array[i + 1]) {
                flag = true;
            }
        }
        if (flag == false) {
            System.out.println("This array is increasing");
        } else {
            System.out.println("This array isn't increasing");
        }
    }
}
