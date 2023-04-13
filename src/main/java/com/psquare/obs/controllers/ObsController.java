package com.psquare.obs.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psquare.obs.models.VacDtl;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
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

    @GetMapping("/get-member-data")
    public ResponseEntity<String> getMemberData(){
        String jsonString = null;
        //log.debug("/get-member-data {}"," Getting member data");
        try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("data/member.json")){
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
            jsonString = mapper.writeValueAsString(jsonNode);
        }
        catch(Exception e){
          //  log.error("/get-member-data", "Failed to member data " + e.getMessage());
            throw new RuntimeException(e);
        }
        //log.info("/get-member-data", "Successfully Fetched to member data ");
        return ResponseEntity.ok(jsonString);
    }

    @GetMapping("/get-claim-data")
    public ResponseEntity<String> getClaimData(){
        String jsonString = null;
        //log.debug("/get-claim-data", " Getting claim data");
        try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("data/claim.json")){
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
            jsonString = mapper.writeValueAsString(jsonNode);
        }
        catch(Exception e){
           // log.error("/get-claim-data", "Failed to claim data " + e.getMessage());
            throw new RuntimeException(e);
        }
        //log.info("/get-claim-data", "Successfully Fetched to claim data ");
        return ResponseEntity.ok(jsonString);
    }

  @PostMapping("/submit-vac-dtl")
    public ResponseEntity<String> submitVacDtl(@RequestBody VacDtl vacDtl){
        return ResponseEntity.ok("Thanks for updating the vaccination details");
    }
}
