
import java.io.Serializable;

/**
 * A class to maintain the booking status for day.
 * The list is indexed by the day
 * 
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class BookingStatus implements Serializable
{
    // A list of booleans to hold the booking status
    private boolean[] slots;

    /**
     * Constructor for objects of class BookingStatus
     * @param num The number of elements in the list
     */
    public BookingStatus(int num)
    {
        // initialise the list with false i.e. available
        slots = new boolean[num];
        for(int i = 0; i < num; i++)
            slots[i] = false;
    }

    /**
     * Return the status of the slot i
     * 
     * @param  i   Index number of the slot to be looked up
     * @return     the status of the slot
     */
    public boolean getStatus(int i)
    {
        return slots[i];
    }

    /**
     * Set the status of the slot i
     *
     * @param  i        Index number of the slot to set
     * @param  status   The status to set
     */
    public void setStatus(int i, boolean status)
    {
        this.slots[i] = status;
    }
}
