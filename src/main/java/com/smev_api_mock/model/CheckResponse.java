package com.smev_api_mock.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CheckResponse {
    public boolean isValid = true;
    public String status = "300";
    public String decodeDocStatus = "Паспорт действителен";
    public String issueCode = null;
    public String issueDate = null;
    public String invalidityReason = null;
    public String decodeInvalidityReason = null;
    public String invaliditySince = null;
    public Result result = new Result();


    public static class Result {
        public boolean success = true;
        public String errorCode = "";
        public String description = "";
    }
}

