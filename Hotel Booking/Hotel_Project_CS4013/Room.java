import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalDate;
/**
 * Contains all relevant info for a room.
 */
public class Room
{
    private String type;
    private boolean breakfastIncluded;
    private int AdultOccupancy;
    private int ChildOccupancy;
    private boolean booked = false;
    private int number;
    private double[] dailyPrice;
    /**
     * Constructs a room using all params passed in.
     */
    public Room(String type,int AdultOccupancy,int ChildOccupancy,
    double[] dailyPrice, int number){
        this.type = type;
        this.AdultOccupancy=AdultOccupancy;
        this.ChildOccupancy=ChildOccupancy;
        this.dailyPrice = dailyPrice;
        this.number = number;
    }

    /**
     * Contains all relevant info for a room.
     */
    public Room(String type,int number,boolean breakfastIncluded){
        this.type = type;
        this.number = number;
        this.breakfastIncluded = breakfastIncluded;
    }

    /**
     * Changes the breakfast included status of the room.
     */
    public void breakfastIncluded(boolean breakfastIncluded){
        this.breakfastIncluded = breakfastIncluded;
    }

    /**
     * Contains all relevant info for a room.
     */
    public String isBreakfastIncluded(){
        String bi="";
        if(breakfastIncluded == true){
            bi = "Breakfast included.";
        }else if(breakfastIncluded == false){
            bi = "Breakfast not included.";
        }
        return bi;
    }

    /**
     * Returns the breakfast included status of the room.
     */
    public boolean getBreakfastIncluded(){
        return breakfastIncluded;
    }

    /**
     * Returns the room type.
     */
    public String getType(){
        return type;
    }

    /**
     * Returns the Adult Occupancy of the room.
     */
    public int getAdultOccupancy(){
        return AdultOccupancy;
    }

    /**
     * Returns the Child Occupancy of the room.
     */
    public int getChildOccupancy(){
        return ChildOccupancy;
    }

    /**
     * Returns the room number.
     */
    public int getNumber(){
        return number;
    }

    /**
     * Returns the price of the room on the day passed in in the parameter.
     */
    public double getPrice(LocalDate day){
        int i = day.getDayOfWeek().getValue();
        double price = dailyPrice[i-1];
        return price;
    }
}