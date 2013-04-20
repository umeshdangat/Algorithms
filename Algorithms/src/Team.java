public class Team {

	private final int teamIndex;
	private final int wins;
	private final int loses;
	private final int remaining;

	public Team(int teamIndex, int wins, int loses, int remaining) {
		this.teamIndex = teamIndex;
		this.wins = wins;
		this.loses = loses;
		this.remaining = remaining;
	}

	public int getTeamIndex() {
		return teamIndex;
	}

	public int getWins() {
		return wins;
	}

	public int getLoses() {
		return loses;
	}

	public int getRemaining() {
		return remaining;
	}

}
