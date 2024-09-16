package com.smart.device.access.common.converter;

import com.wuwenze.poi.convert.ReadConverter;
import com.wuwenze.poi.exception.ExcelKitWriteConverterException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConverterNullValue implements ReadConverter {

    @Override
    public Object convert(Object value) throws ExcelKitWriteConverterException {
        if (value != null) {
            String valueString = String.valueOf(value);
            if ( "$EMPTY_CELL$".equals(valueString) ) {
                return null;
            }else{
                return value;
            }
        }
        return null;
    }

}
