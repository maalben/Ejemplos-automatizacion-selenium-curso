package automationtesting.myaccount;

import static automationtesting.utils.Utilidades.typeInField;
import static automationtesting.utils.Utilidades.waitOwn;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestRegistro {

    public static WebDriver driver;
    private final String email = "automatizacioncursos7@gmail.com";
    private final String email2 = "automatizacioncursos2@gmail.com";
    private final String password = "Pp123456Prueba++P";


    public String getEmail(){
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }

    public void startPage(){
        driver.manage().window().maximize();
        driver.get("http://practice.automationtesting.in/my-account/");

    }

    private void diligenciarEmail(String correo) {
        final WebElement emailField = driver.findElement(By.id("reg_email"));
        typeInField(emailField, correo);
    }

        private void diligenciarPassword() {
        final WebElement passwordField = driver.findElement(By.id("reg_password"));
        typeInField(passwordField, this.getPassword());
    }

    @Test
    public void registroExitoso(){
        startPage();
        diligenciarEmail(this.email);
        diligenciarPassword();
        final WebElement registerButton = driver.findElement(By.name("register"));
        registerButton.click();

        waitOwn(6);

        String[] partsEmail = this.getEmail().split("@");
        String expectedValue = "Hello " +partsEmail[0]+ " (not "+partsEmail[0]+"? Sign out)";
        WebElement labelResult = driver.findElement(By.xpath("//div[@class='woocommerce-MyAccount-content']/p[1]"));

        assertEquals(expectedValue, labelResult.getText());
    }

    @Test
    public void validarCorreoExistente(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        startPage();
        diligenciarEmail(this.email2);
        diligenciarPassword();
        final WebElement registerButton = driver.findElement(By.name("register"));
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//ul[@class='woocommerce-error']/li"), "Error: An account is already registered with your email address. Please login."));
        String expectedValue = "Error: An account is already registered with your email address. Please login.";
        WebElement labelResult = driver.findElement(By.xpath("//ul[@class='woocommerce-error']/li"));
        assertEquals(expectedValue, labelResult.getText());
    }

    @Test
    public void validarCampoEmailVacio(){
        startPage();
        diligenciarPassword();
        final WebElement registerButton = driver.findElement(By.name("register"));
        waitOwn(6);
        registerButton.click();
        waitOwn(10);

        String expectedValue = "Error: Please provide a valid email address.";
        WebElement labelResult = driver.findElement(By.xpath("//ul[@class='woocommerce-error']/li"));
        assertEquals(expectedValue, labelResult.getText());

    }

    @After
    public void close(){
        driver.quit();
    }

}
