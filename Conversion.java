package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.HashMap;

public class Conversion {
    private final int sourceBase;
    private final int targetBase;
    private final String valueToConvert;

    Conversion(int sourceBase, int targetBase, String valueToConvert) {
        this.sourceBase = sourceBase;
        this.targetBase = targetBase;
        this.valueToConvert = valueToConvert;
    }

    public String getConversionResult() {
        String[] valueParts = this.valueToConvert.split("\\.");
        String integerPart = valueParts[0];
        String fractionalPart = (valueParts.length > 1) ? valueParts[1] : "";

        String integerResult = this.getTargetValue(this.getDecimalValue(integerPart));
        String fractionalResult = (fractionalPart.isEmpty()) ? "" : this.getFractionalConversionResult(fractionalPart);

        return integerResult + ((fractionalResult.isEmpty()) ? "" : "." + fractionalResult);
    }

    private String getFractionalConversionResult(String fractionalPart) {
        BigDecimal fractionalValue = BigDecimal.ZERO;
        BigDecimal base = BigDecimal.valueOf(this.sourceBase);
        BigDecimal baseReciprocal = BigDecimal.ONE.divide(base, MathContext.DECIMAL128);
        StringBuilder result = new StringBuilder();

        int maxPrecision = 5; // Maximum number of fractional digits to include in the result

        for (int i = 0; i < fractionalPart.length(); i++) {
            char digitChar = fractionalPart.charAt(i);
            int digit;
            if (Character.isDigit(digitChar)) {
                digit = Character.getNumericValue(digitChar);
            } else {
                digit = this.curateNumberToDecimal(Character.toUpperCase(digitChar));
                if (digit < 0 || digit >= this.sourceBase) {
                    // Skip invalid characters
                    continue;
                }
            }

            fractionalValue = fractionalValue.add(BigDecimal.valueOf(digit).multiply(baseReciprocal));
            baseReciprocal = baseReciprocal.divide(base, MathContext.DECIMAL128);
        }

        // Convert fractional value to target base
        for (int i = 0; i < maxPrecision; i++) {
            fractionalValue = fractionalValue.multiply(BigDecimal.valueOf(this.targetBase));
            int integerDigit = fractionalValue.intValue();
            if (integerDigit >= 10) {
                result.append(this.curateNumberFromDecimal(BigInteger.valueOf(integerDigit)));
            } else {
                result.append(integerDigit);
            }
            fractionalValue = fractionalValue.subtract(BigDecimal.valueOf(integerDigit));
        }

        return result.toString();
    }

    private BigInteger getDecimalValue(String value) {
        String[] valueToParts = value.split("");
        BigInteger decimalNumber = BigInteger.ZERO;

        int j = 0;
        for (int i = valueToParts.length - 1; i >= 0; i--) {
            if (Character.isDigit(valueToParts[j].charAt(0))) {
                decimalNumber = decimalNumber.add(BigInteger.valueOf(Integer.parseInt(valueToParts[j]))
                        .multiply(BigInteger.valueOf(this.sourceBase).pow(i)));
            } else {
                int curatedNumber = this.curateNumberToDecimal(valueToParts[j].toUpperCase().charAt(0));
                decimalNumber = decimalNumber.add(BigInteger.valueOf(curatedNumber)
                        .multiply(BigInteger.valueOf(this.sourceBase).pow(i)));
            }

            j++;
        }

        return decimalNumber;
    }

    private String getTargetValue(BigInteger decimalValue) {
        if (decimalValue.equals(BigInteger.ZERO)) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        BigInteger subsequentQuotient = decimalValue;

        while (subsequentQuotient.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divisionResult = subsequentQuotient.divideAndRemainder(BigInteger.valueOf(this.targetBase));
            BigInteger quotient = divisionResult[0];
            BigInteger remainder = divisionResult[1];

            if (remainder.compareTo(BigInteger.ZERO) >= 0) {
                result.append(this.curateNumberFromDecimal(remainder));
            }

            subsequentQuotient = quotient;
        }

        return result.reverse().toString();
    }

    private String curateNumberFromDecimal(BigInteger number) {
        int digit = number.intValue();

        if (digit >= 0 && digit <= 9) {
            return String.valueOf(digit);
        } else {
            char letter = (char) ('A' + (digit - 10));
            return String.valueOf(letter);
        }
    }

    private int curateNumberToDecimal(char letter) {
        if (Character.isDigit(letter)) {
            return Character.getNumericValue(letter);
        } else if (Character.isLetter(letter)) {
            char uppercaseLetter = Character.toUpperCase(letter);
            return uppercaseLetter - 'A' + 10;
        } else {
            return 0; // Default value for invalid characters
        }
    }
}