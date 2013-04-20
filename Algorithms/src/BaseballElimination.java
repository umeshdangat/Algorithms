import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BaseballElimination {

	private final int numberOfTeams;
	private int[][] opponentsLeft;
	private Map<String, Team> teams;
	private Map<Integer, String> teamIndices;
	private String leadingTeam;

	// create a baseball division from given filename in format specified below
	public BaseballElimination(String filename) {
		this(new In(filename));
	}

	BaseballElimination(In in) {
		numberOfTeams = in.readInt();
		opponentsLeft = new int[numberOfTeams][numberOfTeams];
		teams = new HashMap<String, Team>();
		teamIndices = new HashMap<Integer, String>();

		for (int i = 0; i < numberOfTeams; i++) {
			int teamIndex = i;
			String teamName = in.readString();
			int wins = in.readInt();
			int loses = in.readInt();
			int remaining = in.readInt();
			for (int j = 0; j < numberOfTeams; j++) {
				opponentsLeft[i][j] = in.readInt();
			}
			teams.put(teamName, new Team(teamIndex, wins, loses, remaining));
			teamIndices.put(teamIndex, teamName);
			// update team with most wins
			if (leadingTeam == null || wins > teams.get(leadingTeam).getWins()) {
				leadingTeam = teamName;
			}
		}
	}

	// number of teams
	public int numberOfTeams() {
		return numberOfTeams;
	}

	// all teams
	public Iterable<String> teams() {
		return teams.keySet();
	}

	// number of wins for given team
	public int wins(String team) {
		Team currentTeam = teams.get(team);
		if (currentTeam == null) {
			throw new IllegalArgumentException();
		} else {
			return currentTeam.getWins();
		}
	}

	// number of losses for given team
	public int losses(String team) {
		Team currentTeam = teams.get(team);
		if (currentTeam == null) {
			throw new IllegalArgumentException();
		} else {
			return currentTeam.getLoses();
		}
	}

	// number of remaining games for given team
	public int remaining(String team) {
		Team currentTeam = teams.get(team);
		if (currentTeam == null) {
			throw new IllegalArgumentException();
		} else {
			return currentTeam.getRemaining();
		}
	}

	// number of remaining games between team1 and team2
	public int against(String team1, String team2) {
		Team currentTeam1 = teams.get(team1);
		Team currentTeam2 = teams.get(team2);
		if(currentTeam1 == null || currentTeam2 == null){
			throw new IllegalArgumentException();
		}
		int team1Id = currentTeam1.getTeamIndex();
		int team2Id = currentTeam2.getTeamIndex();
		return opponentsLeft[team1Id][team2Id];
	}

	// is given team eliminated?
	public boolean isEliminated(String team) {
		Team currentTeam = teams.get(team);
		if (currentTeam == null) {
			throw new IllegalArgumentException();
		}
		// isEliminated trivially?
		if (isEliminatedTrivially(team)) {
			return true;
		} else {
			return isEliminatedNonTrivially(team);
		}
	}

	private boolean isEliminatedNonTrivially(String team) {
		// create flow network
		int totalVertices = calculateTotalVertices();
		FlowNetwork flowNetwork = new FlowNetwork(totalVertices);

		// build game vertices and their values i.e games left between those
		// team pairs
		int vertexNumber = 1;
		List<TeamPair> teamPairs = new ArrayList<TeamPair>();
		vertexNumber = buildGameVertices(team, vertexNumber, teamPairs);

		// build team vertex with vertex ids and team names
		Map<String, Integer> teamVertex = new HashMap<String, Integer>();
		vertexNumber = buildTeamVertices(team, vertexNumber, teamVertex);

		// build flow network for game vertices and team vertices from
		// source
		int gamesLeft = buildFlowNetworkFromSource(flowNetwork, teamPairs,
				teamVertex);

		// finish building flow network to sink
		buildFlowNetworkFromTeamVerticesToSink(team, flowNetwork, vertexNumber,
				teamVertex);

		// System.out.println("FlowNetwork " + flowNetwork.toString());

		FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, 0,
				vertexNumber);
		double maxFlow = fordFulkerson.value();
		// System.out.println("Games Left: " + gamesLeft + " Max Flow: " +
		// maxFlow);

		return gamesLeft > maxFlow;
	}

	private int calculateTotalVertices() {
		int teamsWithoutEliminatedTeam = opponentsLeft.length - 1;
		int numberOfGameVertices = (teamsWithoutEliminatedTeam * (teamsWithoutEliminatedTeam - 1)) / 2;
		int totalVertices = 2 + numberOfGameVertices
				+ teamsWithoutEliminatedTeam;
		return totalVertices;
	}

	private void buildFlowNetworkFromTeamVerticesToSink(String team,
			FlowNetwork flowNetwork, int vertexNumber,
			Map<String, Integer> teamVertex) {
		Set<Entry<String, Integer>> entry = teamVertex.entrySet();
		for (Entry<String, Integer> eachEntry : entry) {
			String teamName = eachEntry.getKey();
			int vertexNumberForTeam = eachEntry.getValue();
			Team currTeam = teams.get(teamName);
			Team potentiallyEliminatedTeam = teams.get(team);
			int edgeCostToSinkForCurrTeam = potentiallyEliminatedTeam.getWins()
					+ potentiallyEliminatedTeam.getRemaining()
					- currTeam.getWins();
			flowNetwork.addEdge(new FlowEdge(vertexNumberForTeam, vertexNumber,
					edgeCostToSinkForCurrTeam));
		}
	}

	private int buildFlowNetworkFromSource(FlowNetwork flowNetwork,
			List<TeamPair> teamPairs, Map<String, Integer> teamVertex) {
		int gamesLeft = 0;
		for (TeamPair teamPair : teamPairs) {
			flowNetwork.addEdge(new FlowEdge(0, teamPair.getVertexNumber(),
					teamPair.getGamesLeft()));
			gamesLeft += teamPair.getGamesLeft();
			flowNetwork.addEdge(new FlowEdge(teamPair.getVertexNumber(),
					teamVertex.get(teamPair.getTeamA()),
					Double.POSITIVE_INFINITY));
			flowNetwork.addEdge(new FlowEdge(teamPair.getVertexNumber(),
					teamVertex.get(teamPair.getTeamB()),
					Double.POSITIVE_INFINITY));
		}
		return gamesLeft;
	}

	private int buildTeamVertices(String team, int vertexNumber,
			Map<String, Integer> teamVertex) {
		for (String teamName : teams.keySet()) {
			if (teamName.equals(team)) {
				continue;
			}
			teamVertex.put(teamName, vertexNumber);
			vertexNumber++;
		}
		return vertexNumber;
	}

	private int buildGameVertices(String team, int vertexNumber,
			List<TeamPair> teamPairs) {
		for (int i = 0; i < opponentsLeft.length; i++) {
			if (i == teams.get(team).getTeamIndex()) {
				continue;
			}
			for (int j = i + 1; j < opponentsLeft.length; j++) {
				if (j == teams.get(team).getTeamIndex()) {
					continue;
				}
				TeamPair teamPair = new TeamPair(vertexNumber,
						opponentsLeft[i][j], teamIndices.get(i),
						teamIndices.get(j));
				teamPairs.add(teamPair);
				vertexNumber++;
			}
		}
		return vertexNumber;
	}

	private boolean isEliminatedTrivially(String team) {
		Team currentTeam = teams.get(team);
		Team leading = teams.get(leadingTeam);
		if (currentTeam.getWins() + currentTeam.getRemaining() < leading
				.getWins()) {
			return true;
		}
		return false;
	}

	// subset R of teams that eliminates given team; null if not eliminated
	public Iterable<String> certificateOfElimination(String team) {
		Team currentTeam = teams.get(team);
		if(currentTeam==null){
			throw new IllegalArgumentException();
		}
		
		if (isEliminatedTrivially(team)) {
			List<String> subsetTeamsThatEliminateR = new ArrayList<String>();
			subsetTeamsThatEliminateR.add(leadingTeam);
			return subsetTeamsThatEliminateR;
		} else {
			return buildSubSetR(team);
		}
	}

	private String getLeadingTeam() {
		return leadingTeam;
	}

	private Iterable<String> buildSubSetR(String team) {
		// create flow network
		int totalVertices = calculateTotalVertices();
		FlowNetwork flowNetwork = new FlowNetwork(totalVertices);

		// build game vertices and their values i.e games left between those
		// team pairs
		int vertexNumber = 1;
		List<TeamPair> teamPairs = new ArrayList<TeamPair>();
		vertexNumber = buildGameVertices(team, vertexNumber, teamPairs);

		// build team vertex with vertex ids and team names
		Map<String, Integer> teamVertex = new HashMap<String, Integer>();
		vertexNumber = buildTeamVertices(team, vertexNumber, teamVertex);

		// build flow network for game vertices and team vertices from
		// source
		int gamesLeft = buildFlowNetworkFromSource(flowNetwork, teamPairs,
				teamVertex);

		// finish building flow network to sink
		buildFlowNetworkFromTeamVerticesToSink(team, flowNetwork, vertexNumber,
				teamVertex);

		// System.out.println("FlowNetwork " + flowNetwork.toString());

		FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, 0,
				vertexNumber);
		double maxFlow = fordFulkerson.value();
		// System.out.println("Games Left: " + gamesLeft + " Max Flow: " +
		// maxFlow);

		List<String> teamsInEliminatingSubset = new ArrayList<String>();
		if (gamesLeft > maxFlow) {
			Set<Entry<String, Integer>> teamVertices = teamVertex.entrySet();
			for (Entry<String, Integer> eachTeamVertex : teamVertices) {
				String teamName = eachTeamVertex.getKey();
				Integer teamVertexId = eachTeamVertex.getValue();
				if (fordFulkerson.inCut(teamVertexId)) {
					teamsInEliminatingSubset.add(teamName);
				}
			}
			return teamsInEliminatingSubset;
		} else {
			return null;
		}

	}

	public static void main(String[] args) {
		Stopwatch sw = new Stopwatch();
		BaseballElimination division = new BaseballElimination(args[0]);
		for (String team : division.teams()) {
			if (division.isEliminated(team)) {
				StdOut.print(team + " is eliminated by the subset R = { ");
				for (String t : division.certificateOfElimination(team))
					StdOut.print(t + " ");
				StdOut.println("}");
			} else {
				StdOut.println(team + " is not eliminated");
			}
		}
		System.out.println("Time to run " + sw.elapsedTime());
	}

}
