package src;

import java.io.*;
import java.net.*;
import java.util.Iterator;
import javax.json.*;

public class C{
	private static String apiKey = "0c0cba27-7323-4093-90fa-6ead6333538b"; 

	public static String apiKey(){
		return apiKey;
	}

	public static long getSummonerId(String summName) throws MalformedURLException, IOException{
		String s="https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/"
			+summName
			+"?api_key="
			+apiKey;

		InputStream is=new URL(s).openStream(); 
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		String str="";
		while(br.ready())
			str+=br.readLine();
		JsonReader jr = Json.createReader(new StringReader(str));
		JsonObject jobj = jr.readObject();
		return jobj.getJsonObject(summName.toLowerCase()).getJsonNumber("id").longValue();
	}

	public static String getSummonerName(Long summId) throws MalformedURLException, IOException{
		String s="https://na.api.pvp.net/api/lol/na/v1.4/summoner/"
			+summId
			+"?api_key="
			+apiKey;

		InputStream is=new URL(s).openStream(); 
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		String str="";
		while(br.ready())
			str+=br.readLine();
		JsonReader jr = Json.createReader(new StringReader(str));
		JsonObject jobj = jr.readObject();
		return jobj.getJsonObject(""+summId).getString("name");
	}

	//SLOOOOOOW - uses static api
	// public static String getChampionName(int championId) throws MalformedURLException, IOException{
	// 	String s = "https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion/"
	// 	+championId
	// 	+"?api_key="
	// 	+apiKey;

	// 	InputStream is=new URL(s).openStream(); 
	// 	BufferedReader br=new BufferedReader(new InputStreamReader(is));
	// 	String str="";
	// 	while(br.ready())
	// 		str+=br.readLine();
	// 	JsonReader jr = Json.createReader(new StringReader(str));
	// 	JsonObject jobj = jr.readObject();
	// 	return jobj.getString("name");
	// }

	//FAST - uses championData.json
	public static String getChampionName(int championId) throws IOException{
		JsonReader jr = Json.createReader(new FileReader("data/championData.json"));
		JsonObject data = jr.readObject().getJsonObject("data");
		Iterator<String> keys = data.keySet().iterator();
		while(keys.hasNext()){
			JsonObject c = data.getJsonObject(keys.next());
			if(c.getInt("id")==championId)
				return c.getString("name");
		}
		return "invalid id";
	}
}