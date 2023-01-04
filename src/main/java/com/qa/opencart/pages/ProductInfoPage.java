package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class ProductInfoPage {
	
	private WebDriver driver;
	
	private ElementUtils eleutil;
	private Map<String, String> prodInfoMap;

	private By productImg = By.cssSelector("ul.thumbnails img");
	private By productMetadata= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPrice= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	
	
	public ProductInfoPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		
		eleutil = new ElementUtils(driver);
	}

	
	public String getProductHeader(String productName) {
		By h1xpath = By.xpath("//h1[text()=\""+productName+"\"]");
		String productHeader = eleutil.doGetText(h1xpath);
		return productHeader;
	}
	public int getProductImgCount() {
		return eleutil.waitForElementsToBeVisible(productImg, AppConstants.DEFAULT_TIMEOUT_LONG).size();
	}
	
	public String getProdPageTitle(String productTitle) {
		return eleutil.waitForTitleIs(AppConstants.DEFAULT_TIMEOUT_LONG, productTitle);
	}
	public String getProdPageUrl(String searchKey) {
		return eleutil.waitForUrlContains(AppConstants.DEFAULT_TIMEOUT,searchKey );
	}
	
	public Map<String, String> getProductMetadata() {
		List<WebElement> metaList = eleutil.getElements(productMetadata);
		prodInfoMap = new LinkedHashMap<String, String>();
		for(WebElement e: metaList) {
			String metaTest = e.getText();
			String meta[] = metaTest.split(":");
			String metaKey = meta[0].trim();
			String metaVal = meta[1].trim();
			prodInfoMap.put(metaKey, metaVal);
		}
		prodInfoMap.forEach((k,v) -> System.out.println(k+":"+v));
		return prodInfoMap;
		
	}
}
