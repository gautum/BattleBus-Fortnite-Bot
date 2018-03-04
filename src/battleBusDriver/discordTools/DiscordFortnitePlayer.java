package battleBusDriver.discordTools;

import battleBusDriver.fortniteTools.FortnitePlayer;

public class DiscordFortnitePlayer extends FortnitePlayer{
	
	private long discordID;
	
	public DiscordFortnitePlayer(String username, String epicID, long discordID) {
		super(username, epicID);
		// TODO Auto-generated constructor stub
		this.discordID = discordID;
	}
	public long getDiscordID() {
		return discordID;
	}

}
