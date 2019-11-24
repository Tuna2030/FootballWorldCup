/**
 * This is a Football World Cup program Player class. Player class contains the player name and number of goals.
 *
 * @author Tuna Rezaiazar
 * @version 09/05/2018
 */
public class Player
{
    private String name; //Player name is saved in this field.
    private int goals; //Player number of goals are saved in this field.

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        name = "";
        goals = 0;
    }

    /**
     * This method returns the number of goals of a Player.
     *
     * @return    the number of goals.
     */
    public int getGoals()
    {
        return this.goals;
    }

    /**
     * This method returns the name of a Player.
     *
     * @return    the name.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * This method adds the goals to the Players goal field.
     *
     * @param goal    goals that are achived in a match.
     */
    public void setGoal(int goal)
    {
        this.goals = this.goals + goal;
    }

    /**
     * This method saves the name to the Players name field. Before saving the name method checks if the name variable fulfills the requirements.
     *
     * @param name    name that needs to saved for the Player object.
     */
    public boolean setName(String name)
    {
        String trimText = "";
        for (int i = 0; i < name.length(); i++) 
        {
            if (name.charAt(i) != ' ') trimText = trimText + name.charAt(i);
        }
        name = trimText;
        if(name != "")
        {
            if (name.charAt(0) == '-' || name.charAt(name.length() - 1) == '-')
            {
                System.out.println("Player name can not begin or end with hypen!");
                return false;
            }
        }
        int hypen = 0;
        int alpha = 0;
        for (int i = 0; i < name.length(); i++)
        {
            if (name.charAt(i) == '-') hypen++;
            if (hypen > 1) 
            {
                System.out.println("Player name can only contain 1 hypen!");
                return false;
            }
            if (name.charAt(i) == '-') continue;
            if (!Character.isLetterOrDigit(name.charAt(i))) 
            {
                System.out.println("Player name must only contain alphabetical characters!");
                return false;
            }
            if (Character.isAlphabetic(name.charAt(i))) alpha++;
        }
        if (alpha < 2) 
        {
            System.out.println("Player should contain at least 2 alphabetic characters!");
            return false;
        }
        if (name.length() <= 15) this.name = name;
        else
        {
            System.out.println("Player name must be less than or equal to 15 characters!\n");
            return false;
        }
        return true;
    }
}
