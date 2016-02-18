package src;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.json.*;

public class PlayerMastered(){
	
	public static void main(String []args) throws MalformedURLException, IOException{
		RetrieveChampions.getChampionData();
		RetrieveChampions.getChampionImages();
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Summoner Name");
		String player = scan.nextLine()
		ChampionMastery.getMastery(player.replace(" ", ""));
		
		JsonReader jr = Json.createReader(new FileReader("data/championData.json"));
		JsonObject data = jr.readObject().getJsonObject("data");

		jr = Json.createReader(new FileReader("data/"+player.replace(" ", "")+"_mastery.json")
		JsonArray playerMastery = jr.readArray();

		System.out.println("\n"+player+" Champion Mastery")
		for(int i=0; i<playerMastery.size(); i++){
			//write a method in C to get champion name from an Id
			System.out.println()
		}
	}
}