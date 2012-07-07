import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class to maintain a row of seats in a cinema hall.
 * Stores collection of seats.
 * Can find seats by number.
 * Requests for reservations.
 * 
 * @author Fariha Nazmul 
 * @version 01.11.10
 */
public class Row implements Serializable
{
    // the number corresponding to this row
    private int rowNumber;
    // the number of seats in this row
    private int rowLength;
    // the list of seats in the row
    private ArrayList<Seat> seats; 

    /**
     * Constructor for objects of class Row
     */
    public Row(int rowNumber, int rowLength)
    {
        this.rowNumber = rowNumber;
        this.rowLength = rowLength;
        this.seats = new ArrayList<Seat>();

        for(int i = 1; i <= rowLength; i++){
            Seat tempSeat = new Seat(rowNumber, i);
            seats.add(tempSeat);
        }               
    }
    
    /**
     * Returns the row number.
     * @return     the row number
     */
    public int getRowNumber()
    {
        return rowNumber;
    }
    
    /**
     * Returns the row length.
     * @return     the number of seats in the row
     */
    public int getRowLength()
    {
        return rowLength;
    }
    
    /**
     * Looks up for the number of seats booked on a particular day.
     * @param day the day to look up
     * @return     the number of booked seats
     */
    public int getBookedSeats(Day day)
    {
        int bookedSeats = 0;
        for(int i = 0; i < rowLength; i++){
            if(seats.get(i).getBookingStatus(day))
                bookedSeats++;
        }
        return bookedSeats;
    }
    
    /**
     * Returns a single seat.
     * 
     * @param  seatNum    The number of the seat
     * @return     the indexed seat
     */
    public Seat getOneSeat(int seatNum)
    {
        return seats.get(seatNum-1);
    }

    /**
     * Returns a list of seats.
     *
     * @param  startSeatNum    The first seat number of the list
     * @param  endSeatNum      The last seat number of the list
     * @return     the list of seats
     */
    public ArrayList<Seat> getSomeSeats(int startSeatNum, int endSeatNum)
    {
        ArrayList<Seat> someSeats = new ArrayList<Seat>();
        for(int i = startSeatNum-1; i < endSeatNum; i++ ){
            someSeats.add(seats.get(i));
        }
        return someSeats;
    }
    
    /**
     * Books a single seat on a day.
     * @param day       the day to book
     * @param seatNum   the seat number
     */
    public void bookOneSeat(Day day, int seatNum)
    {
        seats.get(seatNum-1).setBookingStatus(day, true);
    }
    
}
