package src;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.json.*;

public class PlayerMastered{
	
	public static void main(String []args) throws MalformedURLException, IOException{
		RetrieveChampions.getChampionData();
		RetrieveChampions.getChampionImages();
		
		Scanner scan=new Scanner(System.in);
		System.out.println("\nEnter Summoner Name");
		String player = scan.nextLine();
		ChampionMastery.getMastery(player.replace(" ", ""));
		
		JsonReader jr = Json.createReader(new FileReader("data/championData.json"));
		JsonObject data = jr.readObject().getJsonObject("data");

		jr = Json.createReader(new FileReader("data/player/"+player.replace(" ", "")+".json"));
		JsonArray playerMastery = jr.readArray();

		String f = "data/"+player.replace(" ", "").toLowerCase()+"_simplemastery.txt";
		BufferedWriter bw=new BufferedWriter(new FileWriter(f));
		for(int i=0; i<playerMastery.size(); i++){		
			JsonObject champ = playerMastery.getJsonObject(i);
			int mLevel = +champ.getJsonNumber("championLevel").intValue();
			String mChampion = C.getChampionName(champ.getJsonNumber("championId").intValue()); 
			long mPoints = champ.getJsonNumber("championPoints").longValue();
			
			System.out.println( mChampion +"  "+ mLevel +"  "+ mPoints);
			bw.write(mChampion+"  "+mPoints+"  "+mLevel);
			bw.newLine();
		}
		bw.flush();
	}
}

