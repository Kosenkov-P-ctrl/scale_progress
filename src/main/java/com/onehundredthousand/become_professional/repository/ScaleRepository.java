package com.onehundredthousand.become_professional.repository;

import com.onehundredthousand.become_professional.model.Scale;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ScaleRepository extends CrudRepository<Scale, String> {
    Optional<Scale> findScaleByPurpose(String purpose);
}
