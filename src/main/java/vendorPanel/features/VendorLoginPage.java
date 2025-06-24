package vendorPanel.features;

import org.openqa.selenium.By;

import utility.BaseTest;
import utility.ElementUtil;

public class VendorLoginPage extends BaseTest {

	private By email = By.id("input-email");
	private By password = By.name("password");
	private By captchacheckBox = By.cssSelector("div.recaptcha-checkbox-border");
	private By continueBtn = By.id("kt_sign_in_submit");
	private By forgotPasswordBtn = By.xpath("//a[contains(text(),'Forgot Password')]");

	public void doVendorLogin() {
		ElementUtil.EnterText(email, "chandantest@gmail.com");
		ElementUtil.EnterText(password, "Password@123");
		ElementUtil.threadSleep(20000);
		
		ElementUtil.ClickOn(continueBtn);

	}

	public void ClickOnForgotBtn() {

	}

	public void ClickOnCreateAnAccount() {

	}

	
}
