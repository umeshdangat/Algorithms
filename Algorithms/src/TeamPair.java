
public class TeamPair {

	private final int vertexNumber;
	private final int gamesLeft;
	private String teamA;
	private String teamB;

	public TeamPair(int vertexNumber, int gamesLeft, String teamA, String teamB) {
		this.vertexNumber = vertexNumber;
		this.gamesLeft = gamesLeft;
		this.teamA= teamA;
		this.teamB = teamB;
	}

	public String getTeamA() {
		return teamA;
	}


	public String getTeamB() {
		return teamB;
	}


	public int getVertexNumber() {
		return vertexNumber;
	}

	public int getGamesLeft() {
		return gamesLeft;
	}

}
