package Task_2;

import Task.StringOperation;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StringOperation2Test {
    @BeforeMethod
    public void setUp() {
        System.out.println("---------------");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("---------------");
    }

    @Test
    public void hasSomeString() {
        StringOperation stringOperation = new StringOperation();
        boolean actualResult = stringOperation.hasSomeString("abcdefghi", "cdi");
        Assert.assertTrue(actualResult);
    }
}
