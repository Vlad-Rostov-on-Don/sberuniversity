package homeworke.lesson4;

public class TerminalServer {
    private int balance = 1000;

    public int getBalance() {
        return balance;
    }

    public void deposit(int sum) {
        balance += sum;
    }

    public void withdraw(int sum) throws InsufficientFundsException {
        if (balance < sum) {
            throw new InsufficientFundsException("Недостаточно средств на счете");
        }
        balance -= sum;
    }
}
