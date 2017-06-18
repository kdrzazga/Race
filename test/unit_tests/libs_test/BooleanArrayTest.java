package libs.test;

import libs.BooleanArray;
import libs.UnitTest;

public class BooleanArrayTests extends UnitTest {

    public static void main(String[] args) {
        BooleanArrayTests.testAddingBools();
        BooleanArrayTests.testSetAllItems();
        showTestPassedMessage("testAddingBools");
    }

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
            assertion(bools.toString().equals("110101"), "testAddingBools");
        }
    }

    public static void testSetAllItems() {
        BooleanArray bools = new BooleanArray(5);
        GIVEN:
        {
            bools.values[0] = true;
            bools.values[1] = false;
            bools.values[2] = false;
            bools.values[3] = true;
            bools.values[4] = false;
        }
        WHEN:
        {
            bools.setAllItems(false);
        }
        THEN:
        {
            for (Boolean bool : bools.values) {
                assertion(!bool, "testSetAllItems");
            }
        }
        WHEN:
        {
            bools.setAllItems(true);
        }
        THEN:
        {
            for (Boolean bool : bools.values) {
                assertion(bool, "testSetAllItems");
            }
        }
    }

}
