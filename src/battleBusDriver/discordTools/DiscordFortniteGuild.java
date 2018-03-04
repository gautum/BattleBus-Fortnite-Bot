package battleBusDriver.discordTools;

import java.util.HashMap;

import battleBusDriver.fortniteTools.FortnitePlayer;


public class DiscordFortniteGuild {
	private long guildID;
	private HashMap<Long, FortnitePlayer> players;
	private int numPlayers;
	private long channelID;
	
	
	public DiscordFortniteGuild(long guildID) {
		this.guildID = guildID;
		players = new HashMap<Long, FortnitePlayer>();
		numPlayers = 0;
		channelID = -1;
	}
	
	public FortnitePlayer getPlayer(long playerID) {
		return players.get(playerID);
	}
	
	public HashMap<Long, FortnitePlayer> getPlayers() {
		return players;
	}
	
	public void addPlayer(long playerID, FortnitePlayer p) {
		if(!containsPlayer(playerID)) {
			players.put(playerID, p);
			numPlayers++;
		}
	}
	
	public boolean containsPlayer(long playerID) {
		if(players.keySet().contains(playerID)) {
			return true;
		}
		return false;
	}
	
	public void removePlayer(long playerID) {
		if(containsPlayer(playerID)) {
			players.remove(playerID);
			numPlayers--;
		}
	}
	
	public void setChannelID(long channelID) {
		this.channelID = channelID;
	}
	
	public long getChannelID() {
		return channelID;
	}
	
	public long getGuildID() {
		return guildID;
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}
	
}
