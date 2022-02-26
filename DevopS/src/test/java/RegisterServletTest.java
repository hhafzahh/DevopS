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
	  //here instead of checking, we will check if the button "addUser" is clickable
	  webDriver.navigate().to("http://localhost:8090/DevopS/UserServlet/dashboard");
	  webDriver.findElement(By.xpath("//a[contains(.,'Add New User')]")).click();
	  String navigated = webDriver.getCurrentUrl();
	  Assert.assertEquals(navigated, "http://localhost:8090/DevopS/register.jsp");
  }
  @Test
  public void checkWebsite() {
		webDriver.navigate().to("http://localhost:8090/DevopS/UserServlet/dashboard");
		Assert.assertEquals(webDriver.getTitle(), "User Management Website");
	  
  }
  
}
