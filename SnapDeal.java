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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {

		//call the web driver manager for the browser driver
		WebDriverManager.chromedriver().setup();
		
		//disable the notifications
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--disable-notifications");
		
		//launch the browser
		ChromeDriver driver = new ChromeDriver();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(50));
		
		//load the url
		driver.get("https://www.snapdeal.com/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		
		//maximize the browser
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//span[@class='catText']")).click();
		
		driver.findElement(By.xpath("//span[@class='linkTest']")).click();
		
		System.out.print("The number of sports shoe is : ");
		
		System.out.println(driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText());
		
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();

		driver.findElement(By.xpath("//div[text()[normalize-space()='Popularity']]")).click();
		
		driver.findElement(By.xpath("(//span[text()='Sort by:']/following::li)[2]")).click();

		List<WebElement> training = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));

		System.out.println("The number of training shoes are: " + training.size());
		
		//take snapshot and check if the shoes has been sorted based on price
		WebElement chkSort1 = driver.findElement(By.xpath("(//span[@class='lfloat product-price'])[5]"));
		
		Actions builder = new Actions(driver);
		
		builder.scrollToElement(chkSort1).perform();
		
		//snapshot code
		File source1 = driver.getScreenshotAs(OutputType.FILE);
		
		File dest1 = new File("shoe2.png");
		
		FileUtils.copyFile(source1, dest1);

		//apllying price filter
		driver.findElement(By.xpath("//input[@class='input-filter']")).clear();
		
		driver.findElement(By.xpath("//input[@class='input-filter']")).sendKeys("900");
		
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).clear();
		
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).sendKeys("1200");
		
		driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow btn')]")).click();
		
		System.out.println(driver.findElement(By.xpath("//div[@class='navFiltersPill']")).getText());

		// Locating the Main Menu (Parent element)
		WebElement firstShoe = driver.findElement(By.xpath("//img[@class='product-image wooble']"));

		//Instantiating Actions class
		Actions actions = new Actions(driver);

		//Hovering on main menu
		actions.moveToElement(firstShoe);

		// Locating the Main Menu (Parent element)
		WebElement qckView = driver.findElement(By.xpath("//div[contains(@class,'center quick-view-bar')]"));

		//Hovering on main menu
		actions.moveToElement(qckView);
		
		actions.click().build().perform();
		
		System.out.print("The amount of the shoe is: ");
		
		System.out.println(driver.findElement(By.className("payBlkBig")).getText());
		
		System.out.print("Percentage discount applied  is: ");
		
		System.out.println(driver.findElement(By.xpath("//span[text()='64% Off']")).getText());

		WebElement shoeDtls = driver.findElement(By.className("payBlkBig"));
		
		builder.scrollToElement(shoeDtls).perform();
		
		//snapshot code for the shoe and its price
		File source = driver.getScreenshotAs(OutputType.FILE);
		
		File dest = new File("shoePrice.png");
		
		FileUtils.copyFile(source, dest);
		
		driver.findElement(By.xpath("//div[contains(@class,'close close1')]//i[1]")).click();

		driver.close();
	}
}