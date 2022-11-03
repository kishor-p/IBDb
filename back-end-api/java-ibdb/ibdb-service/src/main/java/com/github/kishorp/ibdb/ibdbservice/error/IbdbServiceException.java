package com.github.kishorp.ibdb.ibdbservice.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IbdbServiceException extends Exception{
    private String errorTitle;
    private String errorCode;
    private String errorDetails;
    private Integer httpStatus;

    public IbdbServiceException(String errorMsg){
        if(!errorMsg.isBlank()){
            String[] errMsgs = errorMsg.split("#");
            String statusStr = errMsgs[0].split("_")[2];
            if(!statusStr.isBlank()){
                try{
                   this.httpStatus = Integer.parseInt(statusStr);
                } catch(NumberFormatException nfe){
                    this.httpStatus = 500;
                }
            }
            this.errorCode = errMsgs[0];
            this.errorTitle = errMsgs[1];
            this.errorDetails = errMsgs[2];
        }
    }
}
