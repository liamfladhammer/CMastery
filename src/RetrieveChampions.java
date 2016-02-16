package src;

import java.io.*;
import java.net.*;
import java.util.Iterator;
import javax.json.*;

public class RetrieveChampions{
		
	static String apiKey="0c0cba27-7323-4093-90fa-6ead6333538b";
	static String version="6.3.1";
	
	public static void main(String []args) throws MalformedURLException, IOException{
		getChampionData();
		getChampionImages();
		System.out.println("RETRIEVECHAMPIONS COMPLETED");
	}

	//Gets championData and writes the champion name and id to a .txt file
	//~redo this method to get the json files rather than just the champion name and id
	public static void getChampionData() throws MalformedURLException, IOException{ 
		if(!new File("data/championData.txt").exists()){
			String url="https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion?champData=image&api_key="+apiKey;
			InputStream is=new URL(url).openStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			String str="";
			while(br.ready())
				str+=br.readLine();	
			new File("data").mkdirs();
			BufferedWriter wr = new BufferedWriter(new FileWriter("data/championData.txt"));
			JsonReader jr = Json.createReader(new StringReader(str));
			JsonObject jobj = jr.readObject().getJsonObject("data");
			Iterator<String> keys = jobj.keySet().iterator();
			while(keys.hasNext()){
				JsonObject j = jobj.getJsonObject(keys.next());
				String k=j.getJsonString("key").toString().replace("\"","");
				str=(k+" "+j.getInt("id")).trim();
				System.out.println(str);
				wr.write(str); 
				wr.newLine();
			}
			wr.flush();
		}
		else
			System.out.println("championData.txt ALRAEDY EXISTS");
	}

	//Downloads all images for the champions based off of the championData.txt file
	//~after redoing the getChampionData, adjust this to iterate through and get the keys
	public static void getChampionImages() throws MalformedURLException, IOException{
		File fpath = new File("data/img");
		fpath.mkdirs();
		BufferedReader br=new BufferedReader(new FileReader("data/championData.txt"));
		byte[] b = new byte[2048];
		while(br.ready()){
			String[] t=br.readLine().split("\\s+");;
			String name=t[0];
			String imgurl = "http://ddragon.leagueoflegends.com/cdn/"+version+"/img/champion/"+name+".png";
			
			if(!new File(fpath+"/"+name+".png").exists()){
				InputStream is=new URL(imgurl).openStream();	
				OutputStream os=new FileOutputStream(fpath+"/"+name+".png");
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