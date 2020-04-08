public class CurrentAccount extends BankAccount{

    private double overdraftlimit;
    double overdraft = 0;

    public CurrentAccount(int id, double balance,double overdraftlimit){
        super(id,balance);
        this.overdraftlimit = overdraftlimit;
        System.out.println("Account Number : "+ id + "\n" + " Overdraft limit : "+overdraftlimit );
    }

    @Override
    public void withdraw(double withdraw){
        if( getBalance() < withdraw){
            if( withdraw < getBalance() + overdraftlimit){
                overdraft = withdraw - getBalance();
                setBalance(getBalance()-overdraft);
                System.out.println("Current account withdraw amount : "+ withdraw+"\n");
            }
        }
    }
    public String toString(){
        return(this.overdraftlimit+"\n"+this.overdraft);
    }
}