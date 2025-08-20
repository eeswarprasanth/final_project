package com.displaybookselves.base;

import org.openqa.selenium.WebDriver;

import com.displaybookselves.pages.Homepage;
import com.displaybookselves.pages.WishList;
import com.displaybookselves.utils.ConfigReader;

public class MainApp {
	public static void main(String[] args) {
		// Initialize WebDriver
		WebDriver driver = DriverSetup.initializeDriver(ConfigReader.getBrowser());
		// Maximize window and open Pepperfry homepage
		driver.manage().window().maximize();
		driver.get(ConfigReader.getAppUrl());

		// Create Homepage object and perform action
		Homepage homepage = new Homepage(driver);
		homepage.LoginIcon();
//		homepage.clickOnBookshelves();
		
		
//        BeingAtHome beingathome = new BeingAtHome(driver);
//        beingathome.categoryList();

//         Close browser
//        driver.quit();

		WishList wishlist = new WishList(driver);
		wishlist.wishList();
	}
}
