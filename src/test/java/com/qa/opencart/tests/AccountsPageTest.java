package com.qa.opencart.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accSetup() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void AccPageTitleTest() {
		String AccPageTitle = ap.getAccPageTitle();
		Assert.assertEquals(AccPageTitle, AppConstants.ACC_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void accPageUrlTest() {
		Assert.assertTrue(ap.getUrl());
	}
	
	@Test(priority = 3)
	public void searchExistsTest() {
		Assert.assertTrue(ap.isSearchExists());
	}
	@Test(priority = 4)
	public void logoutLinkExistsTest() {
		Assert.assertTrue(ap.isLogoutLinkExists());
	}
	
	@Test(priority = 5)
	public void accHeaderTest() {
		ArrayList<String> actHeaderList = ap.getAccHeadersList();
		
		Assert.assertEquals(actHeaderList, AppConstants.ACC_PAGE_SEC_HEADERS);
	}
	
	@DataProvider
	public Object [] getProductKey() {
		return new Object[][] {
			{ "Macbook" },
			{ "iMac" },
			{ "Samsung" }
			
		};
		
	} 
	@Test(dataProvider = "getProductKey", priority = 6)
	public void perforSearchCheckTest(String ProdKey) {
		srp = ap.performSearch(ProdKey);
		Assert.assertTrue(srp.searchSuccessful());
		
	}
	@DataProvider
	public Object [][] getProductData() {
		return new Object[][] {
			{ "Macbook", "MacBook Pro" },
			{ "Macbook", "MacBook Air" },
			{ "iMac", "iMac" },
			{ "Samsung", "Samsung SyncMaster 941BW" },
			{ "Samsung", "Samsung Galaxy Tab 10.1" }
		};
		
	}

	@Test(dataProvider = "getProductData", priority = 7)
	public void SearchTest(String searchKey, String mainProductName) {
		srp = ap.performSearch(searchKey);
		if(srp.searchSuccessful()) {
			pip = srp.selectProduct(mainProductName);
			
			String actualProd= pip.getProductHeader(mainProductName);
			Assert.assertEquals(actualProd, mainProductName);
		}
	}

}
