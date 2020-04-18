package it.univaq.disim.numismatic.stubservice.business.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCode {

    BAD_CREDENTIAL(1001, "Bad credential"),
    USER_NOT_EXISTS(1006, "User {0} does not exist"),
    ;

    @Getter
    private final Integer code;

    @Getter
    private final String message;

}
