import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class UnitTestRunner {
    public static void main(String[] args) {
        var runner = new TestNG();
        List<String> suitefiles = new ArrayList<>();
        suitefiles.add("src/test/resources/AllUnitTests.xml");
        runner.setTestSuites(suitefiles);
        runner.run();
    }
}
