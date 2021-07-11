package com.inetBanking.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver oDriver;

	public LoginPage(WebDriver rDriver) {
		oDriver = rDriver;
		PageFactory.initElements(rDriver, this);
	}

	@FindBy(xpath="//input[@name='uid']")
	@CacheLookup
	WebElement userName;

	@FindBy(xpath="//input[@name='password']")
	@CacheLookup
	WebElement password;

	@FindBy(xpath="//input[@name='btnLogin']")
	@CacheLookup
	WebElement login_Button;

	@FindBy(xpath="/html/body/div[3]/div/ul/li[15]/a")
	@CacheLookup
	WebElement logout_Button;

	public void setUserName(String uName) {
		userName.sendKeys(uName);
	}

	public void setPassword(String psWord) {
		password.sendKeys(psWord);
	}

	public void clickLogin() {
		login_Button.click();
	}

	public void clickLogout()
	{
		logout_Button.click();
	}


}
