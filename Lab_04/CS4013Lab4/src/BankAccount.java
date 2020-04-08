import java.util.GregorianCalendar;

public class BankAccount{

    private int id;
    private double balance;
    private double AIR;
    private GregorianCalendar dateCreated;

    public BankAccount(int id, double balance){
        this.setID(id);
        this.setBalance(balance);
    }

    public double getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public double getAIR(){
        return AIR;
    }

    public void setAIR(double AIR){
        this.AIR = AIR;
    }

    public GregorianCalendar getDateCreated(){
        return dateCreated;
    }

    public double getMonthlyInterestRate(){
        System.out.println("Annual interest rate is : "+AIR);
        return (AIR/12);
    }

    public void withdraw(double withdraw){
        this.balance = balance - withdraw;
        System.out.println(" Amount of money withdrawn " + withdraw);
    }

    public void deposit( double deposit){
        this.balance = balance + deposit;
        System.out.println("Current Balance is now " + deposit);
    }


    public String toString() {
        return (this.id + "\n" + this.balance + "\n" + this.AIR + "\n" + this.dateCreated + "\n");


    }
}