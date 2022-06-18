package com.qa.OpenCart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.OpenCart.Utils.Constants;
import com.qa.OpenCart.Utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private final WebDriver driver;
	private final ElementUtil eleutil;

	// 1. private by locators:
	private final By emailId = By.xpath("//input[@id='email']");
	private final By password = By.xpath("//input[@id='password']");
	private final By loginBtn = By.xpath("//button[contains(text(),'Submit')]");
	private final By forgotPwd = By.linkText("Forgot password?");
	private final By registerLink = By.linkText("Register");
	private final By logout = By.linkText("Logout");

	private final By loginErrorMessg = By.cssSelector("div.alert.alert-danger.alert-dismissible");


	// 2. public page const....
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	// 3. public page actions:
	@Step("getting login page title.....")
	public String getLoginPageTitle() {
		return eleutil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_TITLE);
	}

	@Step("getting login url title.....")
	public String getLoginPageUrl() {
		return eleutil.waitForUrl(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_FRACTION_URL);
	}

	@Step("checking that forgot pwd link is displayed or not.....")
	public boolean isForgotPwdLinkExist() {
		return eleutil.doIsDisplayed(forgotPwd);
	}

	@Step("login to application with correct username {0} and password {1}")
	public HomePage doLogin(String un, String pwd) {
		//eleutil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIME_OUT).sendKeys(un);
		eleutil.doSendKeys(emailId, un);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginBtn);
		return new HomePage(driver);
	}
	
	/*@Step("login to application with correct username {0} and password {1}")
	public boolean doInvalidLogin(String un, String pwd) {
		WebElement email_ele = eleutil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIME_OUT);
		email_ele.clear();
		email_ele.sendKeys(un);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginBtn);
		String actualErrorMesg = eleutil.doElementGetText(loginErrorMessg);
		System.out.println(actualErrorMesg);
			if(actualErrorMesg.contains(Errors.LOGIN_PAGE_ERROR_MESSG)) {
				return true;
			}
			return false;
	}
	
	
	@Step("checking that register link is exist or not.....")
	public boolean isRegisterLinkExist() {
		return eleutil.waitForElementToBeVisible(registerLink, Constants.DEFAULT_TIME_OUT).isDisplayed();

	}
	
	@Step("navigating to register page.....")
	public RegistrationPage navigateToRegisterPage() {
		if(isRegisterLinkExist()) {
			eleutil.doClick(registerLink);
			return new RegistrationPage(driver);
		}
		return null;
	}
	*/

}
