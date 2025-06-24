package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowHandles extends BaseTest {
	WebDriver driver1;
	 public WebDriver SwitchToChildWindow() {
		 Set<String> windowHandles = driver.getWindowHandles();
	        List<String> windowHandlesList = new ArrayList<>(windowHandles);
	     return  driver1 = driver.switchTo().window(windowHandlesList.get(2));
	 }

}
