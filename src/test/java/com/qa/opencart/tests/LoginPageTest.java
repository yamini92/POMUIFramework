package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.listeners.ExtendReportListener;

//@Listeners(ExtendReportListener.class)
public class LoginPageTest extends BaseTest{

	
	
	
	@Test(priority = 1)
	public void loginPageTitleTest() {
		
		
		String title = lp.getPageTitle();
	
		
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 3)
	public void checkForgetPasswordLink() {
	
		
		boolean linkExists = lp.isForgotPasswordLinkExists();
		
		Assert.assertEquals(linkExists, true);
	}
	
	@Test(priority = 2)
	public void loginPageUrlCheck() {
		boolean urlcheck = lp.getUrl();
		Assert.assertEquals(urlcheck, true);
	}
	
	@Test(priority = 4)
	public void loginTest() {
		ap = lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		
		Assert.assertTrue(ap.isLogoutLinkExists());
	}
}
