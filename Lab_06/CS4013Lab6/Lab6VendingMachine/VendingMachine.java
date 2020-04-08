import java.util.ArrayList;

/**
   A vending machine.
*/
public class VendingMachine
{  
   private ArrayList<Product> products;
   public CoinSet coins;
   public CoinSet currentCoins;

   /**
      Constructs a VendingMachine object.
   */
   public VendingMachine()
   { 
      products = new ArrayList<Product>();
      coins = new CoinSet();
      currentCoins = new CoinSet();
   }
  public void addProduct(Product x , int quantity){
      for(int i = 0; i < quantity; i++){
    	  products.add(x);
      }
  }
  
  public void addCoin(Coin c){
	  currentCoins.add(c);
  }
  
  public Product [] getProductTypes(){
	  ArrayList<Product> x = new ArrayList<Product>();
	  Product current;
	  for(int a = 0; a < products.size() ; a++){
		  current = products.get(a);
		  if(x.size()==0){
               x.add(current);
                }
                 int count = 0;
                    for(int b = -1 ; b<(x.size()) ; b++){
                       if(b==-1){
                          b++;
                       }
                         if(current == x.get(b)){
                                  count++;       
                         
                    }           
	        }
                       if(count == 0){
               x.add(current);
         }
                       
	  }
  
         Product[]ar  = new Product[x.size()];
         for(int k = 0 ; k < x.size() ; k++){
           ar[k] = x.get(k);
	  }
	  return ar;
  }
	

  public double removeMoney(){
      double wallet = currentCoins.removeMoney();
      return wallet;
  }
  
  public double buyProduct(Product x) throws VendingException{
	  int temp = 0;
	  for(int i = 0; i < products.size(); i++){
		  Product item = products.get(i);
		  if((x.getDescription()).compareToIgnoreCase(item.getDescription())== 0){
          products.remove(i);
          i = products.size();
          temp++;
	     } 
      }
	 double change = currentCoins.total();
	     if (temp == 0){
	    	 throw new VendingException("Item is out of Stock.");
	     } else {
	    	 if(currentCoins.total() > x.getPrice())
	    	  change = coins.total() - x.getPrice();
	    	  change = change % 1;
	    	  change = change / 1;
	    	  change = change % 0.5;
	    	  change = change / 0.5;
	    	  change = change % 0.1;
	    	  change = change /0.1;
	    	  change = change % 0.05;
	    	  change = change /0.05;
	     }
	     return change;

}

}