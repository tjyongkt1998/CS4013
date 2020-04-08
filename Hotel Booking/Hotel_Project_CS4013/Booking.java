import java.time.LocalDate;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
/**
 * Sets up a booking with all relevant details.
 */
public class Booking implements Payment, Converter, ReadAndWrite
{
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean checkIn = false;
    private boolean checkOut = false;
    private  int numberOfNights;
    private  int numberOfRooms;
    private   Room[] roomsBooked;
    private int reservationNumber;
    private String reservationName;
    private String reservationType;
    private double totalPrice;
    private boolean cancel = false;
    private double deposit;
    private double balance;

    /**
     * Creates a booking with all data fields being set to the parameters.
     */
    Booking(int reservationNumber,String reservationName,String reservationType,
    int numberOfRooms,Room[] roomsBooked,LocalDate checkInDate,
    LocalDate checkOutDate,int numberOfNights,double totalPrice,double deposit,
    double balance,boolean cancel, boolean checkIn, boolean checkOut){
        this.reservationNumber = reservationNumber;
        this.reservationName = reservationName;
        this.reservationType = reservationType;
        this.numberOfRooms = numberOfRooms;
        this.roomsBooked = roomsBooked;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfNights = numberOfNights;
        this.totalPrice = totalPrice;
        this.deposit = deposit;
        this.balance = balance;
        this.cancel = cancel;
    }

    /**
     * Creates a booking with all data fields being set to default.
     */
    Booking(){

    }

    /**
     * Asks for info to set the reservation name, number and type.
     */
    public void reservation(int reservationNumber){
        System.out.println("Please enter the name the reservation"+
            " will be placed under.");
        reservationName = ReadAndWrite.input();
        System.out.println("Please enter the type the reservation"+
            " you wish to make.(Advanced Purchase gives a 5% discount but is non-refundable.)");
        reservationType = getChoice(Options.bookingTypes);
        this.reservationNumber = reservationNumber;
    }

    /**
     * Sets the reservation name, number and type according to the parameters.
     */
    protected void reservation(int reservationNumber, String n , String t){

        this.reservationName = reservationName;
        this.reservationType = reservationType;
        this.reservationNumber = reservationNumber;

    }

    /**
     * Asks for info to set the number of rooms.
     */
    public void numberOfRooms(){
        int nr = 0;
        while (nr == 0){
            System.out.println("Enter number of rooms.");
            String n = ReadAndWrite.input();
            numberOfRooms = convertToInt(n);
            if(numberOfRooms!=0){
                nr++;
            }
        }
    }

    /**
     * Asks for info to set types of room based on the number of rooms in this 
     * object and the hotel passed in in the parameters.
     */
    public void typeOfRooms(Hotel hotel){
        roomsBooked = new Room[numberOfRooms];
        int k = 1;
        String r ="";
        int rb = 0;
        int checkAll=1;
        while (k<=numberOfRooms){
            int i =0;
            if (numberOfRooms==1){
                System.out.println("What type of room would you like?");
            }else{
                System.out.println("What type of room would you like room "
                    + k + " to be?");
            }

            r=getChoice(Options.roomTypes);
            for(i=0;i<hotel.Rooms.length;i++){
                if(r.compareTo(hotel.Rooms[i].getType())==0){
                    boolean thisBooking=false;
                    for(int j=0;j<rb;j++){
                        if(roomsBooked[j].getNumber()==hotel.Rooms[i].getNumber()){
                            thisBooking= true;
                        }
                    }
                    if(thisBooking==false){
                        if(ReadAndWrite.check(Options.hotelBookings,
                            hotel.Rooms[i].getType(),
                            Integer.toString(hotel.Rooms[i].getNumber()),
                            checkInDate, checkOutDate)
                        == true){  
                            System.out.println("Would you like to include breakfast"+ 
                                " with this room?");

                            String bi = getChoice(Options.yn);
                            if(bi.compareToIgnoreCase("yes") == 0)
                            {
                                hotel.Rooms[i].breakfastIncluded(true);
                            }else if(bi.compareToIgnoreCase("no") == 0){
                                hotel.Rooms[i].breakfastIncluded(false);
                            }
                            roomsBooked[rb] = hotel.Rooms[i];
                            rb++;
                            i = hotel.Rooms.length;
                            k++;
                        }
                    }
                }else{
                    checkAll++;
                }
            }
            if(checkAll==hotel.Rooms.length){
                System.out.println("Rooms of this type are not available on these dates.");
            }
        }
    }

    /**
     * Set the cancel status.
     */
    public void setCancel(boolean cancel){
        this.cancel = cancel;
    }

    /**
     * Returns the cancel status.
     */
    public String getCancel(){
        String c = "";
        if(cancel == true){
            c = "Booking canceled.";
        }else{
            c = "Booking not canceled.";
        }
        return c;
    }

    /**
     * Sets the total price.
     */
    public void setPrice(){
        for(int i=0; i<roomsBooked.length; i++){
            for(int j=0; j<numberOfNights; j++){
                totalPrice+= roomsBooked[i].getPrice(checkInDate.plusDays(i));
            }
        }
        if(reservationType.compareToIgnoreCase("ap")==0){
            totalPrice-= (totalPrice*.05);
        }
        deposit = totalPrice*.1;
        balance = totalPrice;
    }

    /**
     * Returns the Deposit.
     */
    public double getDeposit(){
        return deposit;
    }

    /**
     * Sets the check in and out date and the number of nights of the stay.
     */
    public void setCheckInDate(){
        int cidate = 0;
        while(cidate ==0){
            int year = 0;
            int month = 0;
            int day = 0;
            int enterDate =0;
            while(enterDate ==0){
                System.out.println("Please enter a date in format dd/mm/yyyy.");
                String n = ReadAndWrite.input();
                StringTokenizer st = new StringTokenizer(n, "/");

                try{
                    day = convertToInt(st.nextToken());
                    month = convertToInt(st.nextToken());
                    year = convertToInt(st.nextToken());
                    enterDate++;
                }catch(NumberFormatException ex){
                    System.out.println("Not correct format.");
                }catch(RuntimeException ex){
                    System.out.println("Not correct format.");
                }
            }
            try{
                checkInDate = LocalDate.of(year,month,day);
                if(checkInDate.compareTo(LocalDate.now())>=0){
                    cidate++;
                }else{
                    System.out.println("Date has passed.");
                    System.out.println("Please enter a date from today forward.");
                }
            }catch(DateTimeException ex){
                System.out.println("Not a date.");
                System.out.println("Please enter a real date.");
            }
        }

        int ns = 0;
        while(ns ==0){
            System.out.println("Please enter number of nights you wish to stay.");
            String n = ReadAndWrite.input();
            numberOfNights = convertToInt(n);
            if(numberOfNights!=0){
                ns++;
            }else{
                System.out.println("Not a valid number of days.");
            }
        }

        checkOutDate = checkInDate.plusDays(numberOfNights);
    }

    /**
     * Applies a discount to the total based on a percentage figure.
     */
    public void applyDiscountPercent(){
        int d =0;
        double dp = 0;
        double da=0;
        while(d==0){
            System.out.println("Please enter a number that will correspond to the '%' discount");
            System.out.println("Or type 'Exit' to bring you back to menu.");
            String n =(ReadAndWrite.input());
            if(n.compareToIgnoreCase("exit")==0){
                d++;
            }else{
                dp = convertToDouble(n);
                da= totalPrice*(dp/100);
                if(da<=balance){
                    balance-=da;
                    System.out.println("Discount Applied.");
                    d++;
                }else{
                    System.out.println("Discount Not Applied."
                        +'\n'+"Not a valid discount amount.");
                }
            }
        }
    }

    /**
     * Applies a discount to the total based on an amount.
     */
    public void applyDiscountAmount(){
        int d =0;
        double da=0;
        while(d==0){
            System.out.println("Please enter the amount of the discount");
            System.out.println("Or type 'Exit' to bring you back to menu.");
            String n =(ReadAndWrite.input());
            if(n.compareToIgnoreCase("exit")==0){
                d++;
            }else{
                da = convertToDouble(n);
                if(da<=balance){
                    balance-=da;
                    System.out.println("Discount Applied.");
                    d++;
                }else{
                    System.out.println("Discount Not Applied."
                        +'\n'+"Not a valid discount amount.");
                }
            }
        }
    }

    /**
     * Converts a booking to a string.
     */
    public String toString(){
        String s = Integer.toString(reservationNumber)+','+reservationName+','
            +reservationType+ ','+numberOfRooms+',';
        for (int i=0;i<roomsBooked.length;i++){
            s+=roomsBooked[i].getType();
            if(i!=(roomsBooked.length-1)){
                s+='/';
            }
        }
        s+=',';
        for (int i=0;i<roomsBooked.length;i++){
            s+=roomsBooked[i].getNumber();
            if(i!=(roomsBooked.length-1)){
                s+='/';
            }
        }
        s+=',';
        for (int i=0;i<roomsBooked.length;i++){
            s+=roomsBooked[i].getBreakfastIncluded();
            if(i!=(roomsBooked.length-1)){
                s+='/';
            }
        }
        s+=","+checkInDate.getDayOfMonth()+"-"+ checkInDate.getMonthValue()+"-"
        + checkInDate.getYear()+","+checkOutDate.getDayOfMonth()+"-"+
        checkOutDate.getMonthValue()+"-"+ checkOutDate.getYear()+","
        +numberOfNights+","+totalPrice+
        ","+deposit+","+balance+","+cancel+","+checkIn+","+checkOut;
        return s;
    }

    /**
     * Returns the reservation number.
     */
    public int getReservationNumber(){
        return reservationNumber;   
    }

    /**
     * Sets the balance.
     */
    public void setBalance(double balance){
        this.balance = balance;
    }

    /**
     * Sets the check in status.
     */
    public void setCheckIn(boolean checkIn){
        this.checkIn = checkIn;
    }

    /**
     * Sets the check out status.
     */
    public void setCheckOut(boolean checkOut){
        this.checkOut = checkOut;
    }

    /**
     * Returns the Total price of the stay.
     */
    public double getTotalPrice(){
        return totalPrice;
    }

    /**
     * Returns the balance.
     */
    public double getBalance(){
        return balance;
    }

    /**
     * Returns the reservation type.
     */
    public String getReservationType(){
        return reservationType;
    }

    /**
     * Returns the check in date.
     */
    public LocalDate getCheckInDate(){
        return checkInDate;
    }

    /**
     * Returns the check out.
     */
    public LocalDate getCheckOutDate(){
        return checkOutDate;
    }

    /**
     * Returns the number of rooms booked.
     */
    public int getNumberOfRooms(){
        return numberOfRooms;
    }

    /**
     * Returns the reservation name.
     */
    public String getReservationName(){
        return reservationName;
    }

    /**
     * Returns an array containing all rooms booked.
     */
    public Room[] getRoomsBooked(){
        return roomsBooked;
    }
}
