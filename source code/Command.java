import java.io.Serializable;
import java.util.ArrayList;
/**
 * This class holds information about a command issued by the user.
 * Each command has two parts: 
 *      a CommandWord and a list of parameters      
 * If the command had only one word, then the parameter list is empty.
 * 
 * @author  Fariha Nazmul
 * @version 01.11.10
 */

public class Command implements Serializable
{
    private CommandWord commandWord;
    private ArrayList<String> parameterList;

    /**
     * Create a command object. 
     * @param commandWord The CommandWord. 
     * @param parameterList The list of parameters passed to the command.
     * It can also be empty.
     */
    public Command(CommandWord commandWord, ArrayList<String> parameterList)
    {
        this.commandWord = commandWord;
        this.parameterList = new ArrayList<String>();
        this.parameterList = parameterList;
    }

    /**
     * Returns   The command word of this command.
     * @return  The command word.
     */
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    /**
     * Returns the list of parameters of the command.
     * @return The parameter list of this command. 
     */
    public ArrayList<String> getParameterList()
    {
        return parameterList;
    }

    /**
     * Checks if the command is Unknown.
     * @return true if this command is unknown, false otherwise.
     */
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }
    
    /**
     * Checks if the command has any parameters.
     * @return true if the parameter list is not empty, false otherwise.
     */
    public boolean hasParameters()
    {
        return (parameterList.size() != 0);
    }
}

