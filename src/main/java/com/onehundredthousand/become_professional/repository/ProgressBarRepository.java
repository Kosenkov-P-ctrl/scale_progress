package com.onehundredthousand.become_professional.repository;

import com.onehundredthousand.become_professional.model.ProgressBar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProgressBarRepository extends CrudRepository<ProgressBar, String> {

    Optional<ProgressBar> findByIdAndUserId(String id, String userId);
    @Query()
    Iterable<ProgressBar> findAllByUserId(String id);


}
