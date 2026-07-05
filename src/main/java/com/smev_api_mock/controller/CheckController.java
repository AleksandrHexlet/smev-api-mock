package com.smev_api_mock.controller;

import com.smev_api_mock.model.CheckRequest;
import com.smev_api_mock.model.CheckResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("api/check/v1")
public class CheckController {

    @PostMapping("/CheckPassport")
    public ResponseEntity<CheckResponse> check(@RequestHeader(value = "x-token", required = false, defaultValue = "0") String token,
                                               @RequestBody CheckRequest req) {
        boolean valid = ThreadLocalRandom.current().nextBoolean();

        CheckResponse response = new CheckResponse();
        if (valid) {
            response.isValid = true;
            response.status = "300";
            response.decodeDocStatus = "Паспорт действителен";
        } else {
            response.isValid = false;
            response.status = "301";
            response.decodeDocStatus = "Паспорт недействителен";
        }
        return valid == true ? new ResponseEntity<>(response, HttpStatus.OK) : new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
