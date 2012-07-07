
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class holds an enumeration of all command words
 * known to the Cinema Booking System.
 * It is used to recognise commands as they are typed in.
 * It also to checks the legal mode of that command.
 *
 * @author  Fariha Nazmul
 * @version 01.11.10
 */

public class CommandWords implements Serializable
{
    // Mappings between a command word and the CommandWord
    // associated with it in different user mode.
    private HashMap<String, CommandWord> validAllCommands;
    // A mapping between a command and its valid users
    private HashMap<CommandWord, ArrayList<User>> legalUserForCommand;
    
    /**
     * Constructor - initialises the command words.
     */
    public CommandWords()
    {
        validAllCommands = new HashMap<String, CommandWord>();
        
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validAllCommands.put(command.toString(), command);                
            }
        }
        // set the mapping of command with its legal mode
        this.setLegalUser();
    }


    /**
     * Creates the mapping of command and its legal mode.
     */
    public void setLegalUser(){

        legalUserForCommand = new HashMap<CommandWord, ArrayList<User>>();
        //initialize all commands with empty user
        for (CommandWord command: CommandWord.values())
            legalUserForCommand.put(command, new ArrayList<User>());

        // set mapping for each command
        legalUserForCommand.get(CommandWord.ADD_CINEMA).add(User.ADMIN);
        legalUserForCommand.get(CommandWord.ADD_FILM).add(User.ADMIN);
        legalUserForCommand.get(CommandWord.ADD_FROM).add(User.ADMIN);
        legalUserForCommand.get(CommandWord.LOAD).add(User.ADMIN);
        legalUserForCommand.get(CommandWord.SAVE).add(User.ADMIN);
        legalUserForCommand.get(CommandWord.EXIT).add(User.ADMIN);
        legalUserForCommand.get(CommandWord.BOOK).add(User.CUSTOM);
        legalUserForCommand.get(CommandWord.MODE).addAll(
                Arrays.asList(User.values()));
        legalUserForCommand.get(CommandWord.WHEN).addAll(
                Arrays.asList(User.values()));
        legalUserForCommand.get(CommandWord.WHERE).addAll(
                Arrays.asList(User.values()));
        legalUserForCommand.get(CommandWord.SHOW).addAll(
                Arrays.asList(User.values()));
        legalUserForCommand.get(CommandWord.SHOW_ALL).addAll(
                Arrays.asList(User.values()));
    }


    /**
     * Finds the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validAllCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /**
     * Checks whether a given String is a valid command word for the system.
     * @param command The command string
     * @return true if it is, false otherwise.
     */
    public boolean isCommand(String command)
    {
        return validAllCommands.containsKey(command);
    }

     /**
     * Checks whether a given command is a valid
     * command word for a given user mode in the system.
     * @param command   The command word
     * @param mode      The mode of the user
     * @return true if it is, false otherwise.
     */

    public boolean isValid(CommandWord command, User mode)
    {
        if(legalUserForCommand.containsKey(command))
            return legalUserForCommand.get(command).contains(mode);
        else
            return false;
    }
    
}
