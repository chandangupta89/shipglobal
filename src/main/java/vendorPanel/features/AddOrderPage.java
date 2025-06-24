package vendorPanel.features;

import org.openqa.selenium.Keys;

import static org.testng.Assert.assertTrue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import utility.BaseTest;
import utility.ElementUtil;
import utility.ExtentTestManager;
import utility.query;

public class AddOrderPage extends BaseTest {
	
	Float new_wallet_balance;
	
	// menu title
	private By orders = By.xpath("//span[text()='Orders']");
	private By add_order = By.xpath("//span[text()='Add Order']");
	private By ready = By.xpath("//span[text()='Ready']");
	private By wallet = By.xpath("//span[text()='Wallet']");
	private By recharge_wallet = By.xpath("//span[text()='Recharge Wallet']");

	private By packed_menu_title = By.xpath("//span[@class='menu-title' and text()='Packed']");

	private By manifested = By.xpath("//span[text()='Manifested']");
	
	private By dispatched = By.xpath("//span[text()='Dispatched']");
	
	
	
	

	/*
	 * Buyer Shipping Details
	 * 
	 */
	private By select_pickup_address=By.xpath("(//span[@class='select2-selection select2-selection--single form-select form-select-solid'])[1]");
	
	private By pickup_address_list=By.xpath("//li[starts-with(@id,'select2-pickup_address_id')]");
	
	
	private By first_name = By.name("customer_shipping_firstname");
	private By last_name = By.name("customer_shipping_lastname");
	private By mobile_no = By.id("customer_shipping_mobile");
	private By email_id = By.name("customer_shipping_email");
	private By select_country = By.name("customer_shipping_country_code");
	private By shipping_address_1 = By.name("customer_shipping_address");
	private By landmark = By.name("customer_shipping_address_2");
	private By shipping_address_3 = By.name("customer_shipping_address_3");
	private By pincode = By.name("customer_shipping_postcode");
	private By shipping_city = By.name("customer_shipping_city");

	private By shipping_state = By.id("customer_shipping_state_id");
	private By continue_btn = By.xpath("//button[text()='Continue']");

	/*
	 * Order Details
	 */

	private By invoice_no = By.name("vendor_invoice_no");
	private By invoice_date = By.name("vendor_order_date");
	private By reference_id = By.name("vendor_reference_order_id");
	private By ioss_no = By.name("ioss_number");
	private By product_name = By.name("vendor_order_item[0][vendor_order_item_name]");
	private By hsn_no = By.name("vendor_order_item[0][vendor_order_item_hsn]");

	private By quantity = By.name("vendor_order_item[0][vendor_order_item_quantity]");
	private By unit_price = By.name("vendor_order_item[0][vendor_order_item_unit_price]");

	/*
	 * Add shipment Details
	 * 
	 */

	private By shipmentDetailsheading = By.xpath("//h2[text()='Shipment Details']");
	// h2[text()='Shipment Details']
	private By actual_weight = By.xpath("//input[@name='package_weight']");
	private By actual_length = By.xpath("//input[@name='package_length']");
	private By actual_breadth = By.xpath("//input[@name='package_breadth']");
	private By actual_heigth = By.xpath("//input[@name='package_height']");

	
	/*
	 * shipping partner
	 * 
	 */
	private By shiping_partner = By.xpath("//input[@class='form-check-input' and @type='radio']");
	private By Billed_Weight = By.xpath("//div[text()='Billed Weight']");

	private By Dead_Weight = By.xpath("//div[text()='Dead Weight']");
	private By Volumetric_Weight = By.xpath("//div[text()='Volumetric Weight']");

	private By pay_and_AddOrder = By.xpath("//button[@id='submit_add_order']");

	/*
	 * order details page
	 * 
	 */
	private By wallet_balance = By.xpath("(//a[@href='/wallet'])[1]");

	private By order_booking_total_amount = By.xpath("//div[text()='Total']/following-sibling::div");
	private By Logistic_Fee = By.xpath("//div[text()='Logistic Fee']/following-sibling::div");
	private By Gst_Fee = By.xpath("//div[text()='GST']/following-sibling::div");

	

	/*
	 * add items details
	 *
	 */
	private By add_item_btn = By.xpath("//a[@class='btn btn-light-primary']");

	private By add_product_name = By.name("vendor_order_item[1][vendor_order_item_name]");

	private By hsnno = By.name("vendor_order_item[1][vendor_order_item_hsn]");

	private By qty = By.name("vendor_order_item[1][vendor_order_item_quantity]");
	private By unitprice2 = By.name("vendor_order_item[1][vendor_order_item_unit_price]");
	
	/*
	 * delete item
	 */
	private By delete_item_btn = By.xpath("//a[@class='btn btn-sm btn-light-danger mt-3 mt-md-8']");
	
	
	/*
	 * wallet recharge
	 */
	private By recherge_amount = By.id("input-amount");
	private By countinueToPayment_btn = By.xpath("//button[@id='wallet_submit']");

	private By countinuePayment_btn = By.xpath("//div[contains(text(),'Continue to Payment')]");
	private By card_payment_option = By.xpath("//input[@value='card']");
	private By card_no = By.xpath("//input[@placeholder='Card Number']");
	private By card_expiry_mm = By.xpath("//input[@id='mm']");
	private By card_expiry_yy = By.xpath("//input[@id='yy']");
	private By CVV_No = By.xpath("//input[@placeholder='CVV']");
	private By pay_btn = By.xpath("//span[contains(text(),'PAY')]");
	private By Successful_btn = By.xpath("//span[text()='Successful']");
	private By payment_status = By.xpath("//td[contains(text(),'Completed')]");
	
	
	
	/*
	 * Draft Orders
	 */
	
	private String edit_btn1 = "// a[text()='";
	private String edit_btn2 = "']/../following-sibling::td//a[contains(text(),'Edit')]";
	
	private By cancel_btn = By.xpath("//a[text()='Cancel']");
	private By columnName_orderid = By.xpath("//th[text()='Order ID']");
	
	private String viewpah1 = "// a[text()='";
			
    private String viewpah2 = "']/../following-sibling::td//a[contains(text(),'View')]";
	private By delivery_Address = By.xpath("//div[text()='Delivery Address:']/following-sibling::div/text()");
 
			
	
	
	
	/*
	 * Ready
	 */

	private By order_id = By.xpath("(//a[contains(text(),'SG')])[1]");
	private By order_status = By.xpath("(//span[contains(text(),'Ready to Ship')])[1]");

	private By order_view = By.xpath("(//a[contains(text(),'View')])[1]");
	private By order_invoice = By.xpath("(//a[contains(text(),'Invoice')])[1]");
	private By print_firstMile_label = By.xpath("(//a[contains(text(),'Print Label')])[1]");

	/*
	 * packed
	 */

	private String last_mile_awb_path1 = "//a[contains(text(),'";

	private String last_mile_awb_path2 = "')]/../following-sibling::*[4]";

	private String orderstatusforPackedpath1 = "//a[contains(text(),'";

	private String orderstatusforPackedpath2 = "')]/../following-sibling::*[3]";

	

	private By last_mile_AWB_column_name = By.xpath("//th[text()='Last Mile AWB']");

	/*
	 * manifest
	 */

	private By new_manifest = By.xpath("//a[@href='/manifest/add']");
	private By view_btn = By.xpath("(//span[text()='open']/../following-sibling::td//a[text()='View'])[1]");
	private By no_manifested_item = By.xpath("//td[contains(text(),'No Records Found')]");
	
	//td[contains(text(),'No Records Found')]
	private String addToManifestbtnpath1 = "//a[text()='";

	private String addToManifestbtnpath2 = "']/../following-sibling::*[4]//button";

	
	
	
	/*
	 * Manifested Orders list
	 * 
	 */
	private String manifested_orderIdpath1 = "//a[text()='";
	private String manifested_orderIdpath2 = "']";
	private By close_manifest_btn = By.xpath("(//button[contains(text(),'Close Manifest')])[1]");
	
	private By close_manifest_btn2 = By.id("closeManifest");
	private By alert_manifest_closebtn =By.xpath("//button[text()='Close']");
	private By alert_manifest_msg =By.xpath("//div[contains(text(),'Manifest Successfully Closed, Please Download Label & Other Documents.')]");
	
	private By manifestDetailsHeading =By.xpath("//span[text()='Manifest Details']");
	private By manifest_id =By.xpath("//input[@id='manifest_code']");
	private String remove_manifestpath1 ="//a[contains(text(),'";
	private String remove_manifestpath2 ="')]/../following-sibling::td//button[contains(text(),'Remove From Manifest')]";

	
	private By total_count =By.xpath("//div[contains(text(),'Total Count:')]/following-sibling::div");
	private By total_weight =By.xpath("//div[contains(text(),'Total Weight:')]/following-sibling::div");
	private By manifest_order_value =By.xpath("//div[contains(text(),'Manifest Order Value: ')]/following-sibling::div");
	
	private By pickup_service_name =By.xpath("//div[contains(text(),'Pickup Service: ')]/following-sibling::div");
	private By pickup_partner =By.xpath("//div[contains(text(),'Pickup Partner: ')]/following-sibling::div");
	
	
	/*
	 * Dispatched
	 */
	private String manifested_item_statuspath1 ="//a[text()='";
	private String manifested_item_statuspath2 ="']/../following-sibling::td//span[text()='Shipped']";

	
	
	/*
	 * Test case Functions
	 * ===========================================================================================
	 */
	
	// click on add order btn
	public void clickOnaddorderOption() {
		ElementUtil.waitUntilvisibilityOfElementLocated(orders);
		ElementUtil.ClickOn(orders);
		ElementUtil.waitUntilvisibilityOfElementLocated(orders);

		ElementUtil.ClickOn(add_order);

	}
	// click on ready button
	public void clickOnReady() {
		ElementUtil.waitUntilvisibilityOfElementLocated(ready);
		ElementUtil.ClickOn(ready);
        ElementUtil.threadSleep(2000);

	}
	

	public void addBuyerDetails(String firstname,String lastname,String mobileno,String emailid,String country_name,String address1,String shippinglandmark,String address3,String shippingpincode,String city,String state
) {
		ElementUtil.waitUntilvisibilityOfElementLocated(select_pickup_address);
        ElementUtil.findElement(select_pickup_address).click();
        List<WebElement> pickup_add_list = ElementUtil.findelements(pickup_address_list);
        System.out.println(pickup_add_list.get(0).getText());
        ElementUtil.selectDropdownValue(pickup_add_list);
       
		ElementUtil.EnterText(first_name, firstname);
		ElementUtil.EnterText(last_name, lastname);
		ElementUtil.EnterText(mobile_no,"8521091765");
		ElementUtil.EnterText(email_id,emailid);
		ElementUtil.waitUntilvisibilityOfElementLocated(select_country);
		WebElement countryname = ElementUtil.findElement(select_country);

		ElementUtil.selectDropdownValue(countryname, "visibletext",country_name);
		countryname.sendKeys(Keys.ENTER);
		ElementUtil.EnterText(shipping_address_1, address1);
		ElementUtil.EnterText(landmark,shippinglandmark);
		ElementUtil.EnterText(shipping_address_3, address3);
		ElementUtil.EnterText(pincode, shippingpincode);
		ElementUtil.EnterText(shipping_city, city);
		ElementUtil.waitUntilvisibilityOfElementLocated(shipping_state);
		WebElement shiping_state_dropdwn = ElementUtil.findElement(shipping_state);
		ElementUtil.selectDropdownValue(shiping_state_dropdwn, "visibletext", state);
		countryname.sendKeys(Keys.ENTER);

		ElementUtil.ClickOn(continue_btn);

	}

	public void addOrderDetails(String invoiceno,String refrenceid,String iossno,String productname,String hsnno,String order_quantity,String orderunitprice ) {
       
		ElementUtil.EnterText(invoice_no, invoiceno);

		ElementUtil.EnterText(reference_id, refrenceid);
		ElementUtil.EnterText(ioss_no, iossno);
		ElementUtil.EnterText(product_name, productname);
		ElementUtil.EnterText(hsn_no, hsnno);
		ElementUtil.EnterText(quantity, order_quantity);
		ElementUtil.EnterText(unit_price, orderunitprice);

	}

	public void ClickOnContinueBtn() {

		ElementUtil.ClickOn(continue_btn);
	}

	/*
	 * Item details
	 */

	public void addShipmentDetails(String actualweight,String length,String breadth,String height) {
        ElementUtil.EnterText(actual_weight, actualweight);
		ElementUtil.EnterText(actual_length, length);
		ElementUtil.EnterText(actual_breadth, breadth);
		ElementUtil.EnterText(actual_heigth, height);
		ElementUtil.ClickOn(continue_btn);

	}

	public void selectShippingPartner() {
		ElementUtil.waitUntilvisibilityOfElementLocated(Billed_Weight);

		List<WebElement> list_of_shipping_partner = ElementUtil.findelements(shiping_partner);

		int shipper_count = list_of_shipping_partner.size();
		System.out.println("shipper_count:"+shipper_count);
		if (shipper_count > 0) {
			ElementUtil.ClickOn(continue_btn);
		} else {
			assertTrue(false);
		}

	}

	

	public Float getWalletBalance() {
		ElementUtil.waitUntilvisibilityOfElementLocated(wallet_balance);
		String wallebalance = ElementUtil.findElement(wallet_balance).getText();

		String[] balance = wallebalance.split("\\s+");
		String netBalance = balance[1];
		float walletbalance = Float.parseFloat(netBalance);

		return walletbalance;

	}

	public Float getOrderAmount() {
		ElementUtil.scrollDown();
		ElementUtil.waitUntilvisibilityOfElementLocated(order_booking_total_amount);

		String logistic_GST_amount = ElementUtil.getText(order_booking_total_amount);

		String[] total_amount = logistic_GST_amount.split("\\s+");
		String netBookingAmount = total_amount[1];
		String logistic_amount_plus_gst = netBookingAmount.replaceAll(",", "");

		Float order_amount = Float.parseFloat(logistic_amount_plus_gst);
	
		
		return order_amount;
	}

	public double getLogisticAmount() {
		ElementUtil.scrollDown();
		ElementUtil.waitUntilvisibilityOfElementLocated(Logistic_Fee);

		String logistic_amount = ElementUtil.getText(Logistic_Fee);

		String[] logistic_Fee = logistic_amount.split("\\s+");
		String net_LogisticAmount = logistic_Fee[1];
		String logistic = net_LogisticAmount.replaceAll(",", "");

		double logisticFee = Double.parseDouble(logistic);
		return logisticFee;
	}

	public double getGSTAmount() {
		ElementUtil.scrollDown();
		ElementUtil.waitUntilvisibilityOfElementLocated(Gst_Fee);

		String GST_amount = ElementUtil.getText(Gst_Fee);

		String[] gst_amount = GST_amount.split("\\s+");
		String Amount = gst_amount[1];
		String gst_Fee = Amount.replaceAll(",", "");

		System.out.println(gst_Fee);

		double gstFee = Double.parseDouble(gst_Fee);
		return gstFee;
	}

	public void rechargeCustomerWallet( String orderAmount) {
		
		
		ElementUtil.ClickOn(wallet);
		ElementUtil.ClickOn(recharge_wallet);
		ElementUtil.waitUntilvisibilityOfElementLocated(recherge_amount);
		ElementUtil.EnterText(recherge_amount, orderAmount );
		
		ElementUtil.ClickOn(countinueToPayment_btn);

		ElementUtil.waitUntilvisibilityOfElementLocated(countinuePayment_btn);
		ElementUtil.ClickOn(countinuePayment_btn);
		ElementUtil.threadSleep(2000);
		ElementUtil.ClickOn(card_payment_option);
		ElementUtil.waitUntilvisibilityOfElementLocated(card_no);
		ElementUtil.EnterText(card_no, "4111 1111 1111 1111");
		ElementUtil.EnterText(card_expiry_mm, "08");
		ElementUtil.EnterText(card_expiry_yy, "25");
		ElementUtil.EnterText(CVV_No, "123");
		ElementUtil.ClickOn(pay_btn);
		ElementUtil.waitUntilvisibilityOfElementLocated(Successful_btn);
		ElementUtil.ClickOn(Successful_btn);
		ElementUtil.waitUntilvisibilityOfElementLocated(payment_status);
		Assert.assertEquals(ElementUtil.getText(payment_status), "Completed");

	}

	public Float PayandAddOrder(String firstname,String lastname,String mobile_no,String email_id,String countryname,String address1,String landmark,String address3,String pincode,String city,String state,String invoiceno,String refrenceid,String iossno,String productname,String hsnno,String order_quantity,String orderunitprice,String actualweight,String length,String breadth,String height) {
		Float wallet_balance = getWalletBalance();

		Float orderAmount = getOrderAmount();
		
		System.out.println("order_amount: " + orderAmount);
		System.out.println("current_balance: " + wallet_balance);
		
		if (wallet_balance < orderAmount) {
			rechargeCustomerWallet(Float.toString(orderAmount));
			clickOnaddorderOption();
			addBuyerDetails(firstname,lastname,mobile_no,email_id,countryname,address1,landmark,address3,pincode,city,state);
			addOrderDetails(invoiceno,refrenceid,iossno,productname,hsnno,order_quantity, orderunitprice);
			ClickOnContinueBtn();
			addShipmentDetails(actualweight,length,breadth, height);
			selectShippingPartner();
			PayandAddOrder(firstname,lastname,mobile_no,email_id,countryname,address1,landmark,address3,pincode,city,state,invoiceno,refrenceid,iossno,productname,hsnno,order_quantity, orderunitprice,actualweight,length,breadth, height);


		}
		
		else {
		 new_wallet_balance = getWalletBalance();
		
		 
		 ElementUtil.waitUntilvisibilityOfElementLocated(pay_and_AddOrder);

		 clickonPayAndOrderBtn();
			//ElementUtil.ClickOn(pay_and_AddOrder);
		}			

		
		return  new_wallet_balance;
		

	}

	public String getOrderStatus() {

		ElementUtil.waitUntilvisibilityOfElementLocated(order_status);

		String orderStatus = ElementUtil.getText(order_status);
		System.out.println("order Status is :" + orderStatus);
		return orderStatus;
	}

	public String getOrderId() {
		ElementUtil.waitUntilvisibilityOfElementLocated(order_id);
		String OrderID = ElementUtil.getText(order_id);
		System.out.println("order Id is :" + OrderID);
		return OrderID;
	}

	public void ClickOnPrintLabelBtn() {
		ElementUtil.waitUntilvisibilityOfElementLocated(print_firstMile_label);
		ElementUtil.ClickOn(print_firstMile_label);

	}

	public void clickOnPacked() {
		ElementUtil.waitUntilvisibilityOfElementLocated(packed_menu_title);
		ElementUtil.threadSleep(2000);
		ElementUtil.ClickOn(packed_menu_title);
		ElementUtil.threadSleep(3000);
		driver.navigate().refresh();
		ElementUtil.threadSleep(5000);

	}

	public String getLastMileAWB(String orderid) {

		ElementUtil.waitUntilvisibilityOfElementLocated(last_mile_AWB_column_name);
		ElementUtil.threadSleep(5000);

		String lastmile_awb = driver.findElement(By.xpath(last_mile_awb_path1 + orderid + last_mile_awb_path2))
				.getText();
		return lastmile_awb;

	}

	public String getOrderStatusAfterPrintLabel(String orderid) {
		ElementUtil.waitUntilvisibilityOfElementLocated(last_mile_AWB_column_name);
		String orderstatus = driver
				.findElement(By.xpath(orderstatusforPackedpath1 + orderid + orderstatusforPackedpath2)).getText();
		return orderstatus;

	}

	public void clickOnManifested() {
		ElementUtil.waitUntilvisibilityOfElementLocated(manifested);
		ElementUtil.ClickOn(manifested);
	}

	public void clickOnNewManifest() {
		
		ElementUtil.waitUntilvisibilityOfElementLocated(new_manifest);
		ElementUtil.ClickOn(new_manifest);
		

	}

	public void clickOnNewManifestViewBtn() {
	

	try {
		ElementUtil.threadSleep(3000);
		//ElementUtil.waitUntilvisibilityOfElementLocated(view_btn);
		ElementUtil.ClickOnByJavaScriptExecutor(view_btn);
	} catch (Exception NoSuchElementException) {
		
			System.out.println("No old manifested items");
			
		
		
	}
	
	


	}

	public void clickOnAddToManifestBtn(String orderid) {
	
		ElementUtil.threadSleep(3000);
		WebElement addTomanifestbtn = driver.findElement(By.xpath(addToManifestbtnpath1 + orderid + addToManifestbtnpath2));
		ElementUtil.scrollIntoView(addTomanifestbtn);
		addTomanifestbtn.click();

	}

	public int getGstRate() {

		String gst_rate = query.getGstrate();
		int gst = Integer.parseInt(gst_rate);
		System.out.println("GST RATE:" + gst);
		return gst;
	}

	public String TwoDecimalPlace(Float num) {

		final DecimalFormat decfor = new DecimalFormat("0.00");

		String no = decfor.format(num);
		return no;
	}

	public void ClickOnAddBtn() {
		
		ElementUtil.waitUntilvisibilityOfElementLocated(add_item_btn);
		ElementUtil.ClickOn(add_item_btn);

	}
	
public void ClickOnDeleteBtn() {
		
		ElementUtil.waitUntilvisibilityOfElementLocated(delete_item_btn);
		ElementUtil.ClickOn(delete_item_btn);

	}
public Boolean isDisplayedDeleteBtn() {
	
	 try {
		 ElementUtil.threadSleep(2000);
		WebElement deletebtn = ElementUtil.findElement(delete_item_btn);
		deletebtn.isDisplayed();
		return deletebtn.isDisplayed();
	} catch (Exception NoSuchElementException) {
		
		return false;
		
		
	}
	
	 
	
}

	public void addItem() {
		ClickOnAddBtn();
		
		ElementUtil.waitUntilvisibilityOfElementLocated(add_product_name);
		ElementUtil.EnterText(add_product_name, "pen");
		ElementUtil.EnterText(hsnno, "12345678");

		ElementUtil.EnterText(qty, "10");

		ElementUtil.EnterText(unitprice2, "100");

	}

	public String getShipingPageHeadingName() {
		ElementUtil.waitUntilvisibilityOfElementLocated(shipmentDetailsheading);
		return ElementUtil.getText(shipmentDetailsheading);

	}
	
	
	public String getManifestedOrderId(String orderid) {
		
		ElementUtil.threadSleep(2000);
		
		String manifested_orderid = driver.findElement(By.xpath(manifested_orderIdpath1 + orderid + manifested_orderIdpath2)).getText();
		
		
		return manifested_orderid;
		
	}
	
	public void clickOnManifestCloseBtn() {
		
		
		ElementUtil.waitUntilvisibilityOfElementLocated(close_manifest_btn);
		ElementUtil.ClickOn(close_manifest_btn);
		ElementUtil.waitUntilvisibilityOfElementLocated(close_manifest_btn2);
		ElementUtil.ClickOn(close_manifest_btn2);
		
		ElementUtil.waitUntilvisibilityOfElementLocated(alert_manifest_closebtn);
		ElementUtil.ClickOn(alert_manifest_closebtn);
		ElementUtil.waitUntilvisibilityOfElementLocated(manifestDetailsHeading);
		ElementUtil.getText(manifestDetailsHeading);
		String manifestid = ElementUtil.findElement(manifest_id).getAttribute("value");
		System.out.println("Manifest ID is:"+manifestid);
		 ExtentTestManager.getTest().info("Manifest ID is:"+manifestid);
		List<String> manifest_details = query.getManifestDetails();
		String manifest_code = manifest_details.get(1);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(manifest_code.contains("MSG"));
		softAssert.assertFalse(manifest_code.isBlank() || manifest_code == null);
		softAssert.assertEquals(manifestid, manifest_code);
		softAssert.assertEquals(manifest_details.get(3),"closed");
		 ExtentTestManager.getTest().info("Manifested status:"+manifest_details.get(3));
		
		
	}
	
	public String getmanifestedorderStatus(String orderid) {
		
		
		ElementUtil.threadSleep(5000);
		WebElement manifestorder_status = driver.findElement(By.xpath(manifested_item_statuspath1 + orderid + manifested_item_statuspath2));
		return manifestorder_status.getText();
			
		
	}
	
	public  void clickOnDispatched() {
		ElementUtil.waitUntilvisibilityOfElementLocated(dispatched);
		ElementUtil.ClickOn(dispatched);
	}
	
	
	public void ClickOnRemoveManifestButton(String orderid) {
		
		ElementUtil.threadSleep(3000);
		WebElement remove_manifest = driver.findElement(By.xpath(remove_manifestpath1 + orderid + remove_manifestpath2));
		remove_manifest.click();
		
	}
	public String getPickupServiceName() {
		
		
		String pickupservece_name = ElementUtil.getText(pickup_service_name);
		
		
		return pickupservece_name;
		}
	
	
	public void clickonPayAndOrderBtn() {
	
		ElementUtil.waitUntilvisibilityOfElementLocated(pay_and_AddOrder);
		ElementUtil.ClickOn(pay_and_AddOrder);
		
	}
	public void save_in_draft() {
		Float wallet_balance = getWalletBalance();

		Float orderAmount = getOrderAmount();
		
		System.out.println("order_amount: " + orderAmount);
		System.out.println("current_balance: " + wallet_balance);
		 ExtentTestManager.getTest().info("order_amount:" + orderAmount);
		 ExtentTestManager.getTest().info("current_balance: " + wallet_balance);
		
		if (wallet_balance < orderAmount) {
			clickonPayAndOrderBtn();
			
		}
		else {
			
		     
			
			System.out.println("Executed Successfully");
			 ExtentTestManager.getTest().info("can not be tested due to wallet balance are avialable");
			 throw new SkipException("Skipping this exception");
		}		
	}

 public void ClickOnEditBtn(String orderid) {
	 ElementUtil.waitUntilvisibilityOfElementLocated( columnName_orderid);
	 ElementUtil.threadSleep(2000);
	
WebElement editbtn = driver.findElement(By.xpath(edit_btn1 + orderid + edit_btn2));
		
editbtn.click();
	
 }
 public void ClickOnCancelOrderBtn() {
	 ElementUtil.switchToWindowByIndex(driver, 1);
	 ElementUtil.waitUntilvisibilityOfElementLocated(cancel_btn);
		ElementUtil.ClickOn(cancel_btn);
		
	 
	
 }
 // to do
 
 public void ClickOnPayNow() {
	 
	 
	 
 }

 
 public void ClickOnViewBtn(String orderid) {
	 ElementUtil.waitUntilvisibilityOfElementLocated( columnName_orderid);
	 ElementUtil.threadSleep(2000);
	
WebElement viewbtn = driver.findElement(By.xpath(viewpah1 + orderid + viewpah2));
		
viewbtn.click();
	 
 }
 
 public List<String> getDeliveryAddress() {
	 
	 List<String> deliveryAddress= new ArrayList<String>();
	  for(int i=0;i<=6;i++) {
	List<WebElement> delivery_address_list = ElementUtil.findelements(delivery_Address);
	
  String Address = delivery_address_list.get(0).getText();
  deliveryAddress.add(Address);
	  }
	return deliveryAddress;
 }
}



