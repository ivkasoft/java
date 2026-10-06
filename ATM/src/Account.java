public class Account {
    double balance;
    int pin;
    int accountNumber;

    public Account(double balance, int pin, int accountNumber) {
        super();
        this.balance = balance;
        this.pin = pin;
        this.accountNumber = accountNumber;
    }

    public double getBalance(){
        return this.balance;
    }

    public void deposit(double amount){
        balance+=amount;
    }

    public void withdraw(double amount){
        if(amount<=balance)balance-=amount;
    }

    public boolean checkPin(int pin){
        if(this.pin==pin)return true;
        else return false;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }
}

