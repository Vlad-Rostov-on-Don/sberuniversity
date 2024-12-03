package homeworke.lesson4;


public interface Terminal {
    //метод проверки баланса счета
    void checkingAccountBalance() throws AccountIsLockedException;

    //метод снять деньги
    void withdrawMoney(int sum) throws AccountIsLockedException, InvalidSumException, InsufficientFundsException;

    //метод внести деньги
    void depositMoney(int sum) throws AccountIsLockedException, InvalidSumException;
}
