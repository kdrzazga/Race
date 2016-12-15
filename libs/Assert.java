package libs;

public class Assert {

    public static void assertion(boolean statement, String methodName) {
        if (!statement) {
            throw new RuntimeException("Assertion failed in " + methodName);
        }
    }

    public static void assertion(float actual, float expected, String methodName) {
        if (actual != expected) {
            throw new RuntimeException("Assertion failed in " + methodName 
            +" act=" + actual + " exp=" + expected);
        }
    }
}
