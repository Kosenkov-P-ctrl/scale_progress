package com.onehundredthousand.become_professional.service;

import com.onehundredthousand.become_professional.model.ProgressBar;
import com.onehundredthousand.become_professional.model.User;
import com.onehundredthousand.become_professional.repository.ProgressBarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ProgressBarService {
    private final Logger logger = Logger.getLogger(ProgressBarService.class.getName());
    private final ProgressBarRepository progressBarRepository;

    public Iterable<ProgressBar> findByUser(){
        var userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return progressBarRepository.findAllByUserId(userId);
    }

    public Optional<ProgressBar> findById(String id){
        Optional<ProgressBar> progressBar = progressBarRepository.findById(id);
        return progressBar;
    }

    public ProgressBar createProgressBar(){
        var progressBar = new ProgressBar();
        progressBar.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var result = progressBarRepository.save(progressBar);
        logger.info(String.format("Creating progressBar with id = %s, by user with id = %s", result.getId(), progressBar.getUser().getId()));
        return result;
    }

    public ProgressBar saveProgress(Long timestamp, String id){
        Optional<ProgressBar> progressBar = progressBarRepository.findById(id);
        if(!progressBar.isPresent()){
            return null;
        }
        ProgressBar result = progressBar.get();
        result.getTempProgress().setTime(result.getTempProgress().getTime() + timestamp);
        return progressBarRepository.save(result);
    }

    public void deleteProgressBarById(String id){
        progressBarRepository.deleteById(id);
    }
}
