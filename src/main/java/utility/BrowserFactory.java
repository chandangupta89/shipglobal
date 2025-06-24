package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.manager.SeleniumManager;

public class BrowserFactory {

	public static WebDriver launchBrowser(String browser) {

		WebDriver driver = null;

		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			opt.setBrowserVersion("116");
			System.out.println(SeleniumManager.getInstance().getDriverPath(opt, false).getBrowserPath());
			System.out.println(SeleniumManager.getInstance().getDriverPath(opt, false).getDriverPath());

//	opt.addArguments("--headless");
//	opt.addArguments("--window-size=1920,1080");
//	opt.addArguments("--start-maximized");
  

		

		}

		else if (browser.equalsIgnoreCase("firefox")) {

			FirefoxOptions options = new FirefoxOptions();

			System.out.println(SeleniumManager.getInstance().getDriverPath(options, false).getDriverPath());

			//options.addArguments("-headless");
		// options.addArguments("start-maximized");

			driver = new FirefoxDriver(options);

		}

		return driver;

	}

}
