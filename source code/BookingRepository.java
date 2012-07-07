import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
/**
 * A class to maintain an arbitrary number of booking informations.
 * Each booking is indexed by the booking number.
 * 
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class BookingRepository implements Serializable
{
    // Storage for an arbitrary number of booking.
    private HashMap<Integer, BookingInformation> allBooking;
    // A unique booking number for each booking
    private int bookingNumber;

    /**
     * Performs initialization for the booking collection.
     */
    public BookingRepository()
    {
        allBooking = new HashMap<Integer, BookingInformation>();
        bookingNumber = 0;
    }

    /**
     * Returns whether or not the current booking number is in use.
     * @param key The number to be looked up.
     * @return true if the key is in use, false otherwise.
     */
    public boolean keyInUse(int key)
    {
        return allBooking.containsKey(new Integer (key));
    }

    /**
     * Returns the last booking number.
     * @return The last booking number
     */
    public int getBookingNumber()
    {
        return bookingNumber;
    }

    /**
     * Looks up a booking number and returns the
     * corresponding booking information.
     * @param bookingNum The booking number to be looked up.
     * @return The booking information corresponding to the number.
     */
    public BookingInformation getBooking(int bookingNum)
    {
        if(keyInUse(bookingNum))
            return allBooking.get(new Integer(bookingNum));
        else
            return null;
    }

    /**
     * Adds a new booking information to the collection.
     * @param booking The booking information associated
     *                  with next booking number
     */
    public void addBooking(BookingInformation booking)
    {
        bookingNumber++;
        if(booking != null)
            allBooking.put(new Integer(bookingNumber), booking);
    }

    
    /**
     * Searches for all booking informations stored under a person
     * with the given name.
     * @param personName The name of the person
     * @return A String representing all the bookings found.
     */
    public String showAllBooking(String personName)
    {
        boolean foundPerson = false;
        StringBuffer matches = new StringBuffer();
        if(personName != null) { 
            // Find the bookings that are made by the person.
            Iterator<Integer> it = allBooking.keySet().iterator();
            while(it.hasNext()) {
                Integer key = it.next();
                BookingInformation booking = allBooking.get(key);
                if(booking.getPersonName().equals(personName)) {
                    //at least one booking is found
                    foundPerson = true;
                    matches.append(this.showBooking(key));
                }
            }
        }
        if(!foundPerson)    //if there is no match
            matches.append("Person Not Found: " + personName.toUpperCase());

        return matches.toString();
    }

    /**
     * The booking information associated with the booking number.
     * @param bookingNum The booking number
     * @return The booking information
     */
     public String showBooking(int bookingNum)
     {
        StringBuffer booking = new StringBuffer();
        if(keyInUse(bookingNum)){
            booking.append("\nBooking Number: " + bookingNum);
            booking.append(((BookingInformation)(allBooking.get(
                    new Integer(bookingNum)))).toString());
        }
        else{
            booking.append("Booking Not Found: " +  bookingNum);
        }
        return booking.toString();
    }

}
