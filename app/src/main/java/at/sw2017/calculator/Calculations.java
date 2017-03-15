package at.sw2017.calculator;

/**
 * Created by matthee on 15.03.17.
 */

public class Calculations {
    private Calculations() {

    }

    public static int doAddition(int a, int b) {
        return a + b;
    }

    public static int doSubtraction(int a, int b) {
        return a - b;
    }

    public static int doMultiplication(int a, int b) {
        return a * b;
    }

    public static int doDivision(int a, int b) {
        if (b == 0) {
            return 0;
        } else {
            return a / b;
        }
    }
}
