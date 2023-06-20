package com.cflox.challenge.numberconverter.services.converters;

import com.cflox.challenge.numberconverter.common.enums.DataFormat;
import com.cflox.challenge.numberconverter.services.NumberConverter;
import org.springframework.stereotype.Component;

@Component
public class DecimalToRomanConverter implements NumberConverter {

    @Override
    public DataFormat supportedInputFormat() {
        return DataFormat.DECIMAL;
    }

    @Override
    public DataFormat supportedOutputFormat() {
        return DataFormat.ROMAN;
    }

    @Override
    public String convert(String input) {
        // can be reimplemented using BigInteger to support longer numbers,
        // but for 999999999999 conversion took 40s and returned 15.7 million characters in response,
        // so maybe 'long' is enough, though
        long num = Long.parseLong(input);

        StringBuilder result = new StringBuilder();
        int digitCounter = table.length - 1;

        while (num != 0) {
            DecimalRomanPair pair = table[digitCounter];
            long incl = num / pair.value;

            for (int i = 0; i < incl; i++)
                result.append(pair.symbols);

            num %= pair.value;
            digitCounter--;
        }

        return result.toString();
    }

    @Override
    public boolean validate(String input) {

        // TODO actual limitations can be discussed further, this is more of an example
        if (input.length() > String.valueOf(Long.MAX_VALUE).length()
                || !input.matches("^[0-9]+$")
                || input.equals("0")) {
            return false;
        }

        try {
            Long.parseLong(input);
        } catch (NumberFormatException ex) {
            return false;
        }

        return true;

    }

    private final static DecimalRomanPair[] table = new DecimalRomanPair[]{
            new DecimalRomanPair(1, "I"),
            new DecimalRomanPair(4, "IV"),
            new DecimalRomanPair(5, "V"),
            new DecimalRomanPair(9, "IX"),
            new DecimalRomanPair(10, "X"),
            new DecimalRomanPair(40, "XL"),
            new DecimalRomanPair(50, "L"),
            new DecimalRomanPair(90, "XC"),
            new DecimalRomanPair(100, "C"),
            new DecimalRomanPair(400, "CD"),
            new DecimalRomanPair(500, "D"),
            new DecimalRomanPair(900, "CM"),
            new DecimalRomanPair(1000, "M"),
    };

    private record DecimalRomanPair(int value, String symbols) {
    }
}
