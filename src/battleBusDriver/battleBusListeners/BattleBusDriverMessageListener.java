package battleBusDriver.battleBusListeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import battleBusDriver.discordTools.DiscordCommand;
import battleBusDriver.discordTools.DiscordCommandToProcess;
import battleBusDriver.discordTools.DiscordFortniteGuild;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

class FortniteMessageListener implements IListener<MessageReceivedEvent>
{
	private IDiscordClient client;
	private HashMap<Long, DiscordFortniteGuild> guilds;
	private static final String IDENTIFIER = "!tcanfort";
	
	public FortniteMessageListener(IDiscordClient client, HashMap<Long, DiscordFortniteGuild> guilds) {
		this.client = client;
		this.guilds = guilds;
	}
	
	@Override
	public void handle(MessageReceivedEvent event) {
		System.out.println("here");
		IMessage message = event.getMessage(); // Gets the message from the event object NOTE: This is not the content of the message, but the object itself
		IChannel channel = message.getChannel(); // Gets the channel in which this message was sent.
		IUser messageAuthor = message.getAuthor();
		IUser guildOwner = channel.getGuild().getOwner();
		long guildID = channel.getGuild().getLongID();
		
		DiscordCommand command = createDiscordCommand(message);
		
		processCommand(command, messageAuthor, guildOwner, guildID);
		
//		EmbedBuilder builder = new EmbedBuilder();
		
	}
	
	private DiscordCommand createDiscordCommand(IMessage message) {
		String[] split = message.getContent().split("\\s+");
		DiscordCommand out = null;
		if(split.length > 0 && split[0].equals(IDENTIFIER)) {
			out = new DiscordCommand(split);
		}
		return out;
	}
	
	private void processCommand(DiscordCommand command, IUser messageAuthor, IUser guildOwner, long guildID) {
		OutputToDiscord output;
		
		switch(command.getCommandToProcess()) {
			case HELP:		output = processHelp(command, messageAuthor, guildOwner, guildID);			break;
			case STATS:		output = processStats(command, messageAuthor, guildOwner, guildID);		break;
			case LEADERS:	output = processLeaders(command, messageAuthor, guildOwner, guildID);		break;
			case LINK:		output = processLink(command, messageAuthor, guildOwner, guildID);			break;
			case UNLINK:	output = processUnlink(command, messageAuthor, guildOwner, guildID);		break;
			case ADD:		output = processAdd(command, messageAuthor, guildOwner, guildID);			break;
			case REMOVE:	output = processRemove(command, messageAuthor, guildOwner, guildID);		break;
			case CHANNEL:	output = processChannel(command, messageAuthor, guildOwner, guildID);		break;
			case CLAIM:		output = processClaim(command, messageAuthor, guildOwner, guildID);		break;
		}
	}
	
	private OutputToDiscord processHelp(DiscordCommand command, IUser messageAuthor, IUser guildOwner, long guildID) {
		return null;
	}
	private OutputToDiscord processStats(DiscordCommand command, IUser messageAuthor, IUser guildOwner, long guildID) {
		return null;
	}
	private OutputToDiscord processLeaders(DiscordCommand command, IUser messageAuthor, IUser guildOwner, long guildID) {
		return null;
	}
	private OutputToDiscord processLink(DiscordCommand command, IUser messageAuthor, IUser guildOwner, long guildID) {
		return null;
	}
	private OutputToDiscord processUnlink(DiscordCommand command, IUser messageAuthor, IUser guildOwner, long guildID) {
		return null;
	}
	private OutputToDiscord processAdd(DiscordCommand command, IUser messageAuthor, IUser guildOwner, long guildID) {
		return null;
	}
	private OutputToDiscord processRemove(DiscordCommand command, IUser messageAuthor, IUser guildOwner, long guildID) {
		return null;
	}
	private OutputToDiscord processChannel(DiscordCommand command, IUser messageAuthor, IUser guildOwner, long guildID) {
		return null;
	}
	private OutputToDiscord processClaim(DiscordCommand command, IUser messageAuthor, IUser guildOwner, long guildID) {
		return null;
	}
	
	private class OutputToDiscord {
		private OutputToDiscordField[] fields;
		private String text;
		public OutputToDiscord(OutputToDiscordField[] fields) {
			this.fields = fields;
		}
		public OutputToDiscord(String text) {
			this.text = text;
		}
	}
	
	private class OutputToDiscordField {
		private String fieldName;
		private String fieldValue;
		private boolean inline;
		public OutputToDiscordField(String fieldName, boolean inline) {
			this.fieldName = fieldName;
			this.inline = inline;
		}
		public void appendFieldValue(String strToAppend) {
			fieldValue = fieldValue + strToAppend + "\n";
		}
		public String getFieldName() {
			return fieldName;
		}
		public String getFieldValue() {
			return fieldValue;
		}
		public boolean getInline() {
			return inline;
		}
	}
				
}

