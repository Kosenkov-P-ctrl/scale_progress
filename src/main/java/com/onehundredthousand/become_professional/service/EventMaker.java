package com.onehundredthousand.become_professional.service;

import com.onehundredthousand.become_professional.model.Scale;

public interface EventMaker {
    Scale makeEvent(Integer timestamp, String description);
}
