import java.io.Serializable;
import java.util.HashMap;

/**
 * A class to recognise Days as they are typed in.
 * 
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class DayParameters implements Serializable
{

    // A mapping between a day parameter string
    //and the Day associated with it.
    private HashMap<String, Day> dayParameters;

    /**
     * Constructor - initialises the day strings.
     */
    public DayParameters()
    {
        this.dayParameters = new HashMap<String, Day>();
        for(Day day : Day.values()) {
            this.dayParameters.put(day.toString(), day);
        }
    }

    /**
     * Finds the Day associated with a day parameter.
     * @param dayParameter The word to look up.
     * @return The Day correspondng to dayParameter
     */
    public Day getDay(String dayParameter)
    {
        Day day = dayParameters.get(dayParameter);
        return day;
        
    }
    
    /**
     * Checks whether a given String is a valid day parameter.
     * @param dayString The day string
     * @return true if it is, false otherwise.
     */
    public boolean isDay(String dayString)
    {
        return dayParameters.containsKey(dayString);
    }

    /**
     * Prints all days to System.out.
     */
    public void showAll() 
    {
        for(String day : dayParameters.keySet()) {
            System.out.print(day + "  ");
        }
        System.out.println();
    }

}
