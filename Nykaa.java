package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {

		//call the web driver manager for the browser driver
		WebDriverManager.chromedriver().setup();
		
		//launch the browser
		ChromeDriver driver = new ChromeDriver();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(50));

		
		//load the url
		driver.get("https://www.nykaa.com/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//maximize the browser
		driver.manage().window().maximize();
		
		// Locating the Main Menu (Parent element)
		WebElement mainMenu = driver.findElement(By.xpath("(//a[@class='css-1mavm7h'])[2]"));

		//Instantiating Actions class
		Actions actions = new Actions(driver);

		//Hovering on main menu
		actions.moveToElement(mainMenu);

		// Locating the element from Sub Menu - L'oreal Paris
		WebElement subMenu = driver.findElement(By.xpath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']"));

		//To mouseover on sub menu
		actions.moveToElement(subMenu);

		//build()- used to compile all the actions into a single step 
		actions.click().build().perform();
		
		System.out.println(driver.getTitle());
		
		//applying the filters
		driver.findElement(By.className("sort-name")).click();
		
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		Thread.sleep(5000);
		
		//click hair under category
		WebElement ele = driver.findElement(By.xpath("(//div[@class=' css-b5p5ep'])[2]/span"));
		  ele.click();

		//TRYING TO HANDLE STALEELEMENTREFERENCE EXCEPTION
		
		//TRY CATCH METHOD
		/*for(int i=0; i<=2;i++){
			  try{
				  ele.click();
			     break;
			  }
			  catch(StaleElementReferenceException e){
				  wait.until(ExpectedConditions.stalenessOf(ele));
				  System.out.println("The element is stale");
			  }
			}

		for(int i=0; i<=2;i++){
			  try{
				driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
     		     break;
			  }
			  catch(StaleElementReferenceException e){
				  wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//span[text()='Hair Care']"))));
				  System.out.println("The Hair Care element is stale");
			  }
			  catch(NoSuchElementException e1){
				  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Hair Care']")));
				  System.out.println("The element is not availabe");
			  }
			}*/
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='checkbox_Shampoo_316']//div[1]")));

		driver.findElement(By.xpath("//label[@for='checkbox_Shampoo_316']//div[1]")).click();

		driver.findElement(By.xpath("//div[@id='filters-strip']/div[1]/div[1]/div[5]/div[1]")).click();

		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		
		driver.findElement(By.xpath("//div[@class='css-1rd7vky']//div")).click();
		
		//switching the window controls
		Set<String> colorProtect = driver.getWindowHandles();
		
		List<String> lstProtect = new ArrayList<String> (colorProtect);
		
		driver.switchTo().window(lstProtect.get(1));
		
		WebElement size = driver.findElement(By.className("css-2t5nwu"));

		Select select = new Select(size);
		
		select.selectByIndex(1);
		
		System.out.println(driver.findElement(By.xpath("//span[contains(text(),'189')]")).getText());
		
		driver.findElement(By.xpath("//div[@class='css-vp18r8']//button[1]")).click();
		
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();
		
		//switching control to the frame element
		WebElement frameReach = driver.findElement(By.xpath("//div[@class='    css-1w65igk e1j92jav0']/iframe"));
		
		driver.switchTo().frame(frameReach);
		
		System.out.println(driver.findElement(By.xpath("//span[text()='Grand Total']/following::div")).getText());

		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		
		driver.switchTo().defaultContent();
		
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();

		System.out.println(driver.findElement(By.xpath("(//div[@class='value']//span)[2]")).getText());
		
		driver.quit();

	}
	
}