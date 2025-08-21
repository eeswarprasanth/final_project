package com.displaybookselves.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.displaybookselves.utils.ConfigReader;
import com.displaybookselves.utils.PopupHandler;

public class BeingAtHome {

	WebDriver driver;
	WebDriverWait wait;
	PopupHandler popupHandler;

	public BeingAtHome(WebDriver driver) {
		this.driver = driver;
		this.popupHandler = new PopupHandler(driver); // ✅ Initialize here
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "search")
	WebElement search;

	@FindBy(id = "search_button")
	WebElement searchBtn;

	@FindBy(css = "li[data-group='category']")
	WebElement catBtn;

	@FindBy(css = "ul.clearfix.options[data-filter-name='primary_category'] li.option")
	List<WebElement> categoryOptions;

	public void categoryList() {
		search.sendKeys(ConfigReader.getSeachWord());
		searchBtn.click();

		wait.until(ExpectedConditions.visibilityOf(catBtn));

		// Hover over the category dropdown
		Actions action = new Actions(driver);
		action.moveToElement(catBtn).perform();

		// Wait for the category list container
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("ul.clearfix.options[data-filter-name='primary_category']")));

		// Wait for all category options to be visible
		List<WebElement> categories = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.cssSelector("ul.clearfix.options[data-filter-name='primary_category'] li.option")));

		System.out.println("Available Categories:");
		for (WebElement category : categories) {
			String categoryName = category.getAttribute("data-option-name");
			System.out.println("- " + categoryName);
		}
	}

}
