package vendor_panel.features;

import org.openqa.selenium.By;

import utility.BaseTest;
import utility.ElementUtil;


public class VendorLoginPage extends BaseTest {
	  private By email = By.xpath("//input[@id=':r0:-form-item']");
	  private By password=By.xpath("//input[@name='password']");
	  private By captcha_checkbox= By.id("captcha");
	  private By submitbtn=By.xpath("//button[text()='Submit']");
	  
	  public void doLogin() {
		  ElementUtil.EnterText(email, "test@gmail.com");
		  ElementUtil.threadSleep(10000);
		  ElementUtil.EnterText(password, "123456");
		  ElementUtil.ClickOn(captcha_checkbox);
		  ElementUtil.ClickOn(submitbtn);
		  dashboard dashboard_obj =new dashboard();
		  ElementUtil.threadSleep(10000);
		  dashboard_obj.clickOnMenuOption("/orders");
		  ElementUtil.threadSleep(10000);
		  AddOrderPage.clickOnCreateOrder();
		  System.out.println("chandan kumar gupta");

	  }	
}
