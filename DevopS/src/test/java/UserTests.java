import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class UserTests {
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
  public void listUsersTest() {
	  webDriver.navigate().to("http://localhost:8090/DevopS/UserServlet/dashboard");
	  String Heading = webDriver.findElement(By.id("listOfUsers")).getAttribute("innerHTML");
	  Assert.assertTrue(Heading.contains("List of Users"));
  }

  @Test
  public void showEditFormTest() {
	    webDriver.navigate().to("http://localhost:8090/DevopS/UserServlet/dashboard");
		webDriver.findElement(By.xpath("//tbody//tr//td//a")).click();
		System.out.println(webDriver.getCurrentUrl());
		System.out.println(webDriver.getPageSource());
		String Title = webDriver.findElement(By.id("edit")).getAttribute("innerHTML");
		Assert.assertTrue(Title.contains("Edit User"));
  }

 
}
