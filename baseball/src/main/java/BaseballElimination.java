public class BaseballElimination {
  public BaseballElimination(String filename){

  }                    // create a baseball division from given filename in format specified below
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
