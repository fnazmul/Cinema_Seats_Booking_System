import java.io.Serializable;
import java.util.ArrayList;
/**
 * A class to maintain the information of each booking.
 * Each booking holds the following information:
 *      Name of the person,
 *      Name of the film,
 *      Name of the cinema,
 *      Day of the show,
 *      Number of seats reserved
 * 
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class BookingInformation implements Serializable
{
    // The name of the person booking the seats
    private String personName;
    // The filmShow consisting the information of the show
    //i.e. film name, cinema name and day
    private FilmShow filmShow;
    // The reserved seats
    private ArrayList<Seat> reservedSeats;

    /**
     * Constructor for objects of class BookingInformation
     * @param personName Name of the person
     * @param filmShow Information of the filmShow
     * @param seats The list of reserved seats
     */

    public BookingInformation(String personName, FilmShow filmShow,
            ArrayList<Seat> seats)
    {
        this.personName = personName;
        this.filmShow = filmShow;
        this.reservedSeats = (ArrayList<Seat>) seats;
    }

    /**
     * Returns the name of the person making the booking
     * @return     the name of the person
     */
    public String getPersonName()
    {
        return this.personName;
    }

    /**
     * Returns the information of the film show
     * @return     the film show
     */
    public FilmShow getFilmShow()
    {
        return this.filmShow;
    }

    /**
     * Returns the list of reserved seats
     * @return     the list of seats
     */
    public ArrayList<Seat> getReservedSeats()
    {
        return this.reservedSeats;
    }

    /**
     * A String representation of the booking information
     * @return     the string representation of booking information
     */
    public String toString()
    {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("\n"+ "Person Name: " + personName.toUpperCase() +
            "\n"+ filmShow.toString()+ "\n" + "Reserved Seats: ");
        for(Seat s: reservedSeats)
        {
            strBuffer.append(s.toString() + " ");

        }
        return strBuffer.toString();
    }    
}
