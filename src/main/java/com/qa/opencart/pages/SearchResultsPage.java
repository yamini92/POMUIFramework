package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class SearchResultsPage {
	
	private WebDriver driver;

	private ElementUtils eleUtil;
	
	By searchProductSize = By.cssSelector("div.product-layout");
	
	
	public SearchResultsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}
	
	public boolean searchSuccessful(){
		List<WebElement> searchList= eleUtil.waitForElementsToBeVisible(searchProductSize, AppConstants.DEFAULT_TIMEOUT_LONG);
		if (searchList.size()>0) {
			System.out.println("Search is giving result --- " + searchList.size());
			return true;
		}else{
			System.out.println("Search is giving result 0");
			return false;
		}
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productNameLink = By.linkText(productName);
		eleUtil.doClickWithWait(productNameLink, AppConstants.DEFAULT_TIMEOUT_LONG);
		return new ProductInfoPage(driver);
	}

}
