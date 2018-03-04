package battleBusDriver.fortniteapiTools;

import java.io.IOException;

import com.xilixir.fortniteapi.v2.Configuration;
import com.xilixir.fortniteapi.v2.Credentials;
import com.xilixir.fortniteapi.v2.FortniteAPI;
import com.xilixir.fortniteapi.v2.Stats;
import com.xilixir.fortniteapi.v2.Epic.EpicLookup;


public class FortniteApiRequests {
	
	private FortniteAPI api;
	
	public static void main(String[] args){
        try {
        	FortniteApiRequests apiRequests = new FortniteApiRequests();
			System.out.println(apiRequests.getStatsForUser("frizmuffin").getSquadWinRatio());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public FortniteApiRequests() throws IOException{
		Configuration login = new Configuration("epic_login", Credentials.class);
        Credentials credentials = login.read();
        api = new FortniteAPI(credentials);
        api.authenticate();
	}
	
	public Stats getStatsForUser(String username) throws IOException {
		EpicLookup lookup = api.getUserInfo(username);
        return api.getStats(lookup.getId());
	}
	
	public String getIDForUser(String username) throws IOException {
		return api.getUserInfo(username).getId();
	}
}
