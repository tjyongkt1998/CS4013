
public class TestMyPoint{
      public static void main( String[] args){
      MyPoint p1 = new MyPoint();
      MyPoint p2 = new MyPoint(7.9,8.2);
      MyPoint p3 = new MyPoint(10.3,11.6);
      
      System.out.println("Point 1 x value and y value: " + p1.gX() +","+ p1.gY() );
      System.out.println("Point 2 x value and y value: " + p2.gX() +","+ p2.gY() );
      System.out.println("Point 3 x value and y value: " + p3.gX() +","+ p3.gY() );
      
      System.out.println("Distance between point 1 (0,0) and (7.9,8.2) is " + p1.distance(7.9,8.2));
      System.out.println("Distance between Point 2  and Point 1 is " + p2.distance(p1));
      System.out.println("Distance between Point 3  and Point 2 is " + p3.distance(p2));
    }
}