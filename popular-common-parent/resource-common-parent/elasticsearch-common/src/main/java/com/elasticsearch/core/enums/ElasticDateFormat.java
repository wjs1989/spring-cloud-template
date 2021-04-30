package com.elasticsearch.core.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wenjs
 */
public enum ElasticDateFormat {
    epoch_millis("epoch_millis", "毫秒"),
    epoch_second("epoch_second", "秒"),
    strict_date("strict_date", "yyyy-MM-dd"),
    basic_date_time("basic_date_time", "yyyyMMdd'T'HHmmss.SSSZ"),
    strict_date_time("strict_date_time", "yyyy-MM-dd'T'HH:mm:ss.SSSZZ"),
    strict_date_hour_minute_second("strict_date_hour_minute_second", "yyyy-MM-dd'T'HH:mm:ss"),
    strict_hour_minute_second("strict_hour_minute_second", "HH:mm:ss"),
    simple_date("yyyy-MM-dd HH:mm:ss", "通用格式");

    private String value;

    private final String text;

    private ElasticDateFormat(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public static String getFormat(ElasticDateFormat... dateFormats) {
        return getFormat(Arrays.asList(dateFormats));
    }

    public static String getFormat(List<ElasticDateFormat> dateFormats) {
        return getFormatStr(dateFormats.stream()
                .map(ElasticDateFormat::getValue)
                .collect(Collectors.toList())
        );
    }

    private String getValue() {
        return value;
    }

    private String getText() {
        return text;
    }

    public static String getFormatStr(List<String> dateFormats) {
        StringBuffer format = new StringBuffer();
        for (int i = 0; i < dateFormats.size(); i++) {
            format.append(dateFormats.get(i));
            if (i != dateFormats.size() - 1) {
                format.append("||");
            }
        }
        return format.toString();
    }
}
