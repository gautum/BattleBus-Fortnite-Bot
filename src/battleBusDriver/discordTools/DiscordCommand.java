package battleBusDriver.discordTools;

import java.util.Arrays;

public class DiscordCommand {
	private String[] arguments;
	
	private static final String[] OWNER_REQUIRED_COMMANDS = new String[]{
		"channel",
		"add",
		"remove"
	};
	private static final String[] LINKED_ACCOUNT_REQUIRED_COMMANDS = new String[]{
		"stats",
		"unlink"
	};
	private static final String[] LINKED_ACCOUNT_NOT_REQUIRED_COMMANDS = new String[]{
		"leaders",
		"link",
		"help",
	};
	
	public DiscordCommand(String[] arguments) {
		this.arguments = arguments;
	}
	
	public String[] getArguments() {
		return arguments;
	}
	
	public boolean hasAdditionalArguments() {
		if(arguments.length > 1) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean requiresOwner() {
		
		if(hasAdditionalArguments() && 
				Arrays.asList(OWNER_REQUIRED_COMMANDS).contains(arguments[1])) {
			return true;
		}
		return false;
	}
	public boolean requiresLinkedAccount() {
		if(hasAdditionalArguments() && 
				Arrays.asList(LINKED_ACCOUNT_REQUIRED_COMMANDS).contains(arguments[1])) {
			return true;
		}
		return false;
	}
	public boolean noLinkedAccountRequired() {
		if(hasAdditionalArguments() && 
				Arrays.asList(LINKED_ACCOUNT_NOT_REQUIRED_COMMANDS).contains(arguments[1])) {
			return true;
		}
		return false;
	}
	
	public boolean isSupportedCommand() {
		if(!hasAdditionalArguments()) {
			return true;
		}
		return (requiresOwner() || requiresLinkedAccount() || noLinkedAccountRequired());
	}
	
	public DiscordCommandToProcess getCommandToProcess() {
		if(!hasAdditionalArguments()) {
			return DiscordCommandToProcess.HELP;
		}
		return DiscordCommandToProcess.valueOf(arguments[1].toUpperCase());
	}
}
