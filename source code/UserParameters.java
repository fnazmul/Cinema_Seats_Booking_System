import java.io.Serializable;
import java.util.HashMap;

/**
 * A class to recognise Users as they are typed in.
 *
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class UserParameters implements Serializable
{

    // A mapping between a user parameter string
    //and the User associated with it.
    private HashMap<String, User> userParameters;
    

    /**
     * Constructor - initialises the user strings.
     */
    public UserParameters()
    {
        this.userParameters = new HashMap<String, User>();
        for(User user : User.values()) {
            this.userParameters.put(user.toString(), user);
        }       
    }

    /**
     * Finds the User associated with a user parameter.
     * @param userParameter The word to look up.
     * @return The User correspondng to userParameter
     */
    public User getUser(String userParameter)
    {
        User user = userParameters.get(userParameter);
        return user;
    }

    /**
     * Checks whether a given String is a valid user parameter.
     * @param userString The user string
     * @return true if it is, false otherwise.
     */
    public boolean isUser(String userString)
    {
        if(userParameters.get(userString) != User.USER)
            return userParameters.containsKey(userString);
        else
            return false;
    }
}

