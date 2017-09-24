package ru.messenger;

import org.junit.Assert;
import org.junit.Test;

public class SimpleTest {
    @Test
    public void assertCheck() {
        String one = "one";
        String two = null;
        Assert.assertNotNull(one);
        Assert.assertNotNull("lol",one);
        Assert.assertNotNull("lol",two);
    }
}
