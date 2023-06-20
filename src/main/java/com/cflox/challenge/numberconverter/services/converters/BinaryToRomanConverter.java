package com.cflox.challenge.numberconverter.services.converters;

import com.cflox.challenge.numberconverter.common.enums.DataFormat;
import com.cflox.challenge.numberconverter.services.NumberConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

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
        BigInteger inputNum = new BigInteger(input, 2);

        return decimalToRomanConverter.convert(inputNum.toString(10));
    }

    @Override
    public boolean validate(String input) {
        return input.matches("^[01]+$");
    }
}
