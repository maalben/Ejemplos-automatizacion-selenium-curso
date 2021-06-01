package automationtesting.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class Utilidades {

    public static void typeInField(WebElement field, String value){
        field.click();
        field.clear();
        for (int i = 0; i < value.length(); i++){
            char c = value.charAt(i);
            String s = new StringBuilder().append(c).toString();
            field.sendKeys(s);
            waitOwn(1);
        }
    }

    public static void waitOwn(int segundos){
        long time = segundos * 100;
        try {
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickElement(WebDriver driver, String locator, String type){
        switch (type){
            case "id":
                driver.findElement(By.id(locator)).click();
                break;

            case "xpath":
                driver.findElement(By.xpath(locator)).click();
                break;

            case "className":
                driver.findElement(By.className(locator)).click();
                break;

            default:
                driver.findElement(By.id(locator)).click();
        }

    }

}
