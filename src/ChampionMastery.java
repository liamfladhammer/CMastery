package src;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.json.*;

//~try and catch errors
public class ChampionMastery{
	
	static String apiKey=C.apiKey();
	static String platformID="NA1";

	public static void main(String []args) throws MalformedURLException, IOException{	
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Summoner Name");
		String str = scan.nextLine().replace(" ", "");
		getMastery(str);
		System.out.println("CHAMPIONMASTERY COMPLETED");
	}
	  
	//Gets all the champions with mastery for a specific player
	public static void getMastery(String summName) throws MalformedURLException, IOException{
		long summId = getSummonerId(summName);
		new File("data").mkdirs(); //makes the data folder if it doesn't exist
		String url="https://na.api.pvp.net/championmastery/location/"
			+ platformID
			+ "/player/"
			+ summId
			+ "/champions?api_key="
			+ apiKey;

		InputStream is=new URL(url).openStream();
		OutputStream os=new FileOutputStream("data/"+summName+"_mastery.json");
		byte[] b = new byte[2048];
		int length;
		while((length=is.read(b)) != -1) {
    		os.write(b, 0, length);
		}	
		System.out.println(summName+"_mastery.json CREATED");
	}
	
	//Gets a summonerId that matches the summName
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
}