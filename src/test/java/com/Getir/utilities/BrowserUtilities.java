package com.Getir.utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

public class BrowserUtilities {

    public static void forward(){
        Driver.get().navigate().back();
    }

    public static void explicitlyWaitVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static String getURL(){
        return Driver.get().getCurrentUrl();
    }

    public static void scrollToElement(WebElement element){
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);",element);
        JavascriptExecutor jse = (JavascriptExecutor) Driver.get();
        jse.executeScript("window.scrollBy(0,-100)");
    }

    public static void clickButtonWithJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)Driver.get();
        executor.executeScript("arguments[0].click();", element);
    }
    public static void scrollAndClickButtonWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].click();", element);
    }

    public static void hover(WebElement element){
        Actions action = new Actions(Driver.get());
        action.moveToElement(element).pause(200).build().perform();
    }

    public static void waitForNewWindow(){
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(4));
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToWindow(int index){
        try {
            waitForNewWindow();
            Set<String> windowHandles = Driver.get().getWindowHandles();
            ArrayList<String> allTabs = new ArrayList<>(windowHandles);
            Driver.get().switchTo().window(allTabs.get(index));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void staticWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class ExpectedConditions {
    }

    private static class JavascriptExecutor {
    }

    private static class WebDriverWait {
    }
}
