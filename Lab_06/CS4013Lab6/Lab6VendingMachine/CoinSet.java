import java.util.ArrayList;

/**
   A set of coins.
*/
public class CoinSet
{  
   private ArrayList<Coin> set;
private int total;

   /**
      Constructs a CoinSet object.
   */
   public CoinSet()
   {  
      set = new ArrayList<Coin>();
   }
   
   public void add(Coin c){
       set.add(c);
       total+=c.getValue();
    }

  
  public double total(){
	  double y = 0;
      for( Coin x : set){
    	  y = y + x.getValue();
	  }
	  return y;
  }
  
  public double removeMoney(){
	 double m=0;
	 set.clear();
	 m = total;
	 total = 0;
	 return m;
  }
  public void tempWallet(Coin x){
	  set.add(x);
  }
  
  public String toString(){
	  String z= "Amount :";
	  for(int i=0; i <set.size(); i++){
		  z += set.get(i).getValue()+",";
	  }
	    return z;
  }
  }