package in.shopperstreet.PlaylistDownloader;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class Utube {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();
    WebDriverWait wait;


    /*array to hold the videos ids fetched*/
    ArrayList<String> videoIds;


    public void setUp(ArrayList<String> videoList) throws Exception {


        System.setProperty("webdriver.chrome.driver", "/Users/prakash.s/Desktop/chromedriver");

        String downloadFilepath = "/Users/prakash.s/Downloads/selea";



        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

        chromePrefs.put("profile.default_content_settings.popups", 0);

        chromePrefs.put("download.default_directory", downloadFilepath);

        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("prefs", chromePrefs);

        DesiredCapabilities cap = DesiredCapabilities.chrome();

        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        cap.setCapability(ChromeOptions.CAPABILITY, options);



        driver = new ChromeDriver(cap);

        baseUrl = "http://en.savefrom.net/";

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 5);

        videoIds = videoList;

        testUtube();

    }


    public void testUtube() throws Exception {

        driver.get(baseUrl + "/");

        String youtubeUrl = "https://www.youtube.com/watch?v=";

        for (String videoId : videoIds) {

            driver.findElement(By.id("sf_url")).clear();
            driver.findElement(By.id("sf_url")).sendKeys(youtubeUrl+videoId);
            driver.findElement(By.id("sf_submit")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Download")));
            driver.findElement(By.linkText("Download")).click();
            Thread.sleep(8000);
        }
        tearDown();
    }


    public void tearDown() throws Exception {
//        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
//            fail(verificationErrorString);
        }
    }

}