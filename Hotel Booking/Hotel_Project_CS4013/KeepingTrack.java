import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.time.LocalDate;
import java.time.Period;
import java.time.DateTimeException;
/**
 * Keeps track of al relevant data for the system.
 */
public class KeepingTrack implements ReadAndWrite, Converter
{
    /**
     * An array of all of the old bookings over the last 7 years.
     */
    protected ArrayList<Booking> oldBookings;
    /**
     * Keeps track of the size ok the current bookings which can correspond to a booking number.
     */
    private int nextReservationNumber = 1;
    private static ArrayList<User> hdps;
    private static ArrayList<User> supervisors;
    private double balance;
    private Period p;
    /**
     * Initializes a keeping track object and data fields 
     * so that this object represents an empty system.
     */
    public KeepingTrack(){
        oldBookings = new ArrayList<Booking>();
        hdps = new ArrayList<User>();
        supervisors = new ArrayList<User>();
    }

    /**
     * Adds a booking to the relevent csv files and increments the int amount.
     */
    public void add(Booking b)
    {
        write(Options.hotelBookings, b.toString());
        write(Options.hotel7Years, b.toString());
        nextReservationNumber++;
        balance+= b.getDeposit();
    }

    /**
     * Asks for a reservation number, gives a refund to relevent reservation and updates
     * relevant csv files as long as it is outside a 24 hour window and the reservation type
     * is not Advanved Purchase.
     */
    public void refund(){
        Booking r = find();
        if(r.getReservationNumber()>0){
            p = r.getCheckInDate().until(LocalDate.now());
            if(r.getReservationType().compareToIgnoreCase("ap")!=0){
                if((r.getCheckInDate().compareTo(LocalDate.now())<1)&&((p.getDays()<=1)&&(p.getMonths()==0)&&(p.getYears()==0))){
                    System.out.println("Refunds are not available within 24 hours of stay.");
                }else if(r.getCheckInDate().compareTo(LocalDate.now())<0){
                    System.out.println("Refunds are not available after check in date.");
                }else{
                    System.out.println();
                    System.out.println("Are you sure you want to refund"
                        +" reservation number "+r.getReservationNumber());
                    String yn = getChoice(Options.yne);
                    if(yn.compareToIgnoreCase("yes")==0){
                        r.setBalance(r.getTotalPrice()-r.getDeposit());
                        balance-=(r.getTotalPrice()-r.getDeposit());
                        ReadAndWrite.edit(Options.hotelBookings, r.toString(),
                            Integer.toString(r.getReservationNumber()));
                        ReadAndWrite.edit(Options.hotel7Years, r.toString(),
                            Integer.toString(r.getReservationNumber()));
                        System.out.println("Reservation Refunded.");
                    }else{
                        System.out.println("Reservation Not Refunded.");
                    }
                }
            }else{
                System.out.println("Advanced Purchased bookings are non-refundable");
            }
        }
    }

    /**
     * Asks for a reservation number, gives a refund to relevent reservation and updates
     * relevant csv files as long as it is outside a 24 hour window and the reservation type
     * is not Advanved Purchase.
     */
    public String chargeAll(Booking b){
        b.setBalance(0);
        return b.toString();
    }

    /**
     * Asks for a reservation number and returns the Booking that corresponds to it. If it
     * cannot find the reservation it returns an empty Booking.
     */
    public Booking find(){
        Booking found = new Booking();
        int f = 0;
        int l=0;
        String founds = "";
        while(f==0){
            System.out.println("Please enter a valid reservation number");
            System.out.println("Or type 'Exit' to bring you back to menu.");
            String n =ReadAndWrite.input();
            if(n.compareToIgnoreCase("exit")==0){
                f++;
            }else{
                int rn = convertToInt(n);
                String[][] bookings=ReadAndWrite.read(Options.hotelBookings, 0, 2, 1);
                int now = 0;
                for(int i=0; i<bookings.length;i++){
                    now = convertToInt(bookings[i][0]);
                    if(rn==now){
                        l++; 
                        f++;
                        for(int x=0; x<bookings[0].length;x++){
                            founds+= bookings[i][x];
                            if(x != (bookings[0].length-1)){
                                founds+=",";
                            }
                        }
                        found = convertToBooking(founds);
                    }
                }
                if(l==0){
                    System.out.println("Not a valid reservation number.");
                }
            }
        }
        return found;
    }

    /**
     * Asks for a reservation number, cancels the relevent reservation and updates
     * relevant csv files.
     */
    protected void cancel(){
        Booking cancel = find();
        if(cancel.getReservationNumber()>0){
            System.out.println();
            System.out.println("Are you sure you want to cancel"
                +" reservation number "+cancel.getReservationNumber());
            String yn = getChoice(Options.yne);
            if(yn.compareToIgnoreCase("yes")==0){
                cancel.setCancel(true);
                ReadAndWrite.edit(Options.hotelBookings,cancel.toString(),
                    Integer.toString(cancel.getReservationNumber()));
                ReadAndWrite.edit(Options.hotel7Years,cancel.toString(),
                    Integer.toString(cancel.getReservationNumber()));
                System.out.println("Reservation Cancelled.");
            }else{
                System.out.println("Reservation Not Cancelled.");
            }
        }
    }

    /**
     * Asks for a reservation number, checks in the relevent reservation and updates
     * relevant csv files.
     */
    protected void checkIn(){
        Booking checkIn = find();
        if(checkIn.getReservationNumber()>0){
            System.out.println();
            System.out.println("Are you sure you want to check in"
                +" reservation number "+checkIn.getReservationNumber());
            String yn = getChoice(Options.yne);
            if(yn.compareToIgnoreCase("yes")==0){
                checkIn.setCheckIn(true);
                ReadAndWrite.edit(Options.hotelBookings,checkIn.toString(),
                    Integer.toString(checkIn.getReservationNumber()));
                ReadAndWrite.edit(Options.hotel7Years,checkIn.toString(),
                    Integer.toString(checkIn.getReservationNumber()));
                System.out.println("Reservation Checked In.");
            }else{
                System.out.println("Reservation Not Checked In.");
            }
        }
    }

    /**
     * Asks for a reservation number, checks out the relevent reservation and updates
     * relevant csv files.
     */
    protected void checkOut(){
        Booking checkOut = find();
        if(checkOut.getReservationNumber()>0){
            System.out.println();
            System.out.println("Are you sure you want to check out"
                +" reservation number "+checkOut.getReservationNumber());
            String yn = getChoice(Options.yne);
            if(yn.compareToIgnoreCase("yes")==0){
                checkOut.setCheckOut(true);
                ReadAndWrite.edit(Options.hotelBookings,checkOut.toString(),
                    Integer.toString(checkOut.getReservationNumber()));
                ReadAndWrite.edit(Options.hotel7Years,checkOut.toString(),
                    Integer.toString(checkOut.getReservationNumber()));
                System.out.println("Reservation Checked Out.");
            }else{
                System.out.println("Reservation Not Checked Out.");
            }
        }
    }

    /**
     * Asks you to enter new, unique supervisor and
     * adds a unique supervisor of type User to the system using those details.
     */
    protected void addSupervisor(){
        int done = 0;
        while (done == 0){
            int same =0;
            System.out.println();
            System.out.println("Please enter your name.");
            String n =ReadAndWrite.input();
            System.out.println("Please enter a unique username.");
            System.out.println("Or type 'Exit' to bring you back to menu.");
            String u =ReadAndWrite.input();
            if(u.compareToIgnoreCase("exit")!=0){
                for (int i =0; i<supervisors.size(); i++){
                    if(u.compareToIgnoreCase(supervisors.get(i).getUsername())
                    ==0){
                        same++;
                    }
                }

                if(same==0){
                    System.out.println("Please enter a password you will remember.");
                    String p =ReadAndWrite.input();
                    supervisors.add(new User (n, u, p));
                    write(Options.hotelSupervisors,u+","+n+","+p);
                    done++;
                }else{
                    System.out.println();
                    System.out.println("Username already exists");
                }
            }else{
                done++;
            }
        }
    }

    /**
     * Asks you to enter new, unique hotel desk personnel details and
     * adds a unique hotel desk personnel of type User to the system using those details.
     */
    protected void addHotelDeskPersonnel(){
        int done = 0;

        while (done == 0){
            int same = 0;
            System.out.println();
            System.out.println("Please enter your name.");
            String n =ReadAndWrite.input();
            System.out.println();
            System.out.println("Please enter a unique username.");
            System.out.println("Or type 'Exit' to bring you back to menu.");
            String u =ReadAndWrite.input();
            if(u.compareToIgnoreCase("exit")!=0){

                for (int i =0; i<hdps.size(); i++){
                    if(u.compareToIgnoreCase(hdps.get(i).getUsername())
                    ==0){
                        same++;
                    }
                }
                if(same==0){
                    System.out.println("Please enter a password you will remember.");
                    String p =ReadAndWrite.input();
                    hdps.add(new User (n,u, p));
                    write(Options.hotelHotelDeskPersonnel,u+","+n+","+p);
                    done++;
                }else{
                    System.out.println();
                    System.out.println("Username already exists");
                }

            }else{
                done++;
            }
        }
    }

    /**
     * Converts csv file info into Hotel Desk Personnel users.
     */
    protected void addHotelDeskPersonnel(String[][] allInfo){
        for(int y=1;y<allInfo.length;y++){
            hdps.add(new User(allInfo[y][1], allInfo[y][0],allInfo[y][2]));
        }
    }
    
    /**
     * Converts csv file info into Supervisor users.
     */
    protected void addSupervisor(String[][] allInfo){
        for(int y=1;y<allInfo.length;y++){
            supervisors.add(new User(allInfo[y][1], allInfo[y][0],allInfo[y][2]));
        }
    }

    private User findSupervisor(String username){
        User found = new User("Not Found","Not Found", "nf123");
        for (int i =0; i<supervisors.size(); i++){
            if(username.compareTo(supervisors.get(i).getUsername())
            ==0){
                found = supervisors.get(i);
            }
        }
        return found;
    }

    private User findHdp(String username){
        User found = new User("Not Found","Not Found", "nf123");
        for (int i =0; i<hdps.size(); i++){
            if(username.compareToIgnoreCase(hdps.get(i).getUsername())
            ==0){
                found = hdps.get(i);
            }
        }
        return found;
    }

    /**
     * Asks for hotel desk personnel user details and removes that 
     * hotel desk personnel from the system.
     * Does not allow you t oif there is only 1 as you always have to have at 
     * least 1 supervisor.
     */
    protected void removeSupervisor(){
        int done=0;
        while(done==0){
            System.out.println();
            System.out.println("Please enter a valid username.");
            System.out.println("Or type 'Exit' to bring you back to menu.");
            String u =ReadAndWrite.input();
            if(u.compareToIgnoreCase("exit")!=0){
                if(findSupervisor(u).getUsername().compareToIgnoreCase("Not Found")
                !=0){
                    System.out.println();
                    System.out.println("Are you sure you want to remove "+
                        findSupervisor(u).getName() + " As a Supervisor");
                    String yn = getChoice(Options.yne);
                    if(yn.compareToIgnoreCase("yes")==0){
                        if(supervisors.size()>1){
                            supervisors.remove(findHdp(u));
                            System.out.println("Supervisor Removed");
                            ReadAndWrite.remove(Options.hotelSupervisors, u);
                            done++;
                        }else{
                            System.out.println("Error. There must always be at least 1 supervisor");
                            done++;
                        }

                    }else{
                        System.out.println("Supervisor Not Removed");
                        done++;
                    }
                }else{
                    System.out.println("Supervisor Does not exist.");
                }
            }else{
                System.out.println("Supervisor Not Remover.");
                done++;
            }
        }
    }

    /**
     * Asks for hotel desk personnel user details and 
     * removes that hotel desk personnel from the system.
     */
    protected void removeHotelDeskPersonnel(){
        int done=0;
        while(done==0){
            System.out.println();
            System.out.println("Please enter a valid username.");
            System.out.println("Or type 'Exit' to bring you back to menu.");
            String u =ReadAndWrite.input();
            if(u.compareToIgnoreCase("exit")!=0){
                if(findHdp(u).getUsername().compareToIgnoreCase("Not Found")
                !=0){
                    System.out.println();
                    System.out.println("Are you sure you want to remove "+
                        findHdp(u).getName() + " As a Hotel Desk Personnel");
                    String yn = getChoice(Options.yne);
                    if(yn.compareToIgnoreCase("yes")==0){
                        hdps.remove(findHdp(u));
                        System.out.println("Hotel Desk Personnel Removed");
                        ReadAndWrite.remove(Options.hotelHotelDeskPersonnel, u);
                        done++;
                    }else{
                        System.out.println("Hotel Desk Personnel Not Removed");
                        done++;
                    }
                }else{
                    System.out.println("Hotel Desk Personnel does not exist.");
                }
            }else{
                System.out.println("Hotel Desk Personnel Removed");
                done++;
            }

        }
    }

    /**
     * Asks for a supervisors login details and allows that supervisor to log in if details match.
     */
    public boolean LoginSupervisor(){
        int done =0;
        boolean login = false;
        while(done==0){
            System.out.println();
            System.out.println("Please enter your username");
            System.out.println("Or type 'Exit' to leave.");
            String u = ReadAndWrite.input();
            if(u.compareToIgnoreCase("exit") !=0){
                if(u.compareTo(findSupervisor(u).getUsername())
                == 0){
                    System.out.println();
                    System.out.println("Please enter your password");
                    String p = ReadAndWrite.input();
                    if(p.compareToIgnoreCase(findSupervisor(u).getPassword())
                    == 0){
                        System.out.println("Welcome back " +
                            findSupervisor(u).getName() + '!');
                        login=true;
                        done++;
                    }else{
                        System.out.println("Incorrect password.");
                    }
                }else{
                    System.out.println("Username does not exist.");
                }
            }else{
                done++;
            }
        }
        return login;
    }

    /**
     * Asks for a hotel desk personnel login details and allows that hotel desk personnel
     * to log in if details match.
     */
    public boolean LoginHotelDeskPersonnel(){
        boolean login=false;
        int done =0;
        while(done==0){
            System.out.println();
            System.out.println("Please enter your username");
            System.out.println("Or type 'Exit' to leave.");
            String u = ReadAndWrite.input();
            if(u.compareToIgnoreCase("exit") !=0){
                if(u.compareTo(findHdp(u).getUsername())
                == 0){
                    System.out.println();
                    System.out.println("Please enter your password");
                    String p = ReadAndWrite.input();
                    if(p.compareTo(findHdp(u).getPassword())
                    == 0){
                        System.out.println("Welcome back " +
                            findHdp(u).getName() + '!');
                        login=true;
                        done++;
                    }else{
                        System.out.println("Incorrect password.");
                    }
                }else{
                    System.out.println("Username does not exist.");
                }
            }else{
                done++;
            }
        }
        return login;
    }

    /**
     * Asks you to enter dates you would like to analise from and to.
     * Rewrites the hotels analytics csv file to provide relevent analytic information.
     */
    public void analytics(){
        System.out.println("Please enter a date you wish to analysis from.");
        LocalDate from = enterDate();
        System.out.println("Please enter a date you wish to analysis to.");
        LocalDate to = enterDate();
        double[] totals =  analytics(Options.hotel7Years,from,to);
        String row="0,";
        for(int i=0;i<totals.length;i++){
            if(i!=(totals.length-1)){
                row+=(int)totals[i];
                if(i>=(totals.length/2)&&(i!=(totals.length-1))){
                    row+="%";
                }
            }else{
                row+=totals[i];
            }
            row+=",";
        }
        ReadAndWrite.edit(Options.hotelAnalytics,row,"0");
        System.out.println("Analytics uptated in file "+Options.hotelAnalytics);
    }

    private LocalDate enterDate(){
        int cidate = 0;
        LocalDate date = LocalDate.of(2000,01,01);
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
                }
            }
            try{
                date = LocalDate.of(year,month,day);
                cidate++;
            }catch(DateTimeException ex){
                System.out.println("Not a date.");
                System.out.println("Please enter a real date.");
            }
        }
        return date;
    }

    /**
     * Return getNextReservationNumber.
     */
    public int getNextReservationNumber(){
        return nextReservationNumber;
    }

    /**
     * Set getNextReservationNumber to int passed in.
     */
    public void setNextReservationNumber(int nextReservationNumber){
        this.nextReservationNumber = nextReservationNumber;
    }
}
