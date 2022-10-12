import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum RomanNumber {

    I(1),
    IV(4),
    V(5),
    IX(9),
    X(10),
    XL(40),
    L(50),
    XC(90),
    C(100),
    CD(400),
    D(500),
    CM(900),
    M(1000);

    private int arabVal;

    RomanNumber(int arabVal) {
        this.arabVal = arabVal;
    }

    public int getArabVal() {
        return arabVal;
    }

    public static List<RomanNumber> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumber e) -> e.arabVal).reversed())
                .collect(Collectors.toList());
    }
}
