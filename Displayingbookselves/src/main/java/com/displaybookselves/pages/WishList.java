package com.displaybookselves.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.displaybookselves.utils.ConfigReader;
import com.displaybookselves.utils.PopupHandler;

public class WishList {

	WebDriver driver;
	WebDriverWait wait;
	PopupHandler popupHandler;

	public WishList(WebDriver driver) {
		this.driver = driver;
		this.popupHandler = new PopupHandler(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getImplicitWait()));
		PageFactory.initElements(driver, this);
	}

	// Reusable method for safe JavaScript click
	public void jsClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	@FindBy(id = "search")
	WebElement search;

	@FindBy(id = "search_button")
	WebElement searchBtn;

	@FindBy(xpath = "(//a[@class='product-img'])[1]")
	WebElement productCard;

	@FindBy(xpath = "//span[contains(@class,'heart_outline_thick')]")
	WebElement likeBtn;

	@FindBy(xpath = "//h1[contains(text(),'Weston Half Leather Sofa (Licorice Italian Leather)')]")
	WebElement productTitle;

	@FindBy(id = "shortlist-badge")
	WebElement wishListPage;

	@FindBy(css = ".action-button")
	WebElement viewBtn;

	@FindBy(id = "add-to-cart-button")
	WebElement addToCart;

	@FindBy(id = "checkout-link")
	WebElement checkOut;

	public void wishList() {
		// Step 1: Search and open product
		search.clear();
		search.sendKeys(ConfigReader.getWishSearch());
		searchBtn.click();
		productCard.click();

		// Step 2: Like the product if not already liked
		wait.until(ExpectedConditions.visibilityOf(likeBtn));
		String likeBtnClass = likeBtn.getAttribute("class");
		if (likeBtnClass.contains("heart_outline_thick")) {
			likeBtn.click();
		}

		// Step 3: Capture product title
		String productName = productTitle.getText().trim();

		// Step 4: Go to wishlist using JS click
		jsClick(wishListPage);

		// Step 5: Click 'View Product' using JS click
		wait.until(ExpectedConditions.elementToBeClickable(viewBtn));
		jsClick(viewBtn);

		// Step 6: Wait for product page to load again
		wait.until(ExpectedConditions.visibilityOf(productTitle));
		String wishListName = productTitle.getText().trim();

		// Step 7: Compare titles
		if (productName.equalsIgnoreCase(wishListName)) {
			System.out.println("✅ Product successfully added to wishlist: " + wishListName);
		} else {
			System.out.println("❌ Product mismatch! Expected: " + productName + ", Found: " + wishListName);
		}

		// Step 8: Add to cart using JS click
		wait.until(ExpectedConditions.elementToBeClickable(addToCart));
		jsClick(addToCart);

		// Step 9: Click Check Out
		wait.until(ExpectedConditions.elementToBeClickable(checkOut));
		jsClick(checkOut);
	}
}
