package vendor_panel.scripts;

import java.util.List;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utility.BaseTest;
import utility.ExtentTestManager;
import utility.TestDataProvider;
import utility.query;
import vendorPanel.features.AddOrderPage;
import vendorPanel.features.VendorLoginPage;

public class kycTest extends BaseTest {
	@Test( dataProvider = "Add_Order_Details", dataProviderClass = TestDataProvider.class, priority = 1, enabled = true)
	public void Place_The_Order(String firstname, String lastname, String mobile_no, String email_id,
			String countryname, String address1, String landmark, String address3, String pincode, String city,
			String state,String Invoice_no,String reference_id,String ioss_no,String product_name,String hsn_no,String quantity,String unit_price,String actual_weight,String length,String breadth,String height) {

		VendorLoginPage vendor_login = new VendorLoginPage();
		vendor_login.doVendorLogin();
		AddOrderPage add_orders = new AddOrderPage();
		add_orders.clickOnaddorderOption();
		add_orders.addBuyerDetails(firstname, lastname, mobile_no, email_id, countryname, address1, landmark, address3,
				pincode, city, state);
		
		// validate the page nevigated
		
	}

}
