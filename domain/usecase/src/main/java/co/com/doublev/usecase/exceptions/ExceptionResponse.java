package co.com.doublev.usecase.exceptions;

import java.time.LocalDateTime;


public class ExceptionResponse {
    private String message;
    private String code;
    private LocalDateTime date;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String message, String code, LocalDateTime date) {
        this.message = message;
        this.code = code;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

