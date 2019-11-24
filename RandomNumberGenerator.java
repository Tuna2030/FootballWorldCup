
/**
 * This is a Football World Cup program RandomNumberGenerator class. This classes getRandom() method returns a random number between given integer numbers.
 *
 * @author Tuna Rezaiazar
 * @version 09/05/2018
 */
public class RandomNumberGenerator
{

    /**
     * Constructor for objects of class RandomNumberGenerator
     */
    public RandomNumberGenerator()
    {

    }

    /**
     * This method get 2 numbers as parameters and returns a random number in between these parameters.
     *
     * @param  min  minimum number that random number can be.
     * @param  max  minimum number that random number can be.
     * @return    a random number between parameters.
     */
    public int getRandom(int min , int max)
    {
        int random = (int)(Math.random() * (max - min + 1) + min);
        return random;
    } 
}
