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
