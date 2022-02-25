import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class RegisterServletTest {
	private WebDriver webDriver;
  @BeforeTest
  public void beforeTest() {
	//Setting system properties of ChromeDriver
	  //to amend directory path base on your local file path
	  String chromeDriverDir = "C:\\Users\\Hafsah\\chromedriver.exe";

	  System.setProperty("webdriver.chrome.driver", chromeDriverDir);

	  ///initialize FirefoxDriver at the start of test
	  webDriver = new ChromeDriver();  
  }

  @AfterTest
  public void afterTest() {
	  webDriver.close();  
	  }

  @Test
  public void doPostTest() {
	  	webDriver.navigate().to("http://localhost:8090/DevopS/register.jsp");
	  
	  	webDriver.findElement(By.id("name")).sendKeys("user3");
		webDriver.findElement(By.id("email")).sendKeys("user3@gmail.com");
		webDriver.findElement(By.id("psw")).sendKeys("passw");
		Select se = new Select(webDriver.findElement(By.xpath("//*[@name='language']")));
		// Select the option by index
		se.selectByIndex(2);
		System.out.println(se);
		webDriver.findElement(By.className("signupbtn")).click();
		System.out.println(webDriver.getCurrentUrl());
		//test if it got added
		webDriver.navigate().to("http://localhost:8090/DevopS/UserServlet/dashboard");
		//check last row of added data 
		String table2= webDriver.findElement(By.xpath("//table[@class='table']/tbody/tr[last()]")).getAttribute("innerHTML");
		System.out.println(table2);
		Assert.assertTrue(table2.contains("user3"));
		
		
		System.out.println("========================================================================");
  }
  @Test
  public void checkWebsite() {
		webDriver.navigate().to("http://localhost:8090/DevopS/UserServlet/dashboard");
		Assert.assertEquals(webDriver.getTitle(), "User Management Website");
	  
  }
  
}
