package com.elasticsearch.util;

import java.util.*;

/**
 * @author wenjs
 */
public class TermCommonUtils {
    public static List<Object> convertToList(Object value) {
        if (value == null) {
            return Collections.emptyList();
        }
        if (value instanceof String) {
            value = ((String) value).split(",");
        }

        if (value instanceof Object[]) {
            value = Arrays.asList(((Object[]) value));
        }

        if (value instanceof Collection) {
            return new ArrayList<Object>(((Collection) value));
        }

        return Collections.singletonList(value);
    }

    public static Object getStandardsTermValue(List<Object> value) {
        if (value.size() == 1) {
            return value.get(0);
        }
        return value;
    }
}
