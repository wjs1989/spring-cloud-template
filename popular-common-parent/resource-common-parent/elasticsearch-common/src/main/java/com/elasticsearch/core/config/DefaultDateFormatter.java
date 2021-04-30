package com.elasticsearch.core.config;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * @author wenjs
 */
public class DefaultDateFormatter implements DateFormatter {
    private DateTimeFormatter formatter;
    private Predicate<String> predicate;
    private String formatterString;

    public DefaultDateFormatter(Pattern formatPattern, String formatter) {
        this((str) -> {
            return formatPattern.matcher(str).matches();
        }, DateTimeFormat.forPattern(formatter));
        this.formatterString = formatter;
    }

    public DefaultDateFormatter(Predicate<String> predicate, DateTimeFormatter formatter) {
        this.predicate = predicate;
        this.formatter = formatter;
    }

    @Override
    public boolean support(String str) {
        return this.predicate.test(str);
    }

    @Override
    public Date format(String str) {
        return this.formatter.parseDateTime(str).toDate();
    }

    @Override
    public String getPattern() {
        return this.formatterString;
    }

    @Override
    public String toString(Date date) {
        return (new DateTime(date)).toString(this.getPattern());
    }
}
