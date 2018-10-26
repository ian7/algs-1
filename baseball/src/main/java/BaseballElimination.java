import edu.princeton.cs.algs4.FlowNetwork;

public class BaseballElimination {
  private final FlowNetwork flowNetwork;
  private int teamsCount;
  public BaseballElimination(String filename){
    this.teamsCount = 4;
    final int gamesCount = combinations( teamsCount-1,2);
    final int networkNodeCount = gamesCount + teamsCount - 1 + 2;
    this.flowNetwork = new FlowNetwork(networkNodeCount);
  }                    // create a baseball division from given filename in format specified below
  private static int factorial( int n ){
    int result = 1;
    for( int i = 2 ; i <= n; i++){
      result = result * i;
    }
    return result;
  }
  private static int combinations( int n, int k){
    final int register = factorial(n);
    final int denominator = factorial(k) * factorial(n-k);
    return(register/denominator);
  }
  public              int numberOfTeams(){
    return 0;
  }                        // number of teams
  public Iterable<String> teams(){
    return null;
  }                                // all teams
  public              int wins(String team) {
    return 0;
  }                     // number of wins for given team
  public              int losses(String team) {
    return 0;
  }                   // number of losses for given team
  public              int remaining(String team) {
    return 0;
  }                // number of remaining games for given team
  public              int against(String team1, String team2){
    return 0;
  }    // number of remaining games between team1 and team2
  public          boolean isEliminated(String team){
    return false;
  }// is given team eliminated?
  public Iterable<String> certificateOfElimination(String team){
    return null;
  }
}
