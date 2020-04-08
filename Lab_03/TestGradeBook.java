public class TestGradeBook{
    public static void main(String[] args){
      GradeBook test = new GradeBook("CS4013",3);
      
       test.addStudentResult("18221234",1,55);
       test.addStudentResult("18221234",2,40);
       test.addStudentResult("18221234",3,83);
       test.addStudentResult("18226789",1,99);
       test.addStudentResult("18226789",2,76);
       test.addStudentResult("18226789",3,23);
       System.out.print(test);
    }
}
    
    