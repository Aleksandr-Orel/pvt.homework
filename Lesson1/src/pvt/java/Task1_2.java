package pvt.java;

public class Task1_2 {
    public static void main(String[] args) {
        int minNumber = 2;
        int maxNumber = 20;
        int shift = 2;
        int number;
        for (number = minNumber; number <= maxNumber; number = number + shift) {
            System.out.print(number + " ");
        }
    }
}
