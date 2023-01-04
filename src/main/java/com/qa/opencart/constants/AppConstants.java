package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACC_PAGE_TITLE = "My Account";
	public static final String LOGIN_PAGE_URL_PARAM= "route=account/login";

	public static final String ACC_PAGE_URL_PARAM= "route=account/account";
	
	public static final List<String> ACC_PAGE_SEC_HEADERS = 
			 Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	
	
	
	public static final int DEFAULT_TIMEOUT=5;
	public static final int DEFAULT_TIMEOUT_LONG=10;
	
	
	
	public static final int MACBOOKPRO_IMG_COUNT=4;
	public static final int MACBOOKAIR_IMG_COUNT=4;
	public static final int MACBOOKiMAC_IMG_COUNT=3;
	public static final int SAMSUNGSYNCMAST_IMG_COUNT=1;
	public static final int SAMSUNGGALTAB_IMG_COUNT=7;
	public static final String ACC_CREATE_SUCCESS_MSG = "Your Account Has Been Created!";

	


	public static final String REG_SHEET = "register";}
