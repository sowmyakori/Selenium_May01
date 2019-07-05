import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class LogoutTest {

	
	@Test
	public void logoutTest() throws InterruptedException
	{

		System.setProperty("webdriver.gecko.driver","C:\\Users\\Dell-Latitude-E6430\\Selenium_training\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
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
		WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		logout.click();
	}
}
