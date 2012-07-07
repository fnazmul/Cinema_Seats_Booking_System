/**
 * Provides the interfaces of a simple Cinema Seats Booking System.
 * 
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class CinemaSystem
{
    /**
     *The main function to run the command line interface
     * @param args String "GUI" to run the GUI interface,
     *                 Runs the Console if no parameter is given
     */

    public static void main(String[] args){
        //the cinema system engine
        CinemaSystemEngine cinemaSystem = new CinemaSystemEngine();

        Interface interaction;

        if(args.length!=0 && args[0].equalsIgnoreCase("GUI"))
       //starts the GUI
            interaction = new GraphicalInterface(cinemaSystem);
        else
            //starts the command line interface
            interaction = new CommandLineInterface(cinemaSystem);
        interaction.run();
    }
    
}
