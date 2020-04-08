import java.time.LocalDate;
import java.time.DateTimeException;
import java.util.StringTokenizer;
/**
 * Converts Stirngs into other types(Just to save time).
 */
public interface staticConverter
{
    /**
 * Converts a Stirngs into a Int.
 */
    static int convertToInt(String s){
        int n=0;
        try{
            n = Integer.parseInt(s);
        }catch(NumberFormatException ex){
            System.out.println("Not a number.");
        }
        return n;
    }

    /**
 * Converts a Stirngs into a Double.
 */
    static double convertToDouble(String s){
        double n=0;
        try{
            n = Double.parseDouble(s);
        }catch(NumberFormatException ex){
            System.out.println("Not a number.");
        }
        return n;
    }

    /**
 * Converts a Stirngs into a Boolean.
 */
    static boolean convertToBoolean(String s){
        boolean n= false;
        try{
            n = Boolean.parseBoolean(s);
        }catch(NumberFormatException ex){
            System.out.println("Not a boolean.");
        }
        return n;
    }

    /**
 * Converts a Stirngs into a Date.
 */
    static LocalDate convertToDate(String y, String m, String d){
        LocalDate Date = LocalDate.of(2000,1,1);
        int year = convertToInt(y);
        int month = convertToInt(m);
        int day = convertToInt(d);
        try{
            Date = LocalDate.of(year,month,day);
        }catch(DateTimeException ex){
            System.out.println("Not a date.");
            System.out.println("Please enter a real date.");
        }
        return Date;
    }
    
    /**
 * Converts a Stirngs into a Booking.
 */
    static Booking convertToBooking(String s){
        StringTokenizer st = new StringTokenizer(s, ",");
        int reservationNumber = convertToInt(st.nextToken());
        String reservationName = st.nextToken();
        String reservationType = st.nextToken();
        int numberOfRooms = convertToInt(st.nextToken());
        Room[] roomsBooked = new Room[numberOfRooms];
        StringTokenizer rt = new StringTokenizer(st.nextToken(), "/");
        StringTokenizer rn = new StringTokenizer(st.nextToken(), "/");
        StringTokenizer bi = new StringTokenizer(st.nextToken(), "/");
        for(int i=0;i<roomsBooked.length;i++){
            roomsBooked[i] = new Room(rt.nextToken(), convertToInt(rn.nextToken()),
                convertToBoolean(bi.nextToken()));
        }
        StringTokenizer ci = new StringTokenizer(st.nextToken(), "-");
        String day = ci.nextToken();
        String month = ci.nextToken();
        String year = ci.nextToken();
        LocalDate checkInDate = convertToDate(year, month, day);
        StringTokenizer co = new StringTokenizer(st.nextToken(), "-");
        day = co.nextToken();
        month = co.nextToken();
        year = co.nextToken();
        LocalDate checkOutDate = convertToDate(year,month,day);
        int numberOfNights = convertToInt(st.nextToken());
        double totalPrice = convertToDouble(st.nextToken());
        double deposit = convertToDouble(st.nextToken());
        double balance = convertToDouble(st.nextToken());
        boolean cancel = convertToBoolean(st.nextToken());
        boolean checkIn = convertToBoolean(st.nextToken());
        boolean checkOut = convertToBoolean(st.nextToken());
        Booking b = new Booking(reservationNumber,reservationName,reservationType,
                numberOfRooms, roomsBooked, checkInDate, checkOutDate, numberOfNights,
                totalPrice, deposit, balance, cancel, checkIn, checkOut);
        return b;
    }

}
