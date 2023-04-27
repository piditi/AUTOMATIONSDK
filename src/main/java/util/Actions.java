package util;

import config.GeneralConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Actions extends GeneralConfig {
    public static void compareText(String text1, String text2) {
        try {
            Assert.assertEquals(text1,text2);
        } catch (AssertionError e) {
            String message = "Compare text1 & text2 failed!!!";
            System.out.println("Text1: " + text1);
            System.out.println("Text2: " + text2);
            throw new AssertionError(message);
        } catch (Exception e) {
            System.out.printf(e.toString());
        }
    }

    public static void checkTextContains(String text1, String text2) {
        try {
            Assert.assertEquals(text1.contains(text2),true);
        } catch (AssertionError e) {
            String message = "Text1 does not contain text2!!!";
            System.out.println("Text1: " + text1);
            System.out.println("Text2: " + text2);
            throw new AssertionError(message);
        } catch (Exception e) {
            System.out.printf(e.toString());
        }
    }

    public static boolean isElementDisplayed(By element){
        Boolean displayed = null;
        try {
            displayed = driver.findElement(element).isDisplayed();
        } catch (NoSuchElementException e) {
            displayed = false;
        }
        return displayed;
    }

    public static void waitUntilElementDisplayed(By element, int timeOutInSecond){
        if(!isElementDisplayed((element))){
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSecond),Duration.ofMillis(200));
                wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            } catch (NoSuchElementException e) {
                throw e;
            }
        }
    }

    public static void waitForElementToDisplay(By element, int timeOutInSecond){
        if(!isElementDisplayed((element))){
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSecond),Duration.ofMillis(200));
                wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            } catch (TimeoutException ignored) {
            }
        }
    }

    public static String getText(By element) {
        String text = null;
        try {
            waitUntilElementDisplayed(element,3);
            text = driver.findElement(element).getText();
        } catch (NoSuchElementException e) {
            System.out.printf(e.toString());
            throw e;
        }
        return text;
    }

    public static void clickElement(By element) {
        waitUntilElementClickable(element,5);
        driver.findElement(element).click();

    }
/*
    public static boolean isElementClickable(String element){
        MobileElement mElement = (MobileElement) driver.findElementsById(element);
        Boolean clickabled = null;
        try {
            clickabled = mElement.isDisplayed() && mElement.isEnabled();
        } catch (NoSuchElementException e) {
            throw e;
        }
        return clickabled;
    }
*/
    public static void waitUntilElementClickable(By element, int timeOutInSecond){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSecond),Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (NoSuchElementException e) {
            System.out.printf(e.toString());
            throw e;
        }
    }

    public static void waitUntilElementNotDisplayed(By element, int timeOutInSecond){
        DeviceHelper.wait(200);
        if(!isElementDisplayed(element)){
            try {
                //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSecond),Duration.ofMillis(200));
                //wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
            } catch (NoSuchElementException e) {
                System.out.printf(e.toString());
                throw e;
            }
        }
        DeviceHelper.wait(200);
    }
}
