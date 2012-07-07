
import java.io.Serializable;


/**
 * A class to hold the information of a single filmshow
 * A film show has three attributes:
 *      The name of the film,
 *      The name of the cinema,
 *      The name of the day.
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class FilmShow implements Serializable
{
    private String filmName;    // the name of the film
    private String cinemaName;  // the name of the cinema
    private Day showDay;        // the day of the show

    /**
     * Constructor for objects of class FilmShow
     * @param filmName    the name of the film
     * @param cinemaName      the name of the cinema
     * @param showDay       the day of the show
     */
    public FilmShow(String filmName, String cinemaName, Day showDay )
    {
        // initialise instance variables
        this.filmName = filmName;
        this.cinemaName = cinemaName;
        this.showDay = showDay;
    }

    /**
     * Returns the name of the film.
     * @return     the name of the film
     */
    public String getFilmName()
    {
        return filmName;
    }
    
     /**
      * Returns the name of the cinema.
     * @return     the name of the cinema
     */
    public String getCinemaName()
    {
        return cinemaName;
    }

     /**
      * Returns the day of the show.
     * @return     the day of the show
     */
    public Day getShowDay()
    {
        return showDay;
    }
    
     /**
      * Returns the string representation of the film show.
     * @return     the string representation of the film show
     */
    public String toString()
    {
        String stringRep = "Film Name: " + filmName.toUpperCase()
                + "\n" + "Cinema Name: "+ cinemaName.toUpperCase()
                + "\n"+ "Day of Show: "+ showDay.toString().toUpperCase();
        return stringRep;
    }
    
    /**
     * Implements content equality for FilmShows.
     * @return true if this FilmShow matches the other,
     *         false otherwise.
     */
    public boolean equals(Object other)
    {
        if(other instanceof FilmShow) {
            FilmShow otherFilmShow = (FilmShow) other;
            return filmName.equals(otherFilmShow.getFilmName()) &&
                   cinemaName.equals(otherFilmShow.getCinemaName()) &&
                   showDay.toString().equals(
                   otherFilmShow.getShowDay().toString());
        }
        else {
            return false;
        }
    }
}
