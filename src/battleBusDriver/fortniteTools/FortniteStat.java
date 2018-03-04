package battleBusDriver.fortniteTools;

	

public class FortniteStat implements Comparable<FortniteStat>{
	private double value;
	private FortniteStatType statType;
	
	public FortniteStat(double value, FortniteStatType statType) {
		this.value = value;
		this.statType = statType;
	}
	
	public double getValue() {
		return value;
	}
	@Override
	public int compareTo(FortniteStat otherStat) {
		// TODO Auto-generated method stub
		if(value - otherStat.getValue() > 0) {
			return 1;
		}
		else if(value - otherStat.getValue() < 0) {
			return -1;
		}
		return 0;
	}
	
	private boolean isIntegerStat() {
		if(statType.toString().contains("PER") ||
				statType.toString().contains("KD") ||
				statType.toString().contains("RATE")) {
			return false;
		}
		return true;
	}
	
	public String toString() {
		if(isIntegerStat()) {
			return String.valueOf((int)value);
		}
		return String.valueOf(value);
	}
}