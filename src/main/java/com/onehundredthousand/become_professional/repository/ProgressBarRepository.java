package com.onehundredthousand.become_professional.repository;

import com.onehundredthousand.become_professional.model.ProgressBar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProgressBarRepository extends CrudRepository<ProgressBar, String> {

    Iterable<ProgressBar> findAllByUserId(String id);
}
