
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class to maintain an arbitrary number of cinemas.
 *
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class CinemaRepository implements Serializable {
    // Storage for an arbitrary number of cinemas.

    private ArrayList<Cinema> allCinema;

    /**
     * Perform any initialization for the cinema collection.
     */
    public CinemaRepository() {
        allCinema = new ArrayList<Cinema>();
    }

    /**
     * Checks if a Ciname is already created.
     * @param cinemaName The name of the cinema
     * @return true if the Cinema exists, else false
     */
    public boolean isCinema(String cinemaName) {
        for (Iterator it = allCinema.iterator(); it.hasNext();) {
            if (((Cinema) (it.next())).getName().equals(cinemaName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new cinema to the collection.
     * @param newCinema The cinema to be added
     * @return Status of the add operation
     */
    public String addCinema(Cinema newCinema) throws NullPointerException {
        StringBuffer result = new StringBuffer();
        if (newCinema != null) {
            //checks if the cinema already exists
            if (this.isCinema(newCinema.getName())) {
                result.append("Cinema Hall Exists: ");
                result.append("\n" + newCinema.getName().toUpperCase()
                        + " already exists in the system.");
                return result.toString();
            } else {
                allCinema.add(newCinema);
                result.append(newCinema.getName().toUpperCase()
                        + " added to the system.");
            }
        } else {
            throw new NullPointerException("No cinema provided with " +
                    "addCinema command.");
        }

        return result.toString();
    }

    /**
     * Books number of seats for a certain film show.
     * @param filmShow The information of the filmshow
     * @param numOfSeats Number of seats to be booked
     * @return The list of reserved books
     */
    public ArrayList<Seat> bookSeat(FilmShow filmShow, int numOfSeats) {
        int availableSeats = 0, bookedSeats = 0, theatreIndex = 0;
        Theatre theatre = null;
        ArrayList<Seat> seats = new ArrayList<Seat>();
        for (int i = 0; i < allCinema.size(); i++) {
            if (allCinema.get(i).getName().equals(filmShow.getCinemaName())) {
                theatre = allCinema.get(i).getTheatre();
                theatreIndex = i;
            }
        }
        //check first if all the seats are available or not
        for (int i = 1; i <= theatre.getNumberOfRows(); i++) {
            bookedSeats=theatre.getRow(i).getBookedSeats(filmShow.getShowDay());
            bookedSeats++;
            for (int j = availableSeats; j < numOfSeats
                    && bookedSeats <= theatre.getRowLength(); j++) {
                seats.add(theatre.getRow(i).getOneSeat(bookedSeats));
                availableSeats++;
                bookedSeats++;
            }
            if (availableSeats >= numOfSeats) {
                break;
            }
        }
        //book the seats if they are availabe
        if (availableSeats >= numOfSeats) {
            for (int i = 0; i < seats.size(); i++) {
                allCinema.get(theatreIndex).getTheatre().getRow(
                        seats.get(i).getRowNumber()).bookOneSeat(
                          filmShow.getShowDay(), seats.get(i).getSeatNumber());
            }
        } //seats are not available
        else {
            seats = null;
        }
        return seats;
    }

    /**
     * Returns the list of all cinema names in the system.
     * @return the list of cinemas
     */
    public ArrayList<String> getAllCinemas() {

        ArrayList<String> cinemaList = new ArrayList<String>();
        for (Cinema cinema : allCinema) {
            if (!cinemaList.contains(cinema.getName())) {
                cinemaList.add(cinema.getName());
            }
        }
        return cinemaList;
    }
}
