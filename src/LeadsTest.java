import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class LeadsTest {
	
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
	public void leadsTabLinkTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement leadLink = driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		leadLink.click();
		String displayText = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText();
		Assert.assertEquals(displayText, "Home");;
		
	}
	
	@Test(enabled=false)
	public void leadsDropdownTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement leadLink = driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		leadLink.click();
		String displayText = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText();
		Assert.assertEquals(displayText, "Home");
		
		WebElement viewlist = driver.findElement(By.xpath("//select[@id='fcf']"));
		viewlist.click();
		
		Select select = new Select(viewlist);
		List<WebElement> optList = select.getOptions();
		
		List<String> list1 = new ArrayList<String>();
		for(int i=0;i<optList.size();i++)		
			list1.add(optList.get(i).getText());
					
		List<String> list2 = new ArrayList<String>();
		list2.add("All Open Leads");
		list2.add("My Unread Leads");
		list2.add("Recently Viewed Leads");
		list2.add("Today's Leads");
		
		Assert.assertEquals(list1, list2);
				
	}
	
	@Test(enabled=false)
	public void selectTodaysLeadTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement leadLink = driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		leadLink.click();
		Thread.sleep(3000);
		Select select = new Select(driver.findElement(By.id("fcf")));

		select.selectByVisibleText("Today's Leads");
		WebElement userNavArrow = driver.findElement(By.id("userNav-arrow"));
		userNavArrow.click();
		WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		logout.click();
		
		Thread.sleep(3000);

		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("welcome123");
		WebElement rememberMeCheck = driver.findElement(By.id("rememberUn"));
		rememberMeCheck.click();
		WebElement loginButton = driver.findElement(By.id("Login"));
		loginButton.click();
		Thread.sleep(3000);
		
		WebElement leadLinkNew = driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		leadLinkNew.click();
		String displayText = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText();
		Assert.assertEquals(displayText, "Home");
		
		WebElement goButton = driver.findElement(By.xpath("//span[@class='fBody']//input[@name='go']"));
		goButton.click();		

		Select newList = new Select(driver.findElement(By.xpath("//select[@name='fcf']")));
		String selectedOption = newList.getFirstSelectedOption().getText();
		Assert.assertEquals(selectedOption, "Today's Leads");
	}
		
		
		
	@Test(enabled=true)
	public void newsLeadTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement leadLink = driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		leadLink.click();
		Thread.sleep(3000);	
		
		WebElement newbutton = driver.findElement(By.name("new"));
		newbutton.click();
		
		String name = "abcd";
		WebElement lastName = driver.findElement(By.id("name_lastlea2"));
		lastName.sendKeys(name); 
		
		WebElement company= driver.findElement(By.id("lea3"));
		company.sendKeys(name);
		
		WebElement saveButton = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']"));
		saveButton.click();
		
		String displayText = driver.findElement(By.xpath("//h2[@class='topName']")).getText();
		Assert.assertEquals(displayText, name);
	}	
		
	@AfterMethod
	public void logoutTest()
	{
		WebElement userNavArrow = driver.findElement(By.id("userNav-arrow"));
		userNavArrow.click();
		WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		logout.click();
		driver.quit();
	}	
	
}
