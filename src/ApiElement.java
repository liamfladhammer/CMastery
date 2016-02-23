package src;

public abstract class ApiElement{
	
	public static String apiKey;
	public static String region;

	//sets the api key
	public static void apiKey(String key){
		apiKey=key;
	}
	//ges the api key
	public static String apiKey(){
		return apiKey;
	}

	public static void region(String reg){
		region=reg;
	}

	public static String region(){
		return region;
	}

	public static boolean hasKey(){
		if(apiKey==null)
			return false;
		return true;
	}
	
	//~isReady method... should be defined in all children that sees if the key and region are set
}
