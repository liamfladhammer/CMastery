package src;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.json.*;

public class CommandMenu{
	
	private boolean quit;

	public CommandMenu(){
		quit=false;
	}

	public static void main(String []args) throws MalformedURLException, IOException{
		ApiElement.apiKey("0c0cba27-7323-4093-90fa-6ead6333538b");
		ApiElement.region("NA");

		CommandMenu cm=new CommandMenu();

		while(!cm.quit()){
			cm.MainMenu();
		}
	}

	public void MainMenu() throws MalformedURLException, IOException{
		System.out.println();
		System.out.println("/1/  Run");
		System.out.println("/2/  Set Variables");
		System.out.println("/5/  Quit");
		System.out.print("/");

		Scanner scan = new Scanner(System.in);
		String option=scan.next();

		if(option.contains("5")){
			quit=true;
		}
		else if(option.contains("2")){
			ChangeVariables();
		}
		else if(option.contains("1")){
			Run();
		}
		else{
			System.out.println("Bad Input");
		}
	}

	public void ChangeVariables() throws MalformedURLException, IOException{
		System.out.println();
		System.out.println("/1/  Set Region ["+ApiElement.region()+"]");
		System.out.println("/2/  Set Api Key ["+ApiElement.apiKey()+"]");
		System.out.println("/5/  Exit Variable Menu");
		System.out.print("/");

		Scanner scan = new Scanner(System.in);
		String option=scan.next();
		
		if(option.contains("")){
		}
		else if(option.contains("1")){
			System.out.println("\nEnter a valid region");
			String region=scan.next();
			ApiElement.apiKey(region);
			//print out if a valid region or not
		}
		else if(option.contains("2")){
			System.out.println("\nEnter a valid api key");
			String key=scan.next();
			ApiElement.apiKey(key);
			//print out if this is a valid api key or not
		}
		else{
			System.out.println("Bad Input");
			ChangeVariables();
		}
	}

	public void Run() throws MalformedURLException, IOException{
		System.out.println("Gathering Data...");
		StaticData.getChampionData();
		StaticData.getChampionImages();
		Scanner scan=new Scanner(System.in);
		
		System.out.println("\nEnter Summoner Name");
		String player = scan.nextLine();
		CMasteryData.getMastery(player.replace(" ", ""));

		JsonReader jr = Json.createReader(new FileReader("data/championData.json"));
		JsonObject data = jr.readObject().getJsonObject("data");

		jr = Json.createReader(new FileReader("data/player/"+player.replace(" ", "")+".json"));
		JsonArray playerMastery = jr.readArray();

		for(int i=0; i<playerMastery.size(); i++){		
			JsonObject champ = playerMastery.getJsonObject(i);
			int mLevel = +champ.getJsonNumber("championLevel").intValue();
			String mChampion = StaticData.getChampionName(champ.getJsonNumber("championId").intValue()); 
			long mPoints = champ.getJsonNumber("championPoints").longValue();
			
			System.out.println( mChampion +"  Level: "+ mLevel +"  Points: "+ mPoints);
		}
	}

	public boolean quit(){
		return quit;
	}
}

