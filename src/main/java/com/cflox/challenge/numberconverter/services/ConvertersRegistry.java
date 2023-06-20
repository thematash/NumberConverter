package com.cflox.challenge.numberconverter.services;

import com.cflox.challenge.numberconverter.common.enums.DataFormat;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConvertersRegistry {

    private final Map<DataFormat, Map<DataFormat, NumberConverter>> converters;

    public ConvertersRegistry(List<NumberConverter> convertersList) {
        this.converters = new HashMap<>();

        for (NumberConverter converter : convertersList) {
            converters.computeIfAbsent(converter.supportedInputFormat(), (s) -> new HashMap<>() {{
                put(converter.supportedOutputFormat(), converter);
            }});
        }
    }

    public NumberConverter getConverter(DataFormat inputFormat, DataFormat outputFormat) {
        Map<DataFormat, NumberConverter> converterMap = converters.get(inputFormat);
        if (converterMap != null) {
            return converterMap.get(outputFormat);
        }

        return null;
    }
}
