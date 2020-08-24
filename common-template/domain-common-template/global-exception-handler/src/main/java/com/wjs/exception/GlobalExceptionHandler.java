package com.wjs.exception;

import com.wjs.model.exception.APIException;
import com.wjs.model.vo.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/24 12:04
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(APIException.class)
    public BaseResult apiException(APIException e)
    {
        log.error("GlobalExceptionHandler.apiException :",e);

        if(e.getMessageEnum() != null){
            return BaseResult.error(e.getMessageEnum());
        }

        return BaseResult.error(e.getCode(),e.getMessage());
    }

}
