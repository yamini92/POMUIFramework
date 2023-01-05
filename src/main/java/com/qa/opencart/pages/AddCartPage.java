package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class AddCartPage {
	
	By shoppiingCartHeader = By.cssSelector("div#content h1");

	public void click() {
		System.out.println("Clicked ");
	}
}
