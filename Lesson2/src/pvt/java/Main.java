package pvt.java;

public class Main {

    public static void main(String[] args) {
        Methods methods = new Methods();
        System.out.println("Minimum number is " + methods.findMinimalNumber(3,2));
        System.out.println("This number is parity? - " + methods.isParity(9));
        System.out.println(methods.getSquareOfNumber(3));
        System.out.println(methods.getCubeOfNumber(3));
    }
}
