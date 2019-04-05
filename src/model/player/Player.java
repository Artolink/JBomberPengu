package model.player;

public class Player implements IEntity {
	
	private final String playerName;
	private Integer points = 0;
	
	
	public Player(String playerName){
		this.playerName = playerName;
	}

	@Override
	public String getName() {
		return this.playerName;
	}

	@Override
	public void addPoints(Integer points) {
		this.points += points;
	}

	@Override
	public Integer getPoints() {
		return this.points;
	}

}
