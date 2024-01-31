package com.Getir.pages;

import org.testng.Assert;

import java.io.*;
import java.net.URLConnection;
import java.sql.Driver;

public class BasePage {





    public BasePage() {
            Object PageFactory;
            PageFactory.initElements(Driver.get(), this);
        }


        public void openHomePage(String pageName)  {
            Driver.get().get(ConfigReader.get(pageName));
            BrowserUtilities.staticWait(1);
        }

        public void clickButton(WebElement buttonName) {
            BrowserUtilities.explicitlyWaitVisible(buttonName);
            buttonName.click();
        }

        public void goBack() {
            BrowserUtilities.forward();
        }

        public void verifyPage(WebElement element) {
            BrowserUtilities.explicitlyWaitVisible(element);
            Assert.assertTrue(element.isDisplayed());
        }

        public void verifyPageDoesntAppear(WebElement element) {
            //BrowserUtils.explicitlyWaitVisible(element);
            Assert.assertFalse(element.isDisplayed());
        }

        public void hoverAndClickButton(WebElement elementHover, WebElement elementClick) {
            BrowserUtilities.explicitlyWaitVisible(elementHover);
            Actions action = new Actions(Driver.get());
            action.moveToElement(elementHover).perform();
            elementClick.click();
        }

        public void hoverAndClickButtonWithJS(WebElement elementHover, WebElement elementClick) {
            BrowserUtilities.explicitlyWaitVisible(elementHover);
            Actions action = new Actions(Driver.get());
            action.moveToElement(elementHover).perform();
            clickButtonWithJS(elementClick);
        }

        public void clickButtonWithJS(WebElement element) {
            JavascriptExecutor executor = (JavascriptExecutor)Driver.get();
            executor.executeScript("arguments[0].click();", element);
        }

        public void verifyPagewithJS(WebElement element) {
            JavascriptExecutor executor = (JavascriptExecutor)Driver.get();
            executor.executeScript("arguments[0].click();", element);
        }

        public void verifyPageInTheNewTab(WebElement element) {
            BrowserUtilities.switchToWindow(1);
            Driver.get().manage().window().setSize(new Dimension(1920,1080));
            BrowserUtilities.explicitlyWaitVisible(element);
            verifyPage(element);
            Driver.get().close();
            BrowserUtilities.switchToWindow(0);
        }

        public void clickButtonUnderSide(WebElement element) {
            BrowserUtilities.scrollToElement(element);
            BrowserUtilities.explicitlyWaitVisible(element);
            element.click();
        }

        public void verifyPageLowerSide(WebElement element) {
            BrowserUtilities.scrollToElement(element);
            BrowserUtilities.explicitlyWaitVisible(element);
            Assert.assertTrue(element.isDisplayed());
        }

        public void verifyURLInTheNewTab(String url) {
            System.out.println("a");
            URLConnection BrowserUtilities;
            BrowserUtilities.switchToWindow(1);
            System.out.println("b");
            System.out.println(BrowserUtilities.getURL());
            Assert.assertEquals(url, BrowserUtilities.getURL());
            System.out.println("c");
            Driver.get().close();
            System.out.println("d");
            BrowserUtilities.switchToWindow(0);
            System.out.println("e");
        }

        public void checkPage(WebElement pageBody, String pageName, String firstWord, String lastWord) throws Exception {
            Thread.sleep(2);

            String actual1 = pageBody.getText();

            File fileActual = new File("src/test/resources/texts/" + pageName + "_Actual.txt");

//        actual1 = actual1.substring(actual1.lastIndexOf(firstWord), actual1.lastIndexOf(lastWord) + lastWord.length());

            actual1 = actual1.substring(actual1.lastIndexOf(firstWord),actual1.lastIndexOf(lastWord));


            FileWriter write = new FileWriter(fileActual);
            write.write(actual1);
            write.close();
            String expected = "";

            File fileExpected = new File("src/test/resources/texts/" + pageName + "_Expected.txt");

////        first step
//        write = new FileWriter(fileExpected);
//        write.write(actual1);
//        write.close();

            Reader scanner1 = new FileReader(fileExpected);
            BufferedReader bufferedReader1 = new BufferedReader(scanner1);

            boolean flag = true;

            while(flag){
                String temp = bufferedReader1.readLine();
                if (temp==null)
                    flag=false;
                expected += temp;
            }

            flag = true;

            Reader scanner2 = new FileReader(fileActual);
            BufferedReader bufferedReader2 = new BufferedReader(scanner2);

            String actual = "";

            while(flag){
                String temp = bufferedReader2.readLine();
                System.out.println(temp);
                if (temp==null)
                    flag=false;
                actual += temp;
            }


            System.out.println("actual = " + actual.trim());
            System.out.println("expected = " + expected);


            Assert.assertEquals((expected.trim().split(" ")), actual.trim().trim().trim().split(" "));

        }
        public void checkPage2(WebElement pageBody, String pageName, String firstWord, String lastWord) throws Exception {

            String actual1 = pageBody.getText();

            File fileActual = new File("src/test/resources/texts/" + pageName + "_Actual.txt");

            actual1 = actual1.substring(actual1.lastIndexOf(firstWord),actual1.lastIndexOf(lastWord));


            FileWriter write = new FileWriter(fileActual);
            write.write(actual1);
            write.close();
            String expected = "";

            File fileExpected = new File("src/test/resources/texts/" + pageName + "_Expected.txt");

////        first step
//        write = new FileWriter(fileExpected);
//        write.write(actual1);
//        write.close();

            Reader scanner1 = new FileReader(fileExpected);
            BufferedReader bufferedReader1 = new BufferedReader(scanner1);

            boolean flag = true;

            while(flag){
                String temp = bufferedReader1.readLine();
                if (temp==null)
                    flag=false;
                expected += temp;
            }

            flag = true;

            Reader scanner2 = new FileReader(fileActual);
            BufferedReader bufferedReader2 = new BufferedReader(scanner2);

            String actual = "";

            while(flag){
                String temp = bufferedReader2.readLine();
                System.out.println(temp);
                if (temp==null)
                    flag=false;
                actual += temp;
            }


            System.out.println("actual = " + actual.trim());
            System.out.println("expected = " + expected.trim());


            Assert.assertEquals(expected.replaceAll("\\s+", ""), actual.replaceAll("\\s+", ""));

        }


        public void checkPage3(WebElement pageBody, String pageName, String firstWord, String lastWord) throws Exception {
            String actual1 = pageBody.getText();

            int startIndex = actual1.indexOf(firstWord);
            int endIndex = actual1.indexOf(lastWord);

            if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
                actual1 = actual1.substring(startIndex, endIndex + lastWord.length());
            } else {
                // İlgili kelimeleri bulamazsanız ya da sıralama doğru değilse hata durumunu ele alabilirsiniz.
                throw new IllegalArgumentException("Belirtilen kelimeler bulunamadı veya sıralama yanlış.");
            }

            File fileActual = new File("src/test/resources/texts/" + pageName + "_Actual.txt");

            FileWriter write = new FileWriter(fileActual);
            write.write(actual1);
            write.close();

            String expected = "";

            File fileExpected = new File("src/test/resources/texts/" + pageName + "_Expected.txt");

            // İlk adım
            // write = new FileWriter(fileExpected);
            // write.write(actual1);
            // write.close();

            Reader scanner1 = new FileReader(fileExpected);
            BufferedReader bufferedReader1 = new BufferedReader(scanner1);

            boolean flag = true;

            while (flag) {
                String temp = bufferedReader1.readLine();
                if (temp == null)
                    flag = false;
                expected += temp;
            }

            flag = true;

            Reader scanner2 = new FileReader(fileActual);
            BufferedReader bufferedReader2 = new BufferedReader(scanner2);

            String actual = "";

            while (flag) {
                String temp = bufferedReader2.readLine();
                System.out.println(temp);
                if (temp == null)
                    flag = false;
                actual += temp;
            }

            System.out.println("actual = " + actual.trim());
            System.out.println("expected = " + expected.trim());

            Assert.assertEquals(expected.replaceAll("\\s+", ""), actual.replaceAll("\\s+", ""));
        }
    }

}
