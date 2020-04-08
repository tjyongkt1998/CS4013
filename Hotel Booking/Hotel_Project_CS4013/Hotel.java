import java.util.StringTokenizer;
/**
 * Returns this Users username.
 */
public class Hotel implements ReadAndWrite, Converter
{
    Room[] Rooms;
    /**
     * Creates a Hotel object with an array full of Rooms using the int passed in.
     */
    public Hotel(int star)
    {   
        int numberOfRoomTypes =0;
        int startRow=0;
        if(star==3){
            numberOfRoomTypes = 3;
            startRow=10;
        }else if(star==4){
            numberOfRoomTypes = 3;
            startRow=7;
        }else if(star==5){
            numberOfRoomTypes = 4;
            startRow=3;
        }else{
            System.out.println("Error, not a valid hotel");
        }
        String[][] allInfo = ReadAndWrite.read("l4Hotels.csv", numberOfRoomTypes,
                startRow,2);
        int totalNumberOfRooms=0;
        double[] dailyPrice= new double[7];
        for(int tnor=0; tnor<allInfo.length;tnor++){
            totalNumberOfRooms += convertToInt(allInfo[tnor][1]);
        }
        Rooms = new Room[totalNumberOfRooms];
        int i=0;
        int n =1;
        for(int j=0; j<allInfo.length; j++){
            int m = convertToInt(allInfo[j][1]);
            for(int dp=0; dp<7; dp++){
                dailyPrice[dp] = convertToDouble(allInfo[j][dp+4]);
            }
            StringTokenizer st = new StringTokenizer(allInfo[j][3], "+");
            int adultOccupancy = convertToInt(st.nextToken());
            int childOccupancy = convertToInt(st.nextToken());
            for(int l=0; l<m; l++){
                Rooms[i] = new Room(allInfo[j][0],adultOccupancy, childOccupancy, dailyPrice,n);
                n++;
                i++;
            }

        }

    }

}