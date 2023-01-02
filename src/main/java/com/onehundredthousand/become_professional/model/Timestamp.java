package com.onehundredthousand.become_professional.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Timestamp implements Serializable {
    private String title;
    private LocalDate timeLabel;
    private Integer progress;
}
