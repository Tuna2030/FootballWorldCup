import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * This is a Football World Cup program Game class. The program starts when the playGame() method invoked.
 * First for all teams that are listed in the teams.txt document, player names are needed to be entered.
 * After all player names are saved, program menu will appear and selections can be made.
 *
 * @author Tuna Rezaiazar
 * @version 09/05/2018
 */
public class Game
{
    private ArrayList<Team> teamList; //Team array list for storing teams.
    private RandomNumberGenerator rng; //Random number generator object to generate random numbers.
    private Team cupWinner; //Winner team of the World Cup.
    
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        teamList = new ArrayList<Team>();
        rng = new RandomNumberGenerator();
        cupWinner = new Team();
    }
    
    /**
     * This method displays the results of a match between 2 teams. It shows the goal numbers and cards that are given in a single match.
     *
     * @param t1 Team 1 object that has match.
     * @param t2 Team 2 object that has match.
     * @param goal1 goal number of Team 1 in a match against Team 2.
     * @param goal2 goal number of Team 2 in a match against Team 1.
     * @param yellow1 yellow cards that are given to Team 1.
     * @param red1 red cards that are given to Team 1.
     * @param yellow2 yellow cards that are given to Team 2.
     * @param red2 red cards that are given to Team 2.
     */
    public void displayGameResult(Team t1, Team t2, int goal1, int goal2 , int yellow1, int red1, int yellow2, int red2)
    {
        int check = 0;
        if (goal1 > goal2) System.out.println("\n  Game result: " + t1.getName() + " " + goal1 + " vs. " + t2.getName() + " " + goal2); 
        else System.out.println("\n  Game result: " + t2.getName() + " " + goal2 + " vs. " + t1.getName() + " " + goal1);

        if (yellow1 > 0 | red1 > 0 | yellow2 > 0 | red2 > 0) System.out.print("Cards awarded: ");
        if (yellow1 > 0) 
        {
            System.out.print(t1.getName() + " - " + yellow1 + " yellow card.");
            check++;
        }
        if ( red1 > 0 ) 
        {
            if (check > 0 ) System.out.print("\n\t       ");
            System.out.print(t1.getName() + " - " + red1 + " red card.");
            check++;
        }
        if ( yellow2 > 0 ) 
        {
            if (check > 0 ) System.out.print("\n\t       ");
            System.out.print(t2.getName() + " - " + yellow2 + " yellow card.");
            check++;
        }    
        if ( red2 > 0 ) 
        {
            if (check > 0 ) System.out.print("\n\t       ");
            System.out.print(t2.getName() + " - " + red2 + " red card.");
            check++;
        }
        if (check > 0) System.out.println();
    }

    /**
     * This method gets input from the user for team player names. Method has also validation controls for entered names by the user according to requirements. 
     *
     * @param t Team 1 object that player needs to be changed.
     */
    public void enterPlayerNames(Team t)
    {
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        String name = "";
        int count = 0;
        while (true)
        {
            if (count == 2)
            {
                System.out.println("Too many wrong attempts " + t.getName() + "-P1 is given for the 1st " + t.getName() + " player!");
                name = t.getName() + "-P1";
                t.getScorer1().setName(name);
                break;
            }
            System.out.println("\nFor " + t.getName() + " please enter the 1st players name: ");
            name = sc.nextLine();
            check = t.getScorer1().setName(name);
            if (check) break;
            count++;
        }
        check = false;
        count = 0;
        name = "";
        while (true)
        {
            if (count == 2)
            {
                System.out.println("Too many wrong attempts " + t.getName() + "-P2 is given for the 2nd " + t.getName() + " player!");
                name = t.getName() + "-P2";
                t.getScorer2().setName(name);
                break;
            }
            System.out.println("\nFor " + t.getName() + " please enter the 2nd players name: ");
            name = sc.nextLine();
            String trimText = "";
            for (int i = 0; i < name.length(); i++) 
            {
                if (name.charAt(i) != ' ') trimText = trimText + name.charAt(i);
            }
            name = trimText;
            count++;
            if (name.equals(t.getScorer1().getName()))
            {
                System.out.println("Second players name can not be same with the 1st player!");
                continue;
            }
            check = t.getScorer2().setName(name);
            if (check) break;
        }
    }

    /**
     * This method reads the teams.txt file and creates the team objects for league with given names and rankings in the file.
     */
    public void fileReader()
    {
        try
        {
            FileReader file = new FileReader("teams.txt");
            try
            {
                Scanner scan = new Scanner(file);
                while (scan.hasNextLine()) 
                {
                    String[] teamInfo = new String[2];
                    String line = scan.nextLine();
                    teamInfo = line.split(",");
                    teamList.add(new Team(teamInfo[0] , Integer.parseInt(teamInfo[1])));
                }
            }
            finally
            {
                System.out.println("Text file successfully read....closing file.");
                file.close();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Team list is not loaded! Check the teams.txt file!");
        }
        catch (IOException e)
        {
            System.out.println("Unexpected I/O exception occurs! Check the teams.txt file!");
        }
    }

    /**
     * This method checks the teams that have lowest yellow/red card status in a league and prints the Fair Play Award accordingly.
     * 
     * @return the fair play award winners as String
     */
    public String getFairPlay()
    {
        int min = 10;
        int check = 0;
        String text = "Fair Play Award: ";
        for (int i = 0; i < teamList.size(); i++)
        {
            int total = 0;
            total = teamList.get(i).getYellow() + teamList.get(i).getRed() * 2;
            if (total < min) min = total;
        }
        for (int j = 0; j < teamList.size(); j++)
        {
            int total = 0;
            total = teamList.get(j).getYellow() + teamList.get(j).getRed() * 2;
            if (min == total)
            {
                if (check > 0) text = text + " and ";
                text = text + teamList.get(j).getName();
                check++;
            }
        }
        return text;
    }

    /**
     * This method checks the team player who has the most goals in a league prints the Golden Boot Award accordingly.
     * 
     * @return the golden boot award winners as String.
     */
    public String getGoldenBoot()
    {
        int max = 0;
        int check = 0;
        String text = "Golden Boot Award: ";
        for (int i = 0; i < teamList.size(); i++)
        {
            if (teamList.get(i).getScorer1().getGoals() > max) max = teamList.get(i).getScorer1().getGoals();
            if (teamList.get(i).getScorer2().getGoals() > max) max = teamList.get(i).getScorer2().getGoals();
        }
        for (int j = 0; j < teamList.size(); j++)
        {
            if (teamList.get(j).getScorer1().getGoals() == max) 
            {
                if (check > 0) text = text + " and ";
                text = text + teamList.get(j).getScorer1().getName() + " from " + teamList.get(j).getName();
                check++;
            }        
            if (teamList.get(j).getScorer2().getGoals() == max) 
            {
                if (check > 0) text = text + " and ";
                text = text + teamList.get(j).getScorer2().getName() + " from " + teamList.get(j).getName();
                check++;
            }
        }
        return text;
    }

    /**
     * This is the main method to play the program/league. When methods ends league statistics are saved in 'statistics.txt' file.
     */
    public void playGame()
    {        
        teamList.clear();
        System.out.println("Welcome to Football World Cup!");
        Scanner scan = new Scanner(System.in);
        String prompt = "";
        int check = 0;
        System.out.println("Reading teams from teams.txt file.");
        fileReader();
        for (int i = 0; i < teamList.size();i++)
        {
            enterPlayerNames(teamList.get(i));
        }

        while (!prompt.equals("X"))
        {
            System.out.println("\nA. Play Preliminary Stage");
            System.out.println("B. Play Final");
            System.out.println("C. Display Teams");
            System.out.println("D. Display Players");
            System.out.println("E. Display Cup Result");
            System.out.println("X. Close");
            prompt = scan.nextLine().toUpperCase();
            switch (prompt)
            {
                case "A": 
                {
                    if (check == 0)
                    {
                        String[] teamNames = new String[teamList.size()];
                        for (int i = 0; i < teamList.size(); i++)
                        {
                            teamNames[i] = teamList.get(i).getName();
                        }
                        for (int k = 0; k < 2; k++)
                        {
                            for (int i = 0; i < teamList.size(); i++)
                            {
                                for(int j = i + 1; j < teamList.size(); j++)
                                {
                                    startPreMatch(teamNames[i], teamNames[j]);
                                    teamSort();
                                }
                            }
                        }
                        check++;
                    }
                    else System.out.println("Preliminary Stage has already been completed.");
                    break;
                }
                case "B":
                {   
                    if (check == 2) System.out.println("Final Stage has already been completed.");
                    if (check == 1)
                    {
                        startFinalMatch();
                        check++;
                    }
                    if (check == 0) System.out.println("Preliminary Stage needs to be played!");               
                    break;
                }
                case "C":
                {
                    if (check >= 1)
                    {
                        System.out.println("\t\tPlayed\tWon\tLost\tDrawn\tGoals\tPoints\tFair Play Score");
                        for (int i = 0; i < teamList.size(); i++)
                        {
                            Team t = teamList.get(i);
                            int total = t.getStats()[0] + t.getStats()[1] + t.getStats()[2];
                            int fpscore = t.getYellow() + t.getRed() * 2;
                            System.out.print(t.getName());
                            if (t.getName().length() <= 7) System.out.print("\t");
                            System.out.print("\t  " + total + "\t " + t.getStats()[1] + "\t " + t.getStats()[2] + "\t  " + t.getStats()[0] + "\t  " +
                                t.getGoals() + "\t  " + t.getPoint() + "\t      " + fpscore + "\n");
                        }
                    }
                    else System.out.println("Preliminary Stage needs to be played!");
                    break;
                }
                case "D":
                {
                    if (check >= 1)
                    {
                        for (int i = 0; i < teamList.size(); i++)
                        {
                            System.out.println("\n" + teamList.get(i).getScorer1().getName() + " (" + teamList.get(i).getName() + 
                                ") - " + teamList.get(i).getScorer1().getGoals());
                            System.out.println("\n" + teamList.get(i).getScorer2().getName() + " (" + teamList.get(i).getName() + 
                                ") - " + teamList.get(i).getScorer2().getGoals());
                        }
                    }
                    else System.out.println("Preliminary Stage needs to be played!"); 
                    break;
                }
                case "E":
                {
                    if (check == 2)
                    {
                        System.out.println();
                        System.out.println("Football World Cup Winner: " + cupWinner.getName());
                        System.out.println(getGoldenBoot());
                        System.out.print(getFairPlay());
                        System.out.println();
                    }
                    if (check == 0) System.out.println("Preliminary Stage and Final Stage needs to be played for the Cup Result!");
                    if (check == 1) System.out.println("Final Stage needs to be played for the Cup Result!");
                    break;
                }
                case "X":
                {
                    if (check == 2)
                    {
                        try
                        {
                            PrintWriter printfile = new PrintWriter("statistics.txt");
                            try
                            {
                                printfile.println("Football World Cup Winner: " + cupWinner.getName());
                                printfile.println(getGoldenBoot());
                                printfile.println(getFairPlay());
                            }
                            finally
                            {
                                System.out.println("Text file successfully saved....closing file.");
                                printfile.close();
                            }
                        }
                        catch (IOException e)
                        {
                            System.out.println("Unexpected I/O exception occurs! Save operation failed!");
                        }
                    }
                    else System.out.println("Because final match is not played, statistics.txt file is not saved!");
                    break;
                }
                default: System.out.println("Please select from the menu!"); break;
            }
        }
    }

    /**
     * If the final ends in a draw, this method helps to decide the winner.
     */
    public void playPenaltyShootOut()
    {
        int goal1 = 0;
        int goal2 = 0;
        for (int i = 0; i < 5 ; i++)
        {
            if (rng.getRandom(0, 1) == 1) goal1++;
            if (rng.getRandom(0, 1) == 1) goal2++;
        }
        if (goal1 > goal2) System.out.println("\nPenalty result: " + teamList.get(0).getName() + " " + goal1 + " vs. " + teamList.get(1).getName() + " " + goal2); 
        else System.out.println("\nPenalty result: " + teamList.get(1).getName() + " " + goal2 + " vs. " + teamList.get(0).getName() + " " + goal1);
        if (goal1 > goal2) cupWinner = teamList.get(0);
        if (goal2 > goal1) cupWinner = teamList.get(1);
        if (goal1 == goal2)
        {
            System.out.println("\nPenalty shoot out ended with draw going for another penalty shoot out!");
            playPenaltyShootOut();
        }
    }

    /**
     * This method sets the player goals in a single match for a team.
     * 
     * @param t team that players goaled.
     * @param score teams goal number in a single match.
     */
    public void setPlayerGoals(Team t, int score)
    {
        int goal = 0;
        goal = rng.getRandom(0, score);
        t.getScorer1().setGoal(goal);
        t.getScorer2().setGoal(score - goal);
    }

    /**
     * This method sets the yellow cards for a team.
     * 
     * @param t team that yellow cards are given.
     */
    public int setTeamYellowCards(Team t)
    {
        if (rng.getRandom(0, 10) == 10) 
        {
            t.setYellow(1);
            if (rng.getRandom(0, 10) == 10) 
            {
                t.setYellow(2);
                return 2;
            }
            return 1;
        }
        return 0;
    }

    /**
     * This method sets the red cards for a team.
     * 
     * @param t team that red cards are given.
     */
    public int setTeamRedCards(Team t)
    {
        if (rng.getRandom(0, 40) == 40)
        {
            if (rng.getRandom(0, 40) == 40)
            {
                t.setRed(2);
                return 2;
            }
            t.setRed(1);
            return 1;
        }
        return 0;
    }

    /**
     * This method plays the final match for the given teams.
     * 
     * @param t1 team 1 that is going to play final.
     * @param t2 team 2 that is going to play final.
     */
    public void startFinalMatch()
    {
        int goal1 = 0; //Team 1 number of goals in the match.
        int goal2 = 0; //Team 2 number of goals in the match.
        Team t1 = teamList.get(0);
        Team t2 = teamList.get(1);
        goal1 = rng.getRandom(0, 5 + rng.getRandom(0,2));
        goal2 = rng.getRandom(0, 5 - (t2.getRank() - t1.getRank()) + rng.getRandom(0,2));
        if (goal1 > goal2) cupWinner = t1;
        if (goal2 > goal1) cupWinner = t2;       
        if (goal1 > goal2) System.out.println("\nGame result: " + teamList.get(0).getName() + " " + goal1 + " vs. " + teamList.get(1).getName() + " " + goal2); 
        else System.out.println("\nGame result: " + teamList.get(1).getName() + " " + goal2 + " vs. " + teamList.get(0).getName() + " " + goal1);
        if (goal1 == goal2) 
        {
            System.out.println("\nFinal has ended drawn panalty shoot out will decide the winner!");
            playPenaltyShootOut();
        } 
    }

    /**
     * This method plays the preliminary match for the given teams.
     * 
     * @param s1 name of the team 1 that is going to play the preliminary match.
     * @param s2 name of the team 2 that is going to play the preliminary match.
     */
    public void startPreMatch(String s1, String s2)
    {
        Team t1 = new Team(); //Team 1 that is going to play preliminary match
        Team t2 = new Team(); //Team 2 that is going to play preliminary match
        int goal1 = 0; //Team 1 number of goals in the match.
        int goal2 = 0; //Team 2 number of goals in the match.
        int yellow1 = 0; //Team 1 number of yellow cards in the match.
        int yellow2 = 0; //Team 2 number of yellow cards in the match.
        int red1 = 0; //Team 1 number of red cards in the match.
        int red2 = 0; //Team 2 number of red cards in the match.
        for(int i = 0; i < teamList.size(); i++)
        {
            if(teamList.get(i).getName().equals(s1)) t1 = teamList.get(i);
            if(teamList.get(i).getName().equals(s2)) t2 = teamList.get(i);
        }
        if (t1.getRank() < t2.getRank())
        {
            goal1 = rng.getRandom(0, 5 + rng.getRandom(0,2));
            goal2 = rng.getRandom(0, 5 - (t2.getRank() - t1.getRank()) + rng.getRandom(0,2));
        }
        else
        {
            goal1 = rng.getRandom(0, 5 - (t1.getRank() - t2.getRank()) + rng.getRandom(0,2));
            goal2 = rng.getRandom(0, 5 + rng.getRandom(0,2)); 
        }       
        t1.setGoals(goal1);
        t2.setGoals(goal2);
        setPlayerGoals(t1,goal1);
        setPlayerGoals(t2,goal2);
        yellow1 = setTeamYellowCards(t1);
        red1 = setTeamRedCards(t1);
        yellow2 = setTeamYellowCards(t2);
        red2 = setTeamRedCards(t2);
        if (goal1 > goal2) 
        {
            t1.setPoint(3);
            t1.statWin();
            t2.statLose();
        }
        if (goal2 > goal1) 
        {
            t2.setPoint(3);
            t2.statWin();
            t1.statLose();
        }
        if (goal1 == goal2) 
        {
            t1.setPoint(1);
            t2.setPoint(1);
            t1.statDraw();
            t2.statDraw();
        }  
        displayGameResult(t1, t2, goal1, goal2, yellow1, red1, yellow2, red2);
    }

    /**
     * This method sorts the team array list according to their league points.
     */
    public void teamSort()
    {
        Team t = new Team();
        int count = 0;
        while (count < 3)
        {
            for (int i = 0; i < teamList.size() - 1; i++)
            {
                if (teamList.get(i).getPoint() < teamList.get(i + 1).getPoint())
                {
                    t = teamList.get(i);
                    teamList.set(i, teamList.get(i + 1));
                    teamList.set(i + 1, t);
                }
                if (teamList.get(i).getPoint() == teamList.get(i + 1).getPoint())
                {
                    if (teamList.get(i).getGoals() < teamList.get(i + 1).getGoals())
                    {
                        t = teamList.get(i);
                        teamList.set(i, teamList.get(i + 1));
                        teamList.set(i + 1, t);
                    }
                    if (teamList.get(i).getGoals() == teamList.get(i + 1).getGoals())
                    {
                        if (rng.getRandom(1, 2) == 2)
                        {
                            t = teamList.get(i);
                            teamList.set(i, teamList.get(i + 1));
                            teamList.set(i + 1, t);
                        }
                    }
                }
            }
            count++;
        }
        for (int j = 0; j < teamList.size(); j++)
        {
            teamList.get(j).setRank(j + 1);
        }
    }
}
