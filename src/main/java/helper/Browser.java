package helper;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.HomePage;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

    public void checkanswer() {



    }


    public void navigateToBaseUrl() {
        _driver.get(getBaseUrl());
        try {
            Broken();




        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement parent  = _driver.findElement(By.className("questionSection"));

            WebElement Each_question_set;

            List<WebElement> Each_answer_set;

            List<WebElement> question  =  parent.findElements(By.xpath("//*[starts-with(@id,'question')]")); //11

            int i = 0;
            while (i < question.size()) {
                    Each_question_set = question.get(i);
                    System.out.println("Question has Answer ::"+i +":"+Each_question_set.getText());


            i++;
        }

        System.out.println("--------------------------------");
        System.out.println("--------------------------------");

    }


    public void Broken() throws InterruptedException {

        Map<Integer, List<String>> map = _driver.findElements(By.xpath("//*[@href]"))
                .stream()                             // find all elements which has href attribute & process one by one
                .map(ele -> ele.getAttribute("href")) // get the value of href
                .map(String::trim)                    // trim the text
                .distinct()                           // there could be duplicate links , so find unique
                .collect(Collectors.groupingBy(Browser::getResponseCode)); // group the links based on the response code


    }

    // hits the given url and returns the HTTP response code
    public static int getResponseCode(String link) {
        URL url;
        HttpURLConnection con = null;
        Integer responsecode = 0;
        try {
            url = new URL(link);
            con = (HttpURLConnection) url.openConnection();
            responsecode = con.getResponseCode();
        } catch (Exception e) {
            // skip
        } finally {
            if (null != con)
                con.disconnect();
        }
        return responsecode;
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
