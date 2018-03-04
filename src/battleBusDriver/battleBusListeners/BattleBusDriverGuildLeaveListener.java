package battleBusDriver.battleBusListeners;

import java.util.HashMap;

import battleBusDriver.discordTools.DiscordFortniteGuild;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.GuildLeaveEvent;
import sx.blah.discord.handle.obj.IGuild;

public class BattleBusDriverGuildLeaveListener implements IListener<GuildLeaveEvent>{

	private IDiscordClient client;
	private HashMap<Long, DiscordFortniteGuild> guilds;
	
	public BattleBusDriverGuildLeaveListener(IDiscordClient client, HashMap<Long, DiscordFortniteGuild> guilds) {
		this.client = client;
		this.guilds = guilds;
	}
	
	@Override
	public void handle(GuildLeaveEvent event) {
		// TODO Auto-generated method stub
		removeGuild(event.getGuild());
	}
	
	private void removeGuild(IGuild g) {
		if(guilds.containsKey(g.getLongID())) {
			guilds.remove(g.getLongID());
			System.out.println("Guild removed: " + g.getLongID());
		}
	}

}
