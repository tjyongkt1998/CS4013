public class MyPoint{
    private double x;
    private double y;
    public MyPoint(){
        this.x = 0;
        this.y = 0;
        
    }
    
    public MyPoint(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double gX(){
        return x;
    }
    
    public double gY(){
        return y;
    }
    
    public double distance( double x, double y ){
        double dis = 0;
        dis = (Math.sqrt(((x-this.x)*(x-this.x))+((y-this.y)*(y-this.y))));
        return dis;
    }
    
     public double distance( MyPoint p ){
        double dis = 0;
        dis = (Math.sqrt(((p.gX()-this.x)*(p.gX()-this.x))+((p.gY()-this.y)*(p.gY()-this.y))));
        return dis;
    }
    
}
        
        