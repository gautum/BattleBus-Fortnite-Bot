package battleBusDriver.battleBusListeners;

import java.io.IOException;
import java.util.HashMap;

import battleBusDriver.discordTools.DiscordFortniteGuild;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.GuildCreateEvent;
import sx.blah.discord.handle.obj.IGuild;

public class BattleBusDriverGuildCreateListener implements IListener<GuildCreateEvent>{
	
	private IDiscordClient client;
	private HashMap<Long, DiscordFortniteGuild> guilds;
	
	public BattleBusDriverGuildCreateListener(IDiscordClient client, HashMap<Long, DiscordFortniteGuild> guilds) {
		this.client = client;
		this.guilds = guilds;
	}
	
	@Override
	public void handle(GuildCreateEvent event) {
		// TODO Auto-generated method stub
		addGuild(event.getGuild());
	}
	
	private void addGuild(IGuild g) {
		if(!guilds.containsKey(g.getLongID())) {
			DiscordFortniteGuild d = new DiscordFortniteGuild(g.getLongID());
			guilds.put(g.getLongID(), d);
			System.out.println("New guild added: " + g.getLongID());
		}
	}
	
}
