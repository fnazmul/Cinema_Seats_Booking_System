
import java.io.Serializable;


/**
 * Captures an input that is not recognized as any valid command for the system.
 * 
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class UnrecognizedCommandException
        extends Exception implements Serializable
{
    // The inputCommand that is unrecognized as a command.
    private String inputCommand;

    /**
     * Stores the details in error.
     * @param inputCommand The input that is unrecognized.
     */
    public UnrecognizedCommandException(String inputCommand)
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
        return "Unrecognized command: " + inputCommand.toUpperCase();
    }

    public String getMessage(){
        return "Unrecognized command: " + inputCommand.toUpperCase();
    }
}
