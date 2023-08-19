package ya.praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import ya.praktikum.model.Pages.MainPage;
import ya.praktikum.model.TestBase;

import java.time.Duration;


@RunWith(Parameterized.class)
public class ShowAccordionItemsSuccess extends TestBase {
    private final String questionText;
    private final String answerText;

    @Parameterized.Parameters
    public static Object[][] getItemsText() {
        return new Object[][]{
                {
                     "Сколько это стоит? И как оплатить?",
                     "Сутки — 400 рублей. Оплата курьеру — наличными или картой."
                },
                {
                    "Хочу сразу несколько самокатов! Так можно?",
                    "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто " +
                                "сделать несколько заказов — один за другим."
                },
                {
                    "Как рассчитывается время аренды?",
                    "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени " +
                            "аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат " +
                            "8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."
                },
                {
                    "Можно ли заказать самокат прямо на сегодня?",
                    "Только начиная с завтрашнего дня. Но скоро станем расторопнее."
                },
                {
                    "Можно ли продлить заказ или вернуть самокат раньше?",
                    "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому " +
                            "номеру 1010."
                },
                {
                    "Вы привозите зарядку вместе с самокатом?",
                    "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете " +
                            "кататься без передышек и во сне. Зарядка не понадобится."
                },
                {
                    "Можно ли отменить заказ?",
                    "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. " +
                            "Все же свои."
                },
                {
                    "Я жизу за МКАДом, привезёте?",
                    "Да, обязательно. Всем самокатов! И Москве, и Московской области."
                }
        };
    }

    public ShowAccordionItemsSuccess(String questionText, String answerText) {
        this.questionText = questionText;
        this.answerText = answerText;
    }

    @Test
    public void testAccordionItem() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        MainPage objMainPage = new MainPage(driver);
        objMainPage.closeCookeeDialog();
        WebElement questionItem = driver.findElement( By.xpath("//*[text()='" + questionText + "']"));
        WebElement answerItem = driver.findElement(By.xpath("//*[text()='" + answerText + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionItem);
        questionItem.click();
        Assert.assertTrue(answerItem.isEnabled());
    }
}
