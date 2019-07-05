import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RandomScenariosTest {
	
	WebDriver driver;
	
	@BeforeClass
	public void launchAndLogin() throws InterruptedException
	{
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Dell-Latitude-E6430\\Selenium_training\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
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

	@Test(enabled = false,priority=1)
	public void homeTabTest() throws InterruptedException
	{
		Thread.sleep(6000);
		WebElement homeTab = driver.findElement(By.id("home_Tab"));
		homeTab.click();
		
		WebElement userNameLink = driver.findElement(By.xpath("//h1[@class='currentStatusUserName']"));
		userNameLink.click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='profilePage userProfilePage']")).isDisplayed());
	}
	
	@Test(enabled = false,priority=2,dependsOnMethods= {"homeTabTest"})
	public void editProfileTest() throws InterruptedException
	{
		WebElement editProfile = driver.findElement(By.xpath("//a[@class='contactInfoLaunch editLink']//img"));
		editProfile.click();
		
		Thread.sleep(3000);
		driver.switchTo().frame("contactInfoContentId");
		WebElement aboutTab = driver.findElement(By.xpath("//a[contains(text(),'About')]"));
		aboutTab.click();
		
		String name = "ABCD";
		WebElement lName = driver.findElement(By.xpath("//input[@id='lastName']"));
		lName.clear();
		lName.sendKeys(name);
		
		WebElement saveAllButton = driver.findElement(By.xpath("//input[@class='zen-btn zen-primaryBtn zen-pas']"));
		saveAllButton.click();
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		
		String displayedName = driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']")).getText();
		Assert.assertTrue(displayedName.contains(name));
		
		String userMenu = driver.findElement(By.id("userNav")).getText();
		Assert.assertTrue(userMenu.contains(name));
		
		WebElement homeTab = driver.findElement(By.id("home_Tab"));
		homeTab.click();
		String homeName = driver.findElement(By.xpath("//h1[@class='currentStatusUserName']")).getText();
		Assert.assertTrue(homeName.contains(name));
	}
	
	@Test(enabled = false, priority=3)
	public void allTabTest() throws InterruptedException
	{
		WebElement allTab = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		allTab.click();
		WebElement customizeTab = driver.findElement(By.xpath("//input[@name='customize']"));
		customizeTab.click();
		
		String removeTab = "Leads";
		Select selectedTabList = new Select(driver.findElement(By.id("duel_select_1")));
		selectedTabList.selectByVisibleText(removeTab);
		
		WebElement removeArrow = driver.findElement(By.xpath("//img[@class='leftArrowIcon']"));
		removeArrow.click();
		
		WebElement saveButton = driver.findElement(By.xpath("//input[@name='save']"));
		saveButton.click();
		
		Thread.sleep(3000);
		
		String mainTab = driver.findElement(By.id("tabBar")).getText();
		Assert.assertFalse(mainTab.contentEquals(removeTab));
		
		WebElement userNavArrow = driver.findElement(By.id("userNav-arrow"));
		userNavArrow.click();
		WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		logout.click();
		
		Thread.sleep(5000);
		
//		WebElement userName = driver.findElement(By.id("username"));
//		userName.sendKeys("kr.sowmya1-yz8n@force.com");
		
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("welcome123");
		
		WebElement loginButton = driver.findElement(By.id("Login"));
		loginButton.click();
		Thread.sleep(3000);
		
		mainTab = driver.findElement(By.id("tabBar")).getText();
		Assert.assertFalse(mainTab.contentEquals(removeTab));
		
		
	}
	
	@Test(enabled=false, priority=4)
	public void blockEventInCalenderTest() throws InterruptedException
	{
		Thread.sleep(4000);
		WebElement homeTab = driver.findElement(By.id("home_Tab"));
		homeTab.click();
		
		Thread.sleep(2000);
		WebElement dateLink = driver.findElement(By.xpath("//span[@class='pageDescription']//a"));
		dateLink.click();
		
		WebElement timeLink = driver.findElement(By.xpath("//a[contains(text(),'8:00 PM')]"));
		timeLink.click();
		
		WebElement subComboIcon = driver.findElement(By.xpath("//img[@class='comboboxIcon']"));
		subComboIcon.click();
		
		Thread.sleep(3000);
		
		String parentWindow = driver.getWindowHandle();
		for(String childWindow : driver.getWindowHandles())
		{
			if(!childWindow.equals(parentWindow))
			{
				driver.switchTo().window(childWindow);
				Thread.sleep(2000);
				WebElement othersLink = driver.findElement(By.xpath("//a[contains(text(),'Other')]"));
				othersLink.click();
			}
		}
		driver.switchTo().window(parentWindow);
		
		WebElement endTime = driver.findElement(By.xpath("//input[@id='EndDateTime_time']"));
		endTime.click();
		WebElement time = driver.findElement(By.xpath("//div[@id='timePickerItem_42']"));
		time.click();
		
		WebElement saveButton = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']"));
		saveButton.click();
		
		String event = driver.findElement(By.xpath("//div[@class='multiLineEventBlock dragContentPointer']")).getText();
		Assert.assertTrue(event.contains("Other"));
	}
	
	@Test(enabled=true)
	public void weekelyReccuranceTest() throws InterruptedException, ParseException
	{
		Thread.sleep(4000);
		WebElement homeTab = driver.findElement(By.id("home_Tab"));
		homeTab.click();
		
		Thread.sleep(2000);
		WebElement dateLink = driver.findElement(By.xpath("//span[@class='pageDescription']//a"));
		dateLink.click();
		
		WebElement timeLink = driver.findElement(By.xpath("//a[contains(text(),'4:00 PM')]"));
		timeLink.click();
		
		WebElement subComboIcon = driver.findElement(By.xpath("//img[@class='comboboxIcon']"));
		subComboIcon.click();
		
		Thread.sleep(3000);
		
		String parentWindow = driver.getWindowHandle();
		for(String childWindow : driver.getWindowHandles())
		{
			if(!childWindow.equals(parentWindow))
			{
				driver.switchTo().window(childWindow);
				Thread.sleep(2000);
				WebElement othersLink = driver.findElement(By.xpath("//a[contains(text(),'Other')]"));
				othersLink.click();
			}
		}
		driver.switchTo().window(parentWindow);
		
		WebElement endTime = driver.findElement(By.xpath("//input[@id='EndDateTime_time']"));
		endTime.click();
		WebElement time = driver.findElement(By.xpath("//div[@id='timePickerItem_38']"));
		time.click();
		
		WebElement isReccurance = driver.findElement(By.xpath("//input[@id='IsRecurrence']"));
		isReccurance.click();
		
		WebElement weekly = driver.findElement(By.xpath("//input[@id='rectypeftw']"));
		weekly.click();
		
		WebElement endDate = driver.findElement(By.xpath("//input[@id='RecurrenceEndDateOnly']"));
		endDate.click();
	
		String day = datePicker(14);
	   
	    WebElement selectNewDate = driver.findElement(By.xpath("//td[contains(text(),"+day+")]"));
	    selectNewDate.click();
	    
	    WebElement saveButton = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']"));
		saveButton.click();
		
		String event = driver.findElement(By.xpath("//div[@class='multiLineEventBlock dragContentPointer']")).getText();
		Assert.assertTrue(event.contains("Other"));
		Thread.sleep(3000);
		
		
		WebElement monthIcon = driver.findElement(By.xpath("//a[contains(text(),'Month View')]"));
		monthIcon.click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(@class,'calToday')]//a[contains(text(),'Other')]")).isDisplayed());
		
		String newDay = datePicker(7);
		String link = driver.findElement(By.xpath("//td[@class='calActive']//a[contains(text(),'11')]")).getText();
		Assert.assertTrue(link.contains("Other"));
	    
	}

	public String datePicker(int noOfdays) throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    Calendar cal = Calendar.getInstance();	
	    cal.add(Calendar.DATE, noOfdays);
	    String newDate = dateFormat.format(cal.getTime());
	    String day = new SimpleDateFormat("d").format(dateFormat.parse(newDate));
		return day;
	}
}
