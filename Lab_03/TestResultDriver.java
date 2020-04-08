public class TestResultDriver {
    public static void main(String[] args){
        TestResult test = new TestResult();
        
        test.setScore(1, 99); // Grade A1
        test.setScore(2, 69); // Grade B1
        test.setScore(3, 63); // Grade B2
        test.setScore(4, 100); // Invalid due to 3 test only
        
        System.out.println("Test 1 Score = " + test.getScore(1));
        System.out.println("Test 2 Score = " + test.getScore(2));
        System.out.println("Test 3 Score = " + test.getScore(3));
        
        System.out.println("Average Grade of Score = " + test.getAverage());
        System.out.println("Total Grade of Score = " + test.getTotal());
        System.out.println(test.getGrade(1));
        
        System.out.println(test.getGrade(2));
        System.out.println(test.getGrade(3));
        System.out.println(test);
  
        StudentResult sr = new StudentResult("18224296", 3);
        System.out.println("Student ID = "+sr);
        GradeBook GB = new GradeBook("OOD",3);
        System.out.println("Course Code = "+GB);
 
         
         
     }
}