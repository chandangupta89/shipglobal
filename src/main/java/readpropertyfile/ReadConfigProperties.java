package readpropertyfile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigProperties {

	public static ConfigProperties loadconfigproperties() {
		String propertyfilepath = "./config.properties";
		final ConfigProperties config_prop = new ConfigProperties();
		try {

			Properties prop = new Properties();

			FileInputStream fis1 = new FileInputStream(propertyfilepath);

			try {
				prop.load(fis1);

				String shipglobal_url = prop.getProperty("url");

				config_prop.setUrl(shipglobal_url);

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return config_prop;
	}
}
