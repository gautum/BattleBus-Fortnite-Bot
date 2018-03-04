package battleBusDriver.battleBusListeners;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RateLimitException;

public class BattleBusDriverReadyListener implements IListener<ReadyEvent>
{
	private IDiscordClient client;
	public BattleBusDriverReadyListener(IDiscordClient client) {
		this.client = client;
	}
	
	@Override
	public void handle(ReadyEvent event) {
		try {
			client.changeUsername("BattleBusDriver Driver"); // Changes the bot's username
			//this.client.changeAvatar(Image.forFile(new File("picture.png"))); // Changes the bot's profile picture
			client.online();
			client.changePlayingText("Looking for wins");
		} catch (RateLimitException | DiscordException e) { // An error occurred
			e.printStackTrace();
		}
	}
}