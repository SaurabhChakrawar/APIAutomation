package utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtils {

    @DataProvider(name = "userData")
    public static Object[][] userData() {
        return new Object[][]{
                {"Alice", "alice@example.com"},
                {"Bob", "bob@example.com"}
        };
    }
}
