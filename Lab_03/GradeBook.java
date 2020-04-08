import java.util.ArrayList;
public class GradeBook{
    
    private String name;
    private int numOfTest;
    private ArrayList<StudentResult> list = new ArrayList<StudentResult>();
    
    
    public GradeBook(String name, int numOfTest){
        this.name = name;
        this.numOfTest = numOfTest;
    }
    
    public void addStudentResult(String id, int testID, double value) {
        boolean found = false;
        for(int i = 0; i < list.size() && !found; i++) {
            if( list.get(i).getID().equals(id)) {
                list.get(i).addResult( testID, value);
                found = true;
            }
        }
        
        if(!found){
            StudentResult student = new StudentResult(id,numOfTest);
            student.addResult(testID,value);
            list.add(student);
        }
    }
    
    public String toString() {//check out how to add extra line at end of grade
     String output = name +"\n";
     for(int i =0; i < list.size() ; i++){
         output += list.get(i)+"\n";
       }
     return output;
    }
}