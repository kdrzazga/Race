package libs;

public abstract class Test {

    public static void showTestPassedMessage(String childClassName)
    {
        System.out.println("No assertion exception occured. all tests passed for " + childClassName);
    }
    
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
    
    public static void assertion(double actual, double expected, String methodName) {
        if (actual != expected) {
            throw new RuntimeException("Assertion failed in " + methodName 
            +" act=" + actual + " exp=" + expected);
        }
    }
    
        public static void assertion(int actual, int expected, String methodName) {
        if (actual != expected) {
            throw new RuntimeException("Assertion failed in " + methodName 
            +" act=" + actual + " exp=" + expected);
        }
    }

        public static void assertDifferenceNotGreaterThan(float value1, float value2
                , float expectedMaxDifference, String methodName)
        {
            float difference = Math.abs(value1 - value2);
            
            if (difference > expectedMaxDifference)
                throw new RuntimeException("Assertion failed in " + methodName 
            +" act diff=" + difference + " expected difference = " + expectedMaxDifference);
        }
}
