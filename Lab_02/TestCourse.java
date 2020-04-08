public class TestCourse {
   public static void main(String[] args) {
	
	Course java = new Course("java");
	
	java.addStudent("Kitara");
	java.addStudent("George");
	java.addStudent("Fionn");
	
	java.dropStudent("Kitara");
	
	String[] s = java.getStudents();
	System.out.println("Student in the course "+java.getCourseName()+" are:");
	for(int i = 0; i < s.length; i++) {
		if(s[i] != null) {System.out.println(s[i]);}
	}
   }
}