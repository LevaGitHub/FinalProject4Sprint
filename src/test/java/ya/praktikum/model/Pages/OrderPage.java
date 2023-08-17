package ya.praktikum.model.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static ya.praktikum.model.Constants.OrderPage.BLACK;

public class OrderPage {

    private WebDriver driver;
    // Основные поля заказа
    // Поле Имя
    private final By inputName = By.xpath("//input[contains(@placeholder,'Имя')]");
    // Поле Фамилия
    private final By inputSurname = By.xpath("//input[contains(@placeholder,'Фамилия')]");
    // Поле Адрес
    private final By inputAddress = By.xpath("//input[contains(@placeholder,'Адрес')]");
    // Поле со списком Станция метро
    private final By inputMetro = By.xpath("//input[contains(@placeholder,'Станция метро')]");
    // Область со всплывающими пунктами контекстного меню
    private final String popupItemName = "//div[contains(@class,'Order_Text')";
    // Поле Телефон
    private final By inputPhone = By.xpath("//input[contains(@placeholder,'Телефон')]");
    // Кнопка Далее
    private final By buttonNext = By.xpath("//div[contains(@class,'Order_NextButton')]/button[text()='Далее']");

    // Поля Про аренду
    // Надпись Про аренду
    private final By labelAboutRental = By.xpath("//div[contains(@class,'Order_Header_')]");
    // Поле с календарем Когда
    private final By inputDate = By.xpath("//input[contains(@placeholder,'Когда привезти самокат')]");
    // Поле со списком Срок аренды
    private final By comboBoxRentalPeriod = By.xpath("//div[contains(@class,'Dropdown-control')]");
    // ЧекБокс Черный жемчуг
    private final By checkBoxBlack = By.id("black");
    // Чекбокс Серая безысходность
    private final By checkBoxGrey = By.id("grey");
    // Поле Комментарий
    private final By inputComment= By.xpath("//input[contains(@placeholder,'Комментарий')]");
    // Кнопка Заказать
    private final By buttonCreateOrder = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");

    // Пупап подтверждения/дополнительной информации о заказе
    // Кнопка Да
    private final By buttonYes = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Да']");
    // Надпись Заказ оформлен
    private final By labelSuccess = By.xpath("//div[contains(@class,'Order_Modal')]/div[text()='Заказ оформлен']");
    // Кнопка Посмотреть статус
    private final By buttonShowOrder = By.xpath("//div[contains(@class,'Order_NextButton')]/" +
            "button[text()='Посмотреть статус']");

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void setInputName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }

    public void setInputSurname(String surname) {
        driver.findElement(inputSurname).sendKeys(surname);
    }

    public void setInputAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }

    public void setInputMetro(String metro) throws InterruptedException {
        driver.findElement(inputMetro).click();
        WebElement itemElement = driver.findElement(By.xpath(popupItemName + " and text() = '" + metro +"']/parent::button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", itemElement);
        itemElement.click();
    }

    public void setInputPhone(String phone) {
        driver.findElement(inputPhone).sendKeys(phone);
    }

    public void inputMainOrderData(String name,
                                   String surname,
                                   String address,
                                   String metro,
                                   String phone) throws InterruptedException {
        setInputName(name);
        setInputSurname(surname);
        setInputAddress(address);
        setInputMetro(metro);
        setInputPhone(phone);
    }

    public void clickNextToInputAdditionalOrderData(){
        driver.findElement(buttonNext).click();
    }

    public void setInputDate(String date) {
        driver.findElement(inputDate).sendKeys(date);
        driver.findElement(labelAboutRental).click();
    }

    public void setComboBoxRentalPeriod(String rentalPeriod) {
        driver.findElement(comboBoxRentalPeriod).click();
        WebElement itemElement = driver.findElement(By.xpath("//div[contains(@role,'option') " +
                "and text() = '"+ rentalPeriod +"']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", itemElement);
        itemElement.click();
    }

    public void setScooterColor(String color) {
        if (color.equals(BLACK)) {
            driver.findElement(checkBoxBlack).click();
        } else {
            driver.findElement(checkBoxGrey).click();
        }
    }

    public void setInputComment(String comment) {
        driver.findElement(inputComment).sendKeys(comment);
    }

    public void inputAdditionalOrderData(String date,
                                         String rentalPeriod,
                                         String color,
                                         String comment) throws InterruptedException {
        setInputDate(date);
        setComboBoxRentalPeriod(rentalPeriod);
        setScooterColor(color);
        setInputComment(comment);
    }

    public void clickCraeteOrder() {
        driver.findElement(buttonCreateOrder).click();
    }

    public void clickConfirmOrder() {
        driver.findElement(buttonYes).click();
    }

    public boolean checkEnabledSuccessLabel(){
        return driver.findElement(labelSuccess).isEnabled();
    }
}
