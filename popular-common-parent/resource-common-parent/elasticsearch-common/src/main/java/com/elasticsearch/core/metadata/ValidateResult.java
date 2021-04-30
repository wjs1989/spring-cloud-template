package com.elasticsearch.core.metadata;

import java.beans.ConstructorProperties;
import java.util.function.Consumer;

/**
 * @author wenjs
 */
public class ValidateResult {

    private boolean success;
    private Object value;
    private String errorMsg;

    public static ValidateResult success(Object value) {
        ValidateResult result = new ValidateResult();
        result.setSuccess(true);
        result.setValue(value);
        return result;
    }

    public static ValidateResult success() {
        ValidateResult result = new ValidateResult();
        result.setSuccess(true);
        return result;
    }

    public static ValidateResult fail(String message) {
        ValidateResult result = new ValidateResult();
        result.setSuccess(false);
        result.setErrorMsg(message);
        return result;
    }

    public Object assertSuccess() {
        if (!this.success) {
            throw new IllegalArgumentException(this.errorMsg);
        } else {
            return this.value;
        }
    }

    public void ifFail(Consumer<ValidateResult> resultConsumer) {
        if (!this.success) {
            resultConsumer.accept(this);
        }

    }

    public static ValidateResult.ValidateResultBuilder builder() {
        return new ValidateResult.ValidateResultBuilder();
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Object getValue() {
        return this.value;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @ConstructorProperties({"success", "value", "errorMsg"})
    public ValidateResult(boolean success, Object value, String errorMsg) {
        this.success = success;
        this.value = value;
        this.errorMsg = errorMsg;
    }

    public ValidateResult() {
    }

    public static class ValidateResultBuilder {
        private boolean success;
        private Object value;
        private String errorMsg;

        ValidateResultBuilder() {
        }

        public ValidateResult.ValidateResultBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public ValidateResult.ValidateResultBuilder value(Object value) {
            this.value = value;
            return this;
        }

        public ValidateResult.ValidateResultBuilder errorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
            return this;
        }

        public ValidateResult build() {
            return new ValidateResult(this.success, this.value, this.errorMsg);
        }

    }
}
