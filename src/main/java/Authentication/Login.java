package Authentication;

import org.openqa.selenium.By;

import utility.ElementUtil;
import vendor_panel.features.dashboard;

public class Login {
 
  private By email = By.xpath("//input[@id=':r0:-form-item']");
  private By password=By.xpath("//input[@name='password']");
  private By captcha_checkbox= By.id("captcha");
  private By submitbtn=By.xpath("//button[text()='Submit']");
  
  public void doLogin() {
	  ElementUtil.EnterText(email, "test@gmail.com");
	  ElementUtil.threadSleep(20000);
	  ElementUtil.EnterText(password, "123456");
	  ElementUtil.ClickOn(captcha_checkbox);
	  ElementUtil.ClickOn(submitbtn);
	  dashboard dashboard_obj =new dashboard();
	  ElementUtil.threadSleep(10000);
	  dashboard_obj.clickOnMenuOption("/orders");
	  
	  
  }
 
}
