package wlw.smart.fire.common.converter;

import com.wuwenze.poi.convert.WriteConverter;
import com.wuwenze.poi.exception.ExcelKitWriteConverterException;
import lombok.extern.slf4j.Slf4j;
import wlw.smart.fire.common.utils.DateUtil;

/**
 * Excel导出时间类型字段格式化
 *
 * @author Pano
 */
@Slf4j
public class TimeConverter implements WriteConverter {
    @Override
    public String convert(Object value) throws ExcelKitWriteConverterException {
        try {
            if (value == null) {
                return "";
            } else {
                return DateUtil.formatCSTTime(value.toString(), DateUtil.FULL_TIME_SPLIT_PATTERN);
            }
        } catch (Exception e) {
            log.error("时间转换异常", e);
            return "";
        }
    }
}
