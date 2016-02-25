package src;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.json.*;

public class CommandMenu{
	
	private boolean quit;
	private String summoner;

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

	public boolean quit(){
		return quit;
	}

	public void MainMenu() throws MalformedURLException, IOException{
		System.out.println();
		System.out.println("Main Menu");
		System.out.println("/1/  Run");
		System.out.println("/2/  Set Variables");
		System.out.println("/5/  Quit");
		System.out.print("/");

		Scanner scan = new Scanner(System.in);
		String option=scan.next();

		if(option.equals("5")){
			quit=true;
		}
		else if(option.equals("2")){
			ChangeVariables();
		}
		else if(option.equals("1")){
			Run();
			DataMenu();
		}
		else{
			System.out.println("Bad Input");
		}
	}

	public void ChangeVariables() throws MalformedURLException, IOException{
		System.out.println();
		System.out.println("Main Menu > Set Variables");
		System.out.println("/1/  Set Region ["+ApiElement.region()+"]");
		System.out.println("/2/  Set Api Key ["+ApiElement.previewKey()+"....]");
		System.out.println("/5/  Exit Variable Menu");
		System.out.print("/");

		Scanner scan = new Scanner(System.in);
		String option=scan.next();
		
		if(option.equals("5")){
			MainMenu();
		}
		else if(option.equals("1")){
			System.out.println("\nEnter a valid region");
			String region=scan.next();
			ApiElement.region(region);
			//print out if a valid region or not
			ChangeVariables();
		}
		else if(option.equals("2")){
			System.out.println("\nEnter a valid api key");
			String key=scan.next();
			ApiElement.apiKey(key);
			//print out if this is a valid api key or not
			ChangeVariables();
		}
		else{
			System.out.println("Bad Input");
			ChangeVariables();
		}
	}

	public void Run() throws MalformedURLException, IOException{
		System.out.println("\nGathering Data...");
		StaticData.getChampionData();
		StaticData.getChampionImages();
		Scanner scan=new Scanner(System.in);
		
		System.out.println("\nEnter Summoner Name");
		summoner = scan.nextLine();
		CMasteryData.getMastery(summoner.replace(" ", ""));
	}

	//the data menu used after the run
	public void DataMenu() throws MalformedURLException, IOException{
		System.out.println();
		System.out.println("Main Menu > Run ");
		System.out.println("/1/  Search Champion");
		System.out.println("/2/  Total Mastery Score");
		System.out.println("/3/  Dump Data");
		System.out.println("/5/  Exit Run Menu");
		System.out.print("/");

		Scanner scan = new Scanner(System.in);
		String option=scan.next();
		
		if(option.equals("5")){
			MainMenu();
		}
		else if(option.equals("1")){
			System.out.println("\nEnter a valid champion name");
			String champ=scan.next();
			//MasteryFor(champion);
			DataMenu();
			
		}
		else if(option.equals("2")){
			System.out.print("\nTotal Mastery Score:  ");
			//TotalMasteryScore();
			System.out.println();
			DataMenu();
		}
		else if(option.equals("3")){
			DumpMenu();
		}
		else{
			System.out.println("Bad Input");
			DataMenu();
		}
	}

	public void DumpMenu() throws MalformedURLException, IOException{
		System.out.println();
		System.out.println("Main Menu > Run > Dump Data");
		System.out.println("/1/  Dump by Champion Name");
		System.out.println("/2/  Dump by Mastery Points");
		System.out.println("/5/  Back");
		System.out.print("/");

		Scanner scan = new Scanner(System.in);
		String option=scan.next();
		
		if(option.equals("5")){
			DataMenu();
		}
		else if(option.equals("1")){
			System.out.println("\nDumping Data... by Champion Name");
			DumpDataByChampion();
			DumpMenu();
			
		}
		else if(option.equals("2")){
			System.out.println("\nDumping Data... by Mastery Points");
			DumpDataByPoints();
			DumpMenu();
		}
		else{
			System.out.println("Bad Input");
			DumpMenu();
		}
	}

	public void DumpDataByChampion() throws MalformedURLException, IOException{
		// look below, however need to sort it somehow
	}

	public void DumpDataByPoints() throws MalformedURLException, IOException{
		JsonReader jr = Json.createReader(new FileReader("data/championData.json"));
		JsonObject data = jr.readObject().getJsonObject("data");

		jr = Json.createReader(new FileReader("data/player/"+summoner.replace(" ", "")+".json"));
		JsonArray playerMastery = jr.readArray();

		for(int i=0; i<playerMastery.size(); i++){		
			JsonObject champ = playerMastery.getJsonObject(i);
			String mChampion = StaticData.getChampionName(champ.getJsonNumber("championId").intValue()); 
			long mPoints = champ.getJsonNumber("championPoints").longValue();
			
			System.out.println(mPoints+ "  -  "+ mChampion);
		}
	}
}

