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
		System.out.println("Enter Summoner Name");
		String player = scan.nextLine();
		ChampionMastery.getMastery(player.replace(" ", ""));
		
		JsonReader jr = Json.createReader(new FileReader("data/championData.json"));
		JsonObject data = jr.readObject().getJsonObject("data");

		jr = Json.createReader(new FileReader("data/"+player.replace(" ", "")+"_mastery.json"));
		JsonArray playerMastery = jr.readArray();

		System.out.println(player+" Champion Mastery");
		for(int i=0; i<playerMastery.size(); i++){		
			JsonObject champ = playerMastery.getJsonObject(i);
			String mChampion = ""+champ.getJsonNumber("championId").longValue(); //champion id to name
			String mPoints = ""+champ.getJsonNumber("championPoints").longValue();
			String mLevel = ""+champ.getJsonNumber("championLevel").intValue();
			System.out.println(mChampion+"\tPoints: "+mPoints+"\tLevel: "+mLevel);
		}
	}
}

//https://developer.riotgames.com/api/methods#!/1055/3622