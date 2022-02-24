import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.By;
//import necessary Selenium WebDriver classes
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
public class NewTest {
  private WebDriver webDriver;
  @Test
  public void f() {
  }
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
	  //Quit the ChromeDriver and close all associated window at the end of test
	  webDriver.close();			
  }

}
