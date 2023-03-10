package com.qa.opencart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
//import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtils;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtils eleUtil;
	private static final Logger LOG = Logger.getLogger(LoginPage.class);
	//1. By locator
	
	private By emailId = By.id("input-email");
	private By passwd = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	//private By logoImg = By.cssSelector("img[title='naveenopencart']");
	private By Fpasswd = By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/div[2]/a");
	private By regLink = By.xpath("//div[@class='list-group']/a[text()='Register']");
	//2.page constructor
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}
	
	
	//3. page actions
	@Step("Waiting for login page title and fetching the title")
	public String getPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIMEOUT, AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("Login page title ---" + title);
		LOG.info("Login page title ---" + title);
		return title;
	}
	
	@Step("Waiting for login page url and fetching the url")
	public boolean getUrl() {
		
		String url =eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIMEOUT, AppConstants.LOGIN_PAGE_URL_PARAM);
		if(url.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}
	
	@Step("checking forgot pwd link is displayed on login page")
	public boolean isForgotPasswordLinkExists() {
		return eleUtil.doEleIsDisplayed(Fpasswd);
	}
	

	@Step("login with username : {0} and password: {1}")
	public AccountsPage doLogin(String username, String pwd) {
		eleUtil.doSendKeysWithWait(emailId, AppConstants.DEFAULT_TIMEOUT_LONG, username);
		eleUtil.doSendKeys(passwd, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	@Step("navigating to register page")
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.doClickWithWait(regLink, AppConstants.DEFAULT_TIMEOUT);
		return new RegistrationPage(driver);
		
	}
}
