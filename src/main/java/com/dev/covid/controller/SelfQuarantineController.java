package com.dev.covid.controller;

import java.util.ArrayList;
import java.util.List;

import com.dev.covid.DTO.ResponseDTO;
import com.dev.covid.DTO.SelfQuarantineDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.covid.model.SelfQuarantine;
import com.dev.covid.service.SelfQuarantineService;

@Slf4j
@RestController
@RequestMapping("selfquarantine")
public class SelfQuarantineController {

    @Autowired
    private SelfQuarantineService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<SelfQuarantine> selfQuarantineList = service.findAll();
        List<SelfQuarantineDTO> selfQuarantineDTOList = new ArrayList<>();
        for (SelfQuarantine selfQuarantine : selfQuarantineList) {
            selfQuarantineDTOList.add(SelfQuarantineDTO
                    .builder()
                    .selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
                    .selfQuarantineId(selfQuarantine.getSelfQuarantineId())
                    .selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
                    .patientName(selfQuarantine.getPatient().getPeopleName())
                    .build()
            );
        }
        return ResponseEntity.ok(selfQuarantineDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {

        SelfQuarantine selfQuarantine = service.findById(id);

        SelfQuarantineDTO selfQuarantineDTO = SelfQuarantineDTO.selfQuarantineToDTO(selfQuarantine);
        return ResponseEntity.ok(selfQuarantineDTO);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByselfQuarantineName(@PathVariable("name") String name) {
        List<SelfQuarantine> selfQuarantineList = service.findByselfQuarantineName(name);

        if (selfQuarantineList.size() == 0){
            log.error("해당되는 이름이 없습니다.");
            ResponseDTO responseDTO = ResponseDTO.builder().error("해당되는 이름이 없습니다.").build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }

        List<SelfQuarantineDTO> selfQuarantineDTOList = new ArrayList<>();
        for (SelfQuarantine selfQuarantine : selfQuarantineList) {
            selfQuarantineDTOList.add(
                    SelfQuarantineDTO.selfQuarantineToDTO(selfQuarantine)
            );
        }
        return ResponseEntity.ok(selfQuarantineDTOList);
    }

    @GetMapping("date")
    public ResponseEntity<?> findByselfQuarantineDateBetween(@RequestParam("start") String start, @RequestParam("end") String end) {

        List<SelfQuarantine> selfQuarantineList = service.findByselfQuarantineDateBetween(start, end);

        if (selfQuarantineList.size() == 0){
            log.error("해당 날짜에 대한 기록이 없습니다.");
            ResponseDTO responseDTO = ResponseDTO.builder().error("해당 날짜에 대한 기록이 없습니다.").build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }

        List<SelfQuarantineDTO> selfQuarantineDTOList = new ArrayList<>();
        for (SelfQuarantine selfQuarantine : selfQuarantineList) {
            selfQuarantineDTOList.add(
                    SelfQuarantineDTO.selfQuarantineToDTO(selfQuarantine)
            );
        }
        return ResponseEntity.ok(selfQuarantineDTOList);
    }

    @GetMapping("release")
    public ResponseEntity<?> findByselfQuarantineReleaseBetween(@RequestParam("start") String start, @RequestParam("end") String end) {
        List<SelfQuarantine> selfQuarantineList = service.findByselfQuarantineReleaseBetween(start, end);

        if (selfQuarantineList.size() == 0){
            log.error("해당 날짜에 대한 기록이 없습니다.");
            ResponseDTO responseDTO = ResponseDTO.builder().error("해당 날짜에 대한 기록이 없습니다.").build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }
        List<SelfQuarantineDTO> selfQuarantineDTOList = new ArrayList<>();
        for (SelfQuarantine selfQuarantine : selfQuarantineList) {
            selfQuarantineDTOList.add(
                    SelfQuarantineDTO.selfQuarantineToDTO(selfQuarantine)
            );
        }
        return ResponseEntity.ok(selfQuarantineDTOList);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody SelfQuarantineDTO selfQuarantineDTO) {
        SelfQuarantine newSelfQuarantine = service.save(selfQuarantineDTO);
        SelfQuarantineDTO newSelfQuarantineDTO = SelfQuarantineDTO.selfQuarantineToDTO(newSelfQuarantine);
        return ResponseEntity.ok(newSelfQuarantineDTO);
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody SelfQuarantine putSelfQuarantine) {
        List<SelfQuarantine> selfQuarantineList = service.put(putSelfQuarantine);

        List<SelfQuarantineDTO> selfQuarantineDTOList = new ArrayList<>();
        for (SelfQuarantine selfQuarantine : selfQuarantineList) {
            selfQuarantineDTOList.add(
                    SelfQuarantineDTO.selfQuarantineToDTO(selfQuarantine)
            );
        }
        return ResponseEntity.ok(selfQuarantineDTOList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        List<SelfQuarantine> selfQuarantineList = service.delete(id);
        List<SelfQuarantineDTO> selfQuarantineDTOList = new ArrayList<>();
        for (SelfQuarantine selfQuarantine : selfQuarantineList) {
            selfQuarantineDTOList.add(
                    SelfQuarantineDTO.selfQuarantineToDTO(selfQuarantine)
            );
        }
        return ResponseEntity.ok(selfQuarantineDTOList);
    }

}
