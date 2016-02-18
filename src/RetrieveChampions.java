package src;

import java.io.*;
import java.net.*;
import java.util.Iterator;
import javax.json.*;

//~try and catch errors
public class RetrieveChampions{
		
	static String apiKey=C.apiKey();
	static String version;
	
	public static void main(String []args) throws MalformedURLException, IOException{
		getChampionData();
		getChampionImages();
		System.out.println("RETRIEVECHAMPIONS COMPLETED");
	}

	//Gets championData and writes it to a .json file
	public static void getChampionData() throws MalformedURLException, IOException{ 
		String url="https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion?champData=image&api_key="+apiKey;
		new File("data").mkdirs();
		InputStream is=new URL(url).openStream();
		OutputStream os=new FileOutputStream("data/championData.json");
		byte[] b = new byte[2048];
		int length;
		while((length=is.read(b)) != -1) {
			os.write(b, 0, length);
		}	
		System.out.println("data/championData.json CREATED");
	}

	//Downloads all images for the champions based off of the championData.json file
	public static void getChampionImages() throws MalformedURLException, IOException{
		File fpath = new File("data/img");
		fpath.mkdirs();
		
		JsonReader jr = Json.createReader(new FileReader("data/championData.json"));
		JsonObject cData = jr.readObject();
		version = cData.getJsonString("version").getString();
		JsonObject data = cData.getJsonObject("data");
		Iterator<String> keys = data.keySet().iterator();
		while(keys.hasNext()){
			JsonObject champion = data.getJsonObject(keys.next());
			String name = champion.getJsonString("key").toString().replace("\"","");
			String imgurl = "http://ddragon.leagueoflegends.com/cdn/"+version+"/img/champion/"+name+".png";

			if(!new File(fpath+"/"+name+".png").exists()){
				InputStream is=new URL(imgurl).openStream();	
				OutputStream os=new FileOutputStream(fpath+"/"+name+".png");
				byte[] b = new byte[2048];
				int length;
				while((length=is.read(b)) != -1) {
	    			os.write(b, 0, length);
				}			
				System.out.println(name+".png DOWNLOADED");
			}
			else
				System.out.println(name+".png ALREADY EXISTS");
		}
	}
}