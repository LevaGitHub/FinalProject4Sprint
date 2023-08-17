package ya.praktikum.model.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class MainPage {
    private WebDriver driver;
    // Кнопка Заказать в верхнем правом углу
    private final By buttonMakeOrderHeader = By.xpath("//div[contains(@class,'Header_Nav')]/" +
            "button[contains(@class,'Button_Button')]");
    // Кнопка Заказать в середине страницы
    private final By buttonMakeOrderMiddle = By.xpath("//div[contains(@class,'Home')]/button[contains(@class,'Button')]");
    // Кнопка Да все привыкли, которая разрешает сохранять куки
    private final By buttonCookeeSave = By.xpath("//button[contains(@class,'App_CookieButton')]");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickMakeOrderInHeader(){
        driver.findElement(buttonMakeOrderHeader).click();
    }

    public void clickMakeOrderInMiddle() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(
                buttonMakeOrderMiddle));
        driver.findElement(buttonMakeOrderMiddle).click();
    }

    public void closeCookeeDialog() {
        if (driver.findElement(buttonCookeeSave).isEnabled()) {
            driver.findElement(buttonCookeeSave).click();
        }
    }
}
