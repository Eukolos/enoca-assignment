package com.eukolos.companycase.exception;

public class AlreadyExistInCompanyException extends RuntimeException {

    public AlreadyExistInCompanyException(String AlreadyExistInCompanyStringFormat) {
        super(AlreadyExistInCompanyStringFormat);
    }

    public AlreadyExistInCompanyException(String message, Throwable cause) {
        super(message, cause);
    }

}