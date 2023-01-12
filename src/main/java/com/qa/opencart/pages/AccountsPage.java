package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

import io.qameta.allure.Step;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtils eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By AccSectionHeaders = By.cssSelector("div#content h2");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}
	
	@Step("getAccPageTitle......")
	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIMEOUT, AppConstants.ACC_PAGE_TITLE);
		System.out.println("Login page title ---" + title);
		return title;
	}
	
	@Step("getUrl......")
	public boolean getUrl() {
		String url =eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIMEOUT, AppConstants.ACC_PAGE_URL_PARAM);
		if(url.contains(AppConstants.ACC_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
		
	}
	
	@Step("isLogoutLinkExists......")
	public boolean isLogoutLinkExists() {
		return eleUtil.doEleIsDisplayed(logoutLink);
	}
	
	@Step("isSearchExists......")
	public boolean isSearchExists() {
		return eleUtil.doEleIsDisplayed(search);
	}
	
	@Step("SearchResultsPage......{0}")
	public SearchResultsPage performSearch(String productKey) {
		System.out.println("Searched productname " +productKey);
		if(isSearchExists()) {
			
			eleUtil.doSendKeys(search, productKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		}else {
			System.out.println("Search field is not visible on page");
			return null;
		}
		
	}
	
	@Step("getAccHeadersList......")
	public ArrayList<String> getAccHeadersList() {
		
		List<WebElement> secList = eleUtil.waitForElementsToBeVisible(AccSectionHeaders, AppConstants.DEFAULT_TIMEOUT_LONG);
		ArrayList<String> Headers = new ArrayList<String>();
		//List<WebElement> secList = driver.findElements(AccSectionHeaders);
		for(WebElement e : secList) {
			String text = e.getText();
			System.out.println(text);
			Headers.add(text);
			
			
		}
		return Headers;
	}
}
