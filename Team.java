
/**
 * This is a Football World Cup program Team class. Team class contains the information about team.
 *
 *@author Tuna Rezaiazar
 * @version 09/05/2018
 */
public class Team
{
    private String name; //Name of the team.
    private int rank; //Rank of the team.
    private Player scorer1; //Player scorer 1
    private Player scorer2; //Player scorer 2
    private int[] stats; //index[0] is for number of draws, index[1] is for number of wins, index[2] is for number of loses
    private int goals; //Number of goals for a team
    private int point; //Number of points in the league
    private int red; //Number of red cards.
    private int yellow; //Number of yellow cards.

    /**
     * Constructor for objects of class Team
     * 
     * @param name  gets the name of the Team.
     * @param rank  gets the rank of the Team
     */
    public Team(String name, int rank)
    {
        this.name = name;
        this.rank = rank;
        stats = new int[3];
        scorer1 =  new Player();
        scorer2 =  new Player();
        this.goals = 0;
        this.point = 0;
        this.yellow = 0;
        this.red = 0;
    }

    /**
     * Constructor for objects of class Team
     */
    public Team()
    {

    }

    /**
     * This method returns the goal number of a team.
     *
     * @return  number of goals that are achihved by the team.
     */
    public int getGoals()
    {
        return this.goals;
    }

    /**
     * This method returns the name of a team.
     *
     * @return  name of the team.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * This method returns the points of a team.
     *
     * @return  points of the team.
     */
    public int getPoint()
    {
        return this.point;
    }

    /**
     * This method returns the rank of a team.
     *
     * @return  ranking of the team.
     */
    public int getRank()
    {
        return this.rank;
    }

    /**
     * This method returns the number of red cards of a team.
     *
     * @return  number of red cards of the team.
     */
    public int getRed()
    {
        return this.red;
    }

    /**
     * This method returns scorer Player 1 of a team.
     *
     * @return  first player of the team.
     */
    public Player getScorer1()
    {
        return this.scorer1;
    }

    /**
     * This method returns scorer Player 2 of a team.
     *
     * @return  second player of the team.
     */
    public Player getScorer2()
    {
        return this.scorer2;
    }

    /**
     * This method returns the statistics array of a team. Index 0 is number of draws, index 1 is number of wins, index 2 is number of loses.
     *
     * @return  statistics array of of the team.
     */
    public int[] getStats()
    {
        return this.stats;
    }

    /**
     * This method returns the number of yellow cards of a team.
     *
     * @return  number of yellow cards of the team.
     */
    public int getYellow()
    {
        return this.yellow;
    }

    /**
     * This method adds the number of goals for a team.
     *
     * @param goal number of goals achived in a match.
     */
    public void setGoals(int goal)
    {
        this.goals = this.goals + goal;
    }

    /**
     * This method saves the name for a team.
     *
     * @param name name of a team.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * This method adds the points for a team.
     *
     * @param point points that are achived in a match.
     */
    public void setPoint(int point)
    {
        this.point = this.point + point;
    }

    /**
     * This method changes the ranking of a team.
     *
     * @param rank new ranking for a team.
     */
    public void setRank(int rank)
    {
        this.rank = rank;
    }

    /**
     * This method adds red card numbers for a team.
     *
     * @param red red card numbers achived in a match.
     */
    public void setRed(int red)
    {
        this.red = this.red + red;
    }

    /**
     * This method changes the player 1 of a team.
     *
     * @param p new player for a team.
     */
    public void setScorer1(Player p)
    {
        this.scorer1 = p;
    }

    /**
     * This method changes the player 2 of a team.
     *
     * @param p new player for a team.
     */
    public void setScorer2(Player p)
    {
        this.scorer2 = p;
    }

    /**
     * This method changes statistics of a team. Index 0 is number of draws, index 1 is number of wins, index 2 is number of loses.
     *
     * @param stat array of statistics.
     */
    public void setStat(int[] stat)
    {
        this.stats = stat;
    }

    /**
     * This method adds yellow card numbers for a team.
     *
     * @param yellow yellow card numbers achived in a match.
     */
    public void setYellow(int yellow)
    {
        this.yellow = this.yellow + yellow;
    }

    /**
     * This method adds 1 to draw statistics of a team.
     */
    public void statDraw()
    {
        this.stats[0]++;  
    }

    /**
     * This method adds 1 to win statistics of a team.
     */
    public void statWin()
    {
        this.stats[1]++; 
    }

    /**
     * This method adds 1 to lose statistics of a team.
     */
    public void statLose()
    {
        this.stats[2]++; 
    }
}
