public class Square extends GeometricObject {
  private double side;


    /** Construct a rectangle with width and height */
  public Square(double side) {
    this.side = side;

  }
  
  /**Return width*/
  public double getSide() {
    return side;
  }

  /**Set a new width*/
  public void setSide(double side) {
    this.side = side;
  }

  /**Implement the getArea method in GeometricObject*/
  @Override
  public double getArea() {
    return side*side;
  }

  /**Implement the getPerimeter method in GeometricObject*/
  @Override
  public double getPerimeter() {
    return 4*(side);
  }

  @Override
  public boolean equals(Object o) {
		return this.compareTo((Rectangle)o) == 0;
  }
  
  @Override /** Implement the toString method in GeometricObject */
	public String toString() {
		return super.toString() +"\nSide: " + getSide()+ "\nPerimeter Size: " + getPerimeter() +
			"\nArea Size: " + getArea();
  }
		
   @Override
	 public void howToColor(){
	   System.out.println("Colour all 4 Sides");
	    }
  }
  