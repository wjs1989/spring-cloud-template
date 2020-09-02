package com.wjs.produce.config;

import com.wjs.model.exception.APIException;
import com.wjs.model.vo.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class AdviceController {

	/**
	 * 参数校验异常
	 * 
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public <T> BaseResult<T> methodArgumentNotValidException(MethodArgumentNotValidException e) {

		String errorMsg = String.format("%s:%s", e.getBindingResult().getFieldError().getField(), e.getBindingResult().getFieldError().getDefaultMessage());

		if (log.isDebugEnabled()) {
			log.debug(errorMsg);
		}
		return BaseResult.error(errorMsg);
	}

	/**
	 * 通用异常捕获
	 * 
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public <T> BaseResult<T> exception(Exception e) {

		if (log.isErrorEnabled()) {
			log.error("Exception:", e);
		}

		return BaseResult.error("服务异常");
	}

	@ResponseBody
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public <T> BaseResult<T> missingServletRequestParameterException(MissingServletRequestParameterException e) {

		if (log.isErrorEnabled()) {
			log.error("MissingServletRequestParameterException:", e);
		}

		return BaseResult.error( e.getParameterName());
	}

	/**
	 * 自定义API异常捕获
	 * 
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(APIException.class)
	public <T> BaseResult<T> apiException(APIException e) {

		if (log.isErrorEnabled()) {
			log.error("APIException:", e);
		}
		return BaseResult.error(e.getMessageEnum());
	}
}
