package it.univaq.disim.numismatic.numismaticservice.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(1005, "User {0} not found"),
    ;

    @Getter
    private final Integer code;

    @Getter
    private final String message;

}
