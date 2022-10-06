package com.billyclub.points.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
@Data
@NoArgsConstructor
public class ErrorDetails {
//    private String message;
    private LocalDateTime timestamp;
    private String errorDesc;
    private List<String> errorMsgs;

    public ErrorDetails(LocalDateTime timestamp, String message, String errorDesc) {
        super();
        this.errorMsgs = Arrays.asList(message);
        this.errorDesc = errorDesc;
        this.timestamp = timestamp;
    }
    public ErrorDetails(LocalDateTime timestamp, List<String> errorMsgs, String errorDesc) {
        super();
        this.errorMsgs = errorMsgs;
        this.errorDesc = errorDesc;
        this.timestamp = timestamp;
    }

}