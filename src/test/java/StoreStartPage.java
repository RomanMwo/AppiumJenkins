import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.ITestContext;

public class StoreStartPage {
    private AndroidDriver<AndroidElement> driver;
    private FluentWait<WebDriver> wait;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='GRY']")
    private MobileElement gamesButton;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Wyszukaj']")
    private MobileElement searchButton;


    public StoreStartPage (ITestContext context){
        this.driver = (AndroidDriver<AndroidElement>)context.getAttribute("driver");
        this.wait= (FluentWait<WebDriver>)context.getAttribute("wait");
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean isDisplayed () {
        wait.until(ExpectedConditions.visibilityOf(gamesButton));
        return true;
    }

    public void searchByText () {
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.sendKeys("fferes" + "\n");
        //driver.pressKey(new KeyEvent(AndroidKey.SEARCH));
        // driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        //driver.pressKeyCode(66);
    }
}
