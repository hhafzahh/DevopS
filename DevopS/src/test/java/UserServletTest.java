import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		//Assert.assertEquals(count, 6);
		Assert.assertTrue(count < 10);
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
	void testUpdateUser() {
		//Updating the first user we see in the table (the top user) 
		//Updating name and Password
		webDriver.navigate().to("http://localhost:8090/DevopS/UserServlet/dashboard");
		webDriver.findElement(By.xpath("//tbody//tr//td//a")).click();
		System.out.println(webDriver.getCurrentUrl());
		System.out.println(webDriver.getPageSource());
		webDriver.findElement(By.name("name")).clear();
		webDriver.findElement(By.name("name")).sendKeys("Hafsah");
		webDriver.findElement(By.name("password")).clear();
		webDriver.findElement(By.name("password")).sendKeys("P@ss");
		webDriver.findElement(By.className("btn-success")).click();
	    String updateTable = webDriver.findElement(By.xpath("//tbody//tr//td")).getAttribute("innerHTML");
		System.out.println(updateTable);
		assert(updateTable.contains("Hafsah"));
		System.out.println("========================================================================");
	}
	
	
	@Test
	void testDeleteUser() {
		webDriver.navigate().to("http://localhost:8090/DevopS/UserServlet/dashboard");
		webDriver.findElement(By.xpath("//tbody//tr//td//a[last()]")).click();
		String row = webDriver.findElement(By.xpath("//tbody//tr//td")).getAttribute("innerHTML");
		System.out.println(row);
		assert(!row.contains("Hafsah"));
		System.out.println("-------------------------------------------------------------------------------------------");
	}
	
	@Test
	void checkValidEmail() {
		webDriver.navigate().to("http://localhost:8090/DevopS/UserServlet/dashboard");
		//check first row , 3rd column
		String row2 = webDriver.findElement(By.xpath("//tbody//tr//td[3]")).getAttribute("innerHTML");
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println(row2);
		System.out.println(row2.contains("@"));
		assert(row2.contains("@"));
		//String regex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');
		//Pattern pattern = Pattern.compile(regex);
		//Matcher matcher = pattern.matcher(row2);
		//System.out.println(row2 +":"+ matcher.matches());
		//System.out.println("========================================================================");
	}
	
	
	
	
	
	
	

}
