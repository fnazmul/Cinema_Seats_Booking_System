
import java.io.Serializable;


/**
 * Enumeration class Day
 * Representations for all the days of a week
 * 
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public enum Day implements Serializable
{
    // A value for each day along with its
    // corresponding user interface string
    // and a integer that helps to index day arrays.
    
     MON("mon", 0), 
     TUE("tue", 1),
     WED("wed", 2),
     THU("thu", 3),
     FRI("fri", 4),
     SAT("sat", 5),
     SUN("sun", 6);
    
    // The day string.
    private String dayString;
    // The corresponding integer
    private int dayInteger;
    
    /**
     * Initialise with the corresponding day string.
     * @param dayString  The first three letters of the day string.
     * @param dayInteger The integer value of the day.
     */
    Day(String dayString, int dayInteger)
    {
        this.dayString = dayString;
        this.dayInteger = dayInteger;
    }
    
    /**
     * Returns string representation of the Day.
     * @return day as a string.
     */
    public String toString()
    {
        return dayString;
    }

    /**
     * Returns corresponding integer value of the Day.
     * @return day as an Integer.
     */
    public int toInteger()
    {
        return dayInteger;
    }

}
