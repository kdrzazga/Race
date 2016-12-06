package libs;

public class Assert {
    public static void assertion(boolean statement, String methodName)
    {
        if (!statement)
            throw new RuntimeException("Assertion failed in " + methodName);
    }
}
