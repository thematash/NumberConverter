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
        int num = Integer.parseInt(input);

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
        if (input.length() > 4
                || !input.matches("^[0-9]+$")) { // how to convert zero - need to be discussed
            return false;
        }

        try {
            int val = Integer.parseInt(input);
            if (val > 3999) {
                return false;
            }
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
