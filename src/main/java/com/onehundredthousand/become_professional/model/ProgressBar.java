package com.onehundredthousand.become_professional.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
public class ProgressBar {
    @NotNull
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @NotNull
    private Timestamp startProgress;
    private Timestamp tempProgress;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @PrePersist
    void onCreate(){
        this.setStartProgress(new Timestamp(System.currentTimeMillis()));
        this.setTempProgress(new Timestamp(System.currentTimeMillis()));
    }
}
