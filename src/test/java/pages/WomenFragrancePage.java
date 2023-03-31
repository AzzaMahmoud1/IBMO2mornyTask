package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PageBase;

public class WomenFragrancePage extends PageBase {
	static WebDriver driver;
	WebDriverWait wait;
	WebElement addCartItem ;
	protected Actions action;
	String numberAsString ;
	By DropDown = By.xpath("//*[contains(@id,'responsiveMenu')]/li/a");
	By Women_Fragrances_selection = By.xpath("//span[contains(text(),'Women Fragrances')]");
	By Women_Fragrances_Page = By.xpath("//h1[contains(text(),'Women Fragrances')]");
	By 	Next_Page = By.className("next");
	By Discounted_Element= By.xpath("//*[contains(@class,'badge ')]/../.. | //bdi[@class='badge--save-value']/../../../..");
	By price= By.cssSelector(".product-price h6");
	By Add_Cart_BTNItem=By.className("btn");	
	By cart_count= By.cssSelector("span.cartCountSelector");
	By cart_Total= By.xpath("//span[@class='cartTotalSelector']");

	
	public WomenFragrancePage(WebDriver driver) {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	public void GotoWomenFrgrancepage (WebDriver driver)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(DropDown));
		SelectItemFromList(driver, DropDown, Women_Fragrances_selection, "Women frgrance selection");
	}

	public void NextClick (WebDriver driver)
	{WebElement Next_Page_click =driver.findElement(Next_Page);
	ClickElement_JS(driver, Next_Page_click, "Next_Page click");

	}

	public boolean WomenFrgranceDisplyed(WebDriver driver) {
		return ElementIsDisplayed(driver, Women_Fragrances_Page, "Women_Fragrances_Page");
	}

	public long AddDiscountedItems(WebDriver driver) throws Exception{
		List<WebElement> DiscountedItems = driver.findElements(Discounted_Element);
		long price_Sum=0;

		for (WebElement DiscountedItem : DiscountedItems) {

			addCartItem = DiscountedItem.findElement(Add_Cart_BTNItem);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			waitElement(driver, addCartItem, "addCartItem");
			if(!addCartItem.getText().equals("Sold Out")) {
				waitElement(driver, DiscountedItem, "DiscountedItem");
				waitElement(driver, addCartItem, "addCartItem");
				numberAsString=DiscountedItem.findElement(price).getText();
				ClickElement_JS(driver, addCartItem, "Add_Cart_BTNItem");
				//wait for product to be added to cart
				waitCartCount(driver, cart_count, "cart_count");
				price_Sum += StringValueToInt(driver,numberAsString);
			}			
		}
		return price_Sum;
	}

	// Get Cart Total Price
	public long GetCartTotal( WebDriver driver) {
		WebElement cartTotalElement = driver.findElement(cart_Total);
		String cartTotal = cartTotalElement.getText();    
		return StringValueToInt(driver, cartTotal);
	}
	
	// Converting The Total value to Number
	public long StringValueToInt (WebDriver driver, String numberAsString) {

		if (numberAsString.contains("|")) {
			numberAsString = numberAsString.split("\\|")[0].trim();
		}
		if (numberAsString.contains(",") || numberAsString.contains(".00")) {
			numberAsString=numberAsString.split("EGP ")[1].replace(",", "").replace(".00", "");
		}

		return Integer.parseInt(numberAsString);
	}
}
