
import java.io.Serializable;

/**
 * Representations for all the valid command words in the Cinema Booking System
 * along with their number of parameters.
 * Each command word is associated with a string in a particular language
 * and the minimum number of parameters needed for that command.
 *
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public enum CommandWord implements Serializable
{
    // A value for each command word along with its
    // corresponding user interface string and number of parameters.

    MODE("mode", 1),
    ADD_CINEMA("addcinema", 3),
    ADD_FILM("addfilm", 3),
    ADD_FROM("addfrom", 1),
    LOAD("load", 1),
    SAVE("save", 1),
    WHEN("when", 1),
    WHERE("where", 2),
    BOOK("book", 5),
    SHOW("show", 1),
    SHOW_ALL("showall", 1),
    EXIT("exit", 0),
    UNKNOWN("unknown",0);

    // The command as string.
    private String commandString;
    // The number of parameters
    private int numOfParam;

    /**
     * Initialise with the corresponding command word and num of parameter.
     * @param commandWord The command string.
     * @param numOfParam The valid number of paramters for the command.
     */

    CommandWord(String commandString, int numOfParam)
    {
        this.commandString = commandString;
        this.numOfParam = numOfParam;
    }

    /**
     * Returns the string representation of the command word.
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }


    /**
     * Returns the number of parameters for the command.
     * @return The num of parameters needed for the command.
     */
    public int getNumOfParam()
    {
        return numOfParam;
    }

}
