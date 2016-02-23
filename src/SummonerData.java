package src;

import java.io.*;
import java.net.*;
import java.util.Iterator;
import javax.json.*;


//SUMMONERS API
//~try and catch errors
public class SummonerData extends ApiElement{
	
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
}