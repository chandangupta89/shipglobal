package utility;



import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;




public class TestDataProvider {
	
	 @DataProvider(name="Add_Order_Details")
		public Iterator<Object[]> BuyerShippingDetailsProvider(Method m) {
			
			switch (m.getName()) {
			case "Place_The_Order": 
				ArrayList<Object[]> BuyerShippingDetails1 = TestData.getAddorderDetails(2,2);
				return BuyerShippingDetails1.iterator();
			case "verify_WalletBalance_Deduction_After_place_order": 
				ArrayList<Object[]> BuyerShippingDetails2 = TestData.getAddorderDetails(3,3);
				return BuyerShippingDetails2.iterator();
				
			case "print_Label_And_verify_Lastmile_AWB": 
				ArrayList<Object[]> BuyerShippingDetails3 = TestData.getAddorderDetails(4,4);
				return BuyerShippingDetails3.iterator();
			case "create_and_Close_manifest": 
				ArrayList<Object[]> BuyerShippingDetails4 = TestData.getAddorderDetails(5,5);
				return BuyerShippingDetails4.iterator();
			case "verify_GST_On_Logistic_Fee": 
				ArrayList<Object[]> BuyerShippingDetails5 = TestData.getAddorderDetails(6,6);
				return BuyerShippingDetails5.iterator();
			case "add_Multiple_Orders": 
				ArrayList<Object[]> BuyerShippingDetails6 = TestData.getAddorderDetails(7,7);
				return BuyerShippingDetails6.iterator();
			case "add_and_remove_Orders": 
				ArrayList<Object[]> BuyerShippingDetails7 = TestData.getAddorderDetails(8,8);
				return BuyerShippingDetails7.iterator();
			case "remove_manifest": 
				ArrayList<Object[]> BuyerShippingDetails8 = TestData.getAddorderDetails(9,9);
				return BuyerShippingDetails8.iterator();
			case "verify_pickup_service_name_as_self_ship": 
				ArrayList<Object[]> BuyerShippingDetails9 = TestData.getAddorderDetails(10,10);
				return BuyerShippingDetails9.iterator();
			case "verify_pickup_service_name_as_Pickup": 
				ArrayList<Object[]> BuyerShippingDetails10 = TestData.getAddorderDetails(11,11);
				return BuyerShippingDetails10.iterator();
			case "Verify_Add_order_Save_in_draft": 
				ArrayList<Object[]> BuyerShippingDetails11 = TestData.getAddorderDetails(12,12);
				return BuyerShippingDetails11.iterator();
				
			case "cancel_order": 
				ArrayList<Object[]> BuyerShippingDetails12 = TestData.getAddorderDetails(13,13);
				return BuyerShippingDetails12.iterator();
				
			case "edit_order_details": 
				ArrayList<Object[]> BuyerShippingDetails13 = TestData.getAddorderDetails(14,14);
				return BuyerShippingDetails13.iterator();
				
			case "view_order_details": 
				ArrayList<Object[]> BuyerShippingDetails14 = TestData.getAddorderDetails(15,15);
				return BuyerShippingDetails14.iterator();
				
			}
			return null;
			
		
		}
	 
	
}
