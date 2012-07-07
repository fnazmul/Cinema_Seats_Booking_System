
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Provides the engine of a simple Cinema Seats Booking System.
 *
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class CinemaSystemEngine implements Serializable {

    private User mode;                //the user mode
    private DayParameters dayParams;    //the mapping of enumeration of days
    private CommandWords commandWords;  //the mapping of enumeration of commands
    private CinemaRepository cinemaRepository;     //the collection of cinemas
    private FilmShowRepository filmShowRepository; //the collection of filmShows
    private BookingRepository bookingRepository;  //the collection of bookings

    /**
     * Allows the user to interact with the cinema booking system.
     */
    public CinemaSystemEngine() {
        mode = User.USER;
        dayParams = new DayParameters();
        commandWords = new CommandWords();
        cinemaRepository = new CinemaRepository();
        filmShowRepository = new FilmShowRepository();
        bookingRepository = new BookingRepository();
    }

    /**
     * Returns the mode of the user.
     * @return The user mode
     */
    public User getMode() {
        return mode;
    }

    /**
     * Sets the mode of the user.
     * @param command The command
     * @return The string representing the status of mode change
     * @throws IllegalArgumentException when proper parameter is not passed
     */
    public String setMode(Command command) throws IllegalArgumentException {
        String param = null;
        User user = User.USER;
        UserParameters allUsers = new UserParameters();

        param = command.getParameterList().get(0).toLowerCase();
        user = allUsers.getUser(param);
        
        if ((param != null) && allUsers.isUser(param)) {
            this.mode = user;
            return "Mode changed to " + mode.toString().toUpperCase();
        } else {
            throw new IllegalArgumentException("Correct mode is not " +
                    "specified in MODE command.");
        }
    }

    /**
     * Adds a Cinema in the Cinema Repositoy.
     * @param command The command
     * @return The string representing the status of adding cinema
     * @throws NullPointerException when null is passed
     * @throws IllegalArgumentException when proper parameter is not passed
     */
    public String addCinema(Command command) throws NullPointerException,
            IllegalArgumentException {
        String cinemaName = null;
        int rowNum = 0, rowLength = 0;
        
        //getting all parameters
        cinemaName = command.getParameterList().get(0).toLowerCase();
        rowNum = Integer.parseInt(command.getParameterList().get(1));
        rowLength = Integer.parseInt(command.getParameterList().get(2));

        //checking each parameter
        if (cinemaName != null && rowNum > 0 && rowNum <= 30 && rowLength > 0
                && rowLength <= 20) {
            Cinema tempCinema = new Cinema(cinemaName, rowNum, rowLength);
            return cinemaRepository.addCinema(tempCinema);
        } else {
            throw new IllegalArgumentException("Correct argument not " +
                    "specified in addCinema command.");
        }
    }

    /**
     * Adds a Film show in the FilmShow Repository.
     * @param command The command
     * @return The string representing the status of adding films show
     * @throws NullPointerException when null is passed
     * @throws IllegalArgumentException when proper parameter is not passed
     */
    public String addFilm(Command command) throws NullPointerException,
            IllegalArgumentException {
        Set<Day> days = new HashSet<Day>();
        String filmName = null, cinemaName = null;

        StringBuffer result = new StringBuffer();

        //getting all parameters
        filmName = command.getParameterList().get(0).toLowerCase();
        cinemaName = command.getParameterList().get(1).toLowerCase();
        for (int i = 2; i < command.getParameterList().size(); i++) {
          if(dayParams.isDay(command.getParameterList().get(i).toLowerCase())){
              days.add(dayParams.getDay(
                      command.getParameterList().get(i).toLowerCase()));
          }
        }
        
        //checking each parameter
        if (filmName != null && cinemaName != null && days.size() != 0) {
            if (cinemaRepository.isCinema(cinemaName)) {
                for (Iterator it = days.iterator(); it.hasNext();) {
                    FilmShow filmShow = new FilmShow(filmName,
                            cinemaName, ((Day) (it.next())));
                    result.append(filmShowRepository.addFilmShow(filmShow));
                    result.append("\n");
                }
            } else {
                result.append("No Cinema Found:" + "\n" +
                        cinemaName.toUpperCase() + " does not exist.");
            }
            return result.toString();
        } else {
            throw new IllegalArgumentException("Correct argument not " +
                    "specified in addFilm command.");
        }
    }

    /**
     * Returns the days when a film is shown.
     * @param command The command
     * @return The string representing the days
     * @throws NullPointerException when null is passed
     * @throws IllegalArgumentException when proper parameter is not passed
     */
    public String when(Command command) throws NullPointerException,
            IllegalArgumentException {
        String filmName = null;

        filmName = command.getParameterList().get(0).toLowerCase();

        if (filmName != null) {
            return filmShowRepository.getWhenFilmShow(filmName);
        } else {
            throw new IllegalArgumentException("Correct argument not " +
                    "specified in WHEN command.");
        }
    }

    /** Returns the cinema where a film is shown on a day.
     * @param command The command
     * @return The string representing the cinemas
     * @throws NullPointerException when null is passed
     * @throws IllegalArgumentException when proper parameter is not passed
     */
    public String where(Command command) throws NullPointerException,
            IllegalArgumentException {
        String filmName = null;
        Day day = null;

        filmName = command.getParameterList().get(0).toLowerCase();
        if (dayParams.isDay(command.getParameterList().get(1).toLowerCase())) {
            day = dayParams.getDay(
                    command.getParameterList().get(1).toLowerCase());
        }
        
        if (filmName != null && day != null) {
            return filmShowRepository.getWhereFilmShow(filmName, day);
        } else {
            throw new IllegalArgumentException("Correct argument not " +
                    "specified in WHERE command.");
        }
    }

    /** Books the seats for a certain film show.
     * @param command The command
     * @return The string representing the status of booking
     * @throws NullPointerException when null is passed
     * @throws IllegalArgumentException when proper parameter is not passed
     */
    public String book(Command command) throws IllegalArgumentException {
        String personName = null, filmName = null, cinemaName = null;
        Day showDay = null;
        int numOfSeats = 0;

        //getting all the parameters
        personName = command.getParameterList().get(0).toLowerCase();
        filmName = command.getParameterList().get(1).toLowerCase();
        cinemaName = command.getParameterList().get(2).toLowerCase();
        numOfSeats = Integer.parseInt(command.getParameterList().get(4));
        if (dayParams.isDay(command.getParameterList().get(3).toLowerCase())) {
            showDay = dayParams.getDay(
                    command.getParameterList().get(3).toLowerCase());
        }

        //checking each parameter
        if (personName != null && filmName != null && cinemaName != null
                && showDay != null && numOfSeats > 0) {
            FilmShow filmShow = new FilmShow(filmName, cinemaName, showDay);
            if (filmShowRepository.isFilmShow(filmShow)) {
                ArrayList<Seat> seats =
                        cinemaRepository.bookSeat(filmShow,numOfSeats);
                if (seats != null) {
                    //add booking info
                    bookingRepository.addBooking(new BookingInformation(
                            personName, filmShow, seats));
                    return new String("Successfully done." 
                            + bookingRepository.showBooking(
                                bookingRepository.getBookingNumber()));
                } else {
                    return new String("Not enough free seats are available.");
                }
            } else {
                return new String("No show Exists: " + filmName.toUpperCase() + 
                        " " + cinemaName.toUpperCase() + " "
                        + showDay.toString().toUpperCase());
            }

        } else {
            throw new IllegalArgumentException("Correct argument not " +
                    "specified in BOOK command.");
        }

    }

    /** Shows a booking information given a booking number.
     * @param command The command
     * @return The string representing the booking information
     * @throws NullPointerException when null is passed
     * @throws IllegalArgumentException when proper parameter is not passed
     */
    public String show(Command command)
    {
        int bookingNum = 0;

        bookingNum = Integer.parseInt(command.getParameterList().get(0));
        return bookingRepository.showBooking(bookingNum);
    }

    /** Shows all booking information given a person name.
     * @param command The command
     * @return The string representing all the booking informations
     * @throws NullPointerException when null is passed
     * @throws IllegalArgumentException when proper parameter is not passed
     */
    public String showAll(Command command) {
        String personName = null;

        personName = command.getParameterList().get(0).toLowerCase();
        return bookingRepository.showAllBooking(personName);
    }

    /**
     * Checks the legal mode of the command against the current user mode.
     * @param command The string of the command to be checked
     * @param userMode The mode of the user
     * @return true if the command is valid for the mode,
     * or throws exception otherwise.
     */
    public boolean checkCommand(Command command, User userMode) 
            throws UnrecognizedCommandException, InvalidCommandException,
            IllegalArgumentException {

        // checks the command is a valid command of the system
        if (!commandWords.isCommand(command.getCommandWord().toString())) {
            throw new UnrecognizedCommandException(
                    command.getCommandWord().toString());
        }

        // checks the legal mode of command
        if (!commandWords.isValid(command.getCommandWord(), userMode)) {
            throw new InvalidCommandException(
                    command.getCommandWord().toString());
        }

        // checks the number of parameters
        if (command.getParameterList().size() <
                command.getCommandWord().getNumOfParam()) {
            throw new IllegalArgumentException(
                    "Incorrect number of arguments for command: "
                    + command.getCommandWord().toString().toUpperCase());
        }

        return true;
    }

    /**
     * Returns the list of all film names in the system.
     * @return the list of films
     */
    public ArrayList<String> getAllFilms(){

        ArrayList<String> filmList = new ArrayList<String>();
        filmList = filmShowRepository.getAllFilms();
        return filmList;
    }

    /**
     * Returns the list of all cinema names in the system.
     * @return the list of cinemas
     */
    public ArrayList<String> getAllCinemas(){

        ArrayList<String> cinemaList = new ArrayList<String>();
        cinemaList = cinemaRepository.getAllCinemas();
        return cinemaList;
    }
}
