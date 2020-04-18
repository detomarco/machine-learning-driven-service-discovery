package it.univaq.disim.numismatic.authservice.business.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 264393734606747476L;

    private ErrorCode faultInfo;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.faultInfo = errorCode;
    }

}
