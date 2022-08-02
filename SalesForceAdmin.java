package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class SalesForceAdmin {

	public static void main(String[] args) throws InterruptedException, IOException {

		//call the web driver manager for the browser driver
		WebDriverManager.chromedriver().setup();
		
		//disable the notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//launch the browser
		ChromeDriver driver = new ChromeDriver();
		
		//load the url
		driver.get("https://login.salesforce.com/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//maximize the browser
		driver.manage().window().maximize();	
		
		//login and click on learn more option in mobile publisher
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		
		driver.findElement(By.id("password")).sendKeys("Password$123");
		
		driver.findElement(By.id("Login")).click();
		
		driver.findElement(By.xpath("(//span[@class=' label bBody'])[2]")).click();

		//switch the controls to new window
		Set<String> newWindow = driver.getWindowHandles();
		
		List<String> lstWndw = new ArrayList<String> (newWindow);
		
		driver.switchTo().window(lstWndw.get(1));
		
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		//Click Resources and mouse hover on Learning On Trailhead
		//since the Resources are under shadow root, create a shadow dom and access the elements using the dom
		Shadow dom=new Shadow(driver);	
		
		dom.findElementByXPath("//span[text()='Learning']").click();
		
		dom.findElementByXPath("//span[text()='Learning on Trailhead']").click();
		
		/*mouse hover code throws moveTargetOutOfBound exception, so using click action
		WebElement chkSort1 = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		
		Actions builder = new Actions(driver);
		
		builder.scrollToElement(chkSort1).click().perform();*/
		
		dom.findElementByXPath("//a[text()='Salesforce Certification']").click();

		driver.findElement(By.xpath("//img[@alt='Salesforce<br/>Administrator']")).click();

		 // check if Navigated to Certification - Administrator Overview window
		System.out.println(driver.getTitle());
		
		//Verify the Certifications available for Administrator Certifications 
		
		List<WebElement> certification = driver.findElements(By.xpath("//div[@class = 'credentials-card_title']"));

		System.out.println("The number of administrator certifications are : " + certification.size());

		System.out.println("=======================================================");
		for (WebElement webElement : certification) {
			String name = webElement.getText();
			System.out.println(name);
		}
		
	}
	
}