package Task_4;

import Task.StringOperation;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StringOperation4Test {
    @BeforeMethod
    public void setUp() {
        System.out.println("---------------");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("---------------");
    }

    @DataProvider(name = "ourDataProvider")
    public Object[][] createSomeData() {
        return new Object[][]{
                {"one two three ", 0, 3, "one"},
                {"one two three ", 4, 7, "two"},
                {"one two three ", 8, 13, "three"},
        };
    }

    @Test(dataProvider = "ourDataProvider")
    public void getSubstring(String string, int srcBegin, int srcEnd, String expectedResult) {
        StringOperation stringOperation = new StringOperation();
        String actualResult = stringOperation.getSubstring(string, srcBegin, srcEnd);
        Assert.assertEquals(expectedResult, actualResult);
    }
}
