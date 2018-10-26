import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;

public class BaseballElimination {
  private FlowNetwork flowNetwork;
  private final int teamsCount;
  private final int gameCount;
  private Team[] teams;
  private Team trivialEliminationTeam;

  public BaseballElimination(String filename) {
    final In in = new In(filename);
    this.teamsCount = Integer.valueOf(in.readLine());
    this.gameCount = (int) combinations(teamsCount - 1, 2);

    this.teams = new Team[this.teamsCount];
    for (int i = 0; i < this.teamsCount; i++) {
      final String[] rawLine = in.readLine().trim().split("\\s+");
      final String teamName = rawLine[0];
      final int teamWins = Integer.valueOf(rawLine[1]);
      final int teamLosses = Integer.valueOf(rawLine[2]);
      final int teamLeft = Integer.valueOf(rawLine[3]);
      final int[] teamGames = new int[this.teamsCount];
      for (int j = 0; j < this.teamsCount; j++) {
        teamGames[j] = Integer.valueOf(rawLine[4 + j]);
      }
      this.teams[i] = new Team(teamName, teamWins, teamLosses, teamLeft, teamGames);
    }
    //this.modelNetwork(4);
  }                    // create a baseball division from given filename in format specified below

  private class Team {
    private final String name;
    private final int wins;
    private final int loss;
    private final int left;
    private final int[] games;

    private Team(String name, int wins, int loss, int left, int[] games) {
      this.name = name;
      this.wins = wins;
      this.loss = loss;
      this.left = left;
      this.games = games;
    }

    public int getWins() {
      return wins;
    }

    public String getName() {
      return name;
    }

    public int getLoss() {
      return loss;
    }

    public int getLeft() {
      return left;
    }

    public int[] getGames() {
      return games;
    }
  }

  private Team[] getReducedTeams(int teamInQuestion) {
    final Team[] reducedTeams = new Team[this.teamsCount - 1];
    for (int i = 0; i < this.teamsCount - 1; i++) {
      final int index;
      if (i < teamInQuestion) {
        index = i;
      } else {
        index = i + 1;
      }
      final Team oldTeam = this.teams[index];
      final Team newTeam = new Team(oldTeam.name, oldTeam.wins, oldTeam.loss, oldTeam.left, getReducedGames(oldTeam, teamInQuestion));
      reducedTeams[i] = newTeam;
    }
    return reducedTeams;
  }

  private int[] getReducedGames(Team t, int teamInQuestion) {
    final int[] reducedGames = new int[this.teamsCount - 1];
    for (int i = 0; i < this.teamsCount - 1; i++) {
      if (i < teamInQuestion) {
        reducedGames[i] = t.getGames()[i];
      } else {
        reducedGames[i] = t.getGames()[i + 1];
      }
    }
    return reducedGames;
  }

  private boolean modelNetwork(int teamInQuestionIndex) {
    final int networkNodeCount = gameCount + teamsCount - 1 + 2;
    this.flowNetwork = new FlowNetwork(networkNodeCount);


    final Team[] reducedTeams = getReducedTeams(teamInQuestionIndex);
    final int reducedTeamCount = this.teamsCount - 1;
    final int sourceIndex = 0;

    int index = 1;
    for (int j = 0; j < reducedTeamCount; j++) {
      for (int k = j + 1; k < reducedTeamCount; k++) {
        final int gamesToGoBetweenTeams = reducedTeams[j].getGames()[k];
        this.flowNetwork.addEdge(new FlowEdge(sourceIndex, index, gamesToGoBetweenTeams));

        final int firstTeamIndex = j + 1 + this.gameCount;
        final int secondTeamIndex = k + 1 + this.gameCount;
        this.flowNetwork.addEdge(new FlowEdge(index, firstTeamIndex, Double.POSITIVE_INFINITY));
        this.flowNetwork.addEdge(new FlowEdge(index, secondTeamIndex, Double.POSITIVE_INFINITY));
        index++;
      }
    }
    final int sinkIndex = 1 + this.gameCount + reducedTeamCount;
    for (int i = 0; i < reducedTeamCount; i++) {
      final int offset = 1 + this.gameCount;
      final Team teamInQuestion = this.teams[teamInQuestionIndex];
      final double capacity = teamInQuestion.getWins() + teamInQuestion.getLeft() - reducedTeams[i].getWins();
      if (capacity < 0) {
        this.trivialEliminationTeam = reducedTeams[i];
        return false;
      }
      this.flowNetwork.addEdge(new FlowEdge(offset + i, sinkIndex, capacity));
    }
    return true;
  }

  private static int factorial(int n) {
    int result = 1;
    for (int i = 2; i <= n; i++) {
      result = result * i;
    }
    return result;
  }

  private static long combinations(int n, int k) {
    /*if( n == k ){
      return 1;
    }
    final int register = factorial(n);
    final int denominator = factorial(k) * factorial(n - k);
    return (register / denominator);*/
    return ( n*(n-1)/2);
  }

  public int numberOfTeams() {
    return this.teamsCount;
  }                        // number of teams

  public Iterable<String> teams() {
    return null;
  }                                // all teams

  public int wins(String team) {
    return this.find(team).getWins();
  }                     // number of wins for given team

  public int losses(String team) {
    return this.find(team).getLoss();
  }                   // number of losses for given team

  public int remaining(String team) {
    return this.find(team).getLeft();
  }                // number of remaining games for given team

  public int against(String team1, String team2) {
    Team t = this.find( team1 );
    int otherTeamIndex = this.findIndex( team2 );
    return t.getGames()[otherTeamIndex];
  }    // number of remaining games between team1 and team2

  public boolean isEliminated(String team) {
    final int teamInQuestionIndex = findIndex(team);

    // check if team is trivially eliminated
    if (!this.modelNetwork(teamInQuestionIndex)) {
      return true;
    }

    FordFulkerson ff = new FordFulkerson(this.flowNetwork, 0, 1 + this.gameCount + this.teamsCount - 1);
    for (FlowEdge e : this.flowNetwork.adj(0)) {
      if (e.flow() < e.capacity()) {
        return true;
      }
    }
    return false;
  }// is given team eliminated?

  private Team find(String name) {
    for (Team t : this.teams) {
      if (t.getName().compareTo(name) == 0) {
        return t;
      }
    }
    return null;
  }

  private int findIndex(String name) {
    for (int i = 0; i < this.teamsCount; i++) {
      if (this.teams[i].getName().compareTo(name) == 0) {
        return i;
      }
    }
    return -1;
  }


  public Iterable<String> certificateOfElimination(String team) {
    final Team teamInQuestion = find(team);
    final int teamInQuestionIndex = findIndex(team);
    ArrayList<String> teams = new ArrayList<>();

    // check if team is trivially eliminated
    if (!this.modelNetwork(teamInQuestionIndex)) {
      teams.add(this.trivialEliminationTeam.getName());
      return teams;
    }

    FordFulkerson ff = new FordFulkerson(this.flowNetwork, 0, 1 + this.gameCount + this.teamsCount - 1);

    final int indexOffset = 1+this.gameCount;
    final Team[] reducedTeams = getReducedTeams(teamInQuestionIndex);
    for( int i = 0; i< this.teamsCount-1;i++){
      final int teamIndex = i+indexOffset;
      if( ff.inCut(teamIndex) ){
        teams.add(reducedTeams[i].getName());
      }
    }

    return teams;
  }
}
