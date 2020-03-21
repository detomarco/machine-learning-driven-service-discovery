package it.univaq.disim.numismatic.numismaticservice.common.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends NumismaticException {

	private static final long serialVersionUID = 6838450200466055432L;

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public BusinessException(ErrorCode error, Throwable ex, Object... params) {
		super(error, ex, params);
	}

	public BusinessException(ErrorCode error, Object... params) {
		this(error, null, params);
	}
}
