package com.wjs.produce.emum;

import org.apache.commons.lang3.EnumUtils;

public class EnumTest {

    public static void main(String[] args) {
//        System.out.println(ApiResult.getMessage(MessageEnum.SYSTEM_FIAL));
//
//        System.out.println(ApiResult.getMessage(ErrorEnum.SUCCESS));
//

        EnumUtils.getEnumList(MessageEnum.class).forEach(p-> System.out.println(p.data));
    }
}
