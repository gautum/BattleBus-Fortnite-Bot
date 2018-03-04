package battleBusDriver.battleBusBot;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.xilixir.fortniteapi.v2.Configuration;

import battleBusDriver.battleBusListeners.BattleBusDriverGuildCreateListener;
import battleBusDriver.battleBusListeners.BattleBusDriverGuildLeaveListener;
import battleBusDriver.battleBusListeners.BattleBusDriverReadyListener;
import battleBusDriver.discordTools.DiscordCredentials;
import battleBusDriver.discordTools.DiscordFortniteGuild;
import battleBusDriver.fortniteapiTools.FortniteApiRequests;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

public class BattleBusDriverBot {
		
	public static BattleBusDriverBot BOT_INSTANCE; // Singleton instance of the bot.
	public static FortniteApiRequests api;
	public static final Logger logger = Logger.getLogger(BattleBusDriverBot.class.getName());
	public static HashMap<Long, DiscordFortniteGuild> guilds;
	public IDiscordClient client; // The instance of the discord client.
	
	public static void main(String [] args) {
		setUpLogger();
		
		Configuration discordLogin = new Configuration("discord_login", DiscordCredentials.class);
		if(!tryCreateAPI() || !tryLogin(discordLogin.read())) {
			logger.info("Unable to login or make connection to Fortnite API.");
			logger.info("Exiting...");
			return;
		}
		logger.finest("Discord Bot object has been created");
		logger.fine("Adding GuildCreateListener to client");
	}
	
	public BattleBusDriverBot(IDiscordClient client) {
		this.client = client; // Sets the client instance to the one provided
		
		guilds = new HashMap<Long, DiscordFortniteGuild>();
		
		logger.finest("RegisteringListeners to client");
		client.getDispatcher().registerListener(new BattleBusDriverGuildCreateListener(client, guilds));
		logger.fine("FortniteGuildCreateListener registered");
		client.getDispatcher().registerListener(new BattleBusDriverGuildLeaveListener(client, guilds));
		logger.fine("FortniteGuildLeaveListener registered");
		client.getDispatcher().registerListener(new BattleBusDriverReadyListener(client));
		logger.fine("FortniteReadyListener registered");
	}
	
	private static void setUpLogger() {
		try {
			logger.setLevel(Level.FINEST);
			FileHandler handler;
			handler = new FileHandler("./logs.txt");
			handler.setFormatter(new SimpleFormatter());
			logger.addHandler(handler);
			logger.fine("Added file handler to logger");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static boolean tryCreateAPI() {
		try {
			logger.finest("Attempting to make connection to Fortnite API");
			api = new FortniteApiRequests();
			//logger.info("Api setup successfully");
			logger.finest("Connection created successfully");
			return true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "Error while making connection to Fortnite API");
			return false;
		}
	}
	
	private static boolean tryLogin(DiscordCredentials discordCredentials) {
		try {
			logger.finest("Attempting to login the Discord bot");
			BOT_INSTANCE = login(discordCredentials.getToken());
			logger.finest("Discord bot logged in successfully");
			return true;
		}
		catch(Exception e) {
			logger.severe("Error while logging into bot");
			return false;
		}
	}
	
	private static BattleBusDriverBot login(String token) throws Exception{
		BattleBusDriverBot bot = null; // Initializing the bot variable

		ClientBuilder builder = new ClientBuilder(); // Creates a new client builder instance
		builder.withToken(token); // Sets the bot token for the client
		IDiscordClient client = builder.login(); // Builds the IDiscordClient instance and logs it in
		bot = new BattleBusDriverBot(client); // Creating the bot instance
		return bot;
	}
	
}


