package pvt.java;

public class Methods {
    public int findMinimalNumber(int a, int b) {
        return a > b ? b : a;
    }

    public boolean isParity(int a) {
        return a % 2 == 0 ? true : false;
    }

    public int getSquareOfNumber(int a) {
        return (int) Math.pow(a, 2);
    }

    public int getCubeOfNumber(int a) {
        return (int) Math.pow(a, 3);
    }
}


