package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.WomenFragrancePage;

public class WomenFrgranceTest extends TestBase {
	WomenFragrancePage WomenFragrancePageOBJ;
	long sum=0;
	long	cartTotal=0;
	@BeforeClass
	public void beforeMethod() throws InterruptedException {

		OpenBrowser();
		WomenFragrancePageOBJ = new WomenFragrancePage(driver);
	}

	@Test(priority = 0)
	public void openFrgrancePage() throws Exception {
		WomenFragrancePageOBJ.GotoWomenFrgrancepage(driver);
		boolean state = WomenFragrancePageOBJ.WomenFrgranceDisplyed(driver);
		assertEquals(state, true, "Women Frgrance Page opened successfully");
	}
	
	@Test(priority = 1)
	public void addItemsToCartAndValidatePrice() throws Exception {

		 Assert.assertEquals(sum, cartTotal, "Validating cart total and total added items price");
		for (int i=0; i<4;i++) {
			WomenFragrancePageOBJ.NextClick(driver);
			sum+=	WomenFragrancePageOBJ.AddDiscountedItems(driver);
		}
		cartTotal= WomenFragrancePageOBJ.GetCartTotal(driver);
		System.out.println(sum);
		Assert.assertEquals(sum, cartTotal, "Validating cart total and total added items price");
	}
	
	
	@AfterClass
	public void afterMethod() {
		CloseBrowser();
	}
}
