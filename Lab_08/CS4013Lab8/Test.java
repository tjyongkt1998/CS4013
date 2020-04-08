
public class Test {

		
	public static void main(String[] args) {
	GeometricObject[] objects = {new Circle(8), new Circle(4), new Square(6.9),new Square(15), new Rectangle(6.1,7.8), new Rectangle(11.5,3.5)};
	int i;
	 
    for(i=0;i < objects.length;i++){
    	System.out.println(objects[i].toString());
    }
	
    for(i=0; i < objects.length-1;i++){
	 System.out.println(GeometricObject.max(objects[i],objects[i+1])); 
	}
    
    for(i=0; i < objects.length;i++){
     objects[i].howToColor();
    }
  }
}