package utility;

import java.util.ArrayList;

public class TestData {

	static ExcelUtility reader;

	public static ArrayList<Object[]> getAddorderDetails(int startingrowNum, int endingrowNum) {

		/*
		 * Test Data for sign UP for town planner
		 */

		ArrayList<Object[]> BuyerShippingDetails = new ArrayList<Object[]>();
		try {
			reader = new ExcelUtility("./TestData/TestData_VendorPanel.xlsx");

		} catch (Exception e) {
			e.printStackTrace();

		}

		for (int rowNum = startingrowNum; rowNum <= endingrowNum; rowNum++) {
			// buyer shipping details
			String first_name = reader.getCellData("Buyer_Shipping _Details", "first_name", rowNum);
			String last_name = reader.getCellData("Buyer_Shipping _Details", "last_name", rowNum);
			String mobile_no = reader.getCellData("Buyer_Shipping _Details", "mobile_no", rowNum);
			String email_id = reader.getCellData("Buyer_Shipping _Details", "email_id", rowNum);
			String country_name = reader.getCellData("Buyer_Shipping _Details", "country_name", rowNum);
			String address_1 = reader.getCellData("Buyer_Shipping _Details", "address_1", rowNum);
			String landmark = reader.getCellData("Buyer_Shipping _Details", "landmark", rowNum);
			String address_3 = reader.getCellData("Buyer_Shipping _Details", "address_3", rowNum);
			String pincode = reader.getCellData("Buyer_Shipping _Details", "pincode", rowNum);
			String city = reader.getCellData("Buyer_Shipping _Details", "city", rowNum);
			String state = reader.getCellData("Buyer_Shipping _Details", "state", rowNum);
			// order details
			String Invoice_no = reader.getCellData("Order_Details", "Invoice_no", rowNum);
			String reference_id = reader.getCellData("Order_Details", "reference_id", rowNum);
			String ioss_no = reader.getCellData("Order_Details", "ioss_no", rowNum);
			String product_name = reader.getCellData("Order_Details", "product_name", rowNum);
			String hsn_no = reader.getCellData("Order_Details", "hsn_no", rowNum);
			String quantity = reader.getCellData("Order_Details", "quantity", rowNum);
			String unit_price = reader.getCellData("Order_Details", "unit_price", rowNum);
			// Shipment_Details
			String actual_weight = reader.getCellData("Shipment_Details", "actual_weight (kg)", rowNum);
			String length = reader.getCellData("Shipment_Details", "length cm", rowNum);
			String breadth = reader.getCellData("Shipment_Details", "breadth cm", rowNum);
			String heigth = reader.getCellData("Shipment_Details", "heigth cm", rowNum);

			Object ob[] = { first_name, last_name, mobile_no, email_id, country_name, address_1, landmark, address_3,
					pincode, city, state, Invoice_no, reference_id, ioss_no, product_name, hsn_no, quantity, unit_price,
					actual_weight, length, breadth, heigth };
			BuyerShippingDetails.add(ob);

		}

		return BuyerShippingDetails;

	}

}
