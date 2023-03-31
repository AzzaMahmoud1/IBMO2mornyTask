package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
	public static Logger logger = Logger.getLogger(PageBase.class);
	protected WebDriver driver;
	//	protected Actions action;

	public void ClickElement_JS(WebDriver driver, WebElement element, String element_log) {
		logger = Logger.getLogger("Click Element");
		Boolean flag = ElementIsDisplayed(driver, element, element_log);
		if (flag == true) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			logger.info("click on " + element_log);
		} else {
			logger.error("Can't Click on " + element_log);
		}
	}

	public Boolean ElementIsDisplayed(WebDriver driver, By element_locator, String element_log) {
		logger = Logger.getLogger("Check Element is displayed");

		try {
			driver.findElement(element_locator).isDisplayed();
			logger.info(element_log + " is Displayed successfully");
			return true;

		} catch (Exception e) {
			logger.error(element_log + " is not Displayed");

			return false;
		}

	}
	public Boolean ElementIsDisplayed(WebDriver driver, WebElement element, String element_log) {
		logger = Logger.getLogger("Check Element is displayed");

		try {
			element.isDisplayed();
			logger.info(element_log + " is Displayed successfully");
			return true;

		} catch (Exception e) {
			logger.error(element_log + " is not Displayed");

			return false;
		}
	}
	public void SelectItemFromList(WebDriver driver, By element_locator, By value, String element_log) {
		logger = Logger.getLogger("Select Item from List");
		try {
			WebElement drop =driver.findElement(element_locator);
			new Actions(driver).moveToElement(drop).perform();
			WebElement Women_Fragrances_Selection = driver.findElement(value);
			Women_Fragrances_Selection.click();

			logger.info("Select " + element_log );
		} catch (Exception e) {
			logger.error(" Cannot find element in list with text  : " + element_log);

		}
	}

	public void waitElement( WebDriver driver, WebElement Element , String element_log) {
		logger = Logger.getLogger("Waiting for Element to be visible");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(Element));

			logger.info("wait for " + element_log );
		} catch (Exception e) {
			logger.error(" couldnt wait for  : " + element_log);

		}

	}
	public void waitCartCount( WebDriver driver, By Element , String element_log) {
		logger = Logger.getLogger("Waiting for Element to be visible");
		WebElement	countElement = driver.findElement(Element);
		try {
			int initialCount = Integer.parseInt(countElement.getText());	
			WebDriverWait wait = new WebDriverWait(driver, 10); // 10 seconds timeout
			wait.until(ExpectedConditions.textToBePresentInElement(countElement, String.valueOf(initialCount + 1)));
			//int newCount = Integer.parseInt(countElement.getText());
			logger.info("wait for " + element_log );
		} catch (Exception e) {
			logger.error(" couldnt wait for  : " + element_log);

		}
	}
	
}
