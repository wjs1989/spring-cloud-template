package com.wjs.model.util;

import java.io.File;
import java.util.Collection;
import java.util.Map;

/**
 *校验工具类
 */
public class ValidUtil {
 //   private static final Logger LOG = LoggerFactory.getLogger(ValidUtil.class);
//    File文件判断null 和是否存在
//    String字符串判断null和空字符串
//    map 判断null 和{}
//    list 判断null 和[]
//    String 比较值大小
    public ValidUtil() {
    }
    //File====================================================
    public static boolean isEmpty(File file) {
        return file == null || !file.exists();
    }

    public static boolean isNotEmpty(File file) {
        return !isEmpty(file);
    }
    //Map====================================================
    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }
    //List====================================================
    public static boolean isEmpty(Collection<?> col) {
        return !isNotEmpty(col);
    }

    public static boolean isNotEmpty(Collection<?> col) {
        return col != null && !col.isEmpty();
    }
    //Object[]====================================================
    public static boolean isEmpty(Object[] array) {
        return !isNotEmpty(array);
    }

    public static boolean isNotEmpty(Object[] array) {
        return array != null && array.length > 0;
    }
    //String 不会去掉空字符串====================================================
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }
    //不会去掉空字符串
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    //String 比较大小====================================================
    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
    //String  会去掉空字符串====================================================
    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }
    public static boolean isNotBlank(String str) {
        //会去掉空字符串
        return !isBlank(str);
    }
    //Null ===================================================
    public static boolean isNull(Object obj) {
        return obj == null;
    }
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }
//    public static void main(String[] args) {
//        String str="";
//        boolean bool=ValidUtil.isNotBlank(str);
//        System.out.println("isNotBlank="+bool);
//        bool=ValidUtil.isNotEmpty(str);
//        System.out.println("isNotEmpty="+bool);
////        String str="";
////        isNotBlank=false
////        isNotEmpty=false
////        String str=" ";
////        isNotBlank=false
////        isNotEmpty=true
//
//    }
}
