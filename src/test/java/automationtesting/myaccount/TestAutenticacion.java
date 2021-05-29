package automationtesting.myaccount;

import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class TestAutenticacion {

    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void autenticacionExitosa(){
        driver.manage().window().maximize();
        driver.get("http://practice.automationtesting.in/");

        List<WebElement> listaLibrosPagina = driver.findElements(By.xpath("//h3"));
        List<String> list=new ArrayList();

        for(int i=0; i<listaLibrosPagina.size();i++){
            list.add(listaLibrosPagina.get(i).getText());
        }

        driver.findElement(By.id("menu-item-50")).click();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String expectedValue = "Login";

        final WebElement loginText1 = driver.findElement(By.xpath("//*[@class='u-column1 col-1']/h2"));
        final WebElement loginText2 = driver.findElement(By.cssSelector(".u-column1"))                          .findElement(By.tagName("h2"));
        final WebElement loginText3 =
                driver.findElement(By.className("u-column1")).findElement(By.tagName("h2"));

        boolean loginClass = driver.findElements(By.tagName("form")).get(1).getAttribute("class").equals("login");
        boolean registerClass = driver.findElements(By.tagName("form")).get(2).getAttribute("class").equals("register");
        boolean postMethod = driver.findElements(By.tagName("form")).get(1).getAttribute("method").equals("post");
        boolean actionPage = driver.findElements(By.tagName("form")).get(1).getAttribute("action").equals("");

        String valueClassForm1 = driver.findElements(By.tagName("form")).get(1).getAttribute("class");
        String valueClassForm2 = driver.findElements(By.tagName("form")).get(2).getAttribute("class");

        String value = loginText1.getText();

        assertEquals(expectedValue, value);
        assertEquals(expectedValue, loginText2.getText());
        assertEquals(expectedValue, loginText3.getText());

        assertTrue(loginClass);
        assertTrue(registerClass);
        assertTrue(postMethod);

        assertFalse(actionPage);

        assertNotEquals(valueClassForm1, valueClassForm2);

        assertNotNull(valueClassForm2);

        assertThat(postMethod, is(equalTo(true)));
        assertThat(valueClassForm1, is(equalTo("login")));

        List<String> listaLibrosEsperados=new ArrayList();
        listaLibrosEsperados.add("Thinking in HTML");
        listaLibrosEsperados.add("Mastering JavaScript");
        listaLibrosEsperados.add("Selenium Ruby");

        Collections.sort(listaLibrosEsperados);
        Collections.sort(list);

        assertTrue(listaLibrosEsperados.equals(list));
        assertEquals(listaLibrosEsperados,list);
        assertArrayEquals(listaLibrosEsperados.toArray(), list.toArray());
    }

    @After
    public void close(){
        driver.quit();
    }
}