import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

public class UserMenu_MySettingTest {
	
	
	
	@Test
	public void loginHistoryTest() throws InterruptedException
	{
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Dell-Latitude-E6430\\Selenium_training\\geckodriver.exe");

		
		FirefoxOptions ops = new FirefoxOptions();
		FirefoxProfile profile = new  FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.dir", "C:\\Users\\Dell-Latitude-E6430\\downloads");
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv" );
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.helperApps.neverAsk.openFile", "text/csv");
		ops.setProfile(profile);
		WebDriver driver = new FirefoxDriver(ops);
		
		
		
	
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
		WebElement userNavArrow = driver.findElement(By.id("userNav-arrow"));
		userNavArrow.click();
		
		WebElement mySetting = driver.findElement(By.xpath("//a[contains(text(),'My Setting')]"));
		mySetting.click();
		
//		WebElement personal = driver.findElement(By.xpath("//span[@id='PersonalInfo_font']"));
//		personal.click();
//		
//		WebElement  loginHistory = driver.findElement(By.id("LoginHistory_font"));
//		loginHistory.click();
//		
//		WebElement downloadLoginHistory = driver.findElement(By.xpath("//a[contains(text(),'Download login history for last six months, includ')]"));
//		downloadLoginHistory.click();
//		
//		WebElement displayLayout = driver.findElement(By.id("DisplayAndLayout_font"));
//		displayLayout.click();
//		
//		WebElement customizeTab = driver.findElement(By.id("CustomizeTabs_font"));
//		customizeTab.click();
//		
//		Select customApps = new Select(driver.findElement(By.id("p4")));
//		customApps.selectByVisibleText("Salesforce Chatter");
//		
//		Select availableTabs = new Select(driver.findElement(By.id("duel_select_0")));
//		availableTabs.selectByVisibleText("Reports");
//		
//		WebElement rightArrow = driver.findElement(By.xpath("//img[@class='rightArrowIcon']"));
//		rightArrow.click();
//		
//		WebElement saveButton = driver.findElement(By.xpath("//input[@name='save']"));
//		saveButton.click();
		
//		WebElement emailLink = driver.findElement(By.xpath("//div[@id='EmailSetup']//a[@class='header setupFolder']"));
//		emailLink.click();
//		
//		WebElement emailSetting = driver.findElement(By.xpath("//a[@id='EmailSettings_font']"));
//		emailSetting.click();
//		
//		WebElement emailName = driver.findElement(By.id("sender_name"));
//		emailName.clear();
//		emailName.sendKeys("abc");
//		
//		WebElement emailId = driver.findElement(By.id("sender_email"));
//		emailId.clear();
//		emailId.sendKeys("abc123@mail.com");
//		
//		WebElement bcc = driver.findElement(By.xpath("//input[@id='auto_bcc1']"));
//		bcc.click();
//		
//		Thread.sleep(3000);
//		
//		WebElement saveButton = driver.findElement(By.xpath("//input[@name='save']"));
//		saveButton.click();
//		
//		driver.switchTo().alert().accept();
//		
		WebElement calendarAndReminders = driver.findElement(By.id("CalendarAndReminders_font"));
		calendarAndReminders.click();
		
		WebElement reminder = driver.findElement(By.id("Reminders_font"));
		reminder.click();
		
		WebElement testReminder = driver.findElement(By.id("testbtn"));
		testReminder.click();
		Thread.sleep(5000);
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
		for(String windowHandle  : handles)
		{
		    if(!windowHandle.equals(parentWindow))
		   {
		     driver.switchTo().window(windowHandle);
		     driver.findElement(By.xpath("//input[@id='dismiss_all']")).click();
		    		    
		     driver.switchTo().window(parentWindow); 
		    }
		}

		WebElement saveButton = driver.findElement(By.xpath("//input[@name='save']"));
		saveButton.click();
		
	}

}
