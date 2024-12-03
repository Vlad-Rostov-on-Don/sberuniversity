package homeworke.lesson4;

public class TerminalImpl implements Terminal {

    private final TerminalServer server;
    private final PinValidator pinValidator;
    private boolean isLocked = false;
    private long lockEndTime;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    private void ensureUnlocked() throws AccountIsLockedException {
        if (isLocked) {
            long remainingTime = lockEndTime - System.currentTimeMillis();
            if (remainingTime > 0) {
                throw new AccountIsLockedException("Аккаунт заблокирован. Осталось: " + remainingTime / 1000 + "секунд");
            }
            isLocked = false;
        }
    }

    private void validatePin(String pin) throws InvalidPinException, AccountIsLockedException {
        ensureUnlocked();
        if (!pinValidator.validate(pin)) {
            pinValidator.increasePinCodeInputAttempts();
            if (pinValidator.isAccountLocked()) {
                isLocked = true;
                lockEndTime = System.currentTimeMillis() + 10000;
                throw new AccountIsLockedException("Аккаутн заблокирован на 10 секунд");
            }
            throw new InvalidPinException("Неверный PIN-код");
        }
    }

    @Override
    public void checkingAccountBalance() throws AccountIsLockedException {
        ensureUnlocked();
        System.out.println("Текущий баланс: " + server.getBalance() + " рублей");
    }

    @Override
    public void withdrawMoney(int sum) throws AccountIsLockedException, InvalidSumException, InsufficientFundsException {
        ensureUnlocked();
        if (sum % 100 != 0) {
            throw new InvalidSumException("Сумма должна быть кратной - 100");
        }
        server.withdraw(sum);
    }

    @Override
    public void depositMoney(int sum) throws AccountIsLockedException, InvalidSumException {
        ensureUnlocked();
        if (sum % 100 != 0) {
            throw new InvalidSumException("Сумма должна быть кратной - 100");
        }
        server.deposit(sum);
    }
}
