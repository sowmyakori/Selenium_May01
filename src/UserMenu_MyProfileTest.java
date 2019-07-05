import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UserMenu_MyProfileTest {

	static ExtentReports reports;
	static ExtentTest logger;

	public static void main(String[] args) throws InterruptedException, IOException {
		String fileName = new SimpleDateFormat("'UserMenuTest_'MMMdd_hhmmss'.html'").format(new Date());
		String path="ExtentReport/"+fileName;
		reports = new ExtentReports(path);
		logger = reports.startTest("Begin Login Test******");
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Dell-Latitude-E6430\\Selenium_training\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get("https://login.salesforce.com");		
		logger.log(LogStatus.INFO, "Launched URL");

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
		
		WebElement myProfile = driver.findElement(By.xpath("//a[contains(text(),'My Profile')]"));
		myProfile.click();
		//---------------------------------------------------------------------------------------
//		WebElement editContactInfo = driver.findElement(By.xpath("//a[@class='contactInfoLaunch editLink']//img"));
//		editContactInfo.click();
//	
//		Thread.sleep(5000);
//		
//		int totalFrames = driver.findElements(By.tagName("iframe")).size();
//		System.out.println(totalFrames);
//		
//		driver.switchTo().frame("contactInfoContentId");
//		WebElement aboutTab = driver.findElement(By.xpath("//a[contains(text(),'About')]"));
//		aboutTab.click();
//		
//		WebElement lastName = driver.findElement(By.name("lastName"));
//				lastName.clear();
//		lastName.sendKeys("kori");
//			
//		WebElement saveAllButton = driver.findElement(By.xpath("//input[@class='zen-btn zen-primaryBtn zen-pas']"));
//		saveAllButton.click();
//		
//		driver.switchTo().defaultContent();
//		
//		String displayedName= driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']")).getText();
//		if(displayedName.contains("kori"))
//		{
//			logger.log(LogStatus.PASS, "User Menu Test Case passed");
//		}
//		else
//			logger.log(LogStatus.PASS, "User Menu Test Case Failed");
		//------------------------------------------------------------------------
		
//		Thread.sleep(5000);
//		WebElement postLink = driver.findElement(By.xpath("//a[@id='publisherAttachTextPost']"));
//		postLink.click();
//		Thread.sleep(3000);
//		WebElement frame = driver.findElement(By.xpath("//iframe[@title='Rich Text Editor, publisherRichTextEditor']"));
//		 driver.switchTo().frame(frame);
//		 String messageText = "Hello..";
//			WebElement postTextArea = driver.findElement(By.xpath("//html[1]//body[1]"));
//			postTextArea.sendKeys(messageText);
//
//		driver.switchTo().defaultContent();
//		Thread.sleep(5000);
//		
//		WebElement shareButton = driver.findElement(By.xpath("//input[@id='publishersharebutton']"));
//		shareButton.click();
//				
//		
//		driver.switchTo().defaultContent();		
//		
//		List<WebElement> postMsg = driver.findElements(By.xpath("//span[@class='feeditemtext cxfeeditemtext']//p"));
//		Iterator<WebElement> itr = postMsg.iterator();
//		boolean isPostPresent=false;
//		while(itr.hasNext())
//		{
//			String text = itr.next().getText();
//			System.out.println(text);
//			if(text.contentEquals("Hello.."))
//			{
//				isPostPresent=true;
//				break;
//			}
//			
//		}
	//-------------------------------------------------------------------------------	
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Thread.sleep(3000);
		WebElement fileLink = driver.findElement(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'File')]"));
		//wait.until(ExpectedConditions.elementToBeClickable(fileLink));
		fileLink.click();
		Thread.sleep(5000);
		WebElement uploadButton = driver.findElement(By.id("chatterUploadFileAction"));
		uploadButton.click();	
		WebElement browse = driver.findElement(By.id("chatterFile"));
		browse.click();
		Runtime.getRuntime().exec("E:\\FileUpload.exe");
		Thread.sleep(3000);
//		browse.sendKeys("C:\\Users\\Dell-Latitude-E6430\\Desktop\\Sampledox\\SampleText.odt");
		WebElement shareButton = driver.findElement(By.id("publishersharebutton"));
		shareButton.click();
	//------------------------------------------------------------------------------------	
//		Thread.sleep(5000);
//		WebElement image = driver.findElement(By.xpath("//span[@id='displayBadge']"));
//		Actions action = new Actions(driver);
//		action.moveToElement(image).build().perform();
//
//		WebElement addPhoto = driver.findElement(By.id("uploadLink"));
//		addPhoto.click();
//		Thread.sleep(3000);
//		driver.switchTo().frame("uploadPhotoContentId");
//		WebElement choosePhoto = driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadInputFile']"));
//		choosePhoto.sendKeys("C:\\Users\\Dell-Latitude-E6430\\Desktop\\Sampledox\\SampleImg.jpg");
//		Thread.sleep(7000);
//		WebElement saveButton = driver.findElement(By.xpath("//input[@id='j_id0:j_id7:save']"));
//		saveButton.click();
//		
//		reports.endTest(logger);
//		reports.flush();

	}

}
