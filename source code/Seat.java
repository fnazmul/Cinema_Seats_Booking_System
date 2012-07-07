
import java.io.Serializable;


/**
 * A class to maintain the information of each seat in a Cinema Hall.
 * A Seat can accept booking, stores the booked status
 * and also stores booking information
 * 
 * @author Fariha Nazmul 
 * @version 01.11.10
 */
public class Seat implements Serializable
{
    private int rowNumber;      //the row number
    private int seatNumber;     //the seat number
    private char seatPosition;  //seat representation
    private BookingStatus bookingStatus;    //status of the seats of each day
    
    
    /**
     * Constructor for objects of class Seat.
     */
    public Seat(int row, int seat)
    {
        rowNumber = row;
        seatNumber = seat;
        seatPosition = (char) ('A' + seat - 1);
        bookingStatus = new BookingStatus(Day.values().length);
    }

    /**
     * Checks the booking status of the seat.
     * @param day  the day to check the status
     * @return     true if the seat is booked, else false 
     */
    public boolean getBookingStatus(Day day)
    {
        return bookingStatus.getStatus(day.toInteger());
    }
    
    /**
     * Set the booking status of the seat to given input.
     * @param  day      The day to book
     * @param  status   booking status of the seat
     */
    public void setBookingStatus(Day day, boolean status)
    {
        bookingStatus.setStatus(day.toInteger(), status);
    }
    
    /**
     * Returns the row number of the seat.
     * @return     the row number 
     */
    public int getRowNumber()
    {
        return rowNumber;
    }
    
    /**
     * Returns the seat number.
     * @return     the seat number 
     */
    public int getSeatNumber()
    {
        return seatNumber;
    }
    
    /**
     * A string representation of the seat.
     * @return       the string representation of the seat
     */
    
    public String toString()
    {
        Integer row = new Integer(rowNumber);
        return row.toString()+seatPosition;
    }
    
    /**
     * Implements content equality for seats.
     * @return true if this Seat matches the other,
     *         false otherwise.
     */
    
    public boolean equals(Object other)
    {
        if(other instanceof Seat) {
            Seat otherSeat = (Seat) other;
            return rowNumber == otherSeat.getRowNumber() &&
                   seatNumber == otherSeat.getSeatNumber();
        }
        else {
            return false;
        }
    }
}
