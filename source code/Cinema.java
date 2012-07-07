
import java.io.Serializable;
/**
 * A class to hold the information of the Cinema.
 * A cinema can have many theatres.
 * For simplicity, currently it holds only one thetre.
 * But with some modification, it can add more theatres
 * 
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class Cinema implements Serializable
{
    // The name of the Cinema
    private String cinemaName;
    // The theatre inside the cinema
    private Theatre theatre;
    //private ArrayList<Theatre> theatres;

    /**
     * Constructor for objects of class Cinema.
     * @param cinemaName    The name of the cinema
     * @param numberOfRows  The number of rows in cinema
     * @param rowLength     The number seats in each row
     */
    public Cinema(String cinemaName, int numberOfRows, int rowLength)
    {
        this.cinemaName = cinemaName;        
        this.theatre = new Theatre(cinemaName, numberOfRows, rowLength);
        //addTheatre(theatreName, numberOfRows, rowLength)
    }

    /**
     * Returns the name of the cinema.
     * @return     the name
     */
    public String getName()
    {
        return  this.cinemaName;
    }
    
    /**
     * Returns the theatre inside the cinema.
     * @return     the theatre
     */
    public Theatre getTheatre()
    {
        return this.theatre;
    }

    /* public void addTheatre(String theatreName, int numberOfRows, int rowLength)
    {
        Theatre tempTheatre = new Theatre(theatreName, numberOfRows, rowLength);
        this.theatres.add(tempTheatre);
    }*/
    
}
