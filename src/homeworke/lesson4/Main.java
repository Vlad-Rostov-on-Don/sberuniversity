package homeworke.lesson4;

public class Main {
    public static void main(String[] args) {
        TerminalServer server = new TerminalServer();
        PinValidator validator = new PinValidator();
        TerminalImpl terminal = new TerminalImpl(server, validator);

        try {
            validator.validate("1234");
            terminal.checkingAccountBalance();
            terminal.withdrawMoney(200);
            terminal.depositMoney(300);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
