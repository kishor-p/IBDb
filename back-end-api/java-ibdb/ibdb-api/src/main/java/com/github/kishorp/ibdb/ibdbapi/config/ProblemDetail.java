package com.github.kishorp.ibdb.ibdbapi.config;

import com.github.kishorp.ibdb.ibdbservice.error.IbdbServiceException;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProblemDetail {
    private String errorTitle;
    private String errorCode;
    private String errorDetails;
    private Integer httpStatus;

    ProblemDetail(IbdbServiceException ex){
        this.errorCode = ex.getErrorCode();
        this.errorDetails = ex.getErrorDetails();
        this.errorTitle = ex.getErrorTitle();
        this.httpStatus = ex.getHttpStatus();
    }


}
