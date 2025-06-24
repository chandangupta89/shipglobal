package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ElementUtil extends BaseTest {

	public static void VerifyAlertMessage(String expectedAlert) {
		String Alertmsg = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Assert.assertEquals(expectedAlert, Alertmsg);

	}

	// random no method
	public static final int getRandomInteger(int rangeStart, int rangeEnd) {
		if (rangeStart > rangeEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}

		long range = (long) rangeEnd - (long) rangeStart + 1;

		Random rnd = new Random(System.currentTimeMillis());
		long fraction = (long) (range * rnd.nextDouble());
		int randomNumber = (int) (fraction + rangeStart);
		return randomNumber;
	}

	public static void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)", "");
	}

	public static void ClickOnByJavaScriptExecutor(By Locator) {

		WebElement ele = driver.findElement(Locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
	}

	public static boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public static void HandleAlert() {

		try {
			if (isAlertPresent() == true) {
				driver.switchTo().alert().accept();
			}
		} catch (NoAlertPresentException e) {
		}
	}

	/*
	 * select drop down value for Tag Name others than select
	 */

	public static void selectDropdownValue(List<WebElement> DropDOwnlist, String TextName) {
		int totalTextcount = DropDOwnlist.size();
		for (int i = 0; i < totalTextcount; i++) {
			System.out.println(DropDOwnlist.get(i).getText());
		//	DropDOwnlist.get(i).getText();
			if (DropDOwnlist.get(i).getText().contains(TextName)) {
				DropDOwnlist.get(i).click();
				break;

			}
			
		}
	}
	

	public static void selectDropdownValue(List<WebElement> DropDOwnlist) {
		//int totalTextcount = DropDOwnlist.size();
		
			
				DropDOwnlist.get(0).click();
			

			}
			
		
	

	/*
	 * select drop down value for Tag Name select type
	 */

	public static void selectDropdownValue(WebElement locatorValue, String visibletext, String value) {

		Select select = new Select(locatorValue);
		switch (visibletext) {

		case "index":
			select.selectByIndex(Integer.parseInt(value));
			break;

		case "value":
			select.selectByValue(value);
			break;

		case "visibletext":
			select.selectByVisibleText(value);

		}
	}

	public static void threadSleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	/*
	 * to upload the file through robot class
	 */

	public static void setClipBoard(String file) {
		StringSelection obj = new StringSelection(file);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(obj, null);
	}

	public static void uploadFile(String filePath) throws AWTException {
		setClipBoard(filePath);
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

	}

	static public void switchTOnewTab(String url) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open()");
		// Switch to new tab
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(url);
	}

	static public void EnterText(By locatorValue, String text) {

		driver.findElement(locatorValue).sendKeys(text);

	}

	static public void ClickOnSubmitBtn(By locatorValue) {
		driver.findElement(locatorValue).submit();

	}

	static public void ClickOn(By locatorValue) {

		try {
			driver.findElement(locatorValue).click();
		} 
		
		
		
		catch (Exception e) {
			
			WebElement ele = driver.findElement(locatorValue);
			 
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
		}
		
		
		
		
		
		
		
	}

	static public String getPageName(By locatorValue) {

		String pageName = driver.findElement(locatorValue).getText();
		return pageName;

	}

	static public void verifyPageName(String ExpectedPageNmae, String actualPageName) {
		Assert.assertEquals(ExpectedPageNmae, actualPageName);

	}

	static public String getText(By locatorValue) {

		String textNmae = driver.findElement(locatorValue).getText();
		return textNmae;

	}

	/*
	 * element to be clickable and enabled
	 */
	static public void waitUntilelementToBeClickable(long time, By locatorValue) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(locatorValue));
	}
	/*
	 * fluent wait element to be clickable and enabled
	 */

	static public void fluentWaitUntilelementToBeClickable(By locatorValue) {
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.elementToBeClickable(locatorValue));
	}

	static public void waitUntilvisibilityOfElementLocated(By locatorValue) {
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(locatorValue));
	}

	static public WebElement findElement(By locatorValue) {
		WebElement webElement = driver.findElement(locatorValue);
		return webElement;
	}

	public static List<String> getListofValues(By locatorValue) {
		List<String> actualValues = new ArrayList<>();
		List<WebElement> allElements = driver.findElements(locatorValue);
		for (WebElement element : allElements) {
			actualValues.add(element.getText());
		}
		return actualValues;
	}

	public static int getCountofElements(By locatorValue) {

		try {
			List<WebElement> elements = driver.findElements(locatorValue);
			// explicit wait - to wait for the compose button to be click-able
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			return elements.size();
		} catch (NoSuchElementException e) {
			return -1;
		}

	}

	// Scroll to the element
	public static WebElement ScrollToElement(By locatorValue) {
		WebElement element = driver.findElement(locatorValue);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}

	public static String getMobileNumber() {
		int no = ElementUtil.getRandomInteger(700000000, 999999999);
		String number1 = Integer.toString(no);
		String mobileNumber = number1 + "1";
		return mobileNumber;

	}

	public static boolean isSelected(By locatorValue) {
		WebElement element = driver.findElement(locatorValue);
		return element.isSelected();
	}

	public static boolean isVisible(By locatorValue) {
		try {
			WebElement element = driver.findElement(locatorValue);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfAllElements(element));

			// WebDriverWait wait = new WebDriverWait(driver,Constants.TimeOut);
			// wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public static boolean isEnabled(By locatorValue) {
		WebElement element = driver.findElement(locatorValue);
		// explicit wait - to wait for the compose button to be click-able
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		// driver.manage().timeouts().implicitlyWait(Constants.TimeOut,
		// TimeUnit.SECONDS);
		return element.isEnabled();
	}

	static public List<WebElement> findelements(By locatorValue) {
		List<WebElement> webElement = driver.findElements(locatorValue);
		return webElement;
	}

	public static String getcurrentUrl() {

		return driver.getCurrentUrl();
	}

	public static void scrollIntoView(WebElement element) {
		// Use "center" as the default block option
		String script = "arguments[0].scrollIntoView({ block: 'center' });";
		((JavascriptExecutor) driver).executeScript(script, element);
	}

	public static void switchToWindowByTitle(WebDriver driver, String windowTitle) {
		String currentWindowHandle = driver.getWindowHandle();
		for (String windowHandle : driver.getWindowHandles()) {
			driver.switchTo().window(windowHandle);
			if (driver.getTitle().equals(windowTitle)) {
				return;
			}
		}
		// If the window with the specified title is not found, switch back to the
		// original window.
		driver.switchTo().window(currentWindowHandle);
	}

	public static void switchToWindowByURL(WebDriver driver, String windowURL) {
		String currentWindowHandle = driver.getWindowHandle();
		for (String windowHandle : driver.getWindowHandles()) {
			driver.switchTo().window(windowHandle);
			if (driver.getCurrentUrl().equals(windowURL)) {
				return;
			}
		}
		// If the window with the specified URL is not found, switch back to the
		// original window.
		driver.switchTo().window(currentWindowHandle);
	}

	public static void switchToWindowByIndex(WebDriver driver, int index) {
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles);
		List<String> windowHandlesList = new ArrayList<>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(index));

	}
}
