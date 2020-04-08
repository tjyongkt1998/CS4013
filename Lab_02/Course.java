import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Course {
  private String courseName;
  private String[] students = new String[4];
  private int numberOfStudents;
    
  public Course(String courseName) { /** sets the course name **/ 
    this.courseName = courseName;
  }
  
  public void addStudent(String student) {/** adds name of students increases the number of student till same size of array**/
    students[numberOfStudents] = student;
    numberOfStudents++;
  }
    public void addStudent(String student, int numberOfStudents) {
    students[numberOfStudents] = student;
    numberOfStudents++;
    if(numberOfStudents == students.length){
      String[] newArray = Arrays.copyOf(students,numberOfStudents + students.length);
      students = newArray;
    }
  }

  
  public String[] getStudents() { /** return student names **/
     return students;
  }

  public int getNumberOfStudents() { /** return number or amount of students **/
     return numberOfStudents;
  }  

  public String getCourseName() { /** return course name **/
     return courseName;
  }  
  
  public void dropStudent(String student) {
    int i = 0;
    int counter = 0;
    for ( i = 0; i < students.length; i++){
        if(students[i].equals(student)){
            counter = i;
            break;
        }
    }
    for(int j = counter; j < students.length - 1; j++){
        students[counter] = students[ j + 1];
    }
    numberOfStudents--;
    
    }
 
  public void clear() {
     for(int i = 0; i < students.length; i++){
     students[i] = null;
    }
  }
}