package automationtesting.registro;

import static automationtesting.utils.Utilidades.typeInField;
import static automationtesting.utils.Utilidades.selectRadio;
import static automationtesting.utils.Utilidades.clickElement;
import static automationtesting.utils.Utilidades.waitOwn;
import static automationtesting.utils.Utilidades.selectCheckBox;
import static automationtesting.utils.Utilidades.selectElementList;
import static org.openqa.selenium.By.id;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestRegistro {

    public static WebDriver driver;
    String gender = "female";
    String hobbies = "Movies Cricket Hockey".toLowerCase();

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }

    public void startPage() {
        driver.manage().window().maximize();
        driver.get("http://demo.automationtesting.in/Register.html");
    }

    @Test
    public void testRegisterSuccesful(){
        startPage();

        typeInField(driver.findElement(By.xpath("//input[@ng-model='FirstName']")), "Anacleto");
        typeInField(driver.findElement(By.xpath("//input[@ng-model='LastName']")), "Lopez");
        typeInField(driver.findElement(By.xpath("//textarea[@ng-model='Adress']")), "Barrio Poblado");
        typeInField(driver.findElement(By.xpath("//input[@ng-model='EmailAdress']")), "correo@correo.com");
        typeInField(driver.findElement(By.xpath("//input[@ng-model='Phone']")), "1234567890");

        selectRadio(driver, driver.findElements(By.xpath("//div[@class='col-md-4 col-xs-4 col-sm-4']/label/input")), gender);
        selectCheckBox(driver, hobbies, " ", "//div[@class='col-md-4 col-xs-4 col-sm-4']/div");

        clickElement(driver, "msdd", "id");
        selectElementList(driver, "//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content " +
                "ui-corner-all']/li", "Danish");
        selectElementList(driver, "//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content " +
                "ui-corner-all']/li", "Greek");
        selectElementList(driver, "//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content " +
                "ui-corner-all']/li", "Urdu");

        new Select(driver.findElement(id("Skills"))).selectByValue("Adobe Photoshop");
        new Select(driver.findElement(id("countries"))).selectByValue("Anguilla");

        clickElement(driver, "//span[@class='select2-selection select2-selection--single']", "xpath");
        selectElementList(driver, "//ul[@id='select2-country-results']/li", "India");



        WebElement uploadElement = driver.findElement(By.id("imagesrc"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('style','style')",uploadElement);

        String directory = System.getProperty("user.dir");
        String filePath = directory + "/src/test/resources/image/original.png";
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].setAttribute('style', 'display:none;')",uploadElement);
        uploadElement.sendKeys(filePath);



        waitOwn(30);
    }




    @After
    public void close() {
        driver.quit();
    }

}
