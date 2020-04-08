import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
/**
 * Runs hotel reservation the system.
 */
public class Run implements ReadAndWrite, staticConverter
{   
    private static Scanner input = new Scanner(System.in);
    private static boolean run = true;
    private static Hotel hotel;
    private static KeepingTrack kt = new KeepingTrack();
    private static Booking found = new Booking();
    private static String[][] oldBookings;
    private static Period p;
    private static boolean on = true;
    /**
     * Runs the whole hotel reservation system.
     */
    public static void main() {
        BootUp();
        while(run = true){
            System.out.println("Type 'on' to activate system.");
            if(input.nextLine().compareToIgnoreCase("on")==0){
                update();
                boolean login = false;
                System.out.println('*');
                System.out.println("For the purpose of this Project"
                    +'\n'+"we have already made 5 users.");
                System.out.println();
                System.out.println("Superviser: Michael English"+'\n'+
                    "Username: MichaelE"+'\n'+"Password: me123");
                System.out.println();
                System.out.println("Hotel Desk Personnel: Kitara Stewart"+'\n'+
                    "Username: KitaraS"+'\n'+"Password: ks123");
                System.out.println();
                System.out.println("Hotel Desk Personnel: George Reid"+'\n'+
                    "Username: George R"+'\n'+"Password: gr123");
                System.out.println();
                System.out.println("Hotel Desk Personnel: TJ Yong"+'\n'+
                    "Username: TJY"+'\n'+"Password: tjy123");
                System.out.println();
                System.out.println("Hotel Desk Personnel: Daniel Newman"+'\n'+
                    "Username: DanielN"+'\n'+"Password: dn123");
                System.out.println('*');
                System.out.println();
                System.out.println();
                System.out.println("Please choose the type of user you are");
                String next = ReadAndWrite.getChoices(Options.users);
                if(next.compareToIgnoreCase("Guest")==0){
                    login();
                    while(on==true){
                        Customer();
                    }
                }else if((next.compareToIgnoreCase("Hotel Desk Personnel")==0)){
                    login = kt.LoginHotelDeskPersonnel();
                    if(login==true){
                        login();
                        while(on==true){
                            Hdp();
                        }
                    }
                }else if((next.compareToIgnoreCase("Supervisors")==0)){
                    login = kt.LoginSupervisor();
                    if(login==true){
                        login();
                        while(on==true){
                            Supervisor();
                        }
                    }
                }

            }
        }
    }

    private static void BootUp(){
        int bu=0;
        int s=0;
        System.out.println("Welcome to the KGTD Reservation System");
        while(bu==0){
            System.out.println("Please enter the number of stars the hotel you wish to access has.");
            System.out.println();
            String n = input.nextLine();
            try{
                s= Integer.parseInt(n);
            }catch(NumberFormatException ex){
                System.out.println("Not a number.");
            }
            if(s==3){
                hotel = new Hotel(s);
                Options.hotelBookings="Hotel3StarBookings.csv";
                Options.hotel7Years="Hotel3Star7Years.csv";
                Options.roomTypes= new String[]{"Classic Single",
                    "Classic Twin",
                    "Classic Double"};
                Options.hotelAnalytics="Analitics3StarHotel.csv";
                Options.hotelHotelDeskPersonnel="Hotel3HotelDeskPersonnel.csv";
                Options.hotelSupervisors="Hotel3Supervisors.csv";
                setOldBookings();
                if(oldBookings.length>0){
                    kt.setNextReservationNumber(1+(staticConverter.convertToInt(oldBookings[oldBookings.length-1][0])));
                }else{
                    kt.setNextReservationNumber(1);
                }
                bu++;
            } else if(s==4){
                hotel = new Hotel(s);
                Options.hotelBookings="Hotel4StarBookings.csv";
                Options.hotel7Years="Hotel4Star7Years.csv";
                Options.roomTypes= new String[]{"Executive Single",
                    "Executive Twin",
                    "Executive Double"};
                Options.hotelAnalytics="Analitics4StarHotel.csv";
                Options.hotelHotelDeskPersonnel="Hotel4HotelDeskPersonnel.csv";
                Options.hotelSupervisors="Hotel4Supervisors.csv";  
                setOldBookings();
                if(oldBookings.length>0){
                    kt.setNextReservationNumber(1+(staticConverter.convertToInt(oldBookings[oldBookings.length-1][0])));
                }else{
                    kt.setNextReservationNumber(1);
                }
                bu++;
            }else if(s==5){
                hotel = new Hotel(s);
                Options.hotelBookings="Hotel5StarBookings.csv";
                Options.hotel7Years="Hotel5Star7Years.csv";
                Options.roomTypes= new String[]{"Deluxe Single",
                    "Deluxe Twin",
                    "Deluxe Double", "Deluxe Family"};
                Options.hotelAnalytics="Analitics5StarHotel.csv";
                Options.hotelHotelDeskPersonnel="Hotel5HotelDeskPersonnel.csv";
                Options.hotelSupervisors="Hotel5Supervisors.csv";
                setOldBookings();
                if(oldBookings.length>0){
                    kt.setNextReservationNumber(1+(staticConverter.convertToInt(oldBookings[oldBookings.length-1][0])));
                }else{
                    kt.setNextReservationNumber(1);
                }
                bu++;
            }else{
                System.out.println("This system was only made for l4 3 star, 4 star and 5star hotels");

            }
        }
    }

    private static void update(){
        double balance=0;
        if(kt.oldBookings.size()>0){
            for(int i = 0; i<kt.oldBookings.size();i++){
                p=kt.oldBookings.get(i).getCheckOutDate().until(LocalDate.now());
                if((kt.oldBookings.get(i).getCheckOutDate().compareTo(LocalDate.now())<0)
                &&((p.getDays()>0)||(p.getMonths()>0)||(p.getYears()>0))){
                    
                  ReadAndWrite.edit(Options.hotelBookings,
                  kt.chargeAll(kt.oldBookings.get(i))
                  , Integer.toString(kt.oldBookings.get(i).getReservationNumber()));
                  
                  ReadAndWrite.edit(Options.hotel7Years,
                  kt.chargeAll(kt.oldBookings.get(i))
                  , Integer.toString(kt.oldBookings.get(i).getReservationNumber()));
                  
                if((kt.oldBookings.get(i).getCheckOutDate().compareTo(LocalDate.now())<0)
                &&((p.getMonths()>0)||(p.getYears()>0))){
                    
                    ReadAndWrite.remove(Options.hotelBookings,
                        Integer.toString(kt.oldBookings.get(i).getReservationNumber()));
                        
                        if((kt.oldBookings.get(i).getCheckOutDate().compareTo(LocalDate.now())<0)
                &&(p.getYears()>=7)){
                    
                    ReadAndWrite.remove(Options.hotel7Years,
                        Integer.toString(kt.oldBookings.get(i).getReservationNumber()));
                        
                    }
                }
            }
            }

        }
        
        kt.addHotelDeskPersonnel(ReadAndWrite.read(Options.hotelHotelDeskPersonnel,0,1,1));
        kt.addSupervisor(ReadAndWrite.read(Options.hotelSupervisors,0,1,1));
    }

    private static void Customer(){
        System.out.println();
        System.out.println("--------------------"+
            "----------------------");
        System.out.println();
        System.out.println("User Instructions:");
        String next = ReadAndWrite.getChoices(Options.customerMenu);
        System.out.println();
        System.out.println("--------------------"+
            "----------------------");
        System.out.println();
        if(next.compareToIgnoreCase("booking")==0){
            menuBooking();
        }else if(next.compareToIgnoreCase("make payment")==0){
            menuMakePaymentCustomer();
        }else if(next.compareToIgnoreCase("log out")==0){
            logout();
        }
    }

    private static void Hdp(){
        System.out.println("User Instructions:");
        String next = ReadAndWrite.getChoices(Options.hotelDeskPersonnelMenu);
        System.out.println();
        System.out.println("--------------------"+
            "----------------------");
        System.out.println();
        if(next.compareToIgnoreCase("booking")==0){
            menuBooking();
        }else if(next.compareToIgnoreCase("find reservation")==0){
            menuFind();
        }else if(next.compareToIgnoreCase("cancellation")==0){
            menuCancel();
        }else if(next.compareToIgnoreCase("Check In")==0){
            menuCheckIn();
        }else if(next.compareToIgnoreCase("Check In")==0){
            menuCheckOut();
        }else if(next.compareToIgnoreCase("Refund")==0){
            menuRefund();
        }else if(next.compareToIgnoreCase("make payment")==0){
            menuMakePayment();
        }else if(next.compareToIgnoreCase("log out")==0){
            logout();
        }
    }

    private static void Supervisor(){
        System.out.println("User Instructions:");
        String next = ReadAndWrite.getChoices(Options.supervisorMenu);
        System.out.println();
        System.out.println("--------------------"+
            "----------------------");
        System.out.println();
        if(next.compareToIgnoreCase("booking")==0){
            menuBooking();
        }else if(next.compareToIgnoreCase("find reservation")==0){
            menuFind();
        }else if(next.compareToIgnoreCase("cancellation")==0){
            menuCancel();
        }else if(next.compareToIgnoreCase("Apply discount")==0){
            menuApplyDiscount();
        }else if(next.compareToIgnoreCase("Refund")==0){
            menuRefund();
        }else if(next.compareToIgnoreCase("Check In")==0){
            menuCheckIn();
        }else if(next.compareToIgnoreCase("Check In")==0){
            menuCheckOut();
        }else if(next.compareToIgnoreCase("make payment")==0){
            menuMakePayment();
        }else if(next.compareToIgnoreCase("add Hotel Desk Personnel")==0){
            kt.addHotelDeskPersonnel();
        }else if(next.compareToIgnoreCase("remove Hotel Desk Personnel")==0){
            kt.removeHotelDeskPersonnel();
        }else if(next.compareToIgnoreCase("add supervisor")==0){
            kt.addSupervisor();
        }else if(next.compareToIgnoreCase("remove supervisor")==0){
            kt.removeSupervisor();
        }else if(next.compareToIgnoreCase("Analytics")==0){
            menuAnalytics();
        }else if(next.compareToIgnoreCase("log out")==0){
            logout();
        }

    }

    private static void menuBooking(){
        Booking yes = new Booking();
        yes.reservation(kt.getNextReservationNumber());
        yes.setCheckInDate();
        yes.numberOfRooms();
        yes.typeOfRooms(hotel);
        yes.setPrice();
        yes.setBalance(yes.depositMakePayment(yes.getBalance(), yes.getDeposit()));

        kt.add(yes);

        System.out.println("Booking confirmed.");
        System.out.println("Your reservation number is "+
            yes.getReservationNumber());
        System.out.println("The total price of your stay is €"+
            yes.getTotalPrice());
        System.out.println();
        System.out.println("--------------------"+
            "----------------------");
        System.out.println();
    }

    private static void menuFind(){
        found = kt.find();
        if(found.getReservationNumber()!= 0){
            System.out.println("Reservation Number: "+found.getReservationNumber());
            System.out.println("Reservation Name: "+found.getReservationName());
            System.out.println("Reservation Type: "+found.getReservationType());
            System.out.println("Number Of Rooms: "+found.getNumberOfRooms());
            for(int i =0; i<found.getNumberOfRooms();i++){
                System.out.println("Room "+(i+1)+": "+found.getRoomsBooked()[i].getType()
                    + " " + found.getRoomsBooked()[i].getNumber()
                    + " " + found.getRoomsBooked()[i].isBreakfastIncluded());
            }
            System.out.println("Price Of Stay: €"+found.getTotalPrice());
            System.out.println("Price Of Deposit: €"+found.getDeposit());
            System.out.println("Price Remaining: €"+found.getBalance());
            System.out.println("Check In Date: "+found.getCheckInDate());
            System.out.println("Check Out Date: "+found.getCheckOutDate());
            System.out.println("Number Of Nights Stayed: "+found.getNumberOfRooms());
            System.out.println("Cancel Status: "+found.getCancel());
            System.out.println();
            System.out.println("--------------------"+
                "----------------------");
            System.out.println();
        }
    }

    private static void menuCancel(){
        kt.cancel();
    }

    private static void menuApplyDiscount(){
        String next = ReadAndWrite.getChoices(Options.discount);
        if(next.compareToIgnoreCase("Percentage")==0){
            Booking b = kt.find();
            b.applyDiscountPercent();
            ReadAndWrite.edit(Options.hotelBookings, b.toString(), Integer.toString(b.getReservationNumber()));
            ReadAndWrite.edit(Options.hotel7Years, b.toString(), Integer.toString(b.getReservationNumber()));
        }else if(next.compareToIgnoreCase("Amount")==0){
            Booking b = kt.find();
            b.applyDiscountAmount();
            ReadAndWrite.edit(Options.hotelBookings, b.toString(), Integer.toString(b.getReservationNumber()));
            ReadAndWrite.edit(Options.hotel7Years, b.toString(), Integer.toString(b.getReservationNumber()));
        }
    }

    private static void menuRefund(){
        kt.refund();
    }

    private static void menuAnalytics(){
        kt.analytics();
    }
    
    private static void menuCheckIn(){
        kt.checkIn();
    }
    
    private static void menuCheckOut(){
        kt.checkOut();
    }

    private static void menuMakePayment(){
        Booking mp= kt.find();
        mp.setBalance(mp.makePayment(mp.getBalance()));
        ReadAndWrite.edit(Options.hotelBookings, mp.toString(),Integer.toString(mp.getReservationNumber()));
        ReadAndWrite.edit(Options.hotel7Years, mp.toString(),Integer.toString(mp.getReservationNumber()));
    }

    private static void menuMakePaymentCustomer(){
        Booking mp= kt.find();
        mp.setBalance(mp.makePaymentCustomer(mp.getBalance()));
        ReadAndWrite.edit(Options.hotelBookings, mp.toString(),Integer.toString(mp.getReservationNumber()));
        ReadAndWrite.edit(Options.hotel7Years, mp.toString(),Integer.toString(mp.getReservationNumber()));

    }

    private static void setOldBookings(){
        oldBookings = ReadAndWrite.read(Options.hotel7Years,0, 2, 1);
        for(int y=0;y<oldBookings.length;y++){
            String str = "";
            for(int x=0;x<oldBookings[0].length;x++){
                str+= oldBookings[y][x];
                if(x != (oldBookings[0].length-1)){
                    str+=",";
                }
            }
            kt.oldBookings.add(staticConverter.convertToBooking(str));
        }
    }

    private static void logout(){
        on = false;
    }

    private static void login(){
        on = true;
    }

}