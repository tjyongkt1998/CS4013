import java.util.Scanner;
/**
 * Allows you to make payments.
 */
public interface Payment extends Converter, ReadAndWrite
{
    /**
     * Used to input Strings.
     */
    Scanner input = new Scanner(System.in);

    /**
     * Allows customers to make payment. Only allows them to pay by card.
     */
    default double makePaymentCustomer(double balance){
        double payment=0;
        int p =0;
        while(p==0){
            System.out.println("Balance Remaining = €"+balance);
            System.out.println("Please enter the amount you wish to pay");
            System.out.println("Or select 'Exit' to bring you back to menu.");

            String n =(input.nextLine());
            if(n.compareToIgnoreCase("exit")==0){
                p++;
            }else{
                payment = convertToDouble(n);
                cardPayment();
                if((payment<=balance)&&(payment>0)){
                    balance-= payment;
                    System.out.println("Payment Confirmed.");
                    p++;
                }else{
                    System.out.println("Invalid amount.");
                    System.out.println("Payment Not Made.");
                }
            }
        }
        return balance;
    }

    /**
     * Make payment by cash or card.
     */
    default double makePayment(double balance){
        double payment=0;
        int p =0;
        while(p==0){
            System.out.println("Balance Remaining = €"+balance);
            System.out.println("Please enter the amount you wish to pay");
            System.out.println("Or select 'Exit' to bring you back to menu.");

            String n =(input.nextLine());
            if(n.compareToIgnoreCase("exit")==0){
                p++;
            }else{
                payment = convertToDouble(n);
                System.out.println("Please select your payment type.");
                String c =getChoice(Options.paymentTypes);
                if(c.compareToIgnoreCase("card")==0){
                    if((payment<=balance)&&(payment>0)){
                            balance-= payment;
                            cardPayment();
                            System.out.println("Payment Confirmed.");
                            p++;
                        }else{
                            System.out.println("Invalid amount.");
                            System.out.println("Payment Not Made.");
                        }
                }else if(c.compareToIgnoreCase("cash")==0){
                    System.out.println("Is €"+payment+" the correct amount?");
                    String yn = getChoice(Options.yn);
                    if(yn.compareToIgnoreCase("yes")==0){
                        if((payment<=balance)&&(payment>0)){
                            balance-= payment;
                            System.out.println("Payment Confirmed.");
                            p++;
                        }else{
                            System.out.println("Invalid amount.");
                            System.out.println("Payment Not Made.");
                        }
                    }
                }
            }

        }
        return balance;
    }

    /**
     * Used to pay deposits.
     */
    default double depositMakePayment(double balance, double deposit){
        double payment=0;
        int p =0;
        while(p==0){
            System.out.println("Total Price = €"+balance);
            System.out.println("Deposit = €"+deposit);
            System.out.println("Please enter the amount you wish to pay.");
            String n =(input.nextLine());
            if(n.compareToIgnoreCase("exit")==0){
                p++;
            }else{
                payment = convertToDouble(n);
                if((payment>=deposit)&&(payment<=balance)){
                    cardPayment();
                    balance-= payment;
                    System.out.println("Payment Confirmed.");
                    p++;
                }else{
                    System.out.println("Invalid amount.");
                    System.out.println("Payment Not Made.");
                }
            }

        }
        return balance;
    }

    /**
     * Allows you to enter card details.
     */
    default void cardPayment(){
        System.out.println("Please enter your card number.");
        String n =(input.nextLine());
        System.out.println("Please enter your cvc number.");
        String cvc =(input.nextLine());
        System.out.println("Please enter your card expiry date.");
        String ex =(input.nextLine());
        System.out.println("Card Accepted.");
    }

}
