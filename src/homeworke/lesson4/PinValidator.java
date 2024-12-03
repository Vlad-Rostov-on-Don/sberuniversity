package homeworke.lesson4;

public class PinValidator {

    private final String rightPinCode = "1234";
    private int failedAttempts = 0;
    private final int maxAttempts = 3;

    public boolean validate(String pin) {
        if (rightPinCode.equals(pin)) {
            failedAttempts = 0;
            return true;
        }
        return false;
    }

    public void increasePinCodeInputAttempts() {
        failedAttempts++;
    }

    public boolean isAccountLocked() {
        return failedAttempts >= maxAttempts;
    }
}


