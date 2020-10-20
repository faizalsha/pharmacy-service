package com.example.pharmacyservice.controller;


import com.example.pharmacyservice.model.GenericResponse;
import com.example.pharmacyservice.model.PharmacyCurrentRecord;
import com.example.pharmacyservice.repository.PharmacyCurrentRecordRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {
    @Autowired
    private PharmacyCurrentRecordRepository repository;

    
    @RequestMapping("/get-all-records")
    public GenericResponse getAllRecords(){
        List<PharmacyCurrentRecord> records = null;
        try{
            records = repository.findAll();
            return new GenericResponse(1, "success", records);
        }catch (Exception e){
            e.printStackTrace();
            return new GenericResponse(0, "exception " + e.getMessage(), null);
        }
    }
    
    @RequestMapping("/get-pharmacy-by-id/{id}")
    public GenericResponse getPharmacy(@PathVariable String id){
        if(repository.findById(id).isPresent()) 
        {
        	return new GenericResponse(1,"success",repository.findById(id).get());
        }
        else
        {
        	return new GenericResponse(1,"success",null);
        }
    }  

    @RequestMapping("/delete-by-id/{id}")
    public GenericResponse deleteRecordById(@PathVariable String id){
        repository.deleteById(id);
        return new GenericResponse(1, "success", null);
    }

    @RequestMapping(value = "add-record", method = RequestMethod.POST)
    public GenericResponse insertRecord(@RequestBody PharmacyCurrentRecord record){
        repository.save(record);
        return new GenericResponse(1, "success", null);
    }



}
