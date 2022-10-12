import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ArithmeticOperator {
    SUM ("+"),
    MINUS ("-"),
    DIV ("/"),
    MULT ("*");

    private String symb;
    private static List<String> operators = new ArrayList<>(){{
                add(SUM.symb);
                add(MINUS.symb);
                add(DIV.symb);
                add(MULT.symb);
    }};

    ArithmeticOperator(String symb){
        this.symb = symb;
    }

    String getSymb(){
        return symb;
    }

    static ArithmeticOperator getOperatorBySymb(String symb) {
        for (ArithmeticOperator operator : ArithmeticOperator.values()) {
            if(operator.getSymb().equals(symb)) {
                return operator;
            }
        }
        return null;
    }

    static List<String> getOperations() {
        return operators;
    }
}
