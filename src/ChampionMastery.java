package src;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.json.*;

//CHAMPION MASTERY
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
		long summId = C.getSummonerId(summName);
		new File("data/player").mkdirs(); //makes the data folder if it doesn't exist
		String url="https://na.api.pvp.net/championmastery/location/"
			+ platformID
			+ "/player/"
			+ summId
			+ "/champions?api_key="
			+ apiKey;

		InputStream is=new URL(url).openStream();
		OutputStream os=new FileOutputStream("data/player/"+summName+".json");
		byte[] b = new byte[2048];
		int length;
		while((length=is.read(b)) != -1) {
    		os.write(b, 0, length);
		}	
		//System.out.println(summName+"_mastery.json CREATED");
	}
}