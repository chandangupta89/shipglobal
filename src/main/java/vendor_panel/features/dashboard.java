package vendor_panel.features;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utility.ElementUtil;

public class dashboard {
	
	private By menu_list=By.xpath("//a[@class='mb-3 py-3 text-gray']");
	
	public void clickOnMenuOption(String menu_name) {
		List<WebElement> list = ElementUtil.findelements(menu_list);
		int count =list.size();
		System.out.println(count);
		for(int i=0;i<count;i++) {
			String attribute_name = list.get(i).getAttribute("href");
			
			System.out.println(attribute_name);
			
			 if(attribute_name.contains(menu_name)) {
				 
			list.get(i).click();
			 }	
			
			
		}
		
		
	}

}
