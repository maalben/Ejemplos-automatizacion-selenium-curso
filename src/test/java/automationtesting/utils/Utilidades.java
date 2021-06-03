package automationtesting.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

            case "linkText":
                driver.findElement(By.linkText(locator)).click();
                break;

            default:
                driver.findElement(By.id(locator)).click();
        }

    }

    public static void selectRadio(WebDriver driver, List<WebElement> radios, String value) {
        for(int i=0; i<radios.size(); i++){
            if(radios.get(i).getAttribute("value").toLowerCase().equals(value)){
                radios.get(i).click();
                break;
            }
        }
    }

    public static void selectCheckBox(WebDriver driver, String list, String regex, String locatorList) {
        List<String> userList = new ArrayList<>();
        userList.addAll(Arrays.asList(list.split(regex)));
        List<WebElement> checkBoxs = driver.findElements(By.xpath(locatorList));

        for(int i = 0; i < checkBoxs.size(); i++){
            for(int j=0; j < userList.size(); j++){
                if(checkBoxs.get(i).getText().toLowerCase().equals(userList.get(j))){
                    driver.findElement(By.xpath(locatorList+"["+(i+1)+"]/input")).click();
                    break;
                }
            }
        }
    }

    public static void selectElementList(WebDriver driver, String locator, String value) {
        List<WebElement> listCountry = driver.findElements(By.xpath(locator));
        for(int i=0; i<listCountry.size(); i++){
            if(listCountry.get(i).getText().equals(value)){
                listCountry.get(i).click();
                break;
            }
        }
    }

}
