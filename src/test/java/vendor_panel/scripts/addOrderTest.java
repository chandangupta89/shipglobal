package vendor_panel.scripts;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utility.BaseTest;
import utility.ElementUtil;
import utility.ExtentTestManager;
import utility.TestDataProvider;
import utility.query;
import vendorPanel.features.AddOrderPage;
import vendorPanel.features.VendorLoginPage;

public class addOrderTest extends BaseTest {

	/*
	 * PlaceOrder
	 */

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
		add_orders.addOrderDetails(Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price);
		add_orders.ClickOnContinueBtn();
		add_orders.addShipmentDetails(actual_weight,length, breadth,height);
		add_orders.selectShippingPartner();
		add_orders.PayandAddOrder(firstname,lastname,mobile_no,email_id,countryname,address1,landmark,address3,pincode,city,state,Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price,actual_weight,length,breadth, height);
				
		String orderId = add_orders.getOrderId();
		List<String> list = query.getorderStatusId(orderId);
		SoftAssert softAssert = new SoftAssert();
		System.out.println("order status ID:" + list.get(0));
		
		softAssert.assertEquals(add_orders.getOrderStatus(), "Ready to Ship");
		
		softAssert.assertEquals(list.get(0), "1");
		softAssert.assertTrue(add_orders.getOrderId().contains("SG"));
		ExtentTestManager.getTest().info("order ID is :"  +orderId);
		ExtentTestManager.getTest().info("order status ID:"  + list.get(0));
		ExtentTestManager.getTest().info("order status:"  + add_orders.getOrderStatus());
		
		softAssert.assertAll();
	}

  
	
	  @Test(dataProvider = "Add_Order_Details", dataProviderClass = TestDataProvider.class,  priority = 2,
	  enabled = true)
	
	public void  verify_WalletBalance_Deduction_After_place_order(String firstname, String lastname, String mobile_no, String email_id,
				String countryname, String address1, String landmark, String address3, String pincode, String city,
				String state,String Invoice_no,String reference_id,String ioss_no,String product_name,String hsn_no,String quantity,String unit_price,String actual_weight,String length,String breadth,String height) {
	  
	  VendorLoginPage vendor_login = new VendorLoginPage();
	  vendor_login.doVendorLogin();
	  AddOrderPage add_orders = new AddOrderPage();
	  add_orders.clickOnaddorderOption();
	
	  add_orders.addBuyerDetails(firstname, lastname, mobile_no, email_id, countryname, address1, landmark, address3,
				pincode, city, state);
	  
	 
	  add_orders.addOrderDetails(Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price);
	  add_orders.ClickOnContinueBtn();
	 
		add_orders.addShipmentDetails(actual_weight,length, breadth,height);
	  add_orders.selectShippingPartner();
	  
	  Float order_amount = add_orders.getOrderAmount(); 
	 
	  Float wb = add_orders.PayandAddOrder(firstname, lastname, mobile_no, email_id, countryname, address1, landmark, address3,
				pincode, city, state,Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price,actual_weight,length, breadth,height);
	  String orderId = add_orders.getOrderId();
	 
	  /*
	  
	  ElementUtil.threadSleep(5000); 
	  add_orders.rechargeCustomerWallet("100");
	  
	  add_orders.clickOnaddorderOption(); 
	  add_orders.clickOnReady();
	  
	  
	  */
	  
	  SoftAssert softAssert = new SoftAssert();
	  softAssert.assertEquals(add_orders.getOrderStatus(), "Ready to Ship");
	  
	  Float remaining_wallet_balance = add_orders.getWalletBalance();
	  
	  System.out.println("remaining_wallet_balance:" + remaining_wallet_balance);
	  
	  Float new_wallet_balance = wb - order_amount;
	  
	  String wallet_balance1 = add_orders.TwoDecimalPlace(new_wallet_balance);
	  float wallet_balance2 = Float.parseFloat(wallet_balance1);
	  
	  softAssert.assertEquals(remaining_wallet_balance, wallet_balance2);
	  ExtentTestManager.getTest().info("order ID: "  + orderId);
		ExtentTestManager.getTest().info("order_amount: "  + order_amount);
	  ExtentTestManager.getTest().info("wallet_balance: "  +wb);
	  ExtentTestManager.getTest().info("remaining_wallet_balance: "  +remaining_wallet_balance);
		
	
		
		
		
	  softAssert.assertAll();
	  
	  }
	  
	  @Test(dataProvider = "Add_Order_Details", dataProviderClass = TestDataProvider.class,  priority = 3,
	  enabled = true) 
	  public void print_Label_And_verify_Lastmile_AWB(String firstname, String lastname, String mobile_no, String email_id,
				String countryname, String address1, String landmark, String address3, String pincode, String city,
				String state,String Invoice_no,String reference_id,String ioss_no,String product_name,String hsn_no,String quantity,String unit_price,String actual_weight,String length,String breadth,String height) {
	  
	  VendorLoginPage vendor_login = new VendorLoginPage();
	  vendor_login.doVendorLogin(); 
	  AddOrderPage add_orders = new AddOrderPage();
	  add_orders.clickOnaddorderOption();
	
	  add_orders.addBuyerDetails(firstname, lastname, mobile_no, email_id, countryname, address1, landmark, address3,
				pincode, city, state);

	  add_orders.addOrderDetails(Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price);
	  
	  add_orders.ClickOnContinueBtn();
	
		add_orders.addShipmentDetails(actual_weight,length, breadth,height);
	  add_orders.selectShippingPartner();
	
	  add_orders.PayandAddOrder(firstname,lastname,mobile_no,email_id,countryname,address1,landmark,address3,pincode,city,state,Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price,actual_weight,length,breadth, height);
	  String orderId = add_orders.getOrderId();
	  SoftAssert softAssert = new SoftAssert();
	  softAssert.assertEquals(add_orders.getOrderStatus(), "Ready to Ship");
	  
	  add_orders.ClickOnPrintLabelBtn();
	  add_orders.clickOnPacked();
	  
	  String LastMile_No = add_orders.getLastMileAWB(orderId);
	  System.out.println(LastMile_No);
	  
	  String order_status = add_orders.getOrderStatusAfterPrintLabel(orderId);
	  add_orders.getLastMileAWB(orderId); List<String> list =
	  query.getOrdersDetails(orderId);
	  
	  System.out.println(" partner_connect_id: " + list.get(0));
	  System.out.println("partner_connect_awb: " + list.get(1));
	  System.out.println("partner_lastmile_id: " + list.get(2));
	  System.out.println("partner_lastmile_awb: " + list.get(3));
	  System.out.println("lastmile_label: " + list.get(4));
	  System.out.println("order_status_id: " + list.get(5));
	  softAssert.assertEquals(order_status, "Packed");
	  softAssert.assertEquals(list.get(5), "2");
	  softAssert.assertFalse(list.get(0).isBlank() || list.get(0) == null);
	  softAssert.assertFalse(list.get(1).isBlank() || list.get(1) == null);
	  softAssert.assertFalse(list.get(2).isBlank() || list.get(2) == null);
	  softAssert.assertFalse(list.get(3).isBlank() || list.get(3) == null);
	  softAssert.assertFalse(list.get(4).isBlank() || list.get(4) == null);
	  
	  ExtentTestManager.getTest().info("order ID: "  + orderId);
			ExtentTestManager.getTest().info("order_status: "  + order_status);
			ExtentTestManager.getTest().info("order_status_id: "  + list.get(5));
		  ExtentTestManager.getTest().info("partner_connect_id: "  +list.get(0));
		  ExtentTestManager.getTest().info("partner_connect_awb: "  +list.get(1));
		  ExtentTestManager.getTest().info("partner_lastmile_id: "  + list.get(2));
		  ExtentTestManager.getTest().info("partner_lastmile_awb: "  +list.get(3));
		  ExtentTestManager.getTest().info("lastmile_label:"  +list.get(4));
	  softAssert.assertAll(); }
	  
	  
	  @Test(priority = 4,dataProvider = "Add_Order_Details", dataProviderClass = TestDataProvider.class, enabled = true) 
	  public void create_and_Close_manifest(String firstname, String lastname, String mobile_no, String email_id,
				String countryname, String address1, String landmark, String address3, String pincode, String city,
				String state,String Invoice_no,String reference_id,String ioss_no,String product_name,String hsn_no,String quantity,String unit_price,String actual_weight,String length,String breadth,String height) {
	  
	  VendorLoginPage vendor_login = new VendorLoginPage();
	  vendor_login.doVendorLogin();
	  AddOrderPage add_orders = new AddOrderPage();
	  add_orders.clickOnaddorderOption(); 
	  
	  add_orders.addBuyerDetails(firstname, lastname, mobile_no, email_id, countryname, address1, landmark, address3,
				pincode, city, state);

	  add_orders.addOrderDetails(Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price);
	  add_orders.ClickOnContinueBtn();
	  
		add_orders.addShipmentDetails(actual_weight,length, breadth,height);
	  add_orders.selectShippingPartner();
	 
	  add_orders.PayandAddOrder(firstname,lastname,mobile_no,email_id,countryname,address1,landmark,address3,pincode,city,state,Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price,actual_weight,length,breadth, height);
	  String orderId = add_orders.getOrderId();
	  SoftAssert softAssert = new SoftAssert();
	  softAssert.assertEquals(add_orders.getOrderStatus(), "Ready to Ship");
	  add_orders.ClickOnPrintLabelBtn(); add_orders.clickOnPacked();
	  add_orders.clickOnManifested(); add_orders.clickOnNewManifest();
	  
	  add_orders.clickOnNewManifestViewBtn();
	  
	  add_orders.clickOnAddToManifestBtn(orderId); List<String> list =
	  query.getOrdersDetails(orderId); softAssert.assertEquals(list.get(5),"13");
	  
	  List<String> manifest_details = query.getManifestDetails();
	  softAssert.assertEquals(manifest_details.get(3),"open");
	  ExtentTestManager.getTest().info("Manifested status: "+manifest_details.get(3));
	  System.out.println("order_statust_id:" + list.get(5));
	  ExtentTestManager.getTest().info("order_status_id : "  + list.get(5));
	  softAssert.assertEquals(orderId,add_orders.getManifestedOrderId(orderId));
	  
	  add_orders.clickOnManifestCloseBtn(); 
	 
	  List<String> list2 =query.getorderStatusId(orderId); softAssert.assertEquals(list2.get(0),"15");
	  System.out.println("order_statust_id: " + list2.get(0));
	  add_orders.clickOnaddorderOption(); 
	  add_orders.clickOnDispatched();
	
	  String orderstatus_dispatched = add_orders.getmanifestedorderStatus(orderId);
	  softAssert.assertEquals("Shipped",orderstatus_dispatched);
		  ExtentTestManager.getTest().info("order ID : "  + orderId);
	  ExtentTestManager.getTest().info("order_status : "  + orderstatus_dispatched);
		
	
	  
	  softAssert.assertAll(); 
	  }
	  
	  @Test(dataProvider = "Add_Order_Details", dataProviderClass = TestDataProvider.class,  priority = 5, enabled = true)
	  public void verify_GST_On_Logistic_Fee(String firstname, String lastname, String mobile_no, String email_id,
				String countryname, String address1, String landmark, String address3, String pincode, String city,
				String state,String Invoice_no,String reference_id,String ioss_no,String product_name,String hsn_no,String quantity,String unit_price,String actual_weight,String length,String breadth,String height) {
	  
	  VendorLoginPage vendor_login = new VendorLoginPage();
	  vendor_login.doVendorLogin();
	  AddOrderPage add_orders = new AddOrderPage();
	  add_orders.clickOnaddorderOption(); 
	
	  add_orders.addBuyerDetails(firstname, lastname, mobile_no, email_id, countryname, address1, landmark, address3,
				pincode, city, state);

	  add_orders.addOrderDetails(Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price);
	  add_orders.ClickOnContinueBtn();
	 
		add_orders.addShipmentDetails(actual_weight,length, breadth,height);
	  add_orders.selectShippingPartner(); double
	  GSTFee = add_orders.getGSTAmount(); int gst_rate = add_orders.getGstRate();
	  double logisticFee = add_orders.getLogisticAmount();
	  
	  System.out.println("logisticFee:" + logisticFee); 
	  double gst_cal =  logisticFee * gst_rate / 100; 
	  System.out.println("GSTFee:" + GSTFee);
	  
	  System.out.println("gst_cal" + gst_cal);
	  
	  ExtentTestManager.getTest().info("logisticFee: " +logisticFee);
	  ExtentTestManager.getTest().info("gst_rate: " + gst_rate);
	  ExtentTestManager.getTest().info("GSTFee: " + GSTFee);
	  ExtentTestManager.getTest().info("GST calculated: " + gst_cal);
	  
	  
	  Assert.assertEquals(GSTFee, gst_cal);
	  
	  }
	  
	  @Test(dataProvider = "Add_Order_Details", dataProviderClass = TestDataProvider.class,  priority = 6,
	  enabled = true) 
	  public void add_Multiple_Orders(String firstname, String lastname, String mobile_no, String email_id,
				String countryname, String address1, String landmark, String address3, String pincode, String city,
				String state,String Invoice_no,String reference_id,String ioss_no,String product_name,String hsn_no,String quantity,String unit_price,String actual_weight,String length,String breadth,String height) {
	  
	  VendorLoginPage vendor_login = new VendorLoginPage();
	  vendor_login.doVendorLogin();
	  AddOrderPage add_orders = new AddOrderPage();
	  add_orders.clickOnaddorderOption();
	  add_orders.addBuyerDetails(firstname, lastname, mobile_no, email_id, countryname, address1, landmark, address3,
				pincode, city, state);

	  add_orders.addOrderDetails(Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price);
	  
	  add_orders.addItem();
	  add_orders.ClickOnContinueBtn();
	  Assert.assertEquals(add_orders.getShipingPageHeadingName(), "Shipment Details");
	  
	  }
	  
	  @Test(dataProvider = "Add_Order_Details", dataProviderClass = TestDataProvider.class,  priority = 7,
	  enabled = true) 
	  public void add_and_remove_Orders(String firstname, String lastname, String mobile_no, String email_id,
				String countryname, String address1, String landmark, String address3, String pincode, String city,
				String state,String Invoice_no,String reference_id,String ioss_no,String product_name,String hsn_no,String quantity,String unit_price,String actual_weight,String length,String breadth,String height) {
	  
	  VendorLoginPage vendor_login = new VendorLoginPage();
	  vendor_login.doVendorLogin();
	  AddOrderPage add_orders = new AddOrderPage();
	  add_orders.clickOnaddorderOption();
	 
	  add_orders.addBuyerDetails(firstname, lastname, mobile_no, email_id, countryname, address1, landmark, address3,
				pincode, city, state);

	  add_orders.addOrderDetails(Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price);
	  add_orders.addItem();
	  add_orders.ClickOnDeleteBtn();
	  Assert.assertFalse(add_orders.isDisplayedDeleteBtn());
	  
	  }
	 
	  
	  @Test(priority = 8,dataProvider = "Add_Order_Details", dataProviderClass = TestDataProvider.class, enabled = true) 
	  public void remove_manifest(String firstname, String lastname, String mobile_no, String email_id,
				String countryname, String address1, String landmark, String address3, String pincode, String city,
				String state,String Invoice_no,String reference_id,String ioss_no,String product_name,String hsn_no,String quantity,String unit_price,String actual_weight,String length,String breadth,String height) {
	 
		  VendorLoginPage vendor_login = new VendorLoginPage();
		  vendor_login.doVendorLogin();
		  AddOrderPage add_orders = new AddOrderPage();
		  add_orders.clickOnaddorderOption(); 
		  
		  add_orders.addBuyerDetails(firstname, lastname, mobile_no, email_id, countryname, address1, landmark, address3,
					pincode, city, state);

		  add_orders.addOrderDetails(Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price);
		  add_orders.ClickOnContinueBtn();
		  
			add_orders.addShipmentDetails(actual_weight,length, breadth,height);
		  add_orders.selectShippingPartner();
		 
		  add_orders.PayandAddOrder(firstname,lastname,mobile_no,email_id,countryname,address1,landmark,address3,pincode,city,state,Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price,actual_weight,length,breadth, height);
		  String orderId = add_orders.getOrderId();
		  SoftAssert softAssert = new SoftAssert();
		  softAssert.assertEquals(add_orders.getOrderStatus(), "Ready to Ship");
		  add_orders.ClickOnPrintLabelBtn(); add_orders.clickOnPacked();
		  add_orders.clickOnManifested(); 
		  add_orders.clickOnNewManifest();
		  
		  add_orders.clickOnNewManifestViewBtn();
		  
		  add_orders.clickOnAddToManifestBtn(orderId); List<String> list =
		  query.getOrdersDetails(orderId); softAssert.assertEquals(list.get(5),"13");
		  
		  
		  List<String> manifest_details = query.getManifestDetails();
		  softAssert.assertEquals(manifest_details.get(3),"open");
		  
		 add_orders. ClickOnRemoveManifestButton(orderId);
		  List<String> manifest_order_status = query.getOrdersDetails(orderId);
		  softAssert.assertEquals(manifest_order_status.get(6),"0");
		 
		  
	  }
	
	  
	  
	
	  @Test(priority = 9,dataProvider = "Add_Order_Details", dataProviderClass = TestDataProvider.class, enabled = true) 
	  public void verify_pickup_service_name_as_self_ship(String firstname, String lastname, String mobile_no, String email_id,
				String countryname, String address1, String landmark, String address3, String pincode, String city,
				String state,String Invoice_no,String reference_id,String ioss_no,String product_name,String hsn_no,String quantity,String unit_price,String actual_weight,String length,String breadth,String height) {
	  
		  VendorLoginPage vendor_login = new VendorLoginPage();
		  vendor_login.doVendorLogin();
		  AddOrderPage add_orders = new AddOrderPage();
		  add_orders.clickOnaddorderOption(); 
		  
		  add_orders.addBuyerDetails(firstname, lastname, mobile_no, email_id, countryname, address1, landmark, address3,
					pincode, city, state);

		  add_orders.addOrderDetails(Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price);
		  add_orders.ClickOnContinueBtn();
		  
			add_orders.addShipmentDetails(actual_weight,length, breadth,height);
		  add_orders.selectShippingPartner();
		 
		  add_orders.PayandAddOrder(firstname,lastname,mobile_no,email_id,countryname,address1,landmark,address3,pincode,city,state,Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price,actual_weight,length,breadth, height);
		  String orderId = add_orders.getOrderId();
		  SoftAssert softAssert = new SoftAssert();
		  softAssert.assertEquals(add_orders.getOrderStatus(), "Ready to Ship");
		  add_orders.ClickOnPrintLabelBtn(); add_orders.clickOnPacked();
		  add_orders.clickOnManifested(); add_orders.clickOnNewManifest();
		  
		  add_orders.clickOnNewManifestViewBtn();
		  
		  add_orders.clickOnAddToManifestBtn(orderId); List<String> list =
		  query.getOrdersDetails(orderId); softAssert.assertEquals(list.get(5),"13");
		  
		  List<String> manifest_details = query.getManifestDetails();
		  softAssert.assertEquals(manifest_details.get(3),"open");
		  ExtentTestManager.getTest().info("Manifested status: "+manifest_details.get(3));
		  System.out.println("order_statust_id:" + list.get(5));
		  ExtentTestManager.getTest().info("order_status_id : "  + list.get(5));
		  softAssert.assertEquals(orderId,add_orders.getManifestedOrderId(orderId));
		  
		  add_orders.clickOnManifestCloseBtn(); 
		 
		String pickUp_service_name = add_orders.getPickupServiceName();
			
		 softAssert.assertEquals(pickUp_service_name,"Self Ship");
		  
		  softAssert.assertAll(); 
	
	  
	  }
	  
	  @Test(priority = 10,dataProvider = "Add_Order_Details", dataProviderClass = TestDataProvider.class, enabled = true) 
	  public void verify_pickup_service_name_as_Pickup(String firstname, String lastname, String mobile_no, String email_id,
				String countryname, String address1, String landmark, String address3, String pincode, String city,
				String state,String Invoice_no,String reference_id,String ioss_no,String product_name,String hsn_no,String quantity,String unit_price,String actual_weight,String length,String breadth,String height) {
	  
		  VendorLoginPage vendor_login = new VendorLoginPage();
		  vendor_login.doVendorLogin();
		  AddOrderPage add_orders = new AddOrderPage();
		  add_orders.clickOnaddorderOption(); 
		  
		  add_orders.addBuyerDetails(firstname, lastname, mobile_no, email_id, countryname, address1, landmark, address3,
					pincode, city, state);

		  add_orders.addOrderDetails(Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price);
		  add_orders.ClickOnContinueBtn();
		  
			add_orders.addShipmentDetails(actual_weight,length, breadth,height);
		  add_orders.selectShippingPartner();
		 
		  add_orders.PayandAddOrder(firstname,lastname,mobile_no,email_id,countryname,address1,landmark,address3,pincode,city,state,Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price,actual_weight,length,breadth, height);
		  String orderId = add_orders.getOrderId();
		  SoftAssert softAssert = new SoftAssert();
		  softAssert.assertEquals(add_orders.getOrderStatus(), "Ready to Ship");
		  add_orders.ClickOnPrintLabelBtn(); add_orders.clickOnPacked();
		  add_orders.clickOnManifested(); add_orders.clickOnNewManifest();
		  
		  add_orders.clickOnNewManifestViewBtn();
		  
		  add_orders.clickOnAddToManifestBtn(orderId); List<String> list =
		  query.getOrdersDetails(orderId); softAssert.assertEquals(list.get(5),"13");
		  
		  List<String> manifest_details = query.getManifestDetails();
		  softAssert.assertEquals(manifest_details.get(3),"open");
		  ExtentTestManager.getTest().info("Manifested status: "+manifest_details.get(3));
		  System.out.println("order_statust_id:" + list.get(5));
		  ExtentTestManager.getTest().info("order_status_id : "  + list.get(5));
		  softAssert.assertEquals(orderId,add_orders.getManifestedOrderId(orderId));
		  
		  add_orders.clickOnManifestCloseBtn(); 
		 
		String pickUp_service_name = add_orders.getPickupServiceName();
			
		 softAssert.assertEquals(pickUp_service_name,"Pickup");
		  
		  softAssert.assertAll(); 
	
	  
	  }
	  
	
	  @Test(priority = 11,dataProvider = "Add_Order_Details", dataProviderClass = TestDataProvider.class, enabled = true) 
	  public void Verify_Add_order_Save_in_draft(String firstname, String lastname, String mobile_no, String email_id,
				String countryname, String address1, String landmark, String address3, String pincode, String city,
				String state,String Invoice_no,String reference_id,String ioss_no,String product_name,String hsn_no,String quantity,String unit_price,String actual_weight,String length,String breadth,String height) {
		  VendorLoginPage vendor_login = new VendorLoginPage();
		  vendor_login.doVendorLogin();
			AddOrderPage add_orders = new AddOrderPage();
			add_orders.clickOnaddorderOption();
			add_orders.addBuyerDetails(firstname, lastname, mobile_no, email_id, countryname, address1, landmark, address3,
					pincode, city, state);
			add_orders.addOrderDetails(Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price);
			add_orders.ClickOnContinueBtn();
			add_orders.addShipmentDetails(actual_weight,length, breadth,height);
			add_orders.selectShippingPartner();
			add_orders.save_in_draft();
			 List<String> orderlist = query.getorderId();
			String orderid = orderlist.get(0);
			String order_statusid = orderlist.get(1);
			String trackingN0 = orderlist.get(2);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(order_statusid, "12");
			  ExtentTestManager.getTest().info("orderid : "  + orderid);
			  ExtentTestManager.getTest().info("trackingN0 : "  + trackingN0);
			  softAssert.assertAll(); 
	  }
	
	  @Test(priority = 12,dataProvider = "Add_Order_Details", dataProviderClass = TestDataProvider.class, enabled = true) 
	  public void cancel_order(String firstname, String lastname, String mobile_no, String email_id,
				String countryname, String address1, String landmark, String address3, String pincode, String city,
				String state,String Invoice_no,String reference_id,String ioss_no,String product_name,String hsn_no,String quantity,String unit_price,String actual_weight,String length,String breadth,String height) {
		  VendorLoginPage vendor_login = new VendorLoginPage();
		  vendor_login.doVendorLogin();
			AddOrderPage add_orders = new AddOrderPage();
			add_orders.clickOnaddorderOption();
			add_orders.addBuyerDetails(firstname, lastname, mobile_no, email_id, countryname, address1, landmark, address3,
					pincode, city, state);
			add_orders.addOrderDetails(Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price);
			add_orders.ClickOnContinueBtn();
			ElementUtil.threadSleep(15000);
			add_orders.addShipmentDetails(actual_weight,length, breadth,height);
			add_orders.selectShippingPartner();
			add_orders.save_in_draft();
			 List<String> orderlist = query.getorderId();
			
			String order_statusid = orderlist.get(1);
			String trackingN0 = orderlist.get(2);
			
			add_orders.ClickOnEditBtn(trackingN0);
			add_orders.ClickOnCancelOrderBtn();
			List<String> cancel_order_status = query.getorderStatusId(trackingN0);
			
			SoftAssert softAssert = new SoftAssert();
	
			  
			  ExtentTestManager.getTest().info("trackingN0 : "  + trackingN0);
			  softAssert.assertEquals(order_statusid, "12");
			  softAssert.assertEquals( cancel_order_status.get(0), "10");
			  ExtentTestManager.getTest().info("Cancel order status id: "  + cancel_order_status);
			  softAssert.assertAll(); 
	  } 
	  
	  // to do
	  @Test(priority = 13,dataProvider = "Add_Order_Details", dataProviderClass = TestDataProvider.class, enabled = false) 
	  public void edit_order_details(String firstname, String lastname, String mobile_no, String email_id,
				String countryname, String address1, String landmark, String address3, String pincode, String city,
				String state,String Invoice_no,String reference_id,String ioss_no,String product_name,String hsn_no,String quantity,String unit_price,String actual_weight,String length,String breadth,String height) {
		  VendorLoginPage vendor_login = new VendorLoginPage();
		  vendor_login.doVendorLogin();
			AddOrderPage add_orders = new AddOrderPage();
			add_orders.clickOnaddorderOption();
			add_orders.addBuyerDetails(firstname, lastname, mobile_no, email_id, countryname, address1, landmark, address3,
					pincode, city, state);
			add_orders.addOrderDetails(Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price);
			
			add_orders.ClickOnContinueBtn();
			
			add_orders.addShipmentDetails(actual_weight,length, breadth,height);
			add_orders.selectShippingPartner();
			add_orders.save_in_draft();
			 List<String> orderlist = query.getorderId();
			
			String order_statusid = orderlist.get(1);
			String trackingN0 = orderlist.get(2);
			
			add_orders.ClickOnEditBtn(trackingN0);
			
			
			SoftAssert softAssert = new SoftAssert();
		    ExtentTestManager.getTest().info("trackingN0 : "  + trackingN0);
			softAssert.assertEquals(order_statusid, "12");
			 
	  } 
	  
	  // to do
	  @Test(priority = 14,dataProvider = "Add_Order_Details", dataProviderClass = TestDataProvider.class, enabled = false) 
	  public void view_order_details(String firstname, String lastname, String mobile_no, String email_id,
				String countryname, String address1, String landmark, String address3, String pincode, String city,
				String state,String Invoice_no,String reference_id,String ioss_no,String product_name,String hsn_no,String quantity,String unit_price,String actual_weight,String length,String breadth,String height) {
		  VendorLoginPage vendor_login = new VendorLoginPage();
		  vendor_login.doVendorLogin();
			AddOrderPage add_orders = new AddOrderPage();
			add_orders.clickOnaddorderOption();
			add_orders.addBuyerDetails(firstname, lastname, mobile_no, email_id, countryname, address1, landmark, address3,
					pincode, city, state);
			add_orders.addOrderDetails(Invoice_no,reference_id,ioss_no,product_name,hsn_no,quantity, unit_price);
			add_orders.ClickOnContinueBtn();
			add_orders.addShipmentDetails(actual_weight,length, breadth,height);
			add_orders.selectShippingPartner();
			add_orders.save_in_draft();
			 List<String> orderlist = query.getorderId();
			
			String order_statusid = orderlist.get(1);
			String trackingN0 = orderlist.get(2);
			
			add_orders.ClickOnViewBtn(trackingN0);
			List<String> delivery_address = add_orders.getDeliveryAddress();
			
			
			
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(delivery_address.get(0),firstname+" "+lastname );
//			softAssert.assertEquals(delivery_address.get(1),firstname+" "+lastname );
//			softAssert.assertEquals(delivery_address.get(2),firstname+" "+lastname );
//			softAssert.assertEquals(delivery_address.get(3),firstname+" "+lastname );
//			softAssert.assertEquals(delivery_address.get(4),firstname+" "+lastname );
//			softAssert.assertEquals(delivery_address.get(5),firstname+" "+lastname );
			
		    ExtentTestManager.getTest().info("trackingN0 : "  + trackingN0);
			softAssert.assertEquals(order_statusid, "12");
			 
	  }    
	 
	  
}
