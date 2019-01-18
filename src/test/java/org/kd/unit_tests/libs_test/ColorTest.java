package org.kd.unit_tests.libs_test;

import org.kd.libs.Colors;
import org.testng.Assert;
import org.testng.annotations.Test;

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
