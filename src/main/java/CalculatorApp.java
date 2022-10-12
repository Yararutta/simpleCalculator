import java.util.Scanner;

public class CalculatorApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Калькулятор запущен!");
        System.out.println("Введите операцию над 2-мя числами:");
        String strCommand = scanner.nextLine();
        try {
            CommandService command = new CommandService(strCommand);
            command.doOperation();
            command.printResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
