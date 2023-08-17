package ya.praktikum.model;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class TestBase {
    protected WebDriver driver;

    @Before
    // Драйвер для Chrome
    public void setUpChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        openMainPage();
    }

//    @Before
    // Драйвер для FireFox
    public void setUpFirefox() {
        driver = new FirefoxDriver();
        openMainPage();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    protected void openMainPage(){
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
}
