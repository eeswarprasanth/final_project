package com.displaybookselves.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.displaybookselves.utils.PopupHandler;

public class Homepage {
	WebDriver driver;
	WebDriverWait wait;
	PopupHandler popupHandler;

	public Homepage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.popupHandler = new PopupHandler(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "li.topnav_item.livingunit")
	private WebElement livingTab;

	@FindBy(xpath = "//a[@href='/bookshelf?src=g_topnav_living_living-storage_bookshelves']")
	private WebElement bookshelvesLink;

	@FindBy(xpath = "(//*[name()='path'][@fill-rule='evenodd'])[4]")
	private WebElement loginicon;

	@FindBy(xpath = "//a[@id='header-icon-login']")
	private WebElement login;

	@FindBy(xpath = "//div[@id='password-credentials']//input[@id='spree_user_email']")
	private WebElement email;

	@FindBy(xpath = "//div[@class='password-wrap']//input[@id='spree_user_password']")
	private WebElement password;

	@FindBy(id = "ul_site_login")
	private WebElement clickLogin;

	@FindBy(xpath = "//html/body/div[1]/header/div[2]/div/nav/div/ul/li[4]/span")
	private WebElement Living;

//	@FindBy(xpath = "/html/body/div[1]/header/div[2]/div/nav/div/ul/li[4]/div/div/ul/li[2]/ul/li[2]/a/span")
//	private WebElement bookshelve;

	public void LoginIcon() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Actions actions = new Actions(driver);
		actions.moveToElement(loginicon).perform();
		login.click();
		email.sendKeys("bhanuannam@gmail.com");
		password.sendKeys("Bhanu@6309");
		clickLogin.click();
	}

//	public void Living() {
//		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		Actions actions = new Actions(driver);
//		actions.moveToElement(Living).perform();
//	}
//
//	public void bookshelve() {
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		bookshelve.click();
//	}

	public void clickOnBookshelves() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Hover over Living tab
		Actions actions = new Actions(driver);
		actions.moveToElement(livingTab).perform();

		// Wait and click Bookshelves
		wait.until(ExpectedConditions.elementToBeClickable(bookshelvesLink)).click();
		System.out.println("Bookshelves clicked.");
	}
}
