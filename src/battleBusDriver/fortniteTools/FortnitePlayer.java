package battleBusDriver.fortniteTools;

import java.io.IOException;

import com.xilixir.fortniteapi.v2.Stats;

import battleBusDriver.battleBusBot.BattleBusDriverBot;



public class FortnitePlayer {
	
	private String username;
	private String epicID;
	private Stats stats;
	
	public FortnitePlayer(String username, String epicID) {
		this.username = username;
		this.epicID = epicID;
		stats = null;
	}
	
	public void updateStats() throws IOException {
		stats = BattleBusDriverBot.api.getStatsForUser(username);
	}
	public String getUsername() {
		return username;
	}
	public String getEpicID() {
		return epicID;
	}
	public Stats getStats() {
		return stats;
	}
	
	public FortniteStat getStat(FortniteStatType statType) {
		double value;
		switch(statType) {
			case SOLO_TOP10:			value = stats.getSoloTop10();			break;
			case SOLO_TOP25:			value = stats.getSoloTop25();			break;
			case DUO_TOP5:				value = stats.getDuoTop5();				break;
			case DUO_TOP12:				value = stats.getDuoTop12();			break;
			case SQUAD_TOP3:			value = stats.getSquadTop3();			break;
			case SQUAD_TOP6:			value = stats.getSquadTop6();			break;
			case SOLO_KILLS:			value = stats.getSoloKills();			break;
			case DUO_KILLS:				value = stats.getDuoKills();			break;
			case SQUAD_KILLS:			value = stats.getSquadKills();			break;
			case TOTAL_KILLS:			value = stats.getTotalKills();			break;
			case SOLO_MINUTES_PLAYED:	value = stats.getSoloMinutesPlayed();	break;
			case DUO_MINUTES_PLAYED:	value = stats.getDuoMinutesPlayed();	break;
			case SQUAD_MINUTES_PLAYED:	value = stats.getSquadMinutesPlayed();	break;
			case TOTAL_MINUTES_PLAYED:	value = stats.getTotalMinutesPlayed();	break;
			case SOLO_WINS:				value = stats.getSoloWins();			break;
			case DUO_WINS:				value = stats.getDuoWins();				break;
			case SQUAD_WINS:			value = stats.getSquadWins();			break;
			case TOTAL_WINS:			value = stats.getTotalWins();			break;
			case SOLO_MATCHES_PLAYED:	value = stats.getSoloMatchesPlayed();	break;
			case DUO_MATCHES_PLAYED:	value = stats.getDuoMatchesPlayed();	break;
			case SQUAD_MATCHES_PLAYED:	value = stats.getSquadMatchesPlayed();	break;
			case TOTAL_MATCHES_PLAYED:	value = stats.getTotalMatchesPlayed();	break;
			case SOLO_SCORE:			value = stats.getSoloScore();			break;
			case DUO_SCORE:				value = stats.getDuoScore();			break;
			case SQUAD_SCORE:			value = stats.getSquadScore();			break;
			case SOLO_KD:				value = stats.getSoloKillDeathRatio();	break;
			case DUO_KD:				value = stats.getDuoKillDeathRatio();	break;
			case SQUAD_KD:				value = stats.getSquadKillDeathRatio();	break;
			case TOTAL_KD:				value = stats.getTotalKillDeathRatio();	break;
			case SOLO_WIN_RATE:			value = stats.getSoloWinRatio();		break;
			case DUO_WIN_RATE:			value = stats.getDuoWinRatio();			break;
			case SQUAD_WIN_RATE:		value = stats.getSquadWinRatio();		break;
			case TOTAL_WIN_RATE:		value = stats.getTotalWinRatio();		break;
			case SOLO_KILLS_PER_MINUTE:	value = stats.getSoloKillsPerMinute();	break;
			case DUO_KILLS_PER_MINUTE:	value = stats.getDuoKillsPerMinute();	break;
			case SQUAD_KILLS_PER_MINUTE:value = stats.getSquadKillsPerMinute(); break;
			case TOTAL_KILLS_PER_MINUTE:value = stats.getTotalKillsPerMinute();	break;
			case SOLO_KILLS_PER_MATCH:	value = stats.getSoloKillsPerMatch();	break;
			case DUO_KILLS_PER_MATCH:	value = stats.getDuoKillsPerMatch();	break;
			case SQUAD_KILLS_PER_MATCH:	value = stats.getSquadKillsPerMatch();	break;
			case TOTAL_KILLS_PER_MATCH:	value = stats.getTotalKillsPerMatch();	break;
			default: return null;
		}
		return new FortniteStat(value, statType);
	}
	
	public static int compareByStatType(FortnitePlayer player1, FortnitePlayer player2, FortniteStatType statType) {
		return player1.getStat(statType).compareTo(player2.getStat(statType));
	}
}
