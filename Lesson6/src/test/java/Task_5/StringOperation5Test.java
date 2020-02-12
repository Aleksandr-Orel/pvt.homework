package Task_5;

import Task.StringOperation;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StringOperation5Test {
    @BeforeMethod
    public void setUp() {
        System.out.println("---------------");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("---------------");
    }

    @Test(enabled = false)
    public void getStringWithoutUpperCase() {
        StringOperation stringOperation = new StringOperation();
        String actualResult = stringOperation.getStringWithoutUpperCase("aaaAAAaaaAAA");
        String expectedResult = "aaaaaaaaaaaa";
        Assert.assertEquals(expectedResult, actualResult);
    }
}
