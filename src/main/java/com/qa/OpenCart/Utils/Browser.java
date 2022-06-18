package com.qa.OpenCart.Utils;

public interface Browser {

	String CHROME_BROWSER_VAUE = "chrome";
	String FIREFOX_BROWSER_VAUE = "firefox";
	String SAFARI_BROWSER_VAUE = "safari";
	String EDGE_BROWSER_VAUE = "edge";

	String CHROME_DRIVER_BINARY_KEY = "webdriver.chrome.driver";
	String GECKO_DRIVER_BINARY_KEY = "webdriver.gecko.driver";

	String CHROME_DRIVER_PATH = "./src/test/resources/Drivers/chromedriver";
	String FIREFOX_DRIVER_PATH = "./src/test/resources/Drivers/geckodriver";
	
}
