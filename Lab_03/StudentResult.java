import java.util.*;

public class StudentResult{
     private String ID;
     private TestResult testresult;
     
     public StudentResult(String ID){
      this.ID = ID;
    }
    
    public StudentResult( String ID, int numOfTests){
        this.ID = ID;
        this.testresult = new TestResult();
    }
    
    public String getID(){
        return this.ID;
    }
    
    public void addResult(int testID, double value){
       testresult.setScore(testID, value);
    }
    
    public String toString( ){
       return(this.ID + "\n" + this.testresult);
    } 
}