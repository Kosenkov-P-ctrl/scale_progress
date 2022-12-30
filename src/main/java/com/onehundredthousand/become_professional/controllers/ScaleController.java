package com.onehundredthousand.become_professional.controllers;

import com.onehundredthousand.become_professional.model.Scale;
import com.onehundredthousand.become_professional.repository.ScaleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ScaleController {
    private final ScaleRepository scaleRepository;

    public ScaleController(ScaleRepository scaleRepository){
        this.scaleRepository = scaleRepository;
    }

    //return all scales
    @GetMapping("/")
    public ResponseEntity<Iterable<Scale>> getMyScales(){
        return ResponseEntity.ok(scaleRepository.findAll());
    }

    //return one scale by name
    @GetMapping("/{purpose}")
    public ResponseEntity<Scale> getScaleByPurpose(@PathVariable String purpose){
        Optional<Scale> scale = scaleRepository.findScaleByPurpose(purpose);
        if(scale.isPresent()){
            return ResponseEntity.ok(scale.get());
        }
        return ResponseEntity.notFound().build();
    }

    //set working time
    @PatchMapping("/{purpose}/{progress}")
    public ResponseEntity<Scale> setWorkingTime(@PathVariable String purpose, @PathVariable Ti)
}
