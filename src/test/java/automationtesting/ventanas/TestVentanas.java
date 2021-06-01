package automationtesting.ventanas;

import automationtesting.utils.Utilidades;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

import static automationtesting.utils.Utilidades.clickElement;

public class TestVentanas {

    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }

    public void startPage(){
        driver.manage().window().maximize();
        driver.get("http://demo.automationtesting.in/Windows.html");
    }


    @Test
    public void prueba1Ventana(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        startPage();
        String ventanaInicial = driver.getWindowHandle();
        Utilidades.clickElement(driver, "btn-info", "className");

        for(String manejadorVentanas : driver.getWindowHandles()){
            if(!ventanaInicial.contentEquals(manejadorVentanas)){
                driver.switchTo().window(manejadorVentanas);
                break;
            }
        }

        driver.close();
        driver.switchTo().window(ventanaInicial);

        String textMessageLabel = "If you set the target attribute to \"_blank\" , the link will open in a new " +
                "browser window or a new tab";
        String messageLabeLlocator = driver.findElement(By.id("Tabbed")).findElement(By.tagName("p")).getText();
        Assert.assertEquals(textMessageLabel, messageLabeLlocator);
    }


    @Test
    public void prueba2Ventana(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        startPage();
        String ventanaInicial = driver.getWindowHandle();
        System.out.println("ID ventana inicial: "+ ventanaInicial);
        System.out.println("Titulo de la ventana inicial: " + driver.getTitle());

        clickElement(driver, "//a[@href='#Seperate']", "xpath");
        Utilidades.clickElement(driver, "btn-primary", "className");

        for(String manejadorVentanas : driver.getWindowHandles()){
            if(!ventanaInicial.contentEquals(manejadorVentanas)){
                driver.switchTo().window(manejadorVentanas);
                break;
            }
        }

        /**
         * Se automatiza segunda página
         * */
        js.executeScript("window.scrollBy(0, 300)");

        List<WebElement> contenedorCursos =
                driver.findElement(By.className("getting-started-topic-container")).findElements(By.tagName("h3"));
        List<String> list=new ArrayList();
        for(int i=0; i<contenedorCursos.size();i++){
            list.add(contenedorCursos.get(i).getText());
        }

        List<String> cursosEsperados=new ArrayList();
        cursosEsperados.add("Selenium Grid");
        cursosEsperados.add("Selenium IDE");
        cursosEsperados.add("Selenium WebDriver");

        Collections.sort(list);
        Collections.sort(cursosEsperados);
        Assert.assertArrayEquals(cursosEsperados.toArray(), list.toArray());

        driver.close();
        driver.switchTo().window(ventanaInicial);

        String textMessageLabel = "click the button to open a new window with some specifications";
        String messageLabeLlocator = driver.findElement(By.id("Seperate")).findElement(By.tagName("p")).getText();
        Assert.assertEquals(textMessageLabel, messageLabeLlocator);

    }


    @Test
    public void prueba3Ventana(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        startPage();

        String ventanaInicial = driver.getWindowHandle();
        System.out.println("Ventana principal: " + ventanaInicial);
        System.out.println("titulo ventana principal " + driver.getTitle());
        clickElement(driver, "//a[@href='#Multiple']", "xpath");
        driver.findElement(By.cssSelector("#Multiple>button")).click();

        List<String> ventanas = new ArrayList<>();
        for(String manejadorVentanas : driver.getWindowHandles()){
            ventanas.add(manejadorVentanas);
        }

        System.out.println("Lista de ventanas: " + ventanas);

        //Se ubica en la tercera ventana
        driver.switchTo().window(ventanas.get(1));
        System.out.println("ID de la ventana: "+ ventanas.get(1));
        System.out.println("Titulo "+ driver.getTitle());
        Utilidades.waitOwn(100);

        js.executeScript("window.scrollBy(0, 300)");

        List<WebElement> contenedorCursos =
                driver.findElement(By.className("getting-started-topic-container")).findElements(By.tagName("h3"));
        List<String> list=new ArrayList();
        for(int i=0; i<contenedorCursos.size();i++){
            list.add(contenedorCursos.get(i).getText());
        }

        List<String> cursosEsperados=new ArrayList();
        cursosEsperados.add("Selenium Grid");
        cursosEsperados.add("Selenium IDE");
        cursosEsperados.add("Selenium WebDriver");

        Collections.sort(list);
        Collections.sort(cursosEsperados);
        Assert.assertArrayEquals(cursosEsperados.toArray(), list.toArray());

        driver.close();

        driver.switchTo().window(ventanas.get(2)); //ventana demo automation
        System.out.println("ID De la ventana: " + ventanas.get(2));
        System.out.println("Titulo "+ driver.getTitle());


        Utilidades.typeInField(driver.findElement(By.id("email")), "prueba@correo.com");
        Utilidades.clickElement(driver, "enterimg", "id");

        String expectedMessage = "Automation Demo Site";
        String messageLocator = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(expectedMessage, messageLocator);

        driver.close();

        driver.switchTo().window(ventanas.get(0)); //ventana principal
        System.out.println("ID De la ventana: " + ventanas.get(0));
        System.out.println("Titulo "+ driver.getTitle());
        String expectedMessageMainWindow = "Click the button to open multiple windows";
        String messageLocatorMainWindow = driver.findElement(By.cssSelector("#Multiple>p")).getText();

        Assert.assertEquals(expectedMessageMainWindow, messageLocatorMainWindow);

        Utilidades.waitOwn(100);

    }


    @After
    public void close(){
        driver.quit();
    }

}
