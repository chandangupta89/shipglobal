package vendor_panel.features;
import org.openqa.selenium.By;
import utility.BaseTest;
import utility.ElementUtil;


public class AddOrderPage extends BaseTest {
	 private static By  add_Order_CTABtn= By.xpath("//span[text()='Create Order']");
	 
	 
	 
	 public  static void clickOnCreateOrder() {
		// ElementUtil.waitUntilvisibilityOfElementLocated(add_Order_CTABtn);
		 ElementUtil.ClickOn(add_Order_CTABtn);
		 
		 
		 
	 }
}























