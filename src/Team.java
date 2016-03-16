public class Team {

	private String name;
	private float percentage;
	private String region;
	private int seed;
	private int wins;
	
	public Team(String name, float percentage, String region, int seed){
		this.name = name;
		this.percentage = percentage;
		this.region = region;
		this.seed = seed;
	}
	
	public void addWin(){
		wins++;
	}
	
	public int getWins(){
		return wins;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}
	
}
