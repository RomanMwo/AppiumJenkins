package Framework;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "smokeProvider")
    public Object [][] createData() {
        return new Object[][] {
                {"Marek", 20},
                {"Janek", 10}
        };
    }
}
