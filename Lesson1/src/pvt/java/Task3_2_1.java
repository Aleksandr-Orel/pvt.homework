package pvt.java;

import java.util.Scanner;

public class Task3_2_1 {
    public static void main(String[] args) {
        int minNumber = 1;
        int maxNumber = 100;
        int size = 10000;
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = minNumber + (int) (Math.random() * (maxNumber - minNumber - 1));
            System.out.print(array[i] + " ");
        }
        System.out.println();

        for(int i = 0; i < array.length; i++) {
            int minIndex = min(array, i, array.length - 1);
            change(array, i ,minIndex);
        }
        System.out.println();
        for (int number : array) {
            System.out.print(number + " ");
        }
    }

    public static int min(int[] array, int begin, int end) {
        int minValue = array[begin];
        int minIndex = begin;
        for(int i = begin + 1; i <= end; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void change(int[] array, int i, int j) {
        int temporary = array[i];
        array[i] = array[j];
        array[j] = temporary;
    }

}
