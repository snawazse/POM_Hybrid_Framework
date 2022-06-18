package com.qa.OpenCart.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.OpenCart.Base.BaseTest;
import com.qa.OpenCart.Utils.Constants;
import com.qa.OpenCart.Utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100 - Design login page for open cart application")
@Story("US 101 - desgin login page features")
public class LoginPageTest extends BaseTest{
	
	@Test
	@Description("login Page Title Test.....")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("page title : " + actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE, Errors.LOGIN_PAGE_TITLE_MISMATCHED);
	}
	
	@Test
	@Description("login Page url Test.....")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageUrlTest() {
		String url = loginPage.getLoginPageUrl();
		System.out.println("login page url : " + url);
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Description("check forgot pwd link Test.....")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test
	@Description("login Title Test with correct username and correct password.....")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isAccountsPageHeaderExist());
	}
	
	
	@Test
	@Description("Register link Test.....")
	@Severity(SeverityLevel.CRITICAL)
	public void isRegisterLinkExist() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	

}
