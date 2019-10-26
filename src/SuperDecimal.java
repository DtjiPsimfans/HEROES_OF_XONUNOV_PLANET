import java.math.*;
import java.util.*;

/**
 * This class contains attributes of a super decimal.
 * @author DtjiPsimfans
 * */
public class SuperDecimal{
    /**
     * This class contains attributes of super decimals with very large values.
     */

    // Private attributes
    private BigDecimal num;
    private BigInteger exponent;

    // Constructors

    public SuperDecimal(){
        num = new BigDecimal("0");
        exponent = new BigInteger("0");
    }

    public SuperDecimal(int num, BigInteger exponent){
        this.exponent = exponent;
        setNum(new BigDecimal(num));
    }

    public SuperDecimal(double num, BigInteger exponent){
        this.exponent = exponent;
        setNum(new BigDecimal(num));
    }

    public SuperDecimal(String num, BigInteger exponent){
        this.exponent = exponent;
        setNum(new BigDecimal(num));
    }

    public SuperDecimal(BigDecimal num, BigInteger exponent){
        this.exponent = exponent;
        setNum(num);
    }

    public SuperDecimal(int num, String exponent){
        this.exponent = new BigInteger(exponent);
        setNum(new BigDecimal(num));
    }

    public SuperDecimal(double num, String exponent){
        this.exponent = new BigInteger(exponent);
        setNum(new BigDecimal(num));
    }

    public SuperDecimal(String num, String exponent){
        this.exponent = new BigInteger(exponent);
        setNum(new BigDecimal(num));
    }

    public SuperDecimal(BigDecimal num, String exponent){
        this.exponent = new BigInteger(exponent);
        setNum(num);
    }

    public SuperDecimal(int num, int exponent){
        this.exponent = new BigInteger(exponent + "");
        setNum(new BigDecimal(num));
    }

    public SuperDecimal(double num, int exponent){
        this.exponent = new BigInteger(exponent + "");
        setNum(new BigDecimal(num));
    }

    public SuperDecimal(String num, int exponent){
        this.exponent = new BigInteger(exponent + "");
        setNum(new BigDecimal(num));
    }

    public SuperDecimal(BigDecimal num, int exponent){
        this.exponent = new BigInteger(exponent + "");
        setNum(num);
    }

    public SuperDecimal(String value){
        ArrayList<String> legalChars = new ArrayList<>();
        legalChars.add("0");
        legalChars.add("1");
        legalChars.add("2");
        legalChars.add("3");
        legalChars.add("4");
        legalChars.add("5");
        legalChars.add("6");
        legalChars.add("7");
        legalChars.add("8");
        legalChars.add("9");
        legalChars.add(".");
        legalChars.add("+");
        legalChars.add("-");
        legalChars.add("e");
        legalChars.add("E");
        boolean hasIllegalChar = false;
        ArrayList<String> chars = new ArrayList<>();
        for (int i = 0; i < value.length(); i++){
            chars.add(value.substring(i, i + 1));
        }
        for (String s : chars){
            if (!legalChars.contains(s)){
                hasIllegalChar = true;
                break;
            }
        }
        int eOccurrences = countOccurrences(value, 'e');
        int EOccurrences = countOccurrences(value, 'E');
        if (!hasIllegalChar && eOccurrences + EOccurrences < 2){
            String[] currStr = chars.contains("E") ? value.split("E") : value.split("e");
            String strNum;
            String strExp;
            if (currStr.length > 2){
                strNum = currStr[0] + "E" + currStr[1];
                strExp = currStr[2];
            }
            else if (currStr.length == 2){
                strNum = currStr[0];
                strExp = currStr[1];
            }
            else{
                strNum = currStr[0];
                strExp = "0";
            }
            num = new BigDecimal(strNum);
            exponent = new BigInteger(strExp);
            BigDecimal temp = num;
            setNum(temp);
        }
        else {
            num = new BigDecimal("0");
            exponent = new BigInteger("0");
        }
    }

    // Other methods

    private void setNum(BigDecimal num){
        this.num = num;
        assert exponent != null;
        int exponentDiff = num.precision() - num.scale() - 1;
        int exponentDiffAbs = Math.abs(exponentDiff);
        if (this.num.compareTo(new BigDecimal("10")) >= 0) {
            this.num = this.num.divide(new BigDecimal("10").pow(exponentDiffAbs), 100, RoundingMode.CEILING);
            exponent = exponent.add(new BigInteger(exponentDiffAbs + ""));
        }
        else if (this.num.compareTo(new BigDecimal("-10")) <= 0){
            this.num = this.num.divide(new BigDecimal("10").pow(exponentDiffAbs), 100, RoundingMode.CEILING);
            exponent = exponent.add(new BigInteger(exponentDiffAbs + ""));
        }
        else if (this.num.compareTo(new BigDecimal("-1")) > 0 && this.num.compareTo(new BigDecimal("0")) < 0){
            this.num = this.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            exponent = exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        else if (this.num.compareTo(new BigDecimal("0")) > 0 && this.num.compareTo(new BigDecimal("1")) < 0){
            this.num = this.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            exponent = exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
    }

    public SuperDecimal multiply(int other){
        SuperDecimal cloned = clone();
        cloned.num = cloned.num.multiply(new BigDecimal(other + ""));
        int exponentDiff = cloned.num.precision() - cloned.num.scale() - 1;
        int exponentDiffAbs = Math.abs(exponentDiff);
        if (cloned.num.compareTo(new BigDecimal("10")) >= 0 || cloned.num.compareTo(new BigDecimal("-10")) <= 0){
            cloned.num = cloned.num.divide(new BigDecimal("10").pow(exponentDiffAbs), 100, RoundingMode.CEILING);
            cloned.exponent = cloned.exponent.add(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("1")) < 0 && cloned.num.compareTo(new BigDecimal("0")) > 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("-1")) > 0 && cloned.num.compareTo(new BigDecimal("0")) < 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        return new SuperDecimal(cloned.num, cloned.exponent);
    }

    public SuperDecimal divide(int other){
        SuperDecimal cloned = clone();
        cloned.num = cloned.num.divide(new BigDecimal(other + ""), 100, RoundingMode.CEILING);
        int exponentDiff = cloned.num.precision() - cloned.num.scale() - 1;
        int exponentDiffAbs = Math.abs(exponentDiff);
        if (cloned.num.compareTo(new BigDecimal("1")) < 0 && cloned.num.compareTo(new BigDecimal("0")) > 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("-1")) > 0 && cloned.num.compareTo(new BigDecimal("0")) < 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("10")) >= 0 || cloned.num.compareTo(new BigDecimal("-10")) <= 0){
            cloned.num = cloned.num.divide(new BigDecimal("10").pow(exponentDiffAbs), 100, RoundingMode.CEILING);
            cloned.exponent = cloned.exponent.add(new BigInteger(exponentDiffAbs + ""));
        }
        return new SuperDecimal(cloned.num, cloned.exponent);
    }

    public SuperDecimal multiply(double other){
        SuperDecimal cloned = clone();
        cloned.num = cloned.num.multiply(new BigDecimal(other + ""));
        int exponentDiff = cloned.num.precision() - cloned.num.scale() - 1;
        int exponentDiffAbs = Math.abs(exponentDiff);
        if (cloned.num.compareTo(new BigDecimal("10")) >= 0 || cloned.num.compareTo(new BigDecimal("-10")) <= 0){
            cloned.num = cloned.num.divide(new BigDecimal("10").pow(exponentDiffAbs), 100, RoundingMode.CEILING);
            cloned.exponent = cloned.exponent.add(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("1")) < 0 && cloned.num.compareTo(new BigDecimal("0")) > 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("-1")) > 0 && cloned.num.compareTo(new BigDecimal("0")) < 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        return new SuperDecimal(cloned.num, cloned.exponent);
    }

    public SuperDecimal divide(double other){
        SuperDecimal cloned = clone();
        cloned.num = cloned.num.divide(new BigDecimal(other + ""), 100, RoundingMode.CEILING);
        int exponentDiff = cloned.num.precision() - cloned.num.scale() - 1;
        int exponentDiffAbs = Math.abs(exponentDiff);
        if (cloned.num.compareTo(new BigDecimal("1")) < 0 && cloned.num.compareTo(new BigDecimal("0")) > 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("-1")) > 0 && cloned.num.compareTo(new BigDecimal("0")) < 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("10")) >= 0 || cloned.num.compareTo(new BigDecimal("-10")) <= 0){
            cloned.num = cloned.num.divide(new BigDecimal("10").pow(exponentDiffAbs), 100, RoundingMode.CEILING);
            cloned.exponent = cloned.exponent.add(new BigInteger(exponentDiffAbs + ""));
        }
        return new SuperDecimal(cloned.num, cloned.exponent);
    }

    public SuperDecimal multiply(BigDecimal other){
        SuperDecimal cloned = clone();
        cloned.num = cloned.num.multiply(other);
        int exponentDiff = cloned.num.precision() - cloned.num.scale() - 1;
        int exponentDiffAbs = Math.abs(exponentDiff);
        if (cloned.num.compareTo(new BigDecimal("10")) >= 0 || cloned.num.compareTo(new BigDecimal("-10")) <= 0){
            cloned.num = cloned.num.divide(new BigDecimal("10").pow(exponentDiffAbs), 100, RoundingMode.CEILING);
            cloned.exponent = cloned.exponent.add(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("1")) < 0 && cloned.num.compareTo(new BigDecimal("0")) > 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("-1")) > 0 && cloned.num.compareTo(new BigDecimal("0")) < 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        return new SuperDecimal(cloned.num, cloned.exponent);
    }

    public SuperDecimal divide(BigDecimal other){
        SuperDecimal cloned = clone();
        cloned.num = cloned.num.divide(other, 100, RoundingMode.CEILING);
        int exponentDiff = cloned.num.precision() - cloned.num.scale() - 1;
        int exponentDiffAbs = Math.abs(exponentDiff);
        if (cloned.num.compareTo(new BigDecimal("1")) < 0 && cloned.num.compareTo(new BigDecimal("0")) > 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("-1")) > 0 && cloned.num.compareTo(new BigDecimal("0")) < 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("10")) >= 0 || cloned.num.compareTo(new BigDecimal("-10")) <= 0){
            cloned.num = cloned.num.divide(new BigDecimal("10").pow(exponentDiffAbs), 100, RoundingMode.CEILING);
            cloned.exponent = cloned.exponent.add(new BigInteger(exponentDiffAbs + ""));
        }
        return new SuperDecimal(cloned.num, cloned.exponent);
    }

    public SuperDecimal multiply(SuperDecimal other){
        SuperDecimal cloned = clone();
        cloned.num = cloned.num.multiply(other.num);
        cloned.exponent = cloned.exponent.add(other.exponent);
        int exponentDiff = cloned.num.precision() - cloned.num.scale() - 1;
        int exponentDiffAbs = Math.abs(exponentDiff);
        if (cloned.num.compareTo(new BigDecimal("10")) >= 0 || cloned.num.compareTo(new BigDecimal("-10")) <= 0){
            cloned.num = cloned.num.divide(new BigDecimal("10").pow(exponentDiffAbs), 100, RoundingMode.CEILING);
            cloned.exponent = cloned.exponent.add(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("1")) < 0 && cloned.num.compareTo(new BigDecimal("0")) > 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("-1")) > 0 && cloned.num.compareTo(new BigDecimal("0")) < 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        return new SuperDecimal(cloned.num, cloned.exponent);
    }

    public SuperDecimal divide(SuperDecimal other){
        SuperDecimal cloned = clone();
        cloned.num = cloned.num.divide(other.num, 100, RoundingMode.CEILING);
        cloned.exponent = cloned.exponent.subtract(other.exponent);
        int exponentDiff = cloned.num.precision() - cloned.num.scale() - 1;
        int exponentDiffAbs = Math.abs(exponentDiff);
        if (cloned.num.compareTo(new BigDecimal("1")) < 0 && cloned.num.compareTo(new BigDecimal("0")) > 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("-1")) > 0 && cloned.num.compareTo(new BigDecimal("0")) < 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("10")) >= 0 || cloned.num.compareTo(new BigDecimal("-10")) <= 0){
            cloned.num = cloned.num.divide(new BigDecimal("10").pow(exponentDiffAbs), 100, RoundingMode.CEILING);
            cloned.exponent = cloned.exponent.add(new BigInteger(exponentDiffAbs + ""));
        }
        return new SuperDecimal(cloned.num, cloned.exponent);
    }

    public SuperDecimal pow(int other){
        SuperDecimal cloned = clone();
        cloned.num = cloned.num.pow(other);
        cloned.exponent = cloned.exponent.multiply(new BigInteger(other + ""));
        int exponentDiff = cloned.num.precision() - cloned.num.scale() - 1;
        int exponentDiffAbs = Math.abs(exponentDiff);
        if (cloned.num.compareTo(new BigDecimal("10")) >= 0 || cloned.num.compareTo(new BigDecimal("-10")) <= 0){
            cloned.num = cloned.num.divide(new BigDecimal("10").pow(exponentDiffAbs), 100, RoundingMode.CEILING);
            cloned.exponent = cloned.exponent.add(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("1")) < 0 && cloned.num.compareTo(new BigDecimal("0")) > 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        else if (cloned.num.compareTo(new BigDecimal("-1")) > 0 && cloned.num.compareTo(new BigDecimal("0")) < 0){
            cloned.num = cloned.num.multiply(new BigDecimal("10").pow(exponentDiffAbs));
            cloned.exponent = cloned.exponent.subtract(new BigInteger(exponentDiffAbs + ""));
        }
        return new SuperDecimal(cloned.num, cloned.exponent);
    }

    public SuperDecimal pow(double other){
        SuperDecimal cloned = clone();
        int intOtherPower = (int) other;
        double doubleOtherPower = other % 1;
        SuperDecimal first = cloned.pow(intOtherPower);
        SuperDecimal second = new SuperDecimal(pow(cloned.toBigDecimal(), doubleOtherPower), 0);
        return first.multiply(second);
    }

    private BigDecimal pow(BigDecimal a, double b){
        int intBPower = (int) b;
        double doubleBPower = b % 1;
        BigDecimal first = a.pow(intBPower);
        BigDecimal second = new BigDecimal(Math.pow(a.doubleValue(), doubleBPower));
        return first.multiply(second);
    }

    public SuperDecimal add(int other){
        return add(new SuperDecimal(new BigDecimal(other + ""), new BigInteger("0")));
    }

    public SuperDecimal subtract(int other){
        return subtract(new SuperDecimal(new BigDecimal(other + ""), new BigInteger("0")));
    }

    public SuperDecimal add(double other){
        return add(new SuperDecimal(new BigDecimal(other + ""), new BigInteger("0")));
    }

    public SuperDecimal subtract(double other){
        return subtract(new SuperDecimal(new BigDecimal(other + ""), new BigInteger("0")));
    }

    public SuperDecimal add(BigDecimal other){
        return add(new SuperDecimal(other, new BigInteger("0")));
    }

    public SuperDecimal subtract(BigDecimal other){
        return subtract(new SuperDecimal(other, new BigInteger("0")));
    }

    public SuperDecimal add(SuperDecimal other){
        SuperDecimal cloned = this.clone();
        SuperDecimal higher = cloned.compareTo(other) == 1 ? cloned : other;
        SuperDecimal lower = cloned.equals(higher) ? other : cloned;
        BigInteger lowerNumExp = new BigInteger((lower.num.precision() - lower.num.scale() - 1) + "");
        BigInteger exponentDiff = higher.exponent.subtract(lower.exponent);
        BigInteger negLimit = new BigInteger("-2147483645");
        BigInteger posLimit = new BigInteger("2147483645");
        if (lower.exponent.compareTo(higher.exponent) < 0){
            if (lowerNumExp.subtract(exponentDiff).compareTo(negLimit) < 0 || exponentDiff.compareTo(posLimit) > 0){
                boolean isLowerNumPos = lower.num.compareTo(new BigDecimal("0")) >= 0;
                lower.num = isLowerNumPos? new BigDecimal("1E-2147483645") : new BigDecimal("-1E-2147483645");
                lower.exponent = higher.exponent;
            }
            else{
                lower.num = lower.num.divide(new BigDecimal("1E" + exponentDiff.intValue()), 100, RoundingMode.CEILING);
                lower.exponent = lower.exponent.add(exponentDiff);
            }
        }
        return new SuperDecimal(cloned.num.add(other.num), higher.exponent);
    }

    public SuperDecimal subtract(SuperDecimal other){
        SuperDecimal cloned = this.clone();
        SuperDecimal higher = cloned.compareTo(other) == 1 ? cloned : other;
        SuperDecimal lower = cloned.equals(higher) ? other : cloned;
        BigInteger lowerNumExp = new BigInteger((lower.num.precision() - lower.num.scale() - 1) + "");
        BigInteger exponentDiff = higher.exponent.subtract(lower.exponent);
        BigInteger negLimit = new BigInteger("-2147483645");
        BigInteger posLimit = new BigInteger("2147483645");
        if (lower.exponent.compareTo(higher.exponent) < 0){
            if (lowerNumExp.subtract(exponentDiff).compareTo(negLimit) < 0 || exponentDiff.compareTo(posLimit) > 0){
                boolean isLowerNumPos = lower.num.compareTo(new BigDecimal("0")) >= 0;
                lower.num = isLowerNumPos? new BigDecimal("1E-2147483645") : new BigDecimal("-1E-2147483645");
                lower.exponent = higher.exponent;
            }
            else{
                lower.num = lower.num.divide(new BigDecimal("1E" + exponentDiff.intValue()), 100, RoundingMode.CEILING);
                lower.exponent = lower.exponent.add(exponentDiff);
            }
        }
        return new SuperDecimal(cloned.num.subtract(other.num), higher.exponent);
    }

    public int compareTo(SuperDecimal other){
        setNum(this.num);
        other.setNum(other.num);
        if (exponent.compareTo(other.exponent) > 0){
            return 1;
        }
        else if (exponent.compareTo(other.exponent) < 0){
            return -1;
        }
        else {
            return num.compareTo(other.num);
        }
    }

    public SuperDecimal valueOf(int value){
        return new SuperDecimal(value, 0);
    }

    public SuperDecimal valueOf(double value){
        return new SuperDecimal(value, 0);
    }

    public SuperDecimal valueOf(BigDecimal value){
        return new SuperDecimal(value, 0);
    }

    public SuperDecimal valueOf(String value){
        return new SuperDecimal(value);
    }

    public Double toDouble(){
        return num.doubleValue() * Math.pow(10, exponent.intValue());
    }

    public BigDecimal toBigDecimal(){
        return new BigDecimal(num.doubleValue() + "E" + exponent.intValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }

        if (!obj.getClass().equals(getClass())){
            return false;
        }

        if (obj instanceof SuperDecimal){
            return num.compareTo(((SuperDecimal) obj).num) == 0 && exponent.compareTo(((SuperDecimal) obj).exponent) == 0;
        }
        else{
            return false;
        }
    }

    public SuperDecimal clone(){
        return new SuperDecimal(num, exponent);
    }

    public String toString(){
        setNum(num);
        return exponent.compareTo(new BigInteger("15")) > 0 || exponent.compareTo(new BigInteger("-15")) < 0
                ? "(" + num + ")*10^(" + exponent + ")" : String.valueOf(num.doubleValue() * Math.pow(10, exponent.intValue()));
    }

    private int countOccurrences(String haystack, char needle)
    {
        int count = 0;
        for (int i=0; i < haystack.length(); i++)
        {
            if (haystack.charAt(i) == needle)
            {
                count++;
            }
        }
        return count;
    }
}
