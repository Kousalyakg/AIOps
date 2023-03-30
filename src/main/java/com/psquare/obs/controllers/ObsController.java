package com.psquare.obs.controllers;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class ObsController {

    @GetMapping("/claim-number")
    public ResponseEntity<String> claimNumbers(){
        String str = new String("Message");
        JSONObject jo = new JSONObject();
        try {
            jo.put("identifier", "CLAIM0192");
            jo.put("traceNumber", "22");
            jo.put("status", "Enhancement");
            jo.put("type", "CASHLESS");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(jo.toString());
    }

    @GetMapping("/message")
    public ResponseEntity<String> message(){
        String str = new String("Message");
        JSONObject jo = new JSONObject();
        try {
            jo.put("identifier", "CLAIM0192");
            jo.put("traceNumber", "22");
            jo.put("status", "Enhancement");
            jo.put("type", "CASHLESS");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(jo.toString());
    }

    @GetMapping("/gcover")
    public ResponseEntity<String> GCOverhead(){
            Map<Long, Long> map = new HashMap<>();
            for (long i = 0l; i < Long.MAX_VALUE; i++) {
                map.put(i, i);
            }
        return ResponseEntity.ok(HttpStatus.GATEWAY_TIMEOUT.toString());
    }
}
