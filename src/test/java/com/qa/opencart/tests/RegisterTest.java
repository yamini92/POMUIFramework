package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtils;

public class RegisterTest extends BaseTest{
	
	@BeforeClass
	public void RegSetup() {
		rp = lp.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object regData [][] =ExcelUtils.getTestData(AppConstants.REG_SHEET);
		return regData;
	}
	

	
	public String getRandomEmail() {
	
		Random random = new Random();
		String email = "testAutomation"+random.nextInt(1000)+"@gmail.com";
		return email;
	}
	@Test(dataProvider = "getRegTestData")
	public void regisiterUserTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		String actSucMsg = rp.userRegistration(firstName, lastName, getRandomEmail(), telephone, password, subscribe);
		Assert.assertEquals(actSucMsg, AppConstants.ACC_CREATE_SUCCESS_MSG);
	}

}
