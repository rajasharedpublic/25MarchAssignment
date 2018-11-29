package helper;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.HomePage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Browser {


    public Browser(String browserName, String baseUrl) {
        setBrowser(browserName);
        setBaseUrl(baseUrl);
        Initialise(getBrowser());
    }

    private void Initialise(String browser) {
        capabilities = new DesiredCapabilities();
        //seleniumFolderPath = System.getProperty("user.home") + "/Documents/selenium3/";
        //seleniumFolderPath = "binaries/selenium-server-standalone-3.141.59.jar";
        seleniumFolderPath = "binaries";

        System.out.println("Browser Value::" + browser);

        try {
        switch (browser) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "binaries/chromedriver_v69-71");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                _driver = new ChromeDriver(options);

                break;
            case "Firefox":
                FirefoxProfile ff_profile = new FirefoxProfile();
                ff_profile.setPreference("geo.prompt.testing", true);
                ff_profile.setPreference("geo.prompt.testing.allow", true);
                System.setProperty("webdriver.gecko.driver", seleniumFolderPath + "geckodriver");
                capabilities.setBrowserName("firefox");
                capabilities.setCapability(FirefoxDriver.PROFILE, ff_profile);
                _driver = new FirefoxDriver(capabilities);
                break;

            default:
                System.out.println("Invalid browser passed in: " + browser);
                break;
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        _driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void navigateTo(String url) {
        _driver.get(url);
    }

    public void navigateToBaseUrl() {
        _driver.get(getBaseUrl());
    }

    public String getBrowser() {
        return this.browserName;
    }

    private void setBrowser(String browserName) {
        this.browserName = browserName;
    }

    private void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public HomePage HomePage() {
        if (homePage == null) {
            homePage = new HomePage(this);
        }
        return homePage;
    }

    // Public properties
    public WebDriver _driver;

    // Private properties
    private DesiredCapabilities capabilities;
    private String browserName;
    private String baseUrl;
    private String seleniumFolderPath;
    private HomePage homePage;
}
