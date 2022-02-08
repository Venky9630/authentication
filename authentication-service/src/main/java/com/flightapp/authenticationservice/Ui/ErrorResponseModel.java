package com.flightapp.authenticationservice.Ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorResponseModel {

    private String message;
    private Long errorReportingTime;
    private Integer statusCode;
}
