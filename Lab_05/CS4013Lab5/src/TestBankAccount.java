
import java.util.ArrayList;


public class TestBankAccount{
    public static void main (String args[]){

        ArrayList<BankAccount> list = new ArrayList<BankAccount>();
        SavingsAccount TJSavings = new SavingsAccount(1234,800);
        SavingsAccount MikeysSavings = new SavingsAccount(4321,8000);
        CurrentAccount TJCurrent = new CurrentAccount(1234,40,50);
        CurrentAccount MikeyCurrent = new CurrentAccount(4321,80,100);

        list.add(TJSavings);
        list.add(MikeysSavings);
        list.add(TJCurrent);
        list.add(MikeyCurrent);

        TJSavings.setAIR(4);
        MikeysSavings.setAIR(3);
        TJCurrent.setAIR(5);
        MikeyCurrent.setAIR(6);

        TJSavings.withdraw(100);
        MikeysSavings.withdraw(300);
        TJCurrent.withdraw(80);
        MikeyCurrent.withdraw(60);


        TJSavings.deposit(40);
        MikeysSavings.deposit(30);
        TJCurrent.deposit(50);
        MikeyCurrent.deposit(60);

        System.out.println(TJSavings.toString());
        System.out.println(MikeysSavings.toString());
        System.out.println(TJCurrent.toString());
        System.out.println(MikeyCurrent.toString());



    }
}