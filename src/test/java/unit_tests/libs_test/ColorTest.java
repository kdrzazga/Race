package unit_tests.libs_test;

import org.testng.Assert;
import org.testng.annotations.Test;
import libs.Colors;

import java.awt.*;

public class ColorTest {
    @Test
    public void TestGetColor()
    {
        Assert.assertEquals(Colors.getColor.apply(0), Color.CYAN);
        Assert.assertEquals(Colors.getColor.apply(13), Color.BLACK);
        Assert.assertEquals(Colors.getColor.apply(20), Color.GRAY);
    }
}
