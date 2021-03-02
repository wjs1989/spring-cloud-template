package com.wjs.produce.ptest;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wenjs
 */
@Setter
@Getter
public class BaseResult<T> {

    private Integer code;

    private String message;

    private T data;
}
