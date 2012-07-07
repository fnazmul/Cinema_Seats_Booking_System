
import java.io.Serializable;

/**
 * Captures an input that is not considered as a valid command
 * of the system against the legal mode to execute.
 * 
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class InvalidCommandException extends Exception implements Serializable
{
    // The inputCommand that is invalid to run in the running mode.
    private String inputCommand;

    /**
     * Stores the details in error.
     * @param inputCommand The input that is invalid.
     */
    public InvalidCommandException(String inputCommand)
    {
        this.inputCommand = inputCommand;
    }

    /**
     * Returns the command in error.
     * @return The key in error.
     */
    public String getInputCommand()
    {
        return inputCommand;
    }
    
    /**
     * Returns the string representation of the error.
     * @return A diagnostic string containing the input in error.
     */
    public String toString()
    {
        return "Invalid mode to run the command: " + inputCommand.toUpperCase()
                + "\nAn error occurred while attempting " +
                "to run a privileged command.";
    }

}
