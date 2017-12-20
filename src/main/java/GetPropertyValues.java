import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertyValues {

	static InputStream inputStream;
	
	public GetPropertyValues() throws IOException {
//		Properties prop = new Properties();
	}
	
//	public GetPropertyValues() throws MalformedURLException {}
	
	
	public static Properties getPropValues() throws IOException {
		Properties prop = new Properties();
		try {
			String propFileName = "config.properties";
			inputStream = GetPropertyValues.class.getClassLoader().getResourceAsStream(propFileName);
			
			if (inputStream != null) {
				prop.load(inputStream);
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return prop;
	}
	
	
}
