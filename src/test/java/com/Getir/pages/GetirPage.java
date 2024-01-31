package com.Getir.pages;



public class GetirPage extends BasePage{

    @FindBy(xpath = "//body")
    public WebElement getirTexts;


    @FindBy(xpath = "//div[@id='in-page-channel-node-id']")
    public WebElement popUp;

}


