package vendor_panel.scripts;

import org.testng.annotations.Test;

import utility.BaseTest;
import vendorPanel.features.VendorLoginPage;

public class VendorLoginTest extends BaseTest {
	
	
	@Test
	public void verifyValidLogin() {
	
		VendorLoginPage vendor_login = new VendorLoginPage();
	vendor_login.doVendorLogin();
		
		
		
		
	}
	

}
