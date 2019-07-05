import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



public class Acc_CreateNewViewTest {
	
	@Test
	public void createNewViewTest() throws InterruptedException
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
		Thread.sleep(5000);
		WebElement accountTab = driver.findElement(By.id("Account_Tab"));
		accountTab.click();
		
		WebElement newViewLink = driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		newViewLink.click();
		
		String name = "abc";
		WebElement viewName = driver.findElement(By.id("fname"));
		viewName.sendKeys(name);
		
		WebElement uniqueName = driver.findElement(By.id("devname"));
		uniqueName.sendKeys("abc123");
		
		WebElement save = driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='save']"));
		save.click();
		
		Select select = new Select(driver.findElement(By.xpath("//select[@class='title']")));
		String selectedName = select.getFirstSelectedOption().getText();
		if(selectedName.equals(name))
			System.out.println("Test Case Passed*********New View Created");
		else
			System.out.println("Test Case Failed**********New View Not created");
		
	}

}
