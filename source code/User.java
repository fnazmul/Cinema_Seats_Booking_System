
import java.io.Serializable;

/**
 * Enumeration class User
 * Representations for all users.
 *
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public enum User implements Serializable
{
    // A value for each user in the system along with its
    // corresponding user interface string


    USER("user"), ADMIN("admin"), CUSTOM("custom");
     

    // The user string.
    private String userString;

    /**
     * Initialzse with the corresponding user string.
     * @param userString  The string representation of an user.
     */
    User(String userString)
    {
        this.userString = userString;
    }


    
    /**
     * @return user as a string.
     */
    public String toString()
    {
        return userString;
    }
}
