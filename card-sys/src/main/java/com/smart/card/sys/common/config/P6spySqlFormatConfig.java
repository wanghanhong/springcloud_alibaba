package com.smart.card.sys.common.config;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import com.smart.card.sys.common.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;

/**
 * 自定义 p6spy sql输出格式
 *
 */
public class P6spySqlFormatConfig implements MessageFormattingStrategy {

    /**
     * 过滤掉定时任务的 SQL
     */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN)
                + " | 耗时 " + elapsed + " ms | SQL 语句：" + StringUtils.LF + sql.replaceAll("[\\s]+", StringUtils.SPACE) + ";" : "";
    }
}
