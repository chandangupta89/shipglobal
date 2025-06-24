package adminPanel.features;


import org.openqa.selenium.By;

import utility.BaseTest;
import utility.ElementUtil;

public class AdminPanelLoginPage  extends BaseTest{
  By email=By.id("input-email");
  By password =By.name("password");
  By countinueBtn=By.id("kt_sign_in_submit");
  
  public void doAdminLogin() {
		ElementUtil.EnterText(email, "chandangupta11july@gmail.com");
		ElementUtil.EnterText(password, "Password@123");
		ElementUtil.threadSleep(20000);
	//	ElementUtil.ClickOn(captchacheckBox);
		ElementUtil.ClickOn(countinueBtn);

	}

}
