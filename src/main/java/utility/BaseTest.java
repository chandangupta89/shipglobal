package utility;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


import readpropertyfile.ConfigProperties;
import readpropertyfile.ReadConfigProperties;

public class BaseTest {

	public static WebDriver driver;

	
	
	
	    @BeforeMethod
	public void preCondition() {
		ConfigProperties config_prop = ReadConfigProperties.loadconfigproperties();
		String url = config_prop.getUrl();

		driver = BrowserFactory.launchBrowser("chrome");

	    driver.manage().deleteAllCookies();
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(60));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().window().maximize();

		driver.navigate().to(url);

	}
	
	/*
	 * mail
	 */


	 @AfterSuite

	 public void SendMail() {
	

	// Email.sendMail();
	}

	@AfterMethod
	public void tearDown() {

  // driver.quit();

	}

}
