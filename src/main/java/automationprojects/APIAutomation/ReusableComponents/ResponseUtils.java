package automationprojects.APIAutomation.ReusableComponents;

import io.restassured.path.json.JsonPath;

public class ResponseUtils {
	
	
	public static Object getKeyValue(String response, String key)
	{
		System.out.println(response);
		JsonPath path = new JsonPath(response);
		String keyValue=path.getString(key);
		return keyValue;
		
	}

}
