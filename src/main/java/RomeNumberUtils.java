import java.util.List;

public class RomeNumberUtils {

    private static final String ROME_NUM_REG = "^M{0,3}?" +
            "(CM|CD|D?C{0,3})?" +
            "(XC|XL|L?X{0,3})?" +
            "(IX|IV|V?I{0,3})?$";

    public static int romanToArabic(String romaNum) {
        if(!romaNum.matches(ROME_NUM_REG)){
            throw new IllegalArgumentException("Не верный формат числа");
        }
        String romanNumeral = romaNum.toUpperCase();
        int result = 0;

        List<RomanNumber> romanNumerals = RomanNumber.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumber symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getArabVal();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number < 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " число не попадает в интервал от 0 до 4000");
        }

        if (number == 0) {
            return "0";
        }

        List<RomanNumber> romanNumerals = RomanNumber.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumber currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getArabVal() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getArabVal();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}
