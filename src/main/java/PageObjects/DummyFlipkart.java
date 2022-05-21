package PageObjects;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import Base.BaseClass;

public class DummyFlipkart extends BaseClass{
	public WebDriverWait wait;
	public SoftAssert softAssertion;
	public JavascriptExecutor js;
	public DummyFlipkart(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		this.js=js;
	}
	
	@FindBy(xpath="//input[@placeholder='Search for products, brands and more']")
	WebElement searchtextbox;
	
	@FindBy(className="L0Z3Pu")
	WebElement searchbtn;
	
	@FindBy(xpath="//button[@class='_2KpZ6l _2doB4z']")
	WebElement onloadpopupclosebtn;
	
	@FindBy(xpath="//div[@data-id='MOBG6VF5Q82T3XRS']")
	WebElement firstitem;
	
	@FindBy(xpath="//div[@class='_30jeq3 _16Jk6d']")
	WebElement price;
	
	@FindBy(xpath="//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")
	WebElement addtocartbtn;
	
	@FindBy(xpath="//button[normalize-space()='+']")
	WebElement incrementitembtn;
	
	@FindBy(xpath="//div[@class='Ob17DV _3X7Jj1']")
	WebElement totalcartvalue;
	
	@FindBy(xpath="//input[@value='2']")
	WebElement quantity;


	public void closeOnLoadPopup()
	{
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(onloadpopupclosebtn));
		onloadpopupclosebtn.click();
	}
	public void searchPhone() throws InterruptedException
	{
		searchtextbox.sendKeys("Apple iPhone");
		Thread.sleep(2000);
		searchbtn.click();
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.urlContains("search"));	
	}
	
	public void clickOnFirstPhone() {
		
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(firstitem));
		firstitem.click();	
	}
	
	public void printPriceAndAddToCart() throws InterruptedException {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String>it2=windows.iterator();
		String parentId= it2.next();
		String childId=it2.next();
		driver.switchTo().window(childId);
		System.out.println("The Price of the item is"+price.getText());
		addtocartbtn.click();
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(totalcartvalue));
		softAssertion = new SoftAssert();
		softAssertion.assertEquals(driver.getCurrentUrl(),
				"https://www.flipkart.com/viewcart?otracker=PP_GoToCart");
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		wait.until(ExpectedConditions.visibilityOf(incrementitembtn));
		incrementitembtn.click();
		Thread.sleep(4000);
		System.out.println("The Price of the item is after increasing the one more quantity is "+totalcartvalue.getText());
		driver.close();
		driver.switchTo().window(parentId);
		}
}
