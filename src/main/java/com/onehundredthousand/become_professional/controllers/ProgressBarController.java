package com.onehundredthousand.become_professional.controllers;

import com.onehundredthousand.become_professional.model.ProgressBar;
import com.onehundredthousand.become_professional.service.ProgressBarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/progressbar")
@RequiredArgsConstructor
@Api
public class ProgressBarController {
    private final ProgressBarService progressBarService;
    @ApiOperation(value = "Gets Progress bars",
            response = ProgressBar.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok", response = ProgressBar.class),
            @ApiResponse(code = 404, message = "Error 404"),
            @ApiResponse(code = 403, message = "403 Permission deny"),
            @ApiResponse(code = 500, message = "Server error") })
    @GetMapping("/")
    public ResponseEntity<Iterable<ProgressBar>> getProgressBars(){
        return ResponseEntity.ok(progressBarService.findByUser());
    }

    @ApiOperation(value = "Gets Progress bar by id",
            response = ProgressBar.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok", response = ProgressBar.class),
            @ApiResponse(code = 404, message = "Error 404"),
            @ApiResponse(code = 403, message = "403 Permission deny"),
            @ApiResponse(code = 500, message = "Server error") })
    @GetMapping("/{id}")
    public ResponseEntity<ProgressBar> getProgressBarById( @PathVariable String id){
        Optional<ProgressBar> progressBar = progressBarService.findById(id);
        if(progressBar.isPresent()){
            return ResponseEntity.ok(progressBar.get());
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Set progress")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "ok"),
            @ApiResponse(code = 404, message = "Error 404"),
            @ApiResponse(code = 403, message = "403 Permission deny"),
            @ApiResponse(code = 500, message = "Server error") })
    @PatchMapping("/{timestamp}/{id}")
    public ResponseEntity<ProgressBar> setProgress(@Parameter @PathVariable Long timestamp,
                                                   @Parameter @PathVariable String id){
        ProgressBar result = progressBarService.saveProgress(timestamp, id);
        if(result == null)
            return ResponseEntity.notFound().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(result.getId()).toUri();
        return ResponseEntity.ok().header("Location", location.toString()).build();
    }

    @ApiOperation(value = "Create progress bar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "Error 404"),
            @ApiResponse(code = 403, message = "403 Permission deny"),
            @ApiResponse(code = 500, message = "Server error") })
    @RequestMapping(value="/", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> createProgressBar(){ //@Valid @RequestBody ProgressBar progressBar
        ProgressBar result = progressBarService.createProgressBar();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @ApiOperation(value = "Delete progress bar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "Error 404"),
            @ApiResponse(code = 403, message = "403 Permission deny"),
            @ApiResponse(code = 500, message = "Server error") })
    @DeleteMapping("/{id}")
    public ResponseEntity<ProgressBar> deleteProgressBar(@PathVariable String id){
        progressBarService.deleteProgressBarById(id);
        return ResponseEntity.noContent().build();
    }
}
