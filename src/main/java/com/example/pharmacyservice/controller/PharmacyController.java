package com.example.pharmacyservice.controller;


import com.example.pharmacyservice.model.GenericResponse;
import com.example.pharmacyservice.model.PharmacyCurrentRecord;
import com.example.pharmacyservice.repository.PharmacyCurrentRecordRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/delete-by-id/{id}")
    public GenericResponse deleteRecordById(@PathVariable String id){
        repository.deleteById(id);
        return new GenericResponse(1, "success", null);
    }


    @RequestMapping("/sample")
    public GenericResponse getSample(){
        PharmacyCurrentRecord record =
                new PharmacyCurrentRecord("id",
                        "patienid",
                        "physicianID",
                        "prescription",
                        new Date(2020, 10, 10),
                        new Time(10, 10, 10));

        return new GenericResponse(1, "success", record);
    }

}
