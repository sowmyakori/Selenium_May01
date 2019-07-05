import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class OpportunitiesTest {
	
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
	
/*	@Test
	public void opportunityDropdownTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement opportunitiesLink = driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
		opportunitiesLink.click();
		
		Select oppList = new Select(driver.findElement(By.xpath("//select[@id='fcf']")));
		List<WebElement> allOpportunities = oppList.getOptions();
		List<String> actualList = new ArrayList<String>();
		actualList.add("All Opportunities");
		actualList.add("Closing Next Month");
		actualList.add("Closing This Month");
		actualList.add("My Opportunities");
		actualList.add("New This Week");
		actualList.add("Opportunity Pipeline");
		actualList.add("Recently Viewed Opportunities");
		actualList.add("Won");
		if(allOpportunities.equals(actualList))
			System.out.println("Test case passed*************");
		else
			System.out.println("Ttest Case failed*************");
		
	//	Assert.assertEquals(allOpportunities, actualList);
		
		
	}*/
	
	@Test(enabled=false)
	public void newOpportunityTest() throws InterruptedException, ParseException
	{
		Thread.sleep(5000);
		WebElement opportunitiesLink = driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
		opportunitiesLink.click();
		
		WebElement newButton = driver.findElement(By.xpath("//input[@name='new']"));
		newButton.click();
		
		WebElement oppName = driver.findElement(By.xpath("//input[@id='opp3']"));
		oppName.sendKeys("newOpportunity");
		
		WebElement searchIcon = driver.findElement(By.xpath("//img[@class='lookupIcon']"));
		searchIcon.click();
		Thread.sleep(4000);
		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for(String childWindow:handles)
		{
			if(!childWindow.equals(parentWindow))
			{
				driver.switchTo().window(childWindow);
				System.out.println(driver.getTitle());
				driver.switchTo().frame("searchFrame");
				WebElement goButton = driver.findElement(By.name("go"));
				goButton.click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("resultsFrame");
				WebElement firstLink = driver.findElement(By.xpath("//table[@class='list']//tr[2]//th//a"));
				firstLink.click();
				
			}
		}
		driver.switchTo().window(parentWindow);
		
		String date = "09/08/2020";
		
		SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yyyy");
		Date d = formattedDate.parse(date);
		
		String day = new SimpleDateFormat("dd").format(d);
		String month = new SimpleDateFormat("MMMM").format(d);
		String year = new SimpleDateFormat("yyyy").format(d);
		
		WebElement closeDate = driver.findElement(By.xpath("//input[@id='opp9']"));
		closeDate.click();
		Select monthPicker = new Select(driver.findElement(By.xpath("//select[@id='calMonthPicker']")));
		monthPicker.selectByVisibleText(month);
		
		Select yearPicker = new Select(driver.findElement(By.xpath("//select[@id='calYearPicker']")));
		yearPicker.selectByVisibleText(year);
		
		WebElement daypicker = driver.findElement(By.xpath("//td[contains(text(),"+day+")]"));
		daypicker.click();
		
		Select stage = new Select(driver.findElement(By.xpath("//select[@id='opp11']")));
		stage.selectByVisibleText("Closed Won");
		
		WebElement probability = driver.findElement(By.xpath("//input[@id='opp12']"));
		probability.clear();
		probability.sendKeys("70");
		
		Select leadSource = new Select(driver.findElement(By.xpath("//select[@id='opp6']")));
		leadSource.selectByVisibleText("Web");
		
		WebElement saveButton = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']"));
		saveButton.click();
				
		
		
	} 
	
	@Test(enabled=false)
	public void opportunityPipelineTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement opportunitiesLink = driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
		opportunitiesLink.click();
		
		WebElement pipelineLink = driver.findElement(By.xpath("//a[contains(text(),'Opportunity Pipeline')]"));
		pipelineLink.click();
		
		String displayText = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText();
		
		Assert.assertEquals(displayText, "Opportunity Pipeline");
		
		
	}
	
	@Test(enabled=false)
	public void opportunityStuckTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement opportunitiesLink = driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
		opportunitiesLink.click();
		
		WebElement pipelineLink = driver.findElement(By.xpath("//a[contains(text(),'Stuck Opportunities')]"));
		pipelineLink.click();
		
		String displayText = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText();
		
		Assert.assertEquals(displayText, "Stuck Opportunities");
		
		
	}
	
	
	@Test(enabled=true)
	public void quarterSummaryTest() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement opportunitiesLink = driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
		opportunitiesLink.click();
		
		Select intervalList = new Select(driver.findElement(By.id("quarter_q")));
		intervalList.selectByVisibleText("Next FQ");
		
		Select includeList = new Select(driver.findElement(By.id("open")));
		includeList.selectByVisibleText("Open Opportunities");
		
		WebElement runReportButton = driver.findElement(By.xpath("//table[@class='opportunitySummary']//input[@name='go']"));
		runReportButton.click();
		
		String displayText = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText();
		
		Assert.assertEquals(displayText, "Opportunity Report");
		
		
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
