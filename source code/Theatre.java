import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class to maintain the information of a theatre.
 * 
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class Theatre implements Serializable
{
    private String name;    //the name of the theatre
    private int numberOfRows;       //number of rows
    private int rowLength;          //number of seats in a row
    private ArrayList<Row> rows;    //list of rows

    /**
     * Constructor for objects of class Theatre.
     */
    public Theatre(String theatreName, int numberOfRows, int rowLength)
    {
        this.name = theatreName;
        this.numberOfRows = numberOfRows;
        this.rowLength = rowLength;
        this.rows = new ArrayList<Row>();
     
        for(int i = 1; i <= numberOfRows; i++)
        {
            Row tempRow = new Row(i, rowLength);
            rows.add(tempRow);
        }
    }

    /**
     * Returns the name of the theatre.
     * @return     the name of the theatre
     */
    public String getName()
    {
        return  this.name;
    }

    /**
     * Returns the number of rows.
     * @return     the number of rows of the theatre
     */
    public int getNumberOfRows()
    {
        return  this.numberOfRows;
    }

    /**
     * Returns the length of the row.
     * @return     the length of the row of the theatre
     */
    public int getRowLength()
    {
        return  this.rowLength;
    }

    /**
     * Returns a row object.
     * @param rowNum The number of the row
     * @return       The row object
     */
    public Row getRow(int rowNum)
    {
        return rows.get(rowNum-1);
    }

}
