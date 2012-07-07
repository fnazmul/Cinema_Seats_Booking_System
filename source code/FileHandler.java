

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides the file-handling operations on an CinemaSystemEngine.
 *
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class FileHandler implements Serializable {
    // The system on which i/o operations are performed.

    private CinemaSystemEngine cinemaSystem;

    /**
     * Constructor for objects of class FileHandler.
     * @param cinemaSystem The cinemaSystem to use.
     */
    public FileHandler(CinemaSystemEngine cinemaSystem) {
        this.cinemaSystem = cinemaSystem;
    }

    /**
     * Reads and runs addCinema and addFilm commands from a text file.
     * The file is assumed to contain one command with parameters
     * separated by space in each line.
     * @param command The command with its parameters
     * @throws IOException On input failure.
     * @throws IllegalArgumentException On incorrect parameters of the command
     * @throws InvalidCommandException  On passing command not valid in the mode
     * @throws UnrecognizedCommandException On passing Unknown command
     */
    public String addFrom(Command command)
            throws IOException, IllegalArgumentException,
            InvalidCommandException, UnrecognizedCommandException {
        String inputLine;
        Command commandInFile;
        ArrayList<String> parameterList;
        Scanner reader, tokenized;

        StringBuffer result = new StringBuffer();

        CommandWords commands = new CommandWords();
        String fileName = null, commandString = null;

        if (command.getParameterList().size()
                == command.getCommandWord().getNumOfParam()) {
            fileName = command.getParameterList().get(0);
        }
        if (fileName != null) {
            reader = new Scanner(new File(fileName));

            do {
                inputLine = reader.nextLine();
                // Find the parts of the command in the line.
                tokenized = new Scanner(inputLine);
                if (tokenized.hasNext()) //the command string
                {
                    commandString = tokenized.next().toLowerCase();
                }
                parameterList = new ArrayList<String>();
                while (tokenized.hasNext()) {    //the parameters
                    parameterList.add(tokenized.next());
                }
                commandInFile = new Command(commands.getCommandWord(
                        commandString), parameterList);

                //execute the commands
                if (cinemaSystem.checkCommand(commandInFile,
                        cinemaSystem.getMode())) {
                    switch (commandInFile.getCommandWord()) {
                        case ADD_CINEMA:
                            result.append(cinemaSystem.addCinema(commandInFile));
                            result.append("\n");
                            break;
                        case ADD_FILM:
                            result.append(cinemaSystem.addFilm(commandInFile));
                            result.append("\n");
                            break;
                        default:
                            break;
                    }
                } else {
                    throw new InvalidCommandException(
                            commandInFile.getCommandWord().toString());
                }
            } while (reader.hasNextLine());
            reader.close();
        } else {
            throw new IllegalArgumentException("Correct argument not " +
                    "specified in addFrom command.");
        }
        return result.toString();
    }

    /**
     * Reads the binary version of an Cinema Seat Booking System from given file.
     * @param command The command with its parameters
     * @return The cinema system engine object.
     * @throws IllegalArgumentException On incorrect parameters of the command
     * @throws UnrecognizedCommandException On passing Unknown command
     */
    public CinemaSystemEngine load(Command command) throws IOException,
            ClassNotFoundException, IllegalArgumentException {
        String sourceFile = null;

        if (command.getParameterList().size()
                == command.getCommandWord().getNumOfParam()) {
            sourceFile = command.getParameterList().get(0);
        }
        if (sourceFile != null) {
            File source = new File(sourceFile);
            ObjectInputStream is = new ObjectInputStream(
                    new FileInputStream(source));
            CinemaSystemEngine savedSystem =(CinemaSystemEngine)is.readObject();
            is.close();
            return savedSystem;

        } else {
            throw new IllegalArgumentException("Correct argument not " +
                    "specified in LOAD command.");
        }
    }

    /**
     * Saves a binary version of the CinemaSystemEngine to the given file.
     * @param command The command with its parameters
     * @throws IOException If the saving process fails for any reason.
     * @throws IllegalArgumentException On incorrect parameters of the command
     */
    public String save(Command command)
            throws IOException, IllegalArgumentException {
        String destinationFile = null;

        if (command.getParameterList().size()
                == command.getCommandWord().getNumOfParam()) {
            destinationFile = command.getParameterList().get(0);
        }
        if (destinationFile != null) {
            File destination = new File(destinationFile);
            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream(destination));
            os.writeObject(cinemaSystem);
            os.close();
            return new String("Saved to file: " + destinationFile);
        } else {
            throw new IllegalArgumentException("Correct argument not " +
                    "specified in SAVE command.");
        }
    }
}
