package com.github.kishorp.ibdb.ibdbapi.config;

import com.github.kishorp.ibdb.ibdbservice.error.IbdbServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * <li> Handler to handle the Exceptions thrown by SERVICE layer </li>
 * <li> Based on type of Exception it will construct an appropriate {@link ResponseEntity},
 *  by filling HTTP status code and {@link ProblemDetail} </li>
 */
@ControllerAdvice
@Slf4j
public class IbdbExceptionHandler {

    /**
     * <li> Handles {@link IbdbServiceException} </li>
     *
     * @param ex Thrown exception
     * @param wr WebRequest
     * @return Constructed ResponseEntity
     */
    @ExceptionHandler(value = {IbdbServiceException.class})
    public ResponseEntity<ProblemDetail> handleIbdbServiceException(IbdbServiceException ex, WebRequest wr){
        ProblemDetail pd = new ProblemDetail(ex);
        log.info(pd.toString());
        return new ResponseEntity<>(pd, HttpStatus.valueOf(ex.getHttpStatus()));
    }
}

