package automationtesting.myaccount;

import org.junit.After;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestRegistro {

    public static WebDriver driver;
    private final String email = "automatizacioncursos6@gmail.com";
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
        TypeInField(emailField, correo);
    }

        private void diligenciarPassword() {
        final WebElement passwordField = driver.findElement(By.id("reg_password"));
        TypeInField(passwordField, this.getPassword());
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
        startPage();
        diligenciarEmail(this.email2);
        diligenciarPassword();
        final WebElement registerButton = driver.findElement(By.name("register"));
        waitOwn(6);
        registerButton.click();
        waitOwn(6);

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


    public void TypeInField(WebElement field, String value){
        field.click();
        field.clear();
        for (int i = 0; i < value.length(); i++){
            char c = value.charAt(i);
            String s = new StringBuilder().append(c).toString();
            field.sendKeys(s);
            waitOwn(1);
        }
    }

    public void waitOwn(int segundos){
        long time = segundos * 100;
        try {
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
