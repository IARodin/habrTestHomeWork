package com.example.habrtesthomework;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.function.BooleanSupplier;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.get("https://www.habr.com/");
    }

    @AfterEach public void tearDown() {
        driver.quit();
    }

    @Test
    public void newAuthor() {
        WebElement hauBecomeAuthor = driver.findElement(By.xpath("//*[contains(text(),'Как стать автором')]"));
        hauBecomeAuthor.click();

        WebElement newPublication = driver.findElement(By.cssSelector("a[rel='nofollow']"));
        newPublication.click();

        assertTrue(driver.findElement(By.cssSelector("#login_form")).isDisplayed(), "Вы не на той странице");
    }

    @Test
    public void bestCompany() {
        WebElement allCompany = driver.findElement(By.cssSelector("*[class= 'tm-block-extralink']"));
        allCompany.click();

        WebElement topCompany = driver.findElement(By.xpath("//*[contains(text(), 'RUVDS.com')]"));
        topCompany.click();

        assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Лучший блог компании 2020')]")).isDisplayed(), "Не Лучший блог компании 2020");
    }
}