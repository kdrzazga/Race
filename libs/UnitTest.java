package libs;

public abstract class UnitTest {

    public static void showTestPassedMessage(String childClassName)
    {
        System.out.println("No assertion exception occured. all tests passed for " + childClassName);
    }
    
}
