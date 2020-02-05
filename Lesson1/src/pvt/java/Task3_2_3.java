package pvt.java;

public class Task3_2_3 {
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

        int h = 1;
        int jump = 3;
        while (h * jump < array.length) {
            h = h * jump + 1;
        }
        while (h >= 1) {
            hSort(array, h);
            h = h / jump;
        }
        for (int number : array) {
            System.out.print(number + " ");
        }
    }

    public static void hSort(int[] array, int h) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (array[j] < array[j - 1]) {
                    change(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static void change(int[] array, int i, int j) {
        int temporary = array[i];
        array[i] = array[j];
        array[j] = temporary;
    }
}
