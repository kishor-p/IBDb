package com.github.kishorp.ibdb.ibdbapi.config;

import com.github.kishorp.ibdb.ibdbservice.error.IbdbServiceException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class IbdbExceptionHandler {

    @ExceptionHandler(value = {IbdbServiceException.class})
    public ResponseEntity<ProblemDetail> handleIbdbServiceException(IbdbServiceException ex, WebRequest wr){
        ProblemDetail pd = new ProblemDetail(ex);
        log.info(pd.toString());
        return new ResponseEntity<>(pd, HttpStatus.valueOf(ex.getHttpStatus()));
    }
}

