package ya.praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ya.praktikum.model.Pages.MainPage;
import ya.praktikum.model.Pages.OrderPage;
import ya.praktikum.model.TestBase;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static ya.praktikum.model.Constants.MainPage.HEADER;
import static ya.praktikum.model.Constants.MainPage.MIDDLE;
import static ya.praktikum.model.Constants.OrderPage.BLACK;
import static ya.praktikum.model.Constants.OrderPage.GREY;

@RunWith(Parameterized.class)
public class CreateOrderSuccess extends TestBase {

    private final String buttonMakeOrderPlace;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String color;
    private final String comment;

    public CreateOrderSuccess(String buttonMakeOrderPlace,
                              String name,
                              String surname,
                              String address,
                              String metro,
                              String phone,
                              String date,
                              String rentalPeriod,
                              String color,
                              String comment) {
        this.buttonMakeOrderPlace = buttonMakeOrderPlace;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }


    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {
                        MIDDLE,
                        "Алексей",
                        "Шуйский",
                        "Москва, Каширское шоссе, д63",
                        "Ясенево",
                        "+79261234567",
                        "19.08.2023",
                        "семеро суток",
                        BLACK,
                        "Комментарий"

                },
                {
                        HEADER,
                        "Ольга",
                        "Ливанова",
                        "Санкт-Петербург",
                        "Сокольники",
                        "89101234567",
                        "20.08.2023",
                        "трое суток",
                        GREY,
                        "Комментарий"
                }
        };
    }

    @Test
    public void testCreateOrder() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        MainPage objMainPage = new MainPage(driver);
        objMainPage.closeCookeeDialog();
        if (buttonMakeOrderPlace.equals(HEADER)) {
            objMainPage.clickMakeOrderInHeader();
        } else {
            objMainPage.clickMakeOrderInMiddle();
        }
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.inputMainOrderData(name, surname, address, metro, phone);
        objOrderPage.clickNextToInputAdditionalOrderData();
        objOrderPage.inputAdditionalOrderData(date, rentalPeriod, color, comment);
        objOrderPage.clickCraeteOrder();
        objOrderPage.clickConfirmOrder();
        assertTrue(objOrderPage.checkEnabledSuccessLabel());
    }
}
