package com.testmasuk.testmasuktdi.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testmasuk.testmasuktdi.dto.TennisDTO;
import com.testmasuk.testmasuktdi.service.TennisService;

@RestController
@RequestMapping("/api/tennis")
public class TennisController {
    
    @Autowired
    private TennisService service;

    @GetMapping("/findAllData")
    public ResponseEntity<?> findAll(){
        List<TennisDTO.DataTennis> findAll = service.findAll();
        return ResponseEntity.ok(findAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TennisDTO.DataTennis> findById(@PathVariable("id") BigDecimal id) {
        Optional<TennisDTO.DataTennis> optionalData = service.findById(id);
        return optionalData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("/nilaiAwal")
    public ResponseEntity<?> nilaiAwal(@RequestBody TennisDTO.NilaiAwal value) {
        try {
            TennisDTO.NilaiAwal save = service.nilaiAwal(value);
            return ResponseEntity.ok(save);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateSkorSementara")
    public ResponseEntity<?> updateSkorSementara(@RequestBody TennisDTO.updateSkorSementara value) {
        try {
            TennisDTO.updateSkorSementara update = service.updateSkorSementara(value);
            return ResponseEntity.ok(update);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/resetSkorSementara")
    public void resetSkorSementara() {
        service.resetSkorSementara();
    }
    
    @PutMapping("/updateSkorSet1")
    public ResponseEntity<?> updateSkorSet1(@RequestBody TennisDTO.updateSet value) {
        try {
            TennisDTO.updateSet update = service.updateSkorSet1(value);
            return ResponseEntity.ok(update);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    @PutMapping("/updateSkorSet2")
    public ResponseEntity<?> updateSkorSet2(@RequestBody TennisDTO.updateSet value) {
        try {
            TennisDTO.updateSet update = service.updateSkorSet2(value);
            return ResponseEntity.ok(update);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateSkorSet3")
    public ResponseEntity<?> updateSkorSet3(@RequestBody TennisDTO.updateSet value) {
        try {
            TennisDTO.updateSet update = service.updateSkorSet3(value);
            return ResponseEntity.ok(update);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateSkor")
    public ResponseEntity<?> updateSkor(@RequestBody TennisDTO.updateSkor value) {
        try {
            TennisDTO.updateSkor update = service.updateSkor(value);
            return ResponseEntity.ok(update);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
