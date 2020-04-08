import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.time.LocalDate;
/**
 * Allows you to read, write and edit csv files.
 */
public interface ReadAndWrite extends staticConverter
{
    /**
     * Allows you to read from a given csv file from a given start row and column. Returns a String[][]
     * represenentative of the csz(excel) file.
     */
    static String[][] read(String fileName, int amount, int startRow,
    int startColumn)
    {
        File file = new File(fileName);
        String[][] back = new String[0][0];
        boolean all = false;
        if(amount==0){
            amount = (countRows(fileName));
            all = true;
        }

        try{
            Scanner read = new Scanner(file);
            read.useDelimiter(",");
            for (int sr=1;sr<startRow;sr++){
                read.nextLine();
            }
            if(read.hasNext()){
                StringTokenizer st = new StringTokenizer(read.nextLine(), ",");
                if(all==true){
                    back = new String[amount-startRow+1][(st.countTokens()-startColumn+1)];
                }else{
                    back = new String[amount][(st.countTokens()-startColumn+1)];
                }
                for(int i=0;i<back.length;i++){
                    if((i!=0)){
                        st =  new StringTokenizer(read.nextLine(), ",");
                    }else{
                        for (int sc=1;sc<startColumn;sc++){
                            st.nextToken();
                        }
                    }
                    for(int j=0;j<back[0].length;j++){
                        back[i][j]=st.nextToken();
                    }
                }
            } 
            read.close();
        }catch(FileNotFoundException ex){
            System.out.println("File name not found");
        }
        return back;
    }

    /**
     * Allows you to write String input to a given csv file.
     */
    default void write(String fileName, String input){
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(input);
            pw.flush();
            pw.close();
            bw.close();
            fw.close();
            System.out.print("Done");
        }catch(IOException ex){
            System.out.println("File name not found");
        }
    }

    /**
     * Allows you to write String input to a given csv file.
     */
    static void staticWrite(String fileName, String input){
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(input);
            pw.flush();
            pw.close();
            bw.close();
            fw.close();
            System.out.print("Done");
        }catch(IOException ex){
            System.out.println("File name not found");
        }
    }

    /**
     * Returns a String inputed by a user. Makes sure there are no commas in it.
     */
    static String input(){
        Scanner in = new Scanner(System.in);
        boolean done = false;
        int c=0;
        String input ="";
        while (done ==false){
            input = in.next();
            for (int i =0;i<input.length();i++){
                if(input.charAt(i)==','){
                    c++;
                }
            }
            if (c==0){
                done = true;
            }else{
                System.out.println("Inputs may not contain a comma (,).");
            }
        }
        in.close();
        return input;
    }

    /**
     * Returns a String inputed by a user from an array of choices.
     */
    default String getChoice(String[] choices)
    {
        Scanner in = new Scanner(System.in);
        while (true)
        {

            char c = 'A';
            for (String choice : choices)
            {
                System.out.println(c + ") " + choice);
                c++;
            }
            String s = in.nextLine();
            int n = s.toUpperCase().charAt(0) - 'A';
            if ((0 <= n) && (n < choices.length)){
                in.close();
                return choices[n];
            }
        }      
    }

    /**
     * Returns a String inputed by a user from an array of choices.
     */
    static String getChoices(String[] choices)
    {
        Scanner in = new Scanner(System.in);
        while (true)
        {

            char c = 'A';
            for (String choice : choices)
            {
                System.out.println(c + ") " + choice);
                c++;
            }
            String input = in.nextLine();
            int n = input.toUpperCase().charAt(0) - 'A';
            if ((0 <= n) && (n < choices.length)){
                in.close();
                return choices[n];
            }
        }      
    }

    /**
     * Counts the amount of rows in a given csv file.
     */
    static int countRows(String fileName){
        File file = new File(fileName);
        int rows = 0;
        try{
            Scanner read = new Scanner(file);
            read.useDelimiter(",");
            StringTokenizer st =  new StringTokenizer("fish", ",");
            while((read.hasNext()==true)&&(st.countTokens()!=0)){
                st =  new StringTokenizer(read.nextLine(), ",");
                rows++;
            }
            read.close();
        }catch(FileNotFoundException ex){
            System.out.println("File name not found");
        }
        return rows;
    }

    /**
     * Edits a row in in a given csv file to String edit using String editTerm.
     */
    static void edit(String fileName,String edit,String editTerm){
        StringTokenizer st = new StringTokenizer(edit, ",");
        int done = st.countTokens();
        int notDone=0;
        String[][] old = read(fileName,0,1,1);
        String tempFile = "temp.csv";
        File oldfile = new File(fileName);
        File newfile = new File(tempFile);
        try{
            FileWriter fw = new FileWriter("temp.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            try{
                for(int i=0;i<old.length;i++){
                    String row="";
                    if(old[i][0].compareTo(editTerm)==0){
                        while(notDone<done){
                            row+=st.nextToken();
                            if(notDone!=done-1){
                                row+=",";
                            }
                            notDone++;
                        }
                        pw.println(row);
                    }else{
                        for(int x=0;x<old[0].length;x++){
                            row+= old[i][x];
                            if(x!=old[0].length-1){
                                row+=",";
                            }
                        }
                        pw.println(row);
                    }
                }
                pw.close();
                pw.flush();
                bw.close();
                fw.close();
                oldfile.delete();
                newfile.renameTo(oldfile);
            }catch(FileNotFoundException ex){
                System.out.println("File name not found");
            }
        }catch(IOException ex){
            System.out.println("IO error");
        }
    }

    /**
     * Removes a row in in a given csv file using String removeTerm.
     */
    static void remove(String fileName,String removeTerm){
        String[][] old = read(fileName,0,1,1);
        String tempFile = "temp.csv";
        File oldfile = new File(fileName);
        File newfile = new File(tempFile);
        try{
            FileWriter fw = new FileWriter("temp.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            try{
                for(int i=0;i<old.length;i++){
                    String row="";
                    if(old[i][0].compareTo(removeTerm)!=0){
                        for(int x=0;x<old[0].length;x++){
                            row+= old[i][x];
                            if(x!=old[0].length-1){
                                row+=",";
                            }
                        }
                        pw.println(row);
                    }
                }
                pw.close();
                pw.flush();
                bw.close();
                fw.close();
                oldfile.delete();
                newfile.renameTo(oldfile);
            }catch(FileNotFoundException ex){
                System.out.println("File name not found");
            }
        }catch(IOException ex){
            System.out.println("IO error");
        }
    }

    /**
     * Checks a given csv file to see if it a booking has the same type, number, check in and
     * check out date. Returns true if there is no room with all matching variables and
     * false if there is a room with all matching variables.
     */
    static boolean check(String fileName,String Type,String roomNumber,
    LocalDate checkIn, LocalDate checkOut){
        boolean available = true;
        String[][]allInfo = read(fileName,0,2,1);
        for(int i=0;i<allInfo.length;i++){
            StringTokenizer types = new StringTokenizer(allInfo[i][4], "/");

            StringTokenizer numbers = new StringTokenizer(allInfo[i][5], "/");

            StringTokenizer checkInInfo = new StringTokenizer(allInfo[i][7], "-");
            String day = checkInInfo.nextToken();
            String month = checkInInfo.nextToken();
            String year = checkInInfo.nextToken();
            LocalDate cIn = staticConverter.convertToDate(year,month,day);

            StringTokenizer checkOutInfo = new StringTokenizer(allInfo[i][8], "-");
            day = checkOutInfo.nextToken();
            month = checkOutInfo.nextToken();
            year = checkOutInfo.nextToken();
            LocalDate cOut = staticConverter.convertToDate(year,month,day);

            int tokens=numbers.countTokens();
            for(int j=0;j<tokens;j++){
                String currentRoomType = types.nextToken();
                String currentRoomNum = numbers.nextToken();
                if(currentRoomType.compareTo(Type)==0){
                    if(currentRoomNum.compareTo(roomNumber)==0){
                        if(((checkIn.compareTo(cOut))<=0)&&
                        (checkOut.compareTo(cIn)>=0)){
                            available=false;
                            j=tokens;
                        }
                    }
                }
            }
        }
        return available;
    }

    /**
     * Scans a given csv file and records how often each variable comes up. It also totals
     * the price. Returns a double[][] with all rrr
     */
    default double[] analytics(String fileName,
    LocalDate from, LocalDate to){
        String[][]allInfo = read(fileName,0,2,1);
        double [] totals = new double[((Options.roomTypes.length+1)*2)];

        for(int i=0;i<allInfo.length;i++){
            StringTokenizer types = new StringTokenizer(allInfo[i][4], "/");

            StringTokenizer checkInInfo = new StringTokenizer(allInfo[i][7], "-");
            String day = checkInInfo.nextToken();
            String month = checkInInfo.nextToken();
            String year = checkInInfo.nextToken();
            LocalDate cIn = staticConverter.convertToDate(year,month,day);

            int tokens=types.countTokens();
            for(int j=0;j<tokens;j++){
                String currentRoomType = types.nextToken();
                if(((from.compareTo(cIn))>=0)||
                (to.compareTo(cIn)<=0)){

                }else{
                    for(int k=0;k<totals.length;k++){
                        if((j==0)&&(k==(totals.length-1))){
                            totals[k]+=(staticConverter.convertToDouble(allInfo[i][10])
                                - staticConverter.convertToDouble(allInfo[i][12]));
                        }else if(k<((totals.length/2)-1)){
                            if(currentRoomType.compareTo(Options.roomTypes[k])==0)
                            {
                                totals[k]++;
                                totals[((totals.length/2)-1)]++;
                            }
                        }else if(k>=(totals.length/2)&&(k!=(totals.length-1))){

                            totals[k]=(totals[k-(totals.length/2)]/totals[((totals.length/2)-1)])*100;

                        }
                    }
                }
            }
        }
        return totals;
    }
}
