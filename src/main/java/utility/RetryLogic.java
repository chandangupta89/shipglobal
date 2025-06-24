package utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryLogic implements IRetryAnalyzer {
	
	int counter=0;
	int retryLimit=3;
	
	public boolean retry(ITestResult result) {
		
		if(counter<retryLimit) {
			counter++;
			return true;
		}
		return false;
	}

}
