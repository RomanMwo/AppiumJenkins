import Framework.Capability;
import Framework.DataProviders;
import Framework.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;

@Story("test")
@Epic("google")
public class AppiumClass {

    public AndroidDriver<AndroidElement> driver;
    public FluentWait<WebDriver> wait;


    public DesiredCapabilities capObject() throws FileNotFoundException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        Gson g = new Gson();

        String pathCapabilitiesData = "./src/test/capabilitesdata.json";
        BufferedReader reader = new BufferedReader(new FileReader(pathCapabilitiesData));
        Capability capData = g.fromJson(reader, Capability.class);

        capabilities.setCapability("BROWER_NAME", capData.BROWSER_NAME);
        capabilities.setCapability("VERSION", capData.VERSION);
        capabilities.setCapability("deviceName", capData.deviceName);
        capabilities.setCapability("udid", capData.udid);
        capabilities.setCapability("platformName", capData.platformName);
        capabilities.setCapability("appPackage", capData.appPackage);
        capabilities.setCapability("appActivity", capData.appActivity);
        capabilities.setCapability("autoGrantPermissions", capData.autoGrantPermissions);
        capabilities.setCapability("newCommandTimeout", capData.newCommandTimeout);
        capabilities.setCapability("androidInstallTimeout", capData.androidInstallTimeout);

        return capabilities;
    }


    @Test
    public void testName(ITestContext context) throws MalformedURLException, InterruptedException, FileNotFoundException {

        DesiredCapabilities caps = capObject();

        // capabilities.setCapability("automationName" , "UiAutomator2");

        this.driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4729/wd/hub"), capObject());
        this.wait = new WebDriverWait(driver, 90)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NullPointerException.class)
                .ignoring(ClassCastException.class)
                .ignoring(NoSuchElementException.class);

        context.setAttribute("driver", this.driver);
        context.setAttribute("wait", this.wait);

        StoreStartPage storeStartPage = new StoreStartPage(context);
        storeStartPage.isDisplayed();
        storeStartPage.searchByText();


        Thread.sleep(1000);
    }

    @Test(groups = {"smoke", "reggresion"}, dataProvider = "smokeProvider", dataProviderClass = DataProviders.class)
    public void smokeTest(String name, int age) {
        System.out.println("Hello smoke");
        System.out.println(name);
        System.out.println(age);
    }

    @Test(groups = {"reggresion"})
    public void reggresionTest() {
        System.out.println("Hello regres");
    }

    @Test
    public void loader() throws FileNotFoundException {
        Gson g = new Gson();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Person person1 = g.fromJson("{\"name\": \"John\"}", Person.class);
        Person person2 = gson.fromJson("{\"name\": \"John\"}", Person.class);

        System.out.println(person1.name);
        System.out.println(g.toJson(person1));
        System.out.println(gson.toJson(person2));

        String pathData = "./src/test/data.json";
        BufferedReader reader = new BufferedReader(new FileReader(pathData));
        User user = g.fromJson(reader, User.class);

        System.out.println(user.name);
        System.out.println(user.language);
        System.out.println(user.personal.age);
    }

    public class Person {

        public String name;
    }

}
