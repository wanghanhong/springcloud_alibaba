package com.smart.brd.sys.common.converter;

import com.wuwenze.poi.convert.WriteConverter;
import com.wuwenze.poi.exception.ExcelKitWriteConverterException;
import lombok.extern.slf4j.Slf4j;
import com.smart.brd.sys.common.utils.DateUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Excel导出时间类型字段格式化
 *
 * @author Pano
 */
@Slf4j
public class Time2Converter implements WriteConverter {
    @Override
    public String convert(Object value) throws ExcelKitWriteConverterException {
        try {
            if (value == null) {
                return "";
            } else {
                DateTimeFormatter stfrmatter = DateTimeFormatter.ofPattern(DateUtil.FULL_TIME_SPLIT_PATTERN);

                LocalDateTime date = LocalDateTime.parse(value.toString());
                return stfrmatter.format(date);
            }
        } catch (Exception e) {
            log.error("时间转换异常", e);
            return "";
        }
    }


}
