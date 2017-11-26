package unit_tests.libs_test;

import libs.BooleanArray;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class BooleanArrayTest {

    public BooleanArrayTest() {
    }

    @Test
    public static void testAddingBools() {
        BooleanArray bools;
        GIVEN:
        {
            bools = new BooleanArray(6);
        }
        WHEN:
        {
            bools.values[0] = true;
            bools.values[1] = true;
            bools.values[2] = false;
            bools.values[3] = true;
            bools.values[4] = false;
            bools.values[5] = true;
        }
        THEN:
        {
            assertEquals(bools.toString(), "110101");
        }
    }
}
