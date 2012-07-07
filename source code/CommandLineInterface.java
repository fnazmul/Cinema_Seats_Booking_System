
import java.io.IOException;

/**
 * Provides a command line interface to the Cinema Booking System.
 * Different commands provide access to the data in the system.
 *
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class CommandLineInterface implements Interface

{
    // The cinema booking system to be viewed and manipulated.
    private CinemaSystemEngine cinemaSystem;
    // A parser for handling user commands.
    private Parser parser;
    // A file handler for the IO operations
    private FileHandler fileHandler;

    /**
     * Constructor for objects of class CommandLineInterface
     * @param cinemaSystem The system to be used.
     *
     */
    public CommandLineInterface(CinemaSystemEngine cinemaSystem)
    {
        this.cinemaSystem = cinemaSystem;
        parser = new Parser();
        fileHandler = new FileHandler(this.cinemaSystem);
    }

        
    /**
     * Reads a series of commands from the user to interact
     * with the cinema booking system. 
     * Stops only when the admin types 'exit'.
     */
    public void run()
    {
        printWelcome();
        // Enters the main command loop.
        //Here we repeatedly read commands and
        // execute them until admin wants to exit.

        boolean finished = false;
        while (! finished) {
            System.out.print(">");
            Command command = parser.getCommand();

            try {
                finished = processCommand(command);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } catch (UnrecognizedCommandException ex) {
                System.out.println(ex.toString());
            } catch (InvalidCommandException ex) {
                System.out.println(ex.toString());
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.toString());
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        System.out.println("Thank you. Goodbye!");
    }

     /**
     * Print out the opening message for the user.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Cinema Seats Booking System!");
        System.out.println("Please enter your command...");
        System.out.println();
    }
 

    /**
     * Given a command, processes the command
     * and performs actions accordingly.
     * @param command The command to be processed.
     * @return true if the command quits the system, false otherwise.
     */
    public boolean processCommand(Command command) 
            throws IllegalArgumentException, UnrecognizedCommandException,
            InvalidCommandException, IOException, ClassNotFoundException
    {
        boolean wantToExit = false;

        //check if the command is valid and
        //also the user mode is legal to run the command
        if(cinemaSystem.checkCommand(command, cinemaSystem.getMode())){

            CommandWord commandWord = command.getCommandWord();
            //choose the functions according to the commands
            switch(commandWord){
                case MODE:  
                    System.out.println(cinemaSystem.setMode(command));
                    break;
                case ADD_CINEMA: 
                    System.out.println(cinemaSystem.addCinema(command));
                    break;
                case ADD_FILM: 
                    System.out.println(cinemaSystem.addFilm(command));
                    break;
                case ADD_FROM: 
                    System.out.println(fileHandler.addFrom(command));
                    break;
                case LOAD:
                {this.cinemaSystem = fileHandler.load(command);
                 System.out.println("Successfully loaded.");
                 break;}
                case SAVE: 
                    System.out.println(fileHandler.save(command));
                    break;
                case WHEN: 
                    System.out.println(cinemaSystem.when(command));
                    break;
                case WHERE: 
                    System.out.println(cinemaSystem.where(command));
                    break;
                case BOOK: 
                    System.out.println(cinemaSystem.book(command));
                    break;
                case SHOW: 
                    System.out.println(cinemaSystem.show(command));
                    break;
                case SHOW_ALL: 
                    System.out.println(cinemaSystem.showAll(command));
                    break;
                case EXIT:
                    wantToExit = true;break;
                default: break;
            }
            // else command not recognised.
        }
        return wantToExit;
    }
 
}
