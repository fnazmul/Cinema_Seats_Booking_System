import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This parser reads user input and tries to interpret it as a valid command.
 * Each time it reads a line from the terminal and
 * tries to interpret the line as a command with two parts i.e.
 * a valid commandString and a list of parameters.
 * It returns the command as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Fariha Nazmul
 * @version 01.11.10
 */

public class Parser implements Serializable
{
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    /**
     * Creates a parser to read from the terminal window.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }


    /**
     * Returns the next command from the user.
     * @return The next command from the user.
     */
    public Command getCommand() 
    {
        String inputLine;   // will hold the full input line
        String commandString = null;
        ArrayList<String> parameterList = new ArrayList<String>();

        inputLine = reader.nextLine();

        // Finds the parts of the command in the line.
        Scanner tokenized = new Scanner(inputLine);
        if(tokenized.hasNext()) {
            // the commad string
            commandString = tokenized.next().toLowerCase();
            // the list of parameters
            while(tokenized.hasNext()) {
               parameterList.add(tokenized.next());
            }
        }
        
       return new Command(commands.getCommandWord(commandString),parameterList);
    }
    
}
