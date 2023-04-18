package com.psquare.obs.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psquare.obs.models.VacDtl;
import com.psquare.obs.models.Vaccination;
import com.psquare.obs.service.VaccinationService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ObsController {

    @Autowired
    private VaccinationService vaccinationService;

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
        Double a = vacDtl.getAmount() / 0;
        Vaccination vaccination = new Vaccination();
        vaccination.setMemberId(vacDtl.getMemberId());
        vaccination.setDate(vacDtl.getDate());
          vaccination.setAmount(vacDtl.getAmount());
          vaccination.setProviderName(vacDtl.getProviderName());
          vaccination.setVacName(vacDtl.getVacName());
          vaccination = vaccinationService.saveVaccinationDtl(vaccination);
          System.out.println(vaccination.getId());
      return ResponseEntity.ok("Thanks for updating the vaccination details");
    }
    @GetMapping("/errcr")
    public ResponseEntity<Integer> errcr(){
        String str = null;
        str.split("a");
        return ResponseEntity.ok(500);

    }

    @GetMapping("/dbops")
    public ResponseEntity<String> dbConnectionError(){
        Connection con= null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sonoo","root","root");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from emp");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("");
    }


}
