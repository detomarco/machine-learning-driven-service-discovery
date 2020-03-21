package it.univaq.disim.numismatic.numismaticservice.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class NumismaticException extends RuntimeException {

    private static final long serialVersionUID = 2022869597663447714L;

    private Integer code;

    public abstract HttpStatus getStatus();

    protected NumismaticException(ErrorCode error, Throwable ex, Object... params) {
        super(MessageFormat.format(error.getMessage(), params), ex);
        this.code = error.getCode();
    }
}
