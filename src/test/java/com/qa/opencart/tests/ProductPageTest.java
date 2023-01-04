package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ProductPageTest extends BaseTest{

	@BeforeClass
	public void productInfoSetup() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
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
	@Test(dataProvider = "getProductData")
	public void productHeaderTest(String searchKey, String mainProduct) {
		srp = ap.performSearch(searchKey);
		pip = srp.selectProduct(mainProduct);
		String ActProductHeader = pip.getProductHeader(mainProduct);
	
		Assert.assertEquals(ActProductHeader, mainProduct);
		
	}
	
	@DataProvider
	public Object [][] getProductDataWithCount() {
		return new Object[][] {
			{ "Macbook", "MacBook Pro", AppConstants.MACBOOKPRO_IMG_COUNT},
			{ "Macbook", "MacBook Air", AppConstants.MACBOOKAIR_IMG_COUNT},
			{ "iMac", "iMac",AppConstants.MACBOOKiMAC_IMG_COUNT},
			{ "Samsung", "Samsung SyncMaster 941BW",AppConstants.SAMSUNGSYNCMAST_IMG_COUNT },
			{ "Samsung", "Samsung Galaxy Tab 10.1",AppConstants.SAMSUNGGALTAB_IMG_COUNT }
		};
		
	}
	@Test(dataProvider = "getProductDataWithCount")
	public void productImageCountTest(String searchKey, String mainProductName, int totalImgCnt) {
		srp = ap.performSearch(searchKey);
		pip = srp.selectProduct(mainProductName);
		int imgCount = pip.getProductImgCount();
		System.out.println(imgCount);
		Assert.assertEquals(imgCount, totalImgCnt);
	}
	
	@Test
	public void productMetadataTest() {
		srp = ap.performSearch("Samsung");
		pip = srp.selectProduct("Samsung Galaxy Tab 10.1");
		Map<String, String> actualMapData = pip.getProductMetadata();
		//Assert.assertEquals(actualMapData.get("Brand"), "Apple");
		Assert.assertEquals(actualMapData.get("Product Code"), "SAM1");
		Assert.assertEquals(actualMapData.get("Reward Points"), "1000");
		Assert.assertEquals(actualMapData.get("Availability"), "Pre-Order");
	}
}
