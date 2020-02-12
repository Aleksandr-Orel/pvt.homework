package Task;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StringOperation1Test {
    @BeforeMethod
    public void setUp() {
        System.out.println("---------------");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("---------------");
    }

    @Test
    public void replaceAllSymbols() {
        StringOperation stringOperation = new StringOperation();
        String actualResult = stringOperation.replaceAllSymbols("aallllmmllllnn", 'l', 'B');
        String expectedResult = "aaBBBBmmBBBBnn";
        Assert.assertEquals(expectedResult, actualResult);
    }
}
