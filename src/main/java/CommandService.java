public class CommandService {

    private static String ARAB_SYMB_REG = "\\d";
    private static String ROME_SYMB_REG = "[A-Za-z]";

    private String command;
    private ArithmeticOperator operator;
    private String[] operands;
    private int[] arabOperands;
    private String result;

    CommandService(String command) {
        this.command = command.replaceAll("\\s", "").toUpperCase();
        setOperator();
        isValidCommand();
        setOperands();
        checkOperands();
    }

    public boolean isValidCommand() throws IllegalArgumentException {
        if(containsRomeSymb() && containsArabSymb()) {
            throw new IllegalArgumentException("Операция может содержать одновременно только" +
                    " арабские или только римские цифры");
        }

        if(!isValidOperator()) {
            throw new IllegalArgumentException("Операция не содержит ни одного из операторов "
                    + ArithmeticOperator.getOperations().toString() + " или содержит более одного оператора");
        }

        return true;
    }

    private boolean containsArabSymb() {
        String regex = ".*" + ARAB_SYMB_REG + ".*";
        return command.matches(regex);
    }

    private boolean containsRomeSymb() {
        String regex = ".*" + ROME_SYMB_REG + ".*";
        return command.matches(regex);
    }

    private void setOperator() {
        String operator = command.replaceAll("[" + ARAB_SYMB_REG + ROME_SYMB_REG + "]", "");
        this.operator = ArithmeticOperator.getOperatorBySymb(operator);
    }

    private boolean isValidOperator() {
        return operator != null;
    }

    private void setOperands() {
        operands = command.split("[" + operator.getSymb() + "]");
    }

    private boolean checkOperands() {
        arabOperands = new int[operands.length];
        for(int i = 0; i < operands.length; i++) {
            int oInt = containsRomeSymb() ?
                    RomeNumberUtils.romanToArabic(operands[i]) :
                    Integer.valueOf(operands[i]);

            if(oInt < 1 || oInt > 10) {
                throw new IllegalArgumentException("Операнд не должен быть не менее 1 и не более 10");
            }

            arabOperands[i] = oInt;
        }
        return true;
    }

    public void doOperation() {
        if(containsArabSymb()) {
            doOperatorByArab();
        } else if(containsRomeSymb()) {
            doOperatorByRome();
        }
    }

    private void doOperatorByArab() {
        int o1 = arabOperands[0], o2 = arabOperands[1];
        switch(operator) {
            case SUM:
                result = String.valueOf(o1 + o2);
                break;
            case MINUS:
                int arabResult = o1 - o2;
                if(containsRomeSymb() && arabResult < 0){
                    throw new IllegalArgumentException("Результат вычисления римских чисел не может быть < 0");
                }
                result = String.valueOf(o1 - o2);
                break;
            case DIV:
                result = String.valueOf(o1 / o2);
                break;
            case MULT:
                result = String.valueOf(o1 * o2);
                break;
            default:
                break;
        }
    }

    private void doOperatorByRome() {
        doOperatorByArab();
        result = RomeNumberUtils.arabicToRoman(Integer.valueOf(result));
    }

    public void printResult() {
        System.out.println(command + " = " + result);
        if(containsRomeSymb()){
            System.out.println(arabOperands[0] + operator.getSymb() + arabOperands[1] + " = " + result);
        }
    }
}
