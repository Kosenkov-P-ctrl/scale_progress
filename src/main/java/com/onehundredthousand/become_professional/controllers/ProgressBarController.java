package com.onehundredthousand.become_professional.controllers;

import com.onehundredthousand.become_professional.model.ProgressBar;
import com.onehundredthousand.become_professional.repository.ProgressBarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class ProgressBarController {
    private final ProgressBarRepository progressBarRepository;

    public ProgressBarController(ProgressBarRepository progressBarRepository){
        this.progressBarRepository = progressBarRepository;
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<ProgressBar>> getProgressBar(){
        return ResponseEntity.ok(progressBarRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgressBar> getProgressBarById(@PathVariable String id){
        Optional<ProgressBar> progressBar = progressBarRepository.findById(id);
        if(progressBar.isPresent()){
            return ResponseEntity.ok(progressBar.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{timestamp}/{id}")
    public ResponseEntity<ProgressBar> setProgress(@PathVariable Long timestamp,
                                                   @PathVariable String id){
        Optional<ProgressBar> progressBar = progressBarRepository.findById(id);
        if(!progressBar.isPresent()){
            return ResponseEntity.notFound().build();
        }
        ProgressBar result = progressBar.get();
        result.getTempProgress().setTime(result.getTempProgress().getTime() + timestamp);
        progressBarRepository.save(result);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(result.getId()).toUri();
        return ResponseEntity.ok().header("Location", location.toString()).build();
    }

    @RequestMapping(value="/", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> createProgressBar(@Valid @RequestBody ProgressBar progressBar){
        ProgressBar result = progressBarRepository.save(progressBar);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProgressBar> deleteProgressBar(@PathVariable String id){
        progressBarRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
