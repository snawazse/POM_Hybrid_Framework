package com.qa.OpenCart.Tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.OpenCart.Base.BaseTest;
import com.qa.OpenCart.Utils.Constants;
import com.qa.OpenCart.Utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest{
	
	
	@BeforeClass
	public void productInfoSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
//	@DataProvider
//	public Object[][] getProductData() {
//		return new Object[][] {
//			{"MacBook", "MacBook Pro"},
//			{"MacBook", "MacBook Air"},
//			{"Apple", "Apple Cinema 30\""}
//		};
//	}
	
	@DataProvider
	public Object[][] getProductData(){
		Object[][] productData = ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
		return productData;
	}
	
	@Test(dataProvider = "getProductData")
	public void productInfoHeaderTest(String productName, String mainProductName) {
		searchResultsPage = homePage.doSearch(productName);
		productInfoPage = searchResultsPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeaderText(), mainProductName);
	}
	
	@Test
	public void productImagesTest() {
		searchResultsPage = homePage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Air");
		Assert.assertTrue(productInfoPage.getPorductImagesCount() == Constants.MACBOOK_IMAGES_COUNT);
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = homePage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k,v) -> System.out.println(k + ":" + v));
		
		softAssert.assertEquals(actProductInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertAll();
	}
	
}
