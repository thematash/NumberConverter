package com.cflox.challenge.numberconverter.services.converters;

import com.cflox.challenge.numberconverter.common.enums.DataFormat;
import com.cflox.challenge.numberconverter.services.NumberConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BinaryToRomanConverter implements NumberConverter {

    private final DecimalToRomanConverter decimalToRomanConverter;

    @Override
    public DataFormat supportedInputFormat() {
        return DataFormat.BINARY;
    }

    @Override
    public DataFormat supportedOutputFormat() {
        return DataFormat.ROMAN;
    }

    @Override
    public String convert(String input) {
        Integer inputNum = Integer.parseInt(input, 2);

        return decimalToRomanConverter.convert(String.valueOf(inputNum));
    }

    @Override
    public boolean validate(String input) {
        if (!input.matches("^[01]+$")
                || input.length() > 12) {
            return false;
        }

        try {
            int inputNum = Integer.parseInt(input, 2);
            if (inputNum > 3999) {
                return false;
            }
        } catch (NumberFormatException ex) {
            return false;
        }

        return true;
    }
}
