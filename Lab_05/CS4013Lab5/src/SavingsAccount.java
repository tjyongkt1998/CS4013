

public class SavingsAccount extends BankAccount {

    public SavingsAccount(int id, double balance) {
        super(id, balance);
        System.out.println("Account Number : "+ id + "\n" + " Account Balance : "+balance);
    }

    @Override
    public void withdraw(double withdraw) {
        if (withdraw < getBalance()) {
            setBalance(getBalance() - withdraw);
            System.out.println("Savings Account withdrawn amount is : "+ withdraw+'\n');
        } else {
            System.out.println("Insufficent Fund"+"\n");
        }

    }

    public String toString() {
        return (getID() + "\n" + getBalance() + "\n");
    }
}