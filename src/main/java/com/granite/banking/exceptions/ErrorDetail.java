package com.granite.banking.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDetail {

    private Date timestamp;
    private String message;
    private String details;
    private int status;

    public ErrorDetail(Date timestamp, int status, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.details = details;
    }


}
