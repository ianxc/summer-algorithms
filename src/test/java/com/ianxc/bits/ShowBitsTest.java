package com.ianxc.bits;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ShowBitsTest {
    @Test
    void testShowBits() {
        for (var x : List.of(0, 10, -10)) {
            ShowBits.showBitsBasic(x);
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "  10, '0b00001010'",
            " -10, '0b11110110'",
            "-128, '0b10000000'"
    })
    void testTwosComplement(int input, @ConvertWith(BinaryConverter.class) byte expected) {
        byte res = ShowBits.twosComplement(10);
        assertThat(res).isEqualTo(expected);
    }

    static class BinaryConverter implements ArgumentConverter {

        @Override
        public Object convert(
                Object source,
                ParameterContext context) throws ArgumentConversionException {
            return (byte) Byte.parseByte(source.toString().substring(2), 2);
        }

    }

}
