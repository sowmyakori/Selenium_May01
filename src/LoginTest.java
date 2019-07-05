import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest {

	static ExtentReports reports;
	static ExtentTest logger;
	
	public static void main(String[] args) throws InterruptedException {
		
		String fileName = new SimpleDateFormat("'LoginTest_'MMMdd_hhmmss'.html'").format(new Date());
		String path="ExtentReport/"+fileName;
		reports = new ExtentReports(path);
		logger = reports.startTest("Begin Login Test******");
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Dell-Latitude-E6430\\Selenium_training\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://login.salesforce.com");		
		logger.log(LogStatus.INFO, "Launched URL");
		
		//loginTest1(driver);
	//	loginTest2(driver);
	//	forgotPassowordTest(driver);
		wrongCredentialsTest(driver);
		
		reports.endTest(logger);
		reports.flush();
		
		
}

	//Test ID-Login Error message 1
	public static void loginTest1(WebDriver driver)
	{
		
		
		WebElement userName = driver.findElement(By.id("username"));
		userName.sendKeys("kr.sowmya1-yz8n@force.com");
		
		WebElement loginButton = driver.findElement(By.id("Login"));
		loginButton.click();
		
		WebElement loginError = driver.findElement(By.id("error"));
		String actualErrorMsg = loginError.getText();
		String expectedErrorMsg = "Please enter your password.";
		if(expectedErrorMsg.equals(actualErrorMsg.trim()))
		{
			logger.log(LogStatus.PASS, "Login test case1 is passed");
		}
		else
			logger.log(LogStatus.FAIL, "login Testcase1 is Failed");
	}
	
	//Test Id - Check Remember Me-3
	public static void loginTest2(WebDriver driver) throws InterruptedException
	{
		
		
		WebElement userName = driver.findElement(By.id("username"));
		userName.sendKeys("kr.sowmya1-yz8n@force.com");
		
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("welcome123");
		WebElement rememberMeCheck = driver.findElement(By.id("rememberUn"));
		rememberMeCheck.click();
		WebElement loginButton = driver.findElement(By.id("Login"));
		loginButton.click();
		Thread.sleep(5000);
		WebElement logoutImage = driver.findElement(By.xpath("//div[@class='profileTrigger branding-user-profile bgimg slds-avatar slds-avatar_profile-image-small circular forceEntityIcon']//span[@class='uiImage']"));
		logoutImage.click();
		WebElement logoutLink=driver.findElement(By.xpath("//a[@class='profile-link-label logout uiOutputURL']"));
		logoutLink.click();
		Thread.sleep(3000);
		String actualUserName = driver.findElement(By.id("idcard-identity")).getText();
		String expectedUserName = "kr.sowmya1-yz8n@force.com";
		if(actualUserName.equals(expectedUserName))
			logger.log(LogStatus.PASS, "Login TestCase 2 Passed");
		else
			logger.log(LogStatus.FAIL, "Login TestCase 2 Failed");
	}
	
	
	//Test Id - Forgot Password 4A
	public static void forgotPassowordTest(WebDriver driver)
	{
		WebElement forgotPwdLink = driver.findElement(By.id("forgot_password_link"));
		forgotPwdLink.click();
		WebElement userEmail = driver.findElement(By.id("un"));
		userEmail.sendKeys("kr.sowmya1-yz8n@force.com");
		WebElement continueButton = driver.findElement(By.id("continue"));
		continueButton.click();
		String resetMsg = driver.findElement(By.xpath("//div[@class='message']")).getText();
		System.out.println(resetMsg);
		if(resetMsg.contains("We’ve sent you an email with a link to finish resetting your password."))
			logger.log(LogStatus.PASS, "Login TestCase 3 passed");
		else
			logger.log(LogStatus.FAIL, "Login TestCase 3 Failed");
		
	}
	
	//Test Id- Forgot Password - 4B
	public static void wrongCredentialsTest(WebDriver driver)
	{
		WebElement userName = driver.findElement(By.id("username"));
		userName.sendKeys("123");
		
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("22131");
		WebElement rememberMeCheck = driver.findElement(By.id("rememberUn"));
		rememberMeCheck.click();
		WebElement loginButton = driver.findElement(By.id("Login"));
		loginButton.click();
		String actualErrorMsg = driver.findElement(By.id("error")).getText();
		String expectedErrorMsg="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		if(actualErrorMsg.equals(expectedErrorMsg))
			logger.log(LogStatus.PASS, "Login Test Case 4 is passed");
		else
			logger.log(LogStatus.FAIL, "Login Test Case 4 is Failed");
	}

}
