READ_ME
CREATED BY Liam Fladhammer
github.com/liamfladhammer


For testing purposes, use the summoner name “cb”
The program requires an API key, generally it should be protected but due to low volume and it being a test project, I will leave it as is. The current rate is 10 requests for every 10 seconds, however the static API ignores the rates.


JSON SETUP:
in order to run the program, you need the javax.json extension which can be found here
https://jsonp.java.net/download.html
rename the jar file to json.jar for easy running of the program and put it in your /lib folder


FULL API REFERENCE:
https://developer.riotgames.com/api/methods


EASY ACCESS USING THE COMMAND PROMPT:
Navigate using cd and ls(mac, linux) or dir(windows) to get to your project folder

WINDOWS:
javac -cp .;lib/json.jar src/ApiElement.java
javac -cp .;lib/json.jar src/SummonerData.java
javac -cp .;lib/json.jar src/StaticData.java
javac -cp .;lib/json.jar src/CMasteryData.java
javac -cp .;lib/json.jar src/CommandMenu.java

java -cp .;lib/json.jar src.CommandMenu



MAC:
javac -cp .:lib/json.jar src/ChampionMastery.java
java -cp .:lib/json.jar src.ChampionMastery

javac -cp .:lib/json.jar src/RetrieveChampions.java
java -cp .:lib/json.jar src.RetrieveChampions

javac -cp .:lib/json.jar src/C.java

javac -cp .:lib/json.jar src/PlayerMastered.java
java -cp .:lib/json.jar src.PlayerMastered