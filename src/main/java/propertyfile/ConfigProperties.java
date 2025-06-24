package propertyfile;

public class ConfigProperties {
	
	private String url;
	private String headless;
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setHeadless(String headless) {
		this.headless=headless;
		
		
	}
	
	
	public String getHeadless() {
		return headless;
	}
	

	
	public String toString() {
		return "ConfigProperties [url=" + url + "]";
	}
		
}
