public class Circle extends GeometricObject { 
  protected double radius;

  // Default constructor
  public Circle() {
    this(1.0, "white", 1.0);
  }

  // Construct circle with specified radius
  public Circle(double radius) {
    super("white", 1.0);
    this.radius = radius;
  }

  // Construct a circle with specified radius, weight, and color
  public Circle(double radius, String color, double weight) {
    super(color, weight);
    this.radius = radius;
  }

  // Getter method for radius
  public double getRadius() {
    return radius;
  }

  // Setter method for radius
  public void setRadius(double radius) {
    this.radius = radius;
  }

  // Implement the getArea method defined in GeometricObject
  @Override
  public double getArea() {
    return radius*radius*Math.PI;
  }

  // Implement the getPerimeter method defined in GeometricObject
  @Override
  public double getPerimeter() {
    return 2*radius*Math.PI;
  }
  
  @Override
  public boolean equals(Object o) {
		return this.compareTo((Circle)o) == 0;
  }
  
  @Override /** Implement the toString method in GeometricObject */
	public String toString() {
		return super.toString() +"\nRadius: " + radius+ "\nPerimeter Size: " + getPerimeter() +
			"\nArea Size: " + getArea();
  }
  
  @Override
  public void howToColor()
  {
      System.out.println("Color the circumference");
  }
 
}