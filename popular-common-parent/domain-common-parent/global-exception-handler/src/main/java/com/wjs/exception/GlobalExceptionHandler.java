package com.wjs.exception;

import com.wjs.model.constant.MessageEnum;
import com.wjs.model.exception.GlobalException;
import com.wjs.model.vo.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/24 12:04
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public BaseResult exception(Exception e)
    {
        log.error("Exception :",e);
        return BaseResult.error(MessageEnum.FAIL.getCode(),e.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    public BaseResult apiException(GlobalException e)
    {
        log.error("GlobalExceptionHandler.apiException :",e);

        if(e.getMessageEnum() != null){
            return BaseResult.error(e.getMessageEnum());
        }
        return BaseResult.error(e.getCode(),e.getMessage());
    }

}
