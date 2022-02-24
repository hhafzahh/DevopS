import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
class UserServletTest {
	
	
	private WebDriver webDriver;

	@BeforeEach
	void setUp() throws Exception {
		//Setting system properties of ChromeDriver
		  //to amend directory path base on your local file path
		  String chromeDriverDir = "C:\\Users\\Hafsah\\chromedriver.exe";

		  System.setProperty("webdriver.chrome.driver", chromeDriverDir);

		  ///initialize FirefoxDriver at the start of test
		  webDriver = new ChromeDriver();  
	}

	@AfterEach
	void tearDown() throws Exception {
		webDriver.close();
	}

	
	@Test
	void testGetUsers() {
		webDriver.navigate().to("http://localhost:8090/DevopS/UserServlet/dashboard");
		String table = webDriver.findElement(By.xpath("//table[@class='table']/tbody/tr")).getAttribute("innerHTML");
		System.out.println(table);
		assert(table.contains("name"));
	}
	@Test 
	void testTotalUsersRegistered() {
		webDriver.navigate().to("http://localhost:8090/DevopS/UserServlet/dashboard");
		List<WebElement> rows = webDriver.findElements(By.xpath("//table[@class='table']/tbody/tr"));
		int count = rows.size();
		System.out.println("ROW COUNT : "+count);
		Assert.assertEquals(count, 4);
	}
	
	@Test 
	void testAddUser() {
		//if this works, the totalUsersRegistered count will increase by 1
		// so the above test will fail if this test is success
		webDriver.navigate().to("http://localhost:8090/DevopS/register.jsp");
		// fill in form
		webDriver.findElement(By.id("name")).sendKeys("user2");
		webDriver.findElement(By.id("email")).sendKeys("user2@gmail.com");
		webDriver.findElement(By.id("psw")).sendKeys("pass");
		Select se = new Select(webDriver.findElement(By.xpath("//*[@name='language']")));
		// Select the option by index
		se.selectByIndex(1);
		System.out.println(se);
		webDriver.findElement(By.className("signupbtn")).click();
		System.out.println(webDriver.getCurrentUrl());
		//test if it got added
		webDriver.navigate().to("http://localhost:8090/DevopS/UserServlet/dashboard");
		//check last row of added data 
		String table2= webDriver.findElement(By.xpath("//table[@class='table']/tbody/tr[last()]")).getAttribute("innerHTML");
		System.out.println(table2);
		assert(table2.contains("user2@gmail.com"));
		
		
		System.out.println("========================================================================");
		
	}
	
	@Test
	void testUpdateUser() {}
	
	

}
