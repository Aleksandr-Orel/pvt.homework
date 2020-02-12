package Task_3;

import Task.StringOperation;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StringOperation3Test {
    @BeforeMethod
    public void setUp() {
        System.out.println("---------------");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("---------------");
    }

    @Test(expectedExceptions = StringIndexOutOfBoundsException.class)
    public void getSomeSymbolFromString() {
        StringOperation stringOperation = new StringOperation();
        char actualResult = stringOperation.getSomeSymbolFromString("abcdefghi", 9);
        char expectedResult = 'i';
        Assert.assertEquals(expectedResult, actualResult);
    }
}
