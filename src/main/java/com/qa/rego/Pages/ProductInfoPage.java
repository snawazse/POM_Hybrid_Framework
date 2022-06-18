package com.qa.rego.Pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.rego.Utils.Constants;
import com.qa.rego.Utils.ElementUtil;

public class ProductInfoPage {

	private final WebDriver driver;
	private final ElementUtil eleUtil;

	private final By productHeader = By.cssSelector("div#content h1");
	private final By productImages = By.cssSelector("div#content img");
	private final By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private final By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private final By qty = By.id("input-quantity");
	private final By addToCartBtn = By.id("button-cart");
	private final By successMessg = By.cssSelector("div.alert.alert-success.alert-dismissible");

	private Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeaderText() {
		return eleUtil.doElementGetText(productHeader).trim();
	}

	public int getPorductImagesCount() {
		return eleUtil.waitForElementsToBeVisible(productImages, Constants.DEFAULT_TIME_OUT).size();
	}

	public Map<String,String> getProductInfo() {
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("productname", getProductHeaderText());
		productInfoMap.put("productimagescount", String.valueOf(getPorductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
	}

	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: Out Of Stock
		for (WebElement e : metaDataList) {
			String text = e.getText().trim();
			String[] meta = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
	}

	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleUtil.getElements(productPriceData);
//		$2,000.00
//		Ex Tax: $2,000.00
		String price = metaPriceList.get(0).getText().trim();
		String exPrice = metaPriceList.get(1).getText().trim();
		productInfoMap.put("price", price);
		productInfoMap.put("extaxprice", exPrice);
	}

}
