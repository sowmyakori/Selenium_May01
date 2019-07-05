import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactsTest {
	
	WebDriver driver;

	@BeforeMethod
	public void loginTest() throws InterruptedException
	{
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Dell-Latitude-E6430\\Selenium_training\\geckodriver.exe");
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.webnotifications.enabled", false);
		FirefoxOptions opt = new FirefoxOptions();
		opt.setProfile(profile);
		driver = new FirefoxDriver(opt);
		
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://login.salesforce.com");		
		
		WebElement userName = driver.findElement(By.id("username"));
		userName.sendKeys("kr.sowmya1-yz8n@force.com");
		
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("welcome123");
		WebElement rememberMeCheck = driver.findElement(By.id("rememberUn"));
		rememberMeCheck.click();
		WebElement loginButton = driver.findElement(By.id("Login"));
		loginButton.click();
		Thread.sleep(5000);
		List<WebElement> elements = driver.findElements(By.id("lexNoThanks"));
		if(elements.size()!=0)
		{
			driver.findElement(By.id("lexNoThanks")).click();
			driver.findElement(By.id("tryLexDialogX")).click();
		}
		else {
	
		WebElement profileImg = driver.findElement(By.xpath("//div[@class='profileTrigger branding-user-profile bgimg slds-avatar slds-avatar_profile-image-small circular forceEntityIcon']//span[@class='uiImage']"));
		profileImg.click();
		
		driver.findElement(By.xpath("//a[text()='Switch to Salesforce Classic']")).click();
		}
	}
	
	@Test(enabled=false)
	public void newContactTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement contactsLink = driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		contactsLink.click();
		
		WebElement newButton = driver.findElement(By.xpath("//input[@name='new']"));
		newButton.click();
		
		String name = "acc1";
		WebElement lastName = driver.findElement(By.id("name_lastcon2"));
		lastName.sendKeys(name);
		
		String parentWindow = driver.getWindowHandle();
		WebElement searchIcon = driver.findElement(By.xpath("//a[@id='con4_lkwgt']//img[@class='lookupIcon']"));
		searchIcon.click();
		Thread.sleep(4000);
		Set<String> handles = driver.getWindowHandles();
		
		for(String childWindow : handles)
		{
			if(!childWindow.equals(parentWindow))
			{
				driver.switchTo().window(childWindow);
				System.out.println(driver.getTitle());
				driver.switchTo().frame("resultsFrame");
				WebElement firstLink = driver.findElement(By.xpath("//table//tr[2]//a[@class=' dataCell ']"));
				firstLink.click();
				Thread.sleep(2000);
				
			}
		}
		
		
		
		driver.switchTo().window(parentWindow);
//		WebElement accountName = driver.findElement(By.id("con4"));
//		accountName.sendKeys(name);
//		
		WebElement saveButton = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']"));
		saveButton.click();
		
		String displayText = driver.findElement(By.xpath("//h2[@class='topName']")).getText();
		Assert.assertEquals(displayText, name);
		
		
	}
	
	@Test(enabled=false)
	public void createNewViewTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement contactsLink = driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		contactsLink.click();
		
		WebElement newViewLink = driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		newViewLink.click();
		
		String name="view1";
		WebElement viewName = driver.findElement(By.id("fname"));
		viewName.sendKeys(name);
		
		WebElement uniqueName = driver.findElement(By.xpath("//input[@id='devname']"));
		uniqueName.sendKeys("name1");
		
		WebElement saveButton = driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='save']"));
		saveButton.click();
		
		Select newList = new Select(driver.findElement(By.name("fcf")));
		String firstOption = newList.getFirstSelectedOption().getText();
		Assert.assertEquals(firstOption, name);
		
	}
	
	@Test(enabled=false)
	public void recentlyCreatedTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement contactsLink = driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		contactsLink.click();
		
		Select recent = new Select(driver.findElement(By.id("hotlist_mode")));
		recent.selectByVisibleText("Recently Created");
		
		String text = driver.findElement(By.xpath("//h3[contains(text(),'Recent Contacts')]")).getText();
		Assert.assertEquals(text, "Recent Contacts");
		
	}
	
	@Test(enabled=false)
	public void myContactsTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement contactsLink = driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		contactsLink.click();
		
		Select list = new Select(driver.findElement(By.id("fcf")));
		list.selectByVisibleText("My Contacts");
	}
	
	@Test(enabled=false)
	public void viewContactTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement contactsLink = driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		contactsLink.click();
		
		WebElement firstCell = driver.findElement(By.xpath("//tr[contains(@class,'dataRow even first')]//th//a"));
		String name = firstCell.getText();
		firstCell.click();
		
		String displayName = driver.findElement(By.xpath("//h2[@class='topName']")).getText();
		Assert.assertEquals(displayName, name);
	}
	
	@Test(enabled=false)
	public void newViewErrorMsgTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement contactsLink = driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		contactsLink.click();
		
		WebElement newViewLink = driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		newViewLink.click();
						
		WebElement uniqueName = driver.findElement(By.xpath("//input[@id='devname']"));
		uniqueName.sendKeys("name1");
		
		WebElement saveButton = driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='save']"));
		saveButton.click();
		boolean isDisplayed = driver.findElement(By.xpath("//div[@class='errorMsg']/strong")).isDisplayed();
		Assert.assertTrue(isDisplayed);
	}	
	
	@Test(enabled=false)
	public void newViewCancelTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement contactsLink = driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		contactsLink.click();
		
		WebElement newViewLink = driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		newViewLink.click();
		
		String name="ABCD";
		WebElement viewName = driver.findElement(By.id("fname"));
		viewName.sendKeys(name);
						
		WebElement uniqueName = driver.findElement(By.xpath("//input[@id='devname']"));
		uniqueName.sendKeys("EFGH");
		
		WebElement cancelButton = driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='cancel']"));
		cancelButton.click();
		
		String text = driver.findElement(By.xpath("//h1[@class='pageType']")).getText();
		Assert.assertEquals(text, "Contacts");
	}	
	
	@Test(enabled=true)
	public void saveAndNewButtonTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement contactsLink = driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		contactsLink.click();
		
		WebElement newButton = driver.findElement(By.xpath("//input[@name='new']"));
		newButton.click();
		
		WebElement lname = driver.findElement(By.xpath("//input[@id='name_lastcon2']"));
		lname.sendKeys("Thomas");
		
		String parentWindow = driver.getWindowHandle();
		WebElement searchIcon = driver.findElement(By.xpath("//a[@id='con4_lkwgt']//img[@class='lookupIcon']"));
		searchIcon.click();
		Thread.sleep(4000);
		Set<String> handles = driver.getWindowHandles();
		
		for(String childWindow : handles)
		{
			if(!childWindow.equals(parentWindow))
			{
				driver.switchTo().window(childWindow);
				System.out.println(driver.getTitle());
				driver.switchTo().frame("resultsFrame");
				WebElement firstLink = driver.findElement(By.xpath("//table//tr[2]//a[@class=' dataCell ']"));
				firstLink.click();
				Thread.sleep(2000);
				
			}
		}
			
		driver.switchTo().window(parentWindow);
		WebElement saveAndNewButton = driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@name='save_new']"));
		saveAndNewButton.click();
		
	}
}
