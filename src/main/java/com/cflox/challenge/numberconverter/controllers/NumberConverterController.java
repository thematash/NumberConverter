package com.cflox.challenge.numberconverter.controllers;

import com.cflox.challenge.numberconverter.common.enums.DataFormat;
import com.cflox.challenge.numberconverter.services.ConvertersRegistry;
import com.cflox.challenge.numberconverter.services.NumberConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NumberConverterController {

    private final ConvertersRegistry convertersRegistry;

    @GetMapping("/convert/number")
    public ResponseEntity<String> convertNumber(@RequestParam String inputNumber,
                                                @RequestParam DataFormat inputFormat,
                                                @RequestParam DataFormat outputFormat) {
        NumberConverter numberConverter = convertersRegistry.getConverter(inputFormat, outputFormat);
        if (numberConverter == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Requested input and output formats pair isn't supported");
        }

        if (!numberConverter.validate(inputNumber)) {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid input data");
        }

        String result = numberConverter.convert(inputNumber);

        return ResponseEntity.ok(result);

    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<String> argumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body("Cannot convert value " + ex.getValue() + " to supported types");
    }
}
