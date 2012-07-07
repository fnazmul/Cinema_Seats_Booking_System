import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A class to maintain an arbitrary number of film shows.
 *
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class FilmShowRepository implements Serializable
{
    // Storage for an arbitrary number of film shows.
    private ArrayList<FilmShow> allFilmShow;

    /**
     * Performs initialization for the film show collection.
     */
    public FilmShowRepository()
    {
        allFilmShow = new ArrayList<FilmShow>();
    }

    /**
     * Checks if a filmshow already exists.
     * @param filmShow  The film show object
     * @return true if the film show exists, else false
     */
    public boolean isFilmShow(FilmShow filmShow)
    {
        for(Iterator it = allFilmShow.iterator(); it.hasNext();){
                if(((FilmShow)(it.next())).equals(filmShow))
                    return true;
       }
       return false;        
    }

    /**
     * Adds a new film show to the collection.
     * @param filmShow The film show to be added
     * @return Status of the add operation
     */
    public String addFilmShow(FilmShow filmShow) throws NullPointerException
    {
        StringBuffer result = new StringBuffer();
        if(filmShow != null){
            //if the cinema hall is not free
            if(!this.isFreeCinema(
                    filmShow.getCinemaName(),filmShow.getShowDay())){
                result.append("Cinema Hall Not Available on "
                        + filmShow.getShowDay().toString().toUpperCase() +": ");
                result.append("\n" + filmShow.getCinemaName().toUpperCase()
                        + " is already occupied on "
                        + filmShow.getShowDay().toString().toUpperCase()+ ".");
                return result.toString();
            }
            else{   //cinema hall is free
                allFilmShow.add(filmShow);
                result.append(filmShow.getFilmName().toUpperCase()+" added to "
                        + filmShow.getCinemaName().toUpperCase() + " on "
                        + filmShow.getShowDay().toString().toUpperCase()+ ".");
            }
        }
        else{
            throw new NullPointerException(
                    "Null value provided with addFilmShow Command.");
        }
        return result.toString();
    }


    /**
     * Looks up a film name and returns the
     * corresponding days the film is shown.
     * @param filmName The name of the film to be looked up.
     * @return The days on which the film is shown.
     */
    public String getWhenFilmShow(String filmName) throws NullPointerException
    {
        boolean foundFilm = false;
        Set<Day> matches = new HashSet<Day>();
        StringBuffer result = new StringBuffer();

        if(filmName != null){
            for(Iterator it = allFilmShow.iterator(); it.hasNext();){
                FilmShow tempFilmShow = ((FilmShow)(it.next()));
                if(tempFilmShow.getFilmName().equals(filmName)){
                    matches.add(tempFilmShow.getShowDay());
                    foundFilm = true;
                }               
            }
            if(foundFilm){
                for(Iterator it = matches.iterator(); it.hasNext();){
                    result.append(((Day)(it.next())).toString().toUpperCase());
                    result.append(" ");
                }
            }
            else{
                result.append("No Film Show Found:" );
                result.append("\nThere is no show for the film: "
                        + filmName.toUpperCase() + ".");
            }

        }
        else{
            throw new NullPointerException(
                    "No film provided with WHEN command.");
        }
        return result.toString();
    }

    /**
     * Looks up a film name and the day of show and returns the
     * corresponding cinemas where the film is shown.
     * @param filmName The name of the film to be looked up.
     * @param day The day on which the show is
     * @return The cinemas on which the film is shown on given day.
     */
    public String getWhereFilmShow(String filmName, Day day)
            throws NullPointerException
    {
        boolean foundFilm = false;
        Set<String> matches = new HashSet<String>();
        StringBuffer result = new StringBuffer();

        if(filmName != null){
            for(Iterator it = allFilmShow.iterator(); it.hasNext();){
                FilmShow tempFilmShow = ((FilmShow)(it.next()));
                if(tempFilmShow.getFilmName().equals(filmName)
                        && tempFilmShow.getShowDay().toString().equals(
                        day.toString())){
                    matches.add(tempFilmShow.getCinemaName());
                    foundFilm = true;
                }
            }
            if(foundFilm){
                for(Iterator it = matches.iterator(); it.hasNext();){
                    result.append(((String)(it.next())).toUpperCase());
                    result.append(" ");
                }
            }
            else{
                result.append("No Film Show Found:" );
                result.append("\nThere is no show for the film: "
                        + filmName.toUpperCase()
                        + " on " + day.toString().toUpperCase() + "." );
            }
        }
        else{
            //result.append("\nIllegal Argument: No film provided" );
            throw new NullPointerException(
                    "No film provided with WHERE command.");
        }
        return result.toString();
    }

    /**
     * Checks if a Cinema is free on a particular day
     * @param cinemaName The name of the cinema
     * @param day The day to look up
     * @return true if its free, else false
     */
    public boolean isFreeCinema(String cinemaName, Day day)
    {
           
        for(Iterator it = allFilmShow.iterator(); it.hasNext();){
            FilmShow tempFilmShow = ((FilmShow)(it.next()));
            if(tempFilmShow.getCinemaName().equals(cinemaName)
                  && tempFilmShow.getShowDay().toString().equals(
                  day.toString())){
                    return false;
            }
        }
        return true;
    }

    /**
     * Returns the list of all film names in the system.
     * @return the list of films
     */
    public ArrayList<String> getAllFilms(){

        ArrayList<String> filmList = new ArrayList<String>();
        for(FilmShow filmShow: allFilmShow){
            if(!filmList.contains(filmShow.getFilmName()))
                filmList.add(filmShow.getFilmName());
        }
        return filmList;
    }
    
}
