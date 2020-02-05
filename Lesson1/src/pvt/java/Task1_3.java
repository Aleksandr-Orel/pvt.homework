package pvt.java;

public class Task1_3 {
    public static void main(String[] args) {
        int minNumber = 2;
        int maxNumber = 20;
        int borderNumber = 10;
        int shift = 2;
        int number;
        for (number = minNumber; number <= maxNumber; number = number + shift) {
            if (number > borderNumber) {
                System.out.print(number + " ");
            }
        }
    }
}
