/**
 * Holds all strings that are optional choices in Every other method.
 */
public class Options
{
    /**
     * Holds Yes, No and Exit options.
     */
    protected static String[] yne ={"Yes", "No", "Exit"};
    /**
     * Holds Yes, No and Exit options.
     */
    protected static String[] yn ={"Yes", "No"};
    /**
     * Holds booking type options.
     */
    protected static String[] bookingTypes ={"Standard","Advanced Purchase"};
    /**
     * Holds discount options.
     */
    protected static String[] discount = {"Percentage", "Amount", "Exit"};
    /**
     * Holds payment type options.
     */
    protected static String[] paymentTypes = {"Card","Cash"};
    /**
     * Holds customer menu options.
     */
    protected static String[] customerMenu ={"Booking", "Make Payment", "Log Out"};
    /**
     * Holds hotel desk personnel menu options.
     */
    protected static String[] hotelDeskPersonnelMenu ={"Booking","Find Reservation", "Cancellation",
            "Refund","Check In","Check Out","Make Payment","Log Out"};
    /**
     * Holds supervisor menu options.
     */
    protected static String[] supervisorMenu ={"Booking","Find Reservation", "Cancellation"
        ,"Refund","Check In","Check Out", "Make Payment" ,"Apply Discount",
        "Analytics", "Add Hotel Desk Personnel","Remove Hotel Desk Personnel" 
        ,"Add Supervisor","Remove Supervisor", "Log Out"};
    /**
     * Holds the different type of users options.
     */
    protected static String[] users ={"Guest","Hotel Desk Personnel", "Supervisors", "Exit"};
    /**
     * Holds room type options.
     */
    protected static String[] roomTypes;
    /**
     * Holds hotel bookings csv file name that saves booking info for a month.
     */
    protected static String hotelBookings;
    /**
     * Holds hotel bookings csv file name that saves booking info for 7 years.
     */
    protected static String hotel7Years;
    /**
     * Holds hotel analytic file name.
     */
    protected static String hotelAnalytics;
    /**
     * Holds hotel analytic file name.
     */
    protected static String hotelSupervisors;
    /**
     * Holds hotel analytic file name.
     */
    protected static String hotelHotelDeskPersonnel;
    public Options()
    {
    }
}
