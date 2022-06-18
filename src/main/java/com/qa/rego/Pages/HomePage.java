package com.qa.rego.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.rego.Utils.Constants;
import com.qa.rego.Utils.ElementUtil;

public class HomePage {
	
	private final WebDriver driver;
	private final ElementUtil eleUtil;
	
	private final By search = By.name("search");
	private final By searchBtn = By.cssSelector("div#search button");
	private final By header = By.xpath("//img[@alt='rego logo']");
	private final By accSecList = By.cssSelector("div#content h2");
	private final By signOut=By.xpath("//span[normalize-space()='Sign Out']");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getAccountsPageTitle() {
		return eleUtil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	public boolean isAccountsPageHeaderExist() {
		return eleUtil.doIsDisplayed(signOut);
	}
	
	public boolean isSearchExist() {
		return eleUtil.doIsDisplayed(search);
	}
	
	public SearchResultsPage doSearch(String productName) {
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, productName);
			eleUtil.doClick(searchBtn);
			return new SearchResultsPage(driver);
		}
		return null;
		
	}
	
	
	
	public List<String> getAccountsPageSectionsList() {
		List<WebElement> secList = eleUtil.getElements(accSecList);
		List<String> accSecValList = new ArrayList<String>();
		for(WebElement e : secList) {
			String text = e.getText();
			accSecValList.add(text);
		}
		return accSecValList;
	}
	
	
	
	
	
	

}
