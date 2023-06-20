package com.cflox.challenge.numberconverter.services;

import com.cflox.challenge.numberconverter.common.enums.DataFormat;

public interface NumberConverter {

    DataFormat supportedInputFormat();

    DataFormat supportedOutputFormat();

    String convert(String input);

    // TODO Array of strings with error messages can be returned instead
    boolean validate(String input);
}
