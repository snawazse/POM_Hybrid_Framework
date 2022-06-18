package com.qa.OpenCart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.OpenCart.Utils.Constants;
import com.qa.OpenCart.Utils.ElementUtil;

public class RegistrationPage {

	private final WebDriver driver;
	private final ElementUtil eleutil;

	private final By firstName = By.id("input-firstname");
	private final By lastName = By.id("input-lastname");
	private final By email = By.id("input-email");
	private final By telephone = By.id("input-telephone");
	private final By password = By.id("input-password");
	private final By confirmpassword = By.id("input-confirm");
	private final By subscribeYes = By.xpath("//label[@class='radio-inline']//input[@type='radio' and @value = '1']");
	private final By subscribeNo = By.xpath("//label[@class='radio-inline']//input[@type='radio' and @value = '0']");
	private final By agreeCheckBox = By.name("agree");
	private final By continueBtn = By.xpath("//input[@type='submit' and @value='Continue']");
	private final By sucessMessg = By.cssSelector("div#content h1");
	private final By logoutLink = By.linkText("Logout");
	private final By registerLink = By.linkText("Register");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public boolean accountRegistration(String firstName, String lastName, String email, String telephone,
			String password, String subscribe) {

		eleutil.waitForElementToBeVisible(this.firstName, Constants.DEFAULT_TIME_OUT).sendKeys(firstName);
		eleutil.doSendKeys(this.lastName, lastName);
		eleutil.doSendKeys(this.email, email);
		eleutil.doSendKeys(this.telephone, telephone);
		eleutil.doSendKeys(this.password, password);
		eleutil.doSendKeys(this.confirmpassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleutil.doClick(subscribeYes);
		} else {
			eleutil.doClick(subscribeNo);
		}
		eleutil.doClick(agreeCheckBox);
		eleutil.doClick(continueBtn);

		if (getAccountRegisterSuccessMessg().contains(Constants.REGISTER_SUCCESS_MESSG)) {
			goToRegisterPage();
			return true;
		}
		return false;
	}

	public String getAccountRegisterSuccessMessg() {
		return eleutil.waitForElementToBeVisible(sucessMessg, Constants.DEFAULT_TIME_OUT).getText();
	}

	private void goToRegisterPage() {
		eleutil.doClick(logoutLink);
		eleutil.doClick(registerLink);

	}

}
