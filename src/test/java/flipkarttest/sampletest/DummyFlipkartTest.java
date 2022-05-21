package flipkarttest.sampletest;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseClass;
import PageObjects.DummyFlipkart;

public class DummyFlipkartTest extends BaseClass{
	DummyFlipkart fp;
	
	public DummyFlipkartTest() {
		super();
	}
	
	@BeforeClass(alwaysRun = true)
	public void setUp() throws IOException {
		initializeBrowser();
		fp = new DummyFlipkart(driver);
		PageFactory.initElements(driver, fp);
	}
	
	@Test(priority=1)
	public void searchPhoneInPage() throws InterruptedException {
		fp.closeOnLoadPopup();
		fp.searchPhone();
	}
	
	@Test(priority=2)
	public void clickOnFirstItem() {
		fp.clickOnFirstPhone();
	}
	
	@Test(priority=3)
	public void printPriceAddToCart() throws InterruptedException
	{
fp.printPriceAndAddToCart();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	

}
