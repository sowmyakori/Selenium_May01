import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EditViewNameTeset {
	
	WebDriver driver;
	
	@BeforeMethod
	public void login() throws InterruptedException
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
	
	@Test(enabled=false)
	public void editViewName()
	{
		WebElement accountTab = driver.findElement(By.id("Account_Tab"));
		accountTab.click();
		
		Select view  = new Select(driver.findElement(By.xpath("//select[@id='fcf']")));
		view.getFirstSelectedOption();
		
		WebElement editButton = driver.findElement(By.xpath("//span[@class='fFooter']//a[contains(text(),'Edit')]"));
		editButton.click();
		
		String accName = "ACC5544";
		WebElement viewName = driver.findElement(By.xpath("//input[@id='fname']"));
		viewName.sendKeys(accName);
		
		Select fields= new Select(driver.findElement(By.xpath("//select[@id='fcol1']")));
		fields.selectByVisibleText("Account Name");
		
		Select operator = new Select(driver.findElement(By.xpath("//select[@id='fop1']")));
		operator.selectByVisibleText("contains");
		
		WebElement value = driver.findElement(By.xpath("//input[@id='fval1']"));
		value.sendKeys("a");
		
		Select availableFields =new Select(driver.findElement(By.xpath("//select[@id='colselector_select_0']")));
		availableFields.selectByVisibleText("Last Activity");
		
		WebElement addArrow = driver.findElement(By.xpath("//img[@class='rightArrowIcon']"));
		addArrow.click();
		
		WebElement saveButton = driver.findElement(By.xpath("//div[@class='pbHeader']//input[@value=' Save ']"));
		saveButton.click();
		
		Select viewList = new Select(driver.findElement(By.xpath("//select[@id='00B4P000007TxzP_listSelect']")));
		String selectedView = viewList.getFirstSelectedOption().getText();
		
		Assert.assertEquals(accName, selectedView);
		
		
	}
	
	@Test
	public void mergeAccountTest()
	{
		WebElement accountTab = driver.findElement(By.id("Account_Tab"));
		accountTab.click();
		
		WebElement mergeAccLink = driver.findElement(By.xpath("//a[contains(text(),'Merge Accounts')]"));
		mergeAccLink.click();
		
		WebElement searchField = driver.findElement(By.xpath("//input[@id='srch']"));
		searchField.sendKeys("aa");
		
		WebElement findAccounts = driver.findElement(By.xpath("//input[@name='srchbutton']"));
		findAccounts.click();
		
		WebElement chk1 = driver.findElement(By.xpath("//input[@id='cid0']"));
		WebElement chk2 = driver.findElement(By.xpath("//input[@id='cid1']"));
		if(!chk1.isSelected())
			chk1.click();
		if(!chk2.isSelected())
			chk2.click();
		
		WebElement nextButton = driver.findElement(By.xpath("//div[@class='pbTopButtons']//input[@name='goNext']"));
		nextButton.click();
		
		WebElement mergeButton = driver.findElement(By.xpath("//div[@class='pbTopButtons']//input[@name='save']"));
		mergeButton.click();
		
		driver.switchTo().alert().accept();
	}

}
