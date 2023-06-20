package com.cflox.challenge.numberconverter.controllers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NumberConverterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("successRequestsDataProvider")
    public void convertSuccess(String inputNumber, String inputFormat, String outputFormat, String expected) throws Exception
    {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/convert/number")
                        .queryParam("inputNumber", inputNumber)
                        .queryParam("inputFormat", inputFormat)
                        .queryParam("outputFormat", outputFormat))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expected));
    }

    @ParameterizedTest
    @MethodSource("errorRequestsDataProvider")
    public void convertError(String inputNumber, String inputFormat, String outputFormat, String expectedMessage, int expectedStatus) throws Exception
    {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/convert/number")
                        .queryParam("inputNumber", inputNumber)
                        .queryParam("inputFormat", inputFormat)
                        .queryParam("outputFormat", outputFormat))
                .andDo(print())
                .andExpect(status().is(expectedStatus))
                .andExpect(MockMvcResultMatchers.content().string(expectedMessage));
    }

    private static Stream<Arguments> successRequestsDataProvider() {
        return Stream.of(
                Arguments.of("10111", "BINARY", "ROMAN", "XXIII"),
                Arguments.of("0", "BINARY", "ROMAN", ""),
                Arguments.of("111110011111", "BINARY", "ROMAN", "MMMCMXCIX"),

                Arguments.of("23", "DECIMAL", "ROMAN", "XXIII"),
                Arguments.of("3999", "DECIMAL", "ROMAN", "MMMCMXCIX"),
                Arguments.of("0", "DECIMAL", "ROMAN", "")
        );
    }

    private static Stream<Arguments> errorRequestsDataProvider() {
        return Stream.of(
                Arguments.of("-10111", "BINARY", "ROMAN", "Invalid input data", 400),
                Arguments.of("number", "BINARY", "ROMAN", "Invalid input data", 400),
                Arguments.of("111110100000", "BINARY", "ROMAN", "Invalid input data", 400),

                Arguments.of("-23", "DECIMAL", "ROMAN", "Invalid input data", 400),
                Arguments.of("number", "DECIMAL", "ROMAN", "Invalid input data", 400),
                Arguments.of("4000", "DECIMAL", "ROMAN", "Invalid input data", 400),

                Arguments.of("23", "DECIMAL", "BINARY", "Requested input and output formats pair isn't supported", 404),
                Arguments.of("XXIII", "ROMAN", "BINARY", "Requested input and output formats pair isn't supported", 404),

                Arguments.of("23", "WRONG_TYPE", "BINARY", "Cannot convert value WRONG_TYPE to supported types", 400)

        );
    }

}
