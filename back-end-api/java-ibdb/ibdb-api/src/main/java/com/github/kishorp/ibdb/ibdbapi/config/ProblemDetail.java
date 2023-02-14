package com.github.kishorp.ibdb.ibdbapi.config;

import com.github.kishorp.ibdb.ibdbservice.error.IbdbServiceException;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <li> Defines and holds the structure of Error Response from API layer </li>
 * <li> {@link IbdbExceptionHandler} will construct this type's instance for every exception and responds for API</li>
 */
@Data
@NoArgsConstructor
public class ProblemDetail {
    /** Title of error message */
    private String errorTitle;

    /** Internal Error code */
    private String errorCode;

    /** Detailed error message */
    private String errorDetails;

    /** HTTP Status code */
    private Integer httpStatus;

    /**
     * Constructor to map {@link IbdbServiceException}  to current Type
     * @param ex Exception that needs to eb converted
     */
    ProblemDetail(IbdbServiceException ex){
        this.errorCode = ex.getErrorCode();
        this.errorDetails = ex.getErrorDetails();
        this.errorTitle = ex.getErrorTitle();
        this.httpStatus = ex.getHttpStatus();
    }


}
